#include "SimulatorSingle.h"

/*

	Implementation of the SimulatorSingle class - method declarations are
	included in the SimulatorSingle header file.  The overall class descriptions are also included
	in the header file, right above the class definition.

*/

// constructor taking the filename to read parameters in from this file
SimulatorSingle::SimulatorSingle(double dtNew, std::string fileName) : dt(dtNew), cell(Parameters(fileName)), injectInsects(false) {
	setConfigParams(fileName); // try to read from config.txt if it exists
}

// constructor taking a parameters object containing the params for the simulation
SimulatorSingle::SimulatorSingle(double dtNew, Parameters &params) : dt(dtNew), cell(params) , injectInsects(false) { }

// method to reset the simulation parameters to the default
void SimulatorSingle::setDefaultParams() {
	Parameters params; // default params
	cell.resetCellParams(params);
}

// method to set the value of a single specified parameter to the specified value
// returns "Success!" if all goes well, or a descriptive error message if not
errormsg SimulatorSingle::setSingleParameter(std::string &param, double newVal) {
	return cell.setSingleParameter(param, newVal);
}

// method to return the value of a specified parameter
double SimulatorSingle::getSpecificParameter(std::string &param) const {
	return cell.getSpecificParameter(param);
}

// method to set the parameters to those listed in the specified file
// returns "Success!" if all goes according to plan, or a descriptive error message if not
errormsg SimulatorSingle::setConfigParams(std::string &configFileName) { // returns an error message, or "Success!" if everything works
	
	Parameters params = Parameters(cell.getParams()); // current cell parameters

	errormsg toReturn = params.setConfigParams(configFileName);
	if (toReturn.compare("Success!") == 0)
		cell.resetCellParams(params); // apply changes to the cell, reset parameters to those read in
	
	return toReturn;
}

// method to set the parameters to those contained in the specified map ((string paramname, double paramvalue) pairs in the map)
// same idea for errormsg as above
errormsg SimulatorSingle::setMapParams(std::map<std::string, double> inputMap) {
	Parameters params = Parameters(cell.getParams()); // current cell parameters
	
	errormsg toReturn = params.setMapParams(inputMap);
	if (toReturn.compare("Success!") == 0)
		cell.resetCellParams(params); // reset fruit params in the single cell simulator
	
	return toReturn;
}

// method to run the simulation for a specified number of timesteps
// this runs the sim with a constant temperature (specified as temperature in the list of input params)
// so it's not really used.  more common/practical is the running with a list of per-day temperatures
// this run method is described below
void SimulatorSingle::run(double temperature, double numTimeSteps, bool ignoreDiapause) { // run the model with a constant temperature
	
	if (numTimeSteps < 0)
		return; // no negative time

	// defaulting to startdate of 0
	cell.setAddInitPop(true);
	cell.readInitInsects();
	
	for (double i = 0; round2Decimals(i) < numTimeSteps; i += dt) { // run from time = 0 to the specified number of timesteps
		cell.stepForward(temperature, ignoreDiapause, dt, timeStep); // run the cell with specified temperature!
		timeStep += dt;
	}
}

// method to run the simulation for a specified number of timesteps, with the daily temperatures
// specified as an array (the temperatures vector).
// whether or not to ignore diapause bools are also passed in as arguments, the day to add the insects
// at (startDay), and the dates of the motrality event
void SimulatorSingle::run(std::vector<double> temperatures, double numTimeSteps, bool ignoreDiapause, int startDay, std::vector<int> killDates) {
	
	if (numTimeSteps < 0)
		return; // no negative time!
	
	int index = ((int) timeStep) % temperatures.size(); // current timestep of the cell, to match with the corresponding temperature value in the list
	
	if (startDay >= 0)
		cell.setAddInitPop(true); // if valid startday, set variable to ensure that init pop isn't added before injection date regardless of diapause
	
	for (double i = 0; round2Decimals(i) < numTimeSteps; i += dt) {
		if ((int) index >= temperatures.size()) // if there is not enough temperature data, it reloops to the beginning
			index = 0;
		if ((int) timeStep == startDay && !injectInsects) {
			injectInsects = true;
			cell.readInitInsects(); // read in initial populations on the chosen date
		}
        for(int j = 0; j < killDates.size(); j++){
           if ((int) timeStep == killDates.at(j)){
                killDates.at(j) = -1; //set passed kill dates to -1 so that kill only occurs on the first time step of the killDate
                cell.killInsects(j+1);
            }
        }
		cell.stepForward(temperatures[(int) index], ignoreDiapause, dt, timeStep); // assume temperature given daily

		timeStep += dt;
		index = ((int) timeStep) % temperatures.size();
	}
}

// method to reset the simulation to timestep 0
void SimulatorSingle::resetTime() {
	timeStep = 0;
	cell.resetTime(); // reset the cell to timestep 0
	injectInsects = false;
}
