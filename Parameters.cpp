#include "Parameters.h"

/*

	Implementation of the Parameters class methods. Class definition and accessors are
	included in the Parameters header file. The overall class description is also included
	in the header file, right above the class definition.

*/

std::vector<std::string> Parameters::names;
std::string Parameters::mortalityParams[] = {"min", "max", "min temp", "max temp" }; // mortality parameter names


// useful method: splitting strings by a specified delimiter char
/*
    Function to split a given string by a specified character.  Returns a vector where the elements are
    consecutive delimited sections of the string to split (delimiter not included).
*/
std::vector<std::string> splitBy(const char delim, const std::string toSplit) {
    std::vector<std::string> div;

    std::string temp = "";
    for (int i = 0; i < toSplit.size(); ++ i) {
        if (toSplit[i] != delim) // if the current character is not the delimiter, it's part of the current section
            temp += toSplit[i];
        if ((toSplit[i] == delim || i == toSplit.size() - 1) && temp != "") {
            // if the current character is the delimiter, or it's the last character, and the current section is non-empty,
            // then add it to the vector and reset the current section to ""
            div.push_back(temp);
            temp = "";
        }
    }
    return div;
}

// default constructor - uses default parameters
Parameters::Parameters() {
    setDefaultNames();
	setDefaultParams();
}

// constructor taking a filename to read the parameters in from
// first set default parameters, then read in the file and if there are any
// parameters missing the default values for these parameters will be used
Parameters::Parameters(const std::string fileName) {
    setDefaultNames();
    setDefaultParams();
	setConfigParams(fileName);
    setConfigNames();
}

// constructor taking another parameters object to read parameters from
Parameters::Parameters(const Parameters &params) {
    setDefaultNames();
	setDefaultParams();
	setCopyParams(params);
    setConfigNames();
}

// method to set the parameters to the default parameters
void Parameters::setDefaultParams() {
    // simulation parameters
    paramMap["numDaysToSim"] = 365;
    paramMap["integrationStep"] = 0.05;
    paramMap["printInterval"] = 1;
    
    //species parameters
    paramMap["numInstars"] = 3;
    paramMap["hasPupalStage"] = 1;
    paramMap["numFemStages"] = 7;
        
    // general parameters
    paramMap["male proportion"] = 0.5;
    paramMap["latitude"] = 45.7; // Clark's latitude
    
    // initial populations
    paramMap["addInsectsDate"] = 160;
    paramMap["initial eggs"] = 0.;
    paramMap["initial instar1"] = 0.;
    paramMap["initial instar2"] = 0.;
    paramMap["initial instar3"] = 0.;
    paramMap["initial pupae"] = 0.;
    paramMap["initial males"] = 0.;
    
    for (int i = 0; i < 7; i ++) { // initial populations of each female lifestage
        std::string stageStr = "initial females";
        int val = i + 1;
        std::stringstream sstm;
        sstm << val;
        stageStr += sstm.str();
        paramMap[stageStr] = 0.;
    }
    paramMap["initial females1"] = 50;

    // stage-specific parameters
    for (int i = 0; i < 13; i ++) {
        std::string stage = "females";
        int val = i - 5;
        std::stringstream sstm;
        sstm << val;
        stage += sstm.str();
        if (i < 6)
            stage = names[i];
        
        //development parameters
        paramMap[stage + " development a"] = 0;
        paramMap[stage + " development tmin"] = 0;
        paramMap[stage + " development tmax"] = 0;
            
        // mortality parameters
        paramMap[stage + " mortality min"] = 0;
        paramMap[stage + " mortality max"] = 0;
        paramMap[stage + " mortality min temp"] = 0;
        paramMap[stage + " mortality max temp"] = 0;

        paramMap[stage + " mortality due to predation"] = 0.; // stage-specific mortality rate due to predation
    }
    
    // female-stage specific egg viabilities
    paramMap["females1 egg viability"] = 0;
    paramMap["females2 egg viability"] = 0;
    paramMap["females3 egg viability"] = 0;
    paramMap["females4 egg viability"] = 0;
    paramMap["females5 egg viability"] = 0;
    paramMap["females6 egg viability"] = 0;
    paramMap["females7 egg viability"] = 0;

    // fecundity parameters
    paramMap["fecundity max"] = 0;
    paramMap["fecundity tmax"] = 0;
    paramMap["fecundity tmin"] = 0;
           
    // submodel switch parameters
    paramMap["ignoreOverwintering"] = 1;
    paramMap["ignoreMortTemp"] = 0;
    paramMap["ignoreKill"] = 1;
    paramMap["ignorePredation"] = 1;
    paramMap["ignoreDiapause"] = 1;

    // overwintering parameters
    paramMap["overwinteringStage"] = 0;
    paramMap["initialOverwintering"] = 0;
    paramMap["overwintering success"] = 0;
    paramMap["overwintering induction critical temp"] = 0;
    paramMap["overwintering induction daylight hours"] = 0;
    paramMap["overwintering termination critical temp"] = 0;
    paramMap["overwintering termination daylight hours"] = 0;
    paramMap["overwintering mortality temp"] = -20;

    //reproductive diapause
    paramMap["diapause critical temp"] = 0;
    paramMap["diapause daylight hours"] = 0;
    paramMap["daylight hours half in diapause"] = 0;
    paramMap["diapause rate per daylight hours"] = 0;
    
    // mortality event paramters
    paramMap["numKillDates"] = 0;
    paramMap["killDate1"] = 0;
    paramMap["stageKill"] = 0;
    paramMap["percentKill"] = 0;

}

// method to set the parameters to those specified in the file
// errormsg is returned with Success! if nothing went wrong, or specifying what the error was if one occurred
errormsg Parameters::setConfigParams(const std::string configFileName) {
	int lineNumber = 1; 
	bool changed = false; // check if any valid params are in the specified file (if nothing changed, file is invalid)
	
    std::map<std::string, double> newParams;

	std::string status = "";

	if (&configFileName == NULL) {
		status += "No file chosen, using previous parameters"; // file chooser closed with no file chosen
		return status;
	}
	
	std::ifstream fileIn(configFileName.c_str());
	if (fileIn.bad()) {
		status += "File not found, using default parameters.";
		return status;
	}
	std::string line;

	while (std::getline(fileIn, line)) // read file line-per-line
	{
	    std::vector<std::string> splitLine = splitBy(':', line); // has to be name : value
	    if (splitLine.size() < 2) {
	    	//status += "Input error - value not present - on line " + lineNumber;
	    	continue;
	    }

	    std::string key = splitLine[0];
	    double val;
	    std::istringstream iss(splitLine[1]);
	    if (! (iss >> val)) {
	    	status += "Input error - invalid value - on line " + lineNumber;
	    	break;
	    }

        newParams[key] = val;
        changed = true;
        
	    lineNumber ++;
	}

	fileIn.close();

	if (!changed) {
		status += "No parameters were changed (no valid input in this file).";
	}
	else {
		std::string validMessage = checkMap(newParams); // check all params
		if (!(validMessage.compare("Success!") == 0))
			return validMessage;
		
		setMapParams(newParams); // if it's reached this point, the parameters are all ok and the paramMap datafield can be reset

		status += "Success!";
	}

	return status;
}

// method to reset the parameters to those in the specified map
// same error message idea as for setting params from a file
errormsg Parameters::setMapParams(std::map<std::string, double> inputMap) {
	std::string validMessage = checkMap(inputMap);

	if (! (validMessage.compare("Success!") == 0)) {
		return validMessage;
	}

    paramMap.clear();
	// if this point has been reached, all the parameters are fine
	for (std::map<std::string, double>::iterator iter = inputMap.begin(); iter != inputMap.end(); iter ++) {
		std::string key = iter->first;
        
        paramMap[key] = inputMap[key];
	}
	return validMessage; // at this point it'll be "Success!", otherwise would've bailed at 1st if statement
}

// same as resetting parameters from a map, but taking a parameters object 
// calls the setMapParams method with the map of the paramater object passed in
errormsg Parameters::setCopyParams(const Parameters &params) {
	return setMapParams(params.getMap());
}

// method to return the map of params
// a const method, to avoid unsolicited edits of the map
// (this allows memory-saving shallow copy without worrying about data being edited improperly)
std::map<std::string, double> Parameters::getMap() const {
	return paramMap;
}

// method to return a single parameter, specified by name
double Parameters::getParameter(std::string parameter) const {
    if (paramMap.find(parameter) != paramMap.end()){
            return paramMap.find(parameter)->second;
    }
    else{
        //if parameter doesn't exist in map retrun large negative value
        return -99999;
    }

}

// method to set the value of a specified parameter, to that specified
// same errormsg idea as before (error is returned if the specified parameter is invalid)
errormsg Parameters::setParameter(const std::string &parameter, double newValue) {
	std::map<std::string, double> thisMap = getMap(); 

	std::string validMessage = "Invalid parameter";
	
	if (thisMap.count(parameter) == 0) 
		return validMessage;
	
	thisMap[parameter] = newValue; 
	
	validMessage = checkMap(thisMap); // check all parameters (for invalid values)
	
	if (validMessage.compare("Success!") == 0) 
		paramMap[parameter] = newValue;
	
	return validMessage;
}

// method to set the max mortality of a specified stage to a specified value
// this can also be done with the setParameter method, but there is a specific method for 
// it since it is called more commonly 
// (stage 0 = egg)
errormsg Parameters::setMaxMort(int stage, double newMaxMort) {
	std::string status = "";
    
    int numStages = 1 + getParameter("numInstars") + getParameter("hasPupalStage") + 1 + getParameter("numFemStages");
    int numNotFemStage = 1 + getParameter("numInstars") + getParameter("hasPupalStage") + 1;
    
	// check for potential errors
	if (stage > numStages) {
		status += "Invalid stage!";
		return status;
	}
	if (newMaxMort < 0) {
		status += "mortality max is positive";
		return status;
	}
	
	// if nothing went wrong, update the map
	
	if (stage < numNotFemStage) // not a female stage
		paramMap[names[stage] + " mortality max"] = newMaxMort;
	else  // female stage
		paramMap["females" + (stage - (numNotFemStage-1)) + std::string(" mortality max")] = newMaxMort;
	
	status += "Success!";
	return status;
}

// method to get a vector of the initial female stage-specific populations
std::vector<double>  /*pointer to start of array*/ Parameters::getInitialFemales() const {
	std::vector<double> initialFem;
	
    int numFemStages = getParameter("numFemStages");
    
	for (int i = 0; i < numFemStages; i ++)
		initialFem.push_back(paramMap.find("initial females" + (i + 1))->second);
	
	return initialFem;
}

// method to get the string name corresponding to the int stage specified
// Invalid Stage! is returned if the stage is invalid (i.e. < 0 or > 12)
std::string Parameters::getStage(int stage) const {
	std::string stageStr = "";
    int numStages = 1 + getParameter("numInstars") + getParameter("hasPupalStage") + 1 + getParameter("numFemStages");
    int numNotFemStage = 1 + getParameter("numInstars") + getParameter("hasPupalStage") + 1;
    
	if (stage > numStages) {
			stageStr += "Invalid stage!";
	} else {
		
		if (stage < numNotFemStage) // not a female stage
			stageStr += names[stage];
		else { // female stage
			int val = stage - (numNotFemStage-1);
			stageStr += "females";
			std::stringstream sstm;
			sstm << val;
			stageStr += sstm.str();
		}
	}

	return stageStr;
}

// method to print all the parameters to the output filestream specified
void Parameters::printToFile(std::ofstream &fileOut) const {
	
	fileOut << "\n";
    
    fileOut << "numDaysToSim: " << paramMap.find("numDaysToSim")->second << "\n";
    fileOut << "integrationStep: " << paramMap.find("integrationStep")->second << "\n";
    fileOut << "printInterval: " << paramMap.find("printInterval")->second << "\n";

    fileOut << "\n";
    
    fileOut << "numInstars: " << paramMap.find("numInstars")->second << "\n";
    fileOut << "hasPupalStage: " << paramMap.find("hasPupalStage")->second << "\n";
    fileOut << "numFemStages: " << paramMap.find("numFemStages")->second << "\n";
	
	fileOut << "\n";
	
	// male proportion
	fileOut << "male proportion: " << paramMap.find("male proportion")->second << "\n";
	fileOut << "\n";
	
	// latitude
	fileOut << "latitude: " << paramMap.find("latitude")->second << "\n";
	fileOut << "\n";
	
    // addInsectsDate
    fileOut << "addInsectsDate: " << paramMap.find("addInsectsDate")->second << "\n";
    fileOut << "\n";
    
    int numStages = 1 + getParameter("numInstars") + getParameter("hasPupalStage") + 1 + getParameter("numFemStages");
    int maleStage = 1 + getParameter("numInstars") + getParameter("hasPupalStage");
    int lastFemStage = numStages - 1;
    
	// life stage-specific parameters
	for (int i = 0; i < numStages; i ++) {
		std::string stage = getStage(i); // current stage
		
        //inital population
		fileOut << "initial " << stage << ": " << paramMap.find("initial " + stage)->second << "\n"; // initial populations
		
        //development parameters
        fileOut << stage << " development a: " << paramMap.find(stage + " development a")->second << "\n";
        fileOut << stage << " development tmin: " << paramMap.find(stage + " development tmin")->second << "\n";
        fileOut << stage << " development tmax: " << paramMap.find(stage + " development tmax")->second << "\n";
		
		//mortality parameters
		for (int j = 0; j < 4; j ++)
			fileOut << stage << " mortality " << mortalityParams[j] << ": " << paramMap.find(stage + " mortality " + mortalityParams[j])->second << "\n";
		
		fileOut << stage << " mortality due to predation: " << paramMap.find(stage + " mortality due to predation")->second << "\n"; 
		
        //egg viability parameters
		if (i > maleStage) { // so, if the female stages have been reached
            fileOut << stage << " egg viability: " << paramMap.find(stage + " egg viability")->second << "\n"; // egg viability for each female stage
		}
        
        fileOut << "\n";
	}
    
    //fecundity parameters
    fileOut << "fecundity max: " << paramMap.find("fecundity max")->second << "\n";
    fileOut << "fecundity tmin: " << paramMap.find("fecundity tmin")->second << "\n";
    fileOut << "fecundity tmax: " << paramMap.find("fecundity tmax")->second << "\n";

    fileOut << "\n";
    
    //sub models
    fileOut << "ignoreOverwintering: " << paramMap.find("ignoreOverwintering")->second << "\n";
    fileOut << "ignoreMortTemp: " << paramMap.find("ignoreMortTemp")->second << "\n";
    fileOut << "ignoreKill: " << paramMap.find("ignoreKill")->second << "\n";
    fileOut << "ignorePredation: " << paramMap.find("ignorePredation")->second << "\n";
    fileOut << "ignoreDiapause: " << paramMap.find("ignoreDiapause")->second << "\n";

    fileOut << "\n";

    //diapause parameters
    fileOut << "diapause critical temp: " << paramMap.find("diapause critical temp")->second << "\n";
    fileOut << "diapause daylight hours: " << paramMap.find("diapause daylight hours")->second << "\n";
    fileOut << "daylight hours half in diapause: " << paramMap.find("daylight hours half in diapause")->second << "\n";
    fileOut << "diapause rate per daylight hours: " << paramMap.find("diapause rate per daylight hours")->second << "\n";
    
    fileOut << "\n";
    
    //overwintering parameters
    fileOut << "overwinteringStage: " << paramMap.find("overwinteringStage")->second << "\n";
    fileOut << "initialOverwintering: " << paramMap.find("initialOverwintering")->second << "\n";
    fileOut << "overwintering success: " << paramMap.find("overwintering success")->second << "\n";
    fileOut << "overwintering critical induction temp: " << paramMap.find("overwintering critical induction temp")->second << "\n";
    fileOut << "overwintering induction daylight hours: " << paramMap.find("overwintering induction daylight hours")->second << "\n";
    fileOut << "overwintering critical termination temp: " << paramMap.find("overwintering critical termination temp")->second << "\n";
    fileOut << "overwintering termination daylight hours: " << paramMap.find("overwintering termination daylight hours")->second << "\n";
    fileOut << "overwintering mortality temp: " << paramMap.find("overwintering mortality temp")->second << "\n";

    //mortality event parameters
    fileOut << "numKillDates: " << paramMap.find("numKillDates")->second << "\n";
    for(int i = 1; i <= paramMap.find("numKillDates")->second; i++){
        fileOut << "killDate: " << i << paramMap.find("killDate" + i)->second << "\n";
    }
    fileOut << "stageKill: " << paramMap.find("stageKill")->second << "\n";
    fileOut << "percentKill: " << paramMap.find("percentKill")->second << "\n";
    
    fileOut << "\n";
}

// method to check the validity of the parameters in the specified map
// an error message is returned specifying the invalid value, or Success! if every parameter is valid
// this is mostly used to check a map before updating it with a new value specified by a param updating method
errormsg Parameters::checkMap(std::map<std::string, double> toCheck) const {
	std::string status = "";
            
    int numStages = 1 + getParameter("numInstars") + getParameter("hasPupalStage") + 1 + getParameter("numFemStages");
    int maleStage = 1 + getParameter("numInstars") + getParameter("hasPupalStage");
    int lastFemStage = numStages - 1;
    
		
    if (toCheck["male proportion"] < 0 || toCheck["male proportion"] > 1) {
        status += "male proportion is between 0 and 1 inclusive";
			return status;
    }
		
    for (int i = 0; i < numStages; i ++) { // stage-specific parameters
        std::string stage = getStage(i); // current stage
			
        if (toCheck["initial " + stage] < 0) {
            status += "initial populations are positive";
            return status;
        }
        
        if (toCheck[stage + " mortality due to predation"] < 0) {
            status += "mortality due to predation is positive";
            return status;
        }
			
        if (i > maleStage) {
            if (toCheck[stage + " egg viability"] < 0 || toCheck[stage + " egg viability"] > 1) {
                status += "egg viability is between 0 and 1 inclusive";
                return status;
            }
        }
	}
    
    if (toCheck["overwinteringStage"] < 0 || toCheck["overwinteringStage"] > numStages){
        status += "diapause stage is set to on of the species life stages";
        return status;
    }

    if (toCheck["overwintering success"] < 0 || toCheck["overwintering success"] > 1) {
        status += "overwintering success is between 0 and 1 inclusive";
        return status;
    }
    
    if (toCheck["overwintering termination daylight hours"] < 0 || toCheck["overwintering termination daylight hours"] > 24 ) {
        status += "overwintering termination daylight hours is between 0 and 24 inclusive";
        return status;
    }
    
    if (toCheck["overwintering induction daylight hours"] < 0 || toCheck["overwintering induction daylight hours"] > 24 ) {
        status += "overwintering induction daylight hours is between 0 and 24 inclusive";
        return status;
    }

    if (toCheck["stageKill"] < 0 || toCheck["stageKill"] > numStages){
        status += "stage kill is set to on of the species life stages";
        return status;
    }

    if (toCheck["percentKill"] < 0 || toCheck["percentKill"] > 100) {
        status += "percent kill is between 0 and 100 inclusive";
        return status;
    }
    
	
	status += "Success!";
        
	return status; // if nothing was returned yet, then there were no errors and the parameters are all fine
}

// method to get a set of specified parameters (i.e. parameters which occur in a set) as an array
// the options are: initial populations, mortality parameters, development parameters, or egg viabilities
std::vector<double> Parameters::getArrayParameters(std::string parameterName) const {
	std::string type = splitBy(' ', parameterName)[0]; // first word in the specified parameter
	
	std::vector<double> toReturn;
	int toRetLength = 0;
    
    int numStages = 1 + getParameter("numInstars") + getParameter("hasPupalStage") + 1 + getParameter("numFemStages");
    int numFemStages = getParameter("numFemStages");
    int numNotAdultStage = 1 + getParameter("numInstars") + getParameter("hasPupalStage");
	
    //getting intial
    for (int i = 0; i < numStages; i ++) {
        // mortality parameters are applicable to all lifestages
        if (type.compare("mortality") == 0){
            toReturn.push_back(paramMap.find(names[i] + " " + parameterName)->second);
        }
        // initial populations are applicable to all lifestages
        else if (type.compare("initial") == 0){
            toReturn.push_back(paramMap.find(parameterName + " " + names[i])->second);
        }
        // development parameters are applicable to all lifestages
        else if (type.compare("development") == 0){
                toReturn.push_back(paramMap.find(names[i] + " " + parameterName)->second);
        }
        // egg viability (only applicable for the female stages)
        else if (type.compare("egg") == 0){
            if (i > numNotAdultStage)  //push back if female
                toReturn.push_back(paramMap.find(names[i] + " " + parameterName)->second);
        }
        
        else
            return toReturn; // if the type was none of the above, then it's invalid

    }
 
	return toReturn; 
}

void Parameters::setDefaultNames() const{
    names.clear();
    names.push_back("eggs");
    names.push_back("instar1");
    names.push_back("instar2");
    names.push_back("instar3");
    names.push_back("pupae");
    names.push_back("males");
    names.push_back("females");
}

void Parameters::setConfigNames() const{
    int numInstars = getParameter("numInstars");
    int hasPupalStage = getParameter("hasPupalStage");
    int numFemStages = getParameter("numFemStages");
    names.clear();
    
    names.push_back("eggs");
    for(int i = 1; i <= numInstars; i++){
        std::stringstream sstm;
        sstm << "instar" << i;
        names.push_back(sstm.str());
    }
    if(hasPupalStage)
        names.push_back("pupae");
    names.push_back("males");
    
    for(int i = 1; i <= numFemStages; i++){
        std::stringstream sstm;
        sstm << "females" << i;
        names.push_back(sstm.str());
    }
}

std::string Parameters::getName(int stageNumber) const{
    return names.at(stageNumber);
}
