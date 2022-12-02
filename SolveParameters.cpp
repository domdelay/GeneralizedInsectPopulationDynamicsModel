#include "SolveParameters.h"
#include <string>

/**

	This file contains methods for calculating the model parameters - most
	of these are dependent on temperature or daylight hours; either way, they
	are dependent on time step-specific values, but are not modelled by
	differential equations.  
	Here we have methods to compute fecundity, diapause effect on fecundity,
    the switch functions for turning diapause on/off, development, and mortality.
    All of these are modelled by equations included in the model paper.

*/

// method to calculate the fecundity at a given temperature, given the parameters
// of the current simulation (specified in the parameters object passed in)
double solveSpecificFecundity(double T, const Parameters &params) {
    
    double fecundityMax = params.getParameter("fecundity max");
    double fTmax = params.getParameter("fecundity tmax");
    double fTmin = params.getParameter("fecundity tmin");
    
    if (T > fTmax || T < fTmin)
        return 0;
    
    double a = fecundityMax;
    double b = ((fTmax - fTmin)/2) + fTmin;
    double c = (fTmax - b)/3;
    
    double fecundity = a * exp(-0.5*pow(((T-b)/c),2));
    return fecundity;
}

// method to calculate the effect of diapause on fecundity
// this is a multiplicative effect - the multiplier is what is returned from this method
// this is dependent on the number of daylight hours
double solveFecundityDiapauseEffect(double hours, const Parameters &params) {
    
    double diaMidPoint = params.getParameter("daylight hours half in diapause");
    double slope = params.getParameter("diapause rate per daylight hours");
    
    double effect = 1/ (1+exp( - slope*(hours-diaMidPoint) ) );
    
    return effect;
    
}

// method to return the current timestep value of s1, the first switch function for diapause
// note that this is a step function whose value is either 0 or 1.
int solveDiapauseMultS1(double hours, double temp, int s1prev, int s2prev, double tCrit, double daylightHours) {
    if (s1prev * s2prev > 0 && hours < daylightHours)
        return 0;
    else if (s2prev == 0 && temp > tCrit)
        return 1;
    else
        return s1prev;
}

// method to return the current timestep value of s2, the second switch function for diapause
// note that this, like s1, is a step function whose value is either 0 or 1
// this is coupled with s1 to control the switching on and off of diapause
int solveDiapauseMultS2(double hours, int s1prev, int s2prev, double daylightHours) {
    if (s1prev == 0)
        return 0;
    else if (hours >= daylightHours)
        return 1;
    else
        return s2prev;
}

// method to calculate the development rate (using the Briere 2 equation) at a given temperature,
// given the parameters of the current simulation and life stage (specified in the parameters object
// passed in)
double solveDev_Briere_Juvenile(double T, const Parameters &params, std::string stage) {
        
    double a = params.getParameter(stage + " development a");
    double TL = params.getParameter(stage + " development tmin");
    double TU = params.getParameter(stage + " development tmax");
    
    // if temperature is above max or below min, no development
    if (T > TU || T < TL)
        return 0;
    
    // Briere 2 equation: d(t) = a*T*(T-Tmin)*(Tmax-T)^(1/m)
    double devRate =  a * T * (T-TL) * pow((TU - T), (0.3333333));  //m set to 1/3

    if(devRate < 0)
        devRate = 0;
    
    //if dev rate is greater than the number of time steps per day develop all insects to the next life stage
    double integStep = params.getParameter("integrationStep");
    double numIntSteps = 1/integStep;
    if(devRate > numIntSteps)
        devRate = numIntSteps;
    
    return devRate;
}

// method to calculate the mortaltiy rate at a given temperature, given the parameters
// of the current simulation and life stage (specified in the parameters object passed in)
double solveMortality(double T, const Parameters &params, std::string stage) {
    
    double Tlower = params.getParameter(stage + " mortality min temp");
    double Tupper = params.getParameter(stage + " mortality max temp");
    double minMort = params.getParameter(stage + " mortality min");
    double maxMort = params.getParameter(stage + " mortality max");
    double Topt = (Tupper - Tlower)/2 + Tlower;
        
    //solving parapola equation: mort = a*T^2 + b*T + c
    double denom = (Tlower-Tupper) * (Tlower-Topt) * (Tupper-Topt);
    double a = (Tupper * (maxMort-minMort) + Tlower * (minMort-maxMort)) / denom;
    double b = (Tupper*Tupper * (minMort-maxMort) + Tlower*Tlower * (maxMort-minMort)) / denom;
    double c = (Tupper * Topt * (Tupper-Topt) * maxMort+Topt * Tlower * (Topt-Tlower) * maxMort+Tlower * Tupper * (Tlower-Tupper) * minMort) / denom;
            
    double mortality = a*pow(T, 2) + b*T + c;
    
    if(mortality > maxMort)
        return maxMort;
    else if (mortality < minMort)
        return minMort;
    else if (mortality < 0)
        return 0;
    else if (mortality > 1)
        return 1;
    else
        return mortality;
}
