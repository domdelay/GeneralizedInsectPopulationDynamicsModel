#include "EulersMethod.h"

/**

	This file contains the methods for the numerical integration needed for 
	the solution of the model's coupled differential equations (for stage-specific population,
	and fruit quality).  The numerical integration is done via Euler's method, with a 
	provided timestep (usually of 0.05).

	The differential equations for each parameter are included in the model description in the paper

*/

// method to solve for the number of eggs given the values for the previous timestep.
double obtainEggs(double fecundity, std::vector<double> eggViabilities, std::vector<double> femStagePopulations, double eggsI, double eggMortalityNat, double eggMortalityPred, double eggDevelopment, double step) {
	double dE_dt = 0;
	for (int i = 0; i < femStagePopulations.size(); i ++)
		dE_dt += fecundity * eggViabilities[i] * femStagePopulations[i]; // effect of fecundity, considering female populations and egg viabilities
	dE_dt -= eggsI * (eggMortalityNat + eggMortalityPred + eggDevelopment); // egg differential equation
	return eggsI + dE_dt * step; // Euler's method
    
}

// method to solve for the number of instar given the values for the previous timestep.
// note the name is generic (i.e. instarX instead of specific instar stage), because the calculation is
// the same for all of them.
double obtainInstX(double stageX_1Development, double instXMortalityNat, double instXMortalityPred, double instXDevelopment, double stageX_1I, double instXI, double step) {
	double dIX_dt = stageX_1Development * stageX_1I - instXI * (instXMortalityNat + instXMortalityPred + instXDevelopment); // instar differential equation
	
	double newVal = instXI + dIX_dt * step;
	if ( newVal < 0)
		return instXI;

 	if ( instXI == 0)
		return newVal;
	
	double epsilon = 1E-15;

	if ( newVal / instXI < epsilon)
		return instXI;
	if ( instXI / newVal < epsilon)
		return instXI;

	return newVal;

}

// method to solve for the number of pupae given the values for the previous timestep
double obtainPupae(double stageP_1Development, double pupaeMortalityNat, double pupaeMortalityPred, double pupaeDevelopment, double stageP_1, double pupaeI, double step) {
	double dP_dt = stageP_1Development * stageP_1 - pupaeI * (pupaeMortalityNat + pupaeMortalityPred + pupaeDevelopment); // pupae differential equation
	return pupaeI + dP_dt * step; // Euler's method
}

// method to solve for the number of males given the values for the previous timestep
double obtainMales(double stageM_1Development, double maleProportion, double malesMortalityNat, double malesMortalityPred, double maleDevelopment, double stageM_1, double malesI, double step) {
	double dM_dt = maleProportion * stageM_1Development * stageM_1 - malesI * (malesMortalityNat + malesMortalityPred + maleDevelopment); // male differential equation
	return malesI + dM_dt * step; // Euler's method
}

// method to solve for the number of instar given the values for the previous timestep
// note the name is generic, like for the instars, since the calculation is the same for all female stages
double obtainFemalesX(double maleProportion, double stageX_1Development, double stageX_1I, double femalesXMortalityNat, double femalesXMortalityPred, double femalesXDevelopment, double femalesXI, double step) {
	// generic female differential equation
	double dFX_dt = (1 - maleProportion) * stageX_1Development * stageX_1I - femalesXI * (femalesXMortalityNat + femalesXMortalityPred + femalesXDevelopment);
	return femalesXI + dFX_dt * step; // Euler's method
}

