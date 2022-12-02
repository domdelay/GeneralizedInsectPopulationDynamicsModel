#include "Population.h"

/*

	Implementation of the Population class methods. Class definition and accessors are
	included in the Population header file. The overall class description is also included
	in the header file, right above the class definition.

*/

// constructor setting to the original parameters
Population::Population(const Parameters &params) {

    numInstars = params.getParameter("numInstars");
    numFemStages = params.getParameter("numFemStages");
    hasPupalStage = params.getParameter("hasPupalStage");
    numStages = 1 + numInstars + hasPupalStage + 1 + numFemStages;  //egg + instar(s) + pupa(?) + male + female(s)
    numNotAdultStage = numStages - numFemStages - 1;
    
    //Current population in each life stage: currentStage[0] = eggs, currentStage[1] = instar1...
    currentStage = std::vector<double> (numStages);

    numDaysToSim = params.getParameter("numDaysToSim");
    
    overwinteringStage = params.getParameter("overwinteringStage");
    if (overwinteringStage >= numNotAdultStage)
        overwinteringStage = numNotAdultStage;
    
    OWflag = 0;
    OWstate = 0;
    OWlastState = 0;
    currentOWCondition = 0;

    resetPopulation();
}

// no argument constructor -- this only sets to default parameters, and sets
// the initial population to one egg
Population::Population() {
    numInstars = 3;
    numFemStages = 7;

    hasPupalStage = 1;
    numStages = 13;
    numNotAdultStage = 5;
    
    currentStage = std::vector<double> (numStages);

    numDaysToSim = 365;
    
    resetPopulation();
    
    //default diapause stage egg:0
    overwinteringStage = 0;
    
    OWflag = 0;
    OWstate = 0;
    OWlastState = 0;
    currentOWCondition = 0;
    
    reproductiveDiapauseEffect = 1;

}

// return total females across all stages, at the current timestep
double Population::getFemales() const {
    double sum = 0;
    for (int i = numNotAdultStage+1; i < numStages; i++){
        sum += currentStage.at(i);
    }
    return sum;
}

std::vector<double> Population::getFemStages() const{
    std::vector<double> currentFemaleStages = std::vector<double> (numFemStages);
    currentFemaleStages.clear();
    for(int i = numNotAdultStage+1; i < numStages; i++){
        currentFemaleStages.push_back(currentStage.at(i));
    }
    return currentFemaleStages;
}

// method to read in the population from the params object (i.e. to set the
// initial population to those specified in the parameters)
// this is called when the population is first added (either on diapause crossing, overwintering,
// or at the particular timestep specified)
void Population::readPopulation(const Parameters &params) {
	addInitPop = true;
	
    currentStage.at(0) = params.getParameter("initial eggs");
    for (int i = 0; i < numInstars; i++){
        int val = i + 1;
        std::stringstream sstm;
        sstm << "initial instar" << val;
        currentStage.at(val) = params.getParameter(sstm.str());
    }
    if(hasPupalStage)
        currentStage.at(numInstars+1) = params.getParameter("initial pupae");
    
    currentStage.at(numNotAdultStage) = params.getParameter("initial males");
    
    for (int i = numNotAdultStage+1; i < numStages; i++){
        int val = i - numNotAdultStage;
        std::stringstream sstm;
        sstm << "initial females" << val;
        currentStage.at(i) = params.getParameter(sstm.str());
    }

    bool ignoreOW = params.getParameter("ignoreOverwintering");
    if (!ignoreOW){
        int overwinteringStage = params.getParameter("overwinteringStage");
        double initialOw = params.getParameter("initialOverwintering");
        //adult overwintering
        if (overwinteringStage >= numNotAdultStage){
            double maleProp = params.getParameter("male proportion");
            //males
            currentStage.at(numNotAdultStage) += initialOw*maleProp;
            //females
            currentStage.at(numNotAdultStage+1) += initialOw*(1-maleProp);
        }
        //juvenile life stage overwintering
        else{
            currentStage.at(overwinteringStage) += initialOw;
        }
    }
}

// method to reset the population to simulation step 0 
void Population::resetPopulation() {
    for (int i = 0; i < numStages; i++){
        currentStage.at(i) = 0;
    }
	s1 = 0;
	s2 = 0;
    
	crossedDiapause = false;
	addInitPop = false;

	crossedDiapDay = -1;
}

//method to remove a certian percentage of a life stage's popualtion based on mortality event parameters
void Population::killPopulation(const Parameters &params, int eventNumber){
    if(params.getParameter("ignoreKill") == 0){
        //set the stageKill and percentKill to the proper values for the killDate
        std::stringstream sstm1;
        sstm1 << "stageKill" << eventNumber;
        int stageKill = params.getParameter(sstm1.str());
        std::stringstream sstm2;
        sstm2 << "percentKill" << eventNumber;
        double percentKill = params.getParameter(sstm2.str());
        //adult life stage kill
        if (stageKill >= numNotAdultStage){
            for(int j = numNotAdultStage; j < numStages; j++){
                currentStage.at(j) = currentStage.at(j)*((100-percentKill)/100);
            }
        }
        //juvenile life stage kill
        else{
            currentStage.at(stageKill) = currentStage.at(stageKill)*((100-percentKill)/100);
        }
    }
    
}

// method to compute the population over one timestep with the specified parameters.
// The temperature of the timestep is specified, and whether or not to ignore diapause, and the
// overwintering submodel. The simulation's parameters (as the Parameters object), the integration step (dt -- the length of one timestep), and the current timestep are alsospecified.
void Population::computePopulation(double temperature, const Parameters &params, bool ignoreDiapause, double dt, double timeStep) {
    
    //Setting variable to be used in this method.
    int year = ((int) timeStep) / numDaysToSim;
    int date = ((int) timeStep) % numDaysToSim;
    int offset = getOffSet(year);
    double latitude = params.getParameter("latitude");
    double hours = getDayLightHours(year, date + offset, latitude);
		 
	// note: in order of indices: 0-eggs, 1-instar1, 2-instar2, 3-instar3, 4-pupae, 5-males, 6-females
    
	// no negative populations
    for (int i = 0; i < numStages; i++){
        if(currentStage.at(i) < 0)
            currentStage.at(i) = 0;
    }

	// calculate fecundity rate
	double fecundity = solveSpecificFecundity(temperature, params);
    
    double fecundityDiapauseEffect = 1;
    if (!ignoreDiapause) {
         
        double criticalT = params.getParameter("diapause critical temp");
        double daylightHours = params.getParameter("diapause daylight hours");
         
        // NOTE: don't set s1 here since the previous value of s1 is needed to calculate s2
        int tempS1 = solveDiapauseMultS1(hours, temperature, s1, s2, criticalT, daylightHours); // diapause multiplier (s1)
        s2 = solveDiapauseMultS2(hours, s1, s2, daylightHours); // s2 value for current dt
        s1 = tempS1; // s1 value for current dt
         
        fecundityDiapauseEffect = s1 * solveFecundityDiapauseEffect(hours, params);

        // compute switch functions for diapause
        if (s1 == 0 && !crossedDiapause && !addInitPop)
            return;
        if (s1 != 0 && !crossedDiapause) {
            if (!addInitPop){
                readPopulation(params);
            }
            crossedDiapause = true;
            crossedDiapDay = (int) (timeStep);
        }
         
    }

    fecundity *= fecundityDiapauseEffect; // multiplicative effect of diapause on fecundity (if ignoring diapause, will still be 1)

    
	
    double devRate[numStages];  // development rates per stage
	double mortalityNat[numStages]; // mortality rates per stage, due to natural causes (food, etc.)
	 
	for (int i = 0; i < numStages; i ++) { // calculate the stage-specific mortality and development rates
        devRate[i] = solveDev_Briere_Juvenile(temperature, params, params.getStage(i));
        
        double ignoreMortTemp = params.getParameter("ignoreMortTemp");
        if(ignoreMortTemp){
            mortalityNat[i] = 0;
        }
        else{
            mortalityNat[i] = solveMortality(temperature, params, params.getStage(i));
        }
		 
	}
    
    std::vector<double> tempFemalesPopulation;
    for(int i = numNotAdultStage+1; i<numStages; i++){
        tempFemalesPopulation.push_back(currentStage.at(i));
    } // ensure to use populations from the timestep before
    
    
    std::vector<double> eggViabilities = params.getArrayParameters("egg viability");
    std::vector<double> mortalitiesPred = params.getArrayParameters("mortality due to predation");
    
    double maleProportion = params.getParameter("male proportion");
    double maleProportion_advFemStage = 0; // no males develop from previous female lifestages
    
    
    //Overwintering submodel: if conditions are right for overwintering - mortality and development are stopped (set to 0) and fecundity is also stopped (set to 0) if the insect overwinters in an adult life stage.
    double ignoreOverwintering = params.getParameter("ignoreOverwintering");
    if(!ignoreOverwintering){
        for(int i = 0; i < numStages; i ++) {
            if(i == overwinteringStage){

                //if temperature are lower than overwintering absolut minimum mortality temp, all insects die
                double owMortTemp = params.getParameter("overwintering mortality temp");
                if(temperature < owMortTemp){
                    for (int j = 0; j < numStages; j++){
                        currentStage.at(j) = 0;
                    }
                }
                
                //get requiered params
                double IcritT = params.getParameter("overwintering critical induction temp");
                double IcritH = params.getParameter("overwintering induction daylight hours");
                double TcritT = params.getParameter("overwintering critical termination temp");
                double TcritH = params.getParameter("overwintering termination daylight hours");

                //determin the currentOWCondition
                //condition good for entering or leaving OW
                if ( (temperature <= IcritT && hours <= IcritH) && (temperature >= TcritT && hours >= TcritH))
                    currentOWCondition = 2;
                //condition good for entering OW
                else if (temperature <= IcritT && hours <= IcritH)
                    currentOWCondition = 0;
                //condition good for leaving OW
                else if (temperature >= TcritT && hours >= TcritH)
                    currentOWCondition = 1;
                
                //set the last overwintering state
                OWlastState = OWstate;

                //set the OWstate based on previous OWflag and currentOWCondition
                //OWstate = 0 - in overwintering
                //OWstate = 1 - not in overwintering
                if (OWflag == 1 && currentOWCondition == 2){
                    OWflag = 1;     //leaving overwintering
                    OWstate = 0;
                }
                else if (OWflag == 0 && currentOWCondition == 2){
                    OWflag = 0;     //entering overwintering
                    OWstate = 1;
                }
                else if (OWflag == 0 && currentOWCondition == 1){
                    OWflag = 1;     //leaving overwintering
                    OWstate = 1;
                }
                else if (OWflag == 1 && currentOWCondition == 0){
                    OWflag = 0;     //entering overwintering
                    OWstate = 0;
                }
                else if (OWflag == 1 && currentOWCondition == 1){
                    OWstate = 1;    //leaving overwintering
                }
                else if (OWflag == 0 && currentOWCondition == 0){
                    OWstate = 0;    //entering overwintering
                }

                //OWstate are multipliers for mortality and development rate
                //and fecundity rate for adult overwintering
                if (overwinteringStage >= numNotAdultStage){
                    for(int j = numNotAdultStage; j < numStages; j++){
                        mortalityNat[j] = OWstate * mortalityNat[j];
                        devRate[j] = OWstate * devRate[j];
                    }
                    for(int j = 0; j < numFemStages; j++){
                        eggViabilities[j] = OWstate * eggViabilities[j];
                    }
                }
                //juvenile life stage overwintering
                else{
                    mortalityNat[i] = OWstate * mortalityNat[i];
                    devRate[i] = OWstate * devRate[i];
                }
                
                //terminating or induction
                if(OWstate != OWlastState){
                    //reduce overwintering population based on overwintering success rate parameter when overwintering is induced
                    double owSuccess = params.getParameter("overwintering success");
                    //enter overwintering
                    if(OWstate == 0){
                        //adult overwintering success
                        if (overwinteringStage >= numNotAdultStage){
                            for(int j = numNotAdultStage; j < numStages; j++){
                                currentStage.at(j) *= owSuccess;
                            }
                        }
                        //juvenile life stage overwintering success
                        else{
                            currentStage.at(i) *= owSuccess;
                        }
                    }
                }

            }
        }
    }
    //end of overwintering submodel
    

    // calculate the current populations of all the lifestages
    
    //first female stage
    currentStage.at(numNotAdultStage+1) = obtainFemalesX(maleProportion, devRate[numNotAdultStage-1], currentStage.at(numNotAdultStage-1), mortalityNat[numNotAdultStage+1], mortalitiesPred[numNotAdultStage+1], devRate[numNotAdultStage+1], currentStage.at(numNotAdultStage+1), dt);
    //other female stages
    if(numFemStages > 1){
        for (int i = numNotAdultStage+2; i < numStages; i++){
            currentStage.at(i) = obtainFemalesX(maleProportion_advFemStage, devRate[i -1], tempFemalesPopulation.at(i-(numNotAdultStage+1) - 1), mortalityNat[i], mortalitiesPred[i], devRate[i], currentStage.at(i), dt);
        }
    }
    
    // note: in order of indices: 0-eggs, 1-instar1, 2-instar2, 3-instar3, 4-pupae, 5-males, 6-females
    
    // computed in reverse order to avoid excess use of temporary variables
    currentStage.at(numNotAdultStage) = obtainMales(devRate[numNotAdultStage-1], maleProportion, mortalityNat[numNotAdultStage], mortalitiesPred[numNotAdultStage], devRate[numNotAdultStage], currentStage.at(numNotAdultStage-1), currentStage.at(numNotAdultStage), dt);    //M

    if(hasPupalStage)
        currentStage.at(numNotAdultStage-1) = obtainPupae(devRate[numNotAdultStage-1-1], mortalityNat[numNotAdultStage-1], mortalitiesPred[numNotAdultStage-1], devRate[numNotAdultStage-1], currentStage.at(numNotAdultStage-1-1), currentStage.at(numNotAdultStage-1), dt); //P

    for (int i = numInstars; i > 0; i--){
        currentStage.at(i) = obtainInstX(devRate[i-1], mortalityNat[i], mortalitiesPred[i], devRate[i], currentStage.at(i-1), currentStage.at(i), dt);   //all I
    }
    currentStage.at(0)= obtainEggs(fecundity, eggViabilities, tempFemalesPopulation, currentStage.at(0), mortalityNat[0], mortalitiesPred[0], devRate[0], dt);  //E
    
    for(int i = 0; i < numStages; i++){
        if (currentStage.at(i) < 0)
            currentStage.at(i) = 0;
    }

}

