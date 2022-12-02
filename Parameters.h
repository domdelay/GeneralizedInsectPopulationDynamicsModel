#ifndef PARAMETERS_H
#define PARAMETERS_H

#include <map>
#include <string>
#include <iostream>
#include <fstream>
#include <vector>

#include "UtilityMethods.h"

/*

	This object class represents all the parameters in the CellSingle object.  The 
	parameters are stored in a map (they are all doubles) with String keys as the name
	of the parameter.
	There are various methods to access the parameters - the user can choose to get a 
	map of all the parameters, parameters in arrays, or individual parameters.  
	There are also methods to reset the values of the parameters through various means
	(reading from a file, a map, a Parameters object); the user can also specify to 
    reset an individual parameter.
	There is also a method to facilitate printing of the parameters to a file (in a 
	format so that it can later be read in).
	If there are errors in the input, the user is notified via a String describing the 
	error (if parameters are being reset) or an error is returned (if
	parameters are being accessed).

	Methods described the implementation file, Parameters.cpp.

*/

typedef std::string errormsg; // the type for the messages returned from methods to indicate success or errors (really just a string)

class Parameters {

	std::map<std::string, double> paramMap; // string, double map of params (param name = key, double for the value)
    static std::vector<std::string> names;
	static std::string mortalityParams[]; 


public:

	// constructors
	Parameters();
	Parameters(const std::string fileName);
	Parameters(const Parameters &params);

	// methods described in the implementation file
	errormsg setConfigParams(const std::string configFileName);
	void setDefaultParams();
	errormsg setMapParams(std::map<std::string, double> inputMap);
	errormsg setCopyParams(const Parameters& params);
	std::map<std::string, double> getMap() const;
	double getParameter(std::string parameter) const;
	errormsg setParameter(const std::string& parameter, double newValue);
	errormsg setMaxMort(int stage, double newMaxMort);
	std::vector<double>  /*pointer to start of array*/ getInitialFemales() const;
	std::string getStage(int stage) const;

	void printToFile(std::ofstream& fileOut) const;
	errormsg checkMap(std::map<std::string, double> toCheck) const;

	std::vector<double> getArrayParameters(std::string parameterName) const;
    
    //added to avoid repeating code.
    void setDefaultNames() const;
    void setConfigNames() const;
    std::string getName(int stageNumber) const;

};

#endif
