/*
This file is part of the dsPopSim software and is subject to the license distributed
with the software
Copyright (c) 2016, Aaron B. Langille, Ellen M. Arteca, Jonathan A. Newman
All rights reserved.
*/

#ifndef POPULATION_H
#define POPULATION_H

#include "Parameters.h"
#include "UtilityMethods.h"
#include "SolveParameters.h"
#include "EulersMethod.h"
#include "Daylight.h"

/*

	This class describes an Population object.  This object tracks the populations of
	the various different life stages for the insects (egg, instars, pupae, males, and all female
    stages) over time.
	There are various accessors to see the current values for each lifestage population, 
	and a method for running the lifestages for a timestep (of length dt, where dt is the integration
	step). There is also a mutator for reseting the current life stage specific
	populations to their respective initial populations. The population object also has 
	datafields for the initial populations of each life stage, and there are accessors for each one.

	Method definitions are included in the cpp file, along with descriptions of the code.

*/

class Population {

    
    //Vector to track of the current population at each life stage
    std::vector<double> currentStage;
    int numInstars;
    int numFemStages;
    bool hasPupalStage;
    int numStages;
    int numNotAdultStage;

    int numDaysToSim;
	
	// initially, s1 and s2 are both 0 (start not in diapause) - set in constructor
	int s1, s2;
	bool crossedDiapause;
	bool addInitPop;
	 
	int crossedDiapDay;

    double reproductiveDiapauseEffect;
    
    int overwinteringStage;
    bool OWflag;
    bool OWstate;
    bool OWlastState;
    int currentOWCondition;

public:
	
	// constructors
	Population(const Parameters &params);
	Population(); 

    std::vector<double> getCurrentStage() const {return currentStage;}  // accessors for the current populations of each stage

    double getEggs() const { return currentStage.at(0); }
    double getInstX(int x) const { return currentStage.at(x); }
    double getPupae() const { if(hasPupalStage) return currentStage.at(numInstars+1); return 0;}
    double getMales() const {return currentStage.at(numStages-numFemStages-1);}

    double getFemales() const;
    
    std::vector<double> getFemStages() const;

	// get current population across all stages
	double getTotalPopulation() const { 
        double sum = 0;
        for (int i = 0; i < numStages; i++){
            sum += currentStage.at(i);
        }
        return sum;
	}

	void setAddInitPop(bool toSet) { addInitPop = toSet; } // set day to add the initial females (if not adding in diapause cross day or overwintering termination)
	int getCrossedDiapDay() const { return crossedDiapDay; } // day diapause has been crossed
	
	// changing populations
	void readPopulation(const Parameters &params);
	void resetPopulation();
    void killPopulation(const Parameters &params, int eventNumber);

	// update population to next timestep
    void computePopulation(double temperature, const Parameters &params, bool ignoreDiapause, double dt, double timeStep);

};

#endif
