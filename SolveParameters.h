#ifndef SOLVE_PARAMETERS_H
#define SOLVE_PARAMETERS_H

#include <cmath>
#include <limits>
#include "Parameters.h"

/*

	Header file for the methods in SolveParameters.cpp.
	Comments and descriptions of these methods are included with the 
	implementation in SolveParameters.cpp 

*/ 

double solveSpecificFecundity(double T, const Parameters &params);

double solveFecundityDiapauseEffect(double hours, const Parameters &params);

int solveDiapauseMultS1(double hours, double temp, int s1prev, int s2prev, double tCrit, double daylightHours);

int solveDiapauseMultS2(double hours, int s1prev, int s2prev, double daylightHours);

double solveDev_Briere_Juvenile(double T, const Parameters &params, std::string stage);

double solveMortality(double T, const Parameters &params, std::string stage);

#endif
