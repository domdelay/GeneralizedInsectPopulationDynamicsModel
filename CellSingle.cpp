#include "CellSingle.h"

/*

	Implementation of the SingleCell class methods.  Class definition and accessors are
	included in the CellSingle header file.  The overall class description is also included
	in the header file, right above the class definition.

*/

// Constructor taking a parameters object specifying the model parameters, and the
// initial temperature for the simulation
CellSingle::CellSingle(Parameters paramsNew, double newTemp) {
	
	if (&paramsNew != NULL) {
		params = Parameters(paramsNew); // set Parameters datafield to a copy of the Parameters passed in
		temp = paramsNew.getParameter("constant temp"); // initial temperature of the cell
		population = Population(params); // initialize population with parameters specified in the Parameters
		resetCellParams(params); // set cell datafields to the values specified in the Parameters
	}
	else {
        //used Parameters() - keep same but change setDefaultParam to set all values to 0 so you know file was no good. we don't want it to run with the wrong values (before wanted to: changed to just to stop and return an error message)
        std::cout << "error with newParameter" << std::endl;
		params = Parameters();
		temp = 15;
		population = Population();
	}
    
    numInstars = params.getParameter("numInstars");
    instXSeries = std::vector<XYSeries> (numInstars);
    
    maxInst = std::vector<double> (numInstars);
    maxInstDay = std::vector<double> (numInstars);
    totInst = std::vector<double> (numInstars);
    
    hasPupalStage = params.getParameter("hasPupalStage");
    numFemStages = params.getParameter("numFemStages");
    femaleStageSeries = std::vector<XYSeries> (numFemStages);
    
    numDaysToSim = params.getParameter("numDaysToSim");
	
    ignoreDiapause = true;
    
	resetTime();

	if (newTemp != 888) // 888 is the error state temperature
		temp = newTemp;
}

// method to change the value of a single specified parameter (specified by name) 
// a message is returned indicating success; or specifying the error if one occured
errormsg CellSingle::setSingleParameter(std::string &param, double newVal) {
	return params.setParameter(param, newVal);
}

// method to get the value of a single specified parameter
double CellSingle::getSpecificParameter(std::string &param) const {
	return params.getParameter(param);
}

// method to move the cell forward one timestep
// the temperature of the timestep is specified, and whether or not to ignore diapause,
// and the integration step (dt -- the length of one timestep), and the current timestep 
void CellSingle::stepForward(double temperature, bool ignoreDiapauseNew, double dt, double timeStep) {
	// reset datafields to those passed in
    ignoreDiapause = ignoreDiapauseNew;
	temp = temperature;

    population.computePopulation(temperature, params, ignoreDiapause, dt, timeStep); // update the population

	// update the stage-specific population data series
	eggSeries.push_back(XYPair(timeStep, getEggs()));
    for (int i = 0; i < numInstars; i++){
        int val = i + 1;
        instXSeries.at(val-1).push_back(XYPair(timeStep, getInstX(val)));
    }
    if(hasPupalStage)
        pupaeSeries.push_back(XYPair(timeStep, getPupae()));
	malesSeries.push_back(XYPair(timeStep, getMales()));
	femalesSeries.push_back(XYPair(timeStep, getFemales()));
	
	const std::vector<double> femStagePopulation = getFemStages();
	for (int i = 0; i < numFemStages; i ++)
        femaleStageSeries.at(i).push_back(XYPair(timeStep, femStagePopulation.at(i)));

    temperatureSeries.push_back(XYPair(timeStep, getTemperature()));

    int numDaysToSim = params.getParameter("numDaysToSim");
    int year = ((int) timeStep) / numDaysToSim;
    int date = ((int) timeStep) % numDaysToSim;
    int offset = getOffSet(year);
    double latitude = params.getParameter("latitude");
    double hours = getDayLightHours(year, date + offset, latitude);
    daylightHoursSeries.push_back(XYPair(timeStep, hours));
 
	// update cumulative totals and max populations/days
	totEggs += getEggs() * dt;
    for (int i = 0; i < numInstars; i++){
        int val = i + 1;
        totInst.at(val-1) += getInstX(val) * dt;
    }
    if(hasPupalStage)
        totPupae += getPupae() * dt;
	totMales += getMales() * dt;
	totFemales += getFemales() * dt;
	
	if (maxEggs < getEggs()) {
		maxEggs = getEggs();
		maxEggsDay = timeStep;
	}
	
    for (int i = 0; i < numInstars; i++){
        int val = i + 1;
        if (maxInst.at(val-1) < getInstX(val)) {
            maxInst.at(val-1) = getInstX(val);
            maxInstDay.at(val-1) = timeStep;
        }
    }
	
    if(hasPupalStage){
        if (maxPupae < getPupae()) {
            maxPupae = getPupae();
            maxPupaeDay = timeStep;
        }
    }
        
	if (maxMales < getMales()) {
		maxMales = getMales();
		maxMalesDay = timeStep;
	}
	
	if (maxFemales < getFemales()) {
		maxFemales = getFemales();
		maxFemalesDay = timeStep;
	}
    
    if( maxTemp < getTemperature()){
        maxTemp = getTemperature();
        maxTempDay = timeStep;
    }
    if( maxHours < hours){
        maxHours = hours;
        maxHoursDay = timeStep;
    }
	
}


// method to reset the simulation to timestep 0
void CellSingle::resetTime() {
	population.resetPopulation(); // reset the population
		
	// reset the data series
	eggSeries.clear();
    for (int i = 0; i < numInstars; i++){
        int val = i + 1;
        instXSeries.at(val-1).clear();
    }
	pupaeSeries.clear();
	malesSeries.clear();
	femalesSeries.clear();
	
	for (int i = 0; i < numFemStages; i ++)
        femaleStageSeries.at(i).clear();
		
	// now, reset max populations to 0, since they're all 0 at timestep 0
	maxEggs = 0;
    for (int i = 0; i < numInstars; i++){
        int val = i + 1;
        maxInst.at(val-1) = 0;
    }
	maxPupae = 0;
	maxMales = 0;
	maxFemales = 0;
    maxTemp = 0;
    maxHours = 0;
	
	// reset the maximum population date to timestep 0
	maxEggsDay = 0;
    for (int i = 0; i < numInstars; i++){
        int val = i + 1;
        maxInstDay.at(val-1) = 0;
    }
	maxPupaeDay = 0;
	maxMalesDay = 0;
	maxFemalesDay = 0;
    maxTempDay = 0;
    maxHoursDay = 0;
	
	totEggs = 0;
    for (int i = 0; i < numInstars; i++){
        int val = i + 1;
        totInst.at(val-1) = 0;
    }
	totPupae = 0;
	totMales = 0;
	totFemales = 0;
}

// method to reset the cell parameters to new values specified by the parameters
// object passed in
void CellSingle::resetCellParams(Parameters &paramsNew) {
	params.setCopyParams(paramsNew);
}
