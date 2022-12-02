#ifndef CELL_SINGLE_H
#define CELL_SINGLE_H

#include "Population.h"

/*

	Header file for the CellSingle.cpp. Contains the CellSingle class definition,
	described below.  
	Method definitions are included in the cpp file, along with descriptions of the code.

*/

/*
	Class representing a coordinate pair; used to store (time, value) data points.
*/ 
class XYPair {

	double x, y;
	
public:

	XYPair(double xi, double yi) : x(xi), y(yi) {} // constructor
	// accessors for the coordinates
	double getX() const { return x; }
	double getY() const { return y; }

}; 


// XYSeries exists basically for symmetry with the old Java code - XYSeries was a JFreechart 
// object describing a list of (x, y) coordinate pairs of data points
// Here, XYSeries is a list (vector) of XYPairs
typedef std::vector<XYPair> XYSeries;


/*
	This class describes an CellSingle object, modeling the environment for
	an Population. This class has a Parameters object representing the various
	parameters to calculate the vitals for the insects, given the temperature of the cell.
	There is a general accessor for each of the datafields (from the parameters object),
	and accessors for the population's datafields (using the population's accessors).  
	There is also a mutator to reset the timestep back to 0, and a mutator to reset 
	all of the cell's parameters to those specified in the Parameters object passed in.

	Methods described in-code, in the implementation file CellSingle.cpp.
*/

class CellSingle {
	double temp; // temperature of the cell during the current timestep
	Population population; // population of the cell (for all lifestages - for details see Population class)
    
    bool ignoreDiapause;

    int numInstars;
    bool hasPupalStage;
    int numFemStages;
    
    int numDaysToSim;

	// each series keeps all the data for its respective lifestage up to the current timestep
	// there is one data point for every dt
	XYSeries eggSeries, pupaeSeries, malesSeries, femalesSeries;
    std::vector<XYSeries> instXSeries;
    std::vector<XYSeries> femaleStageSeries;
    XYSeries temperatureSeries, daylightHoursSeries;
	
	Parameters params; // parameters for all life processes stored 
	
	double maxEggs, maxPupae, maxMales, maxFemales; // max population of each respective lifestage
    std::vector<double> maxInst;
    double maxTemp, maxHours;
    
	double maxEggsDay, maxPupaeDay, maxMalesDay, maxFemalesDay; // timestep where the max occured
    std::vector<double> maxInstDay;
    double maxTempDay, maxHoursDay;
	
	double totEggs, totPupae, totMales, totFemales; // total cumulative population per stage
    std::vector<double> totInst;

public:

	CellSingle(Parameters paramsNew, double newTemp = 888);
	errormsg setSingleParameter(std::string &param, double newVal);
	double getSpecificParameter(std::string &param) const;

	void stepForward(double temperature, bool ignoreDiapauseNew, double dt, double timeStep);
	void resetTime();

	double getTemperature() const { return temp; }
	void readInitInsects() { population.readPopulation(params); }
    void killInsects(int eventNumber) { population.killPopulation(params, eventNumber); }
	void setAddInitPop(bool toSet) { population.setAddInitPop(toSet); }
	int getCrossedDiapDay() const { return population.getCrossedDiapDay(); }

	double getEggs() const { return population.getEggs(); }
    double getInstX(int x) const { return population.getInstX(x); }
	double getPupae() const { return population.getPupae(); }
	double getMales() const { return population.getMales(); }
	double getFemales() const { return population.getFemales(); }
	double getTotalPopulation() const { return population.getTotalPopulation(); }
	const std::vector<double> getFemStages() const { return population.getFemStages(); }
	double getMaxFemales() const { return maxFemales; }
	double getMaxMales() const { return maxMales; }
	double getMaxPupae() const { return maxPupae; }
    double getMaxInstX(int x) const { return maxInst.at(x-1); }
	double getMaxEggs() const { return maxEggs; }
    double getMaxTemp() const {return maxTemp; }
    double getMaxHours() const {return maxHours; }

	double getDayMaxFemales() const { return maxFemalesDay; }
	double getDayMaxMales() const { return maxMalesDay; }
	double getDayMaxPupae() const { return maxPupaeDay; }
    double getDayMaxInstX(int x) const { return maxInstDay.at(x-1); }
	double getDayMaxEggs() const { return maxEggsDay; }
    double getDayMaxTemp() const {return maxTempDay; }
    double getDayMaxHours() const {return maxHoursDay; }

	double getTotFemales() const { return totFemales; }
	double getTotMales() const { return totMales; }
	double getTotPupae() const { return totPupae; }
    double getTotInstX(int x) const { return totInst.at(x-1); }
	double getTotEggs() const { return totEggs; }

	Parameters getParams() const { return Parameters(params); }
	void resetCellParams(Parameters &paramsNew);

	const XYSeries& getEggSeries() const { return eggSeries; }
    const XYSeries& getInstXSeries(int x) const { return instXSeries.at(x-1); }
	const XYSeries& getPupaeSeries() const { return pupaeSeries; }
	const XYSeries& getMalesSeries() const { return malesSeries; }
	const XYSeries& getFemalesSeries() const { return femalesSeries; }
    const XYSeries& getTemperatureSeries() const { return temperatureSeries; }
    const XYSeries& getDaylightHoursSeries() const { return daylightHoursSeries; }
    const XYSeries& getFemaleStageSeries(int index) const { return femaleStageSeries.at(index); }
		
};

#endif
