#ifndef SIMULATOR_SINGLE_H
#define SIMULATOR_SINGLE_H

#include "CellSingle.h"

/*

	This class describes an SimulatorSingle object, which is a simulator to run
	its cell datafield under various specified conditions.  
	There are accessors for all the cell's accessors (to access the cell's and its
	population's parameters).  
	There are also methods to reset the parameters (i.e. the parameter map) either 
	with arguments or by reading in from a file).
	There are also mutators to run the cell for a specified time interval under 
	specified temperature conditions.

	Method definitions are included in the SimulatorSingle.cpp file, along with descriptions of the code.
*/

class SimulatorSingle {
	CellSingle cell; // cell to run the simulation on
	
	double timeStep; // current timestep
	double dt; // integration step
	
	bool injectInsects;

public:
	// constructors
	SimulatorSingle(double dtNew = 0.05, std::string fileName = "config.txt");
	SimulatorSingle(double dtNew, Parameters &params);

	// these methods are described in the cpp file
	void setDefaultParams();
	errormsg setSingleParameter(std::string &param, double newVal);
	double getSpecificParameter(std::string &param) const;
	errormsg setConfigParams(std::string &configFileName);
	errormsg setMapParams(std::map<std::string, double> inputMap);

	// methods to run a sim - also explained in the cpp file
	void run(double temperature, double numTimeSteps, bool ignoreDiapause);
    void run(std::vector<double> temperatures, double numTimeSteps, bool ignoreDiapause, int startDay, std::vector<int> killDates);

	// self-explanatory accessors and mutators, similar to those in the CellSingle class
	void setDT(double dtNew) { dt = dtNew; }
	Parameters getParams() const { return cell.getParams(); }

	double getEggs() const { return cell.getEggs(); }
    double getInstX(int x) const { return cell.getInstX(x); }
	double getPupae() const { return cell.getPupae(); }
	double getMales() const { return cell.getMales(); }
	double getFemales() const { return cell.getFemales(); }
	const std::vector<double> getFemStages() const { return cell.getFemStages(); }
	double getTotalPopulation() const { return cell.getTotalPopulation(); }

	double getMaxFemales() const { return cell.getMaxFemales(); }
	double getMaxMales() const { return cell.getMaxMales(); }
	double getMaxPupae() const { return cell.getMaxPupae(); }
    double getMaxInstX(int x) const { return cell.getInstX(x); }
	double getMaxEggs() const { return cell.getMaxEggs(); }

	double getDayMaxFemales() const { return cell.getDayMaxFemales(); }
	double getDayMaxMales() const { return cell.getDayMaxMales(); }
	double getDayMaxPupae() const { return cell.getDayMaxPupae(); }
    double getDayMaxInstX(int x) const { return cell.getInstX(x); }
	double getDayMaxEggs() const { return cell.getDayMaxEggs(); }

	double getTotFemales() const { return cell.getTotFemales(); }
	double getTotMales() const { return cell.getTotMales(); }
	double getTotPupae() const { return cell.getTotPupae(); }
    double getTotInstX(int x) const { return cell.getInstX(x); }
	double getTotEggs() const { return cell.getTotEggs(); }

	double getTemperature() const { return cell.getTemperature(); }
	double getTimeStep() const { return timeStep; }
	void resetTime();
	int getCrossedDiapDay() const { return cell.getCrossedDiapDay(); }

	const XYSeries& getEggSeries() const { return cell.getEggSeries(); }
    const XYSeries& getInstXSeries(int x) const { return cell.getInstXSeries(x); }
	const XYSeries& getPupaeSeries() const { return cell.getPupaeSeries(); }
	const XYSeries& getMalesSeries() const { return cell.getMalesSeries(); }
	const XYSeries& getFemalesSeries() const { return cell.getFemalesSeries(); }
	const XYSeries& getFemaleStageSeries(int index) const { return cell.getFemaleStageSeries(index); }

	CellSingle& getCell() { return cell; }

};


#endif
