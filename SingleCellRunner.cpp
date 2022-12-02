#include "SimulatorSingle.h"
#include "UtilityMethods.h"


/*

	This file is a runner for the singlecell simulator.
	It takes in command line arguments to run the simulator.
	Run the simulation as follows:

	First, compile the model.  Call it using:

    ./execSingleCellRunner parametersFileName temperatureFileName outputFileName

    parametersFileName = the extended parameters list to run the simulation with.  Usually configparams.txt
    temperatureFileName = a file with 365 single line entries of temperatures
    outputFileName = destination file for results

	If you have the incorrect number of arguments the code will exit with an error.
	
	Otherwise the output of the simulation will be printed to the output file specified.
	This output consists of: 
		-- daily population of every lifestage
		-- total cumulative population for each stage
		-- peak population for each stage
		-- peak population date for each stage

*/

void printCellInfo( std::string outputFile, CellSingle cell, Parameters params, std::vector< double> temperatures) {
    
    int numInstars = params.getParameter("numInstars");
    bool hasPupalStage = params.getParameter("hasPupalStage");
    int numFemStages = params.getParameter("numFemStages");
    int totalSize = 1 + numInstars + hasPupalStage + 1 + 1 + 1 + 1; //egg, instars, pupa, male, female, temperature, dayligthHours
    
    std::string names[totalSize];
    int num = 0;
    names[num] = "eggs";
    num++;
    while(num <= numInstars){
        std::stringstream sstm;
        sstm << "instar" << num;
        names[num] = sstm.str();
        num++;
    }
    if(hasPupalStage){
        names[num] = "pupae";
        num++;
    }
    names[num] = "males";
    num++;
    names[num] = "females";
    num++;
    names[num] = "temperature";
    num++;
    names[num] = "dayligth hours";
    num++;
		
	XYSeries toPrint[totalSize]; // array of data series for all lifestages, temperature, vs time
	
    int num1 = 0;
    toPrint[num1] = cell.getEggSeries();
    num1++;
    for (int i = 0; i < numInstars; i++){
        toPrint[num1] = cell.getInstXSeries(i+1);
        num1++;
    }
    if(hasPupalStage){
        toPrint[num1] = cell.getPupaeSeries();
        num1++;
    }
	toPrint[num1] = cell.getMalesSeries();
    num1++;
	toPrint[num1] = cell.getFemalesSeries();
    num1++;
    toPrint[num1] = cell.getTemperatureSeries();
    num1++;
    toPrint[num1] = cell.getDaylightHoursSeries();
    num1++;
	
	std::ofstream fileOut;
	fileOut.open(outputFile);

    std::string spc = "\t";

	fileOut << "Time:" << spc;
	// print daily data
	for (int jk = 0; jk < totalSize; jk ++) { // print data labels
			fileOut << names[jk] << ":" << spc;
	}
	fileOut << "\n";
    double numStepBetweenPrints = params.getParameter("printInterval") / params.getParameter("integrationStep");
	for (int ik = 0; ik < toPrint[0].size(); ik +=numStepBetweenPrints) { // += printInterval so it prints every _th datapoint (e.g. once per day)
	 	fileOut << toPrint[0][ik].getX() << spc; // print the timestep (same for all series)
	 	for (int jk = 0; jk < totalSize; jk ++) {
	 			fileOut << toPrint[jk][ik].getY() << spc;
	
	 	}
	 	fileOut << "\n";
	}

    int numDaysToSim = params.getParameter("numDaysToSim");

	double diapi = 0;
	for ( int i = 0; i < numDaysToSim*20; i += 20) {
		if ( toPrint[totalSize-2][i].getY() > 0) {
			diapi = i/20.0;
			break;
		}
	}

    double total = cell.getTotEggs() + cell.getTotMales() + cell.getTotFemales();
    for (int i = 0; i < numInstars; i++){
        total += cell.getTotInstX(i+1);
    }
    if(hasPupalStage)
        total += cell.getTotPupae();
	
	// print overall data
	fileOut << "\n\nTotal Cumulative Populations\n";
	std::stringstream sstm;
    sstm << "\n" << spc << cell.getTotEggs() << spc;
    for (int i = 0; i < numInstars; i++){
        int val = i + 1;
        sstm << cell.getTotInstX(val) << spc;
    }
    if(hasPupalStage)
        sstm << cell.getTotPupae() << spc;
    sstm << cell.getTotMales() << spc << cell.getTotFemales() << spc;
				
	fileOut << sstm.str();
	fileOut << "\n\nPeak Populations\n";

	std::stringstream sstm1;
    sstm1 << "\n" << spc << cell.getMaxEggs() << spc;
    for (int i = 0; i < numInstars; i++){
        int val = i + 1;
        sstm1 << cell.getMaxInstX(val) << spc;
    }
    if(hasPupalStage)
        sstm1 << cell.getMaxPupae() << spc;
    sstm1 << cell.getMaxMales() << spc << cell.getMaxFemales() << spc << cell.getMaxTemp() << spc << cell.getMaxHours();
    
	fileOut << sstm1.str();

	fileOut << "\n\nPeak Populations Day\n";

	std::stringstream sstm2;
    sstm2 << "\n" << spc << cell.getDayMaxEggs() << spc;
    for (int i = 0; i < numInstars; i++){
        int val = i + 1;
        sstm2 << cell.getDayMaxInstX(val) << spc;
    }
    if(hasPupalStage)
        sstm2 << cell.getDayMaxPupae() << spc;
    sstm2 << cell.getDayMaxMales() << spc << cell.getDayMaxFemales() << spc << cell.getDayMaxTemp() << spc << cell.getDayMaxHours();
        
	fileOut << sstm2.str();

	std::stringstream sstm3;
    sstm3 << "\n\nDiapause injection date: " << diapi << "\n";
	fileOut << sstm3.str();

	fileOut.close();
	
}

// main method to run the code; this takes in the cmd line arguments
int main(int argc, char *argv[]) {

	if ( argc != 4)
	{
        printf( "Error, exiting now\nUsage: ./singleSim paramFileName tempFileName outputFileName");
		exit( 0);
	}

	// read in all the arguments
	std::string inputFileName = argv[ 1];
	std::string tempFileName = argv[ 2];
	std::string outputFileName = argv[ 3];

    int ignoreDiapause, startDate, startFemPop;
                                                         
	// read temperatures in from file
	std::ifstream infile( tempFileName);
	std::vector< double> temperatures;

	double temp;
	while ( infile >> temp)
	{
		temperatures.push_back( temp);
	}
    
    Parameters params(inputFileName);
        
    //setting variables from input file - param
    ignoreDiapause = params.getParameter("ignoreDiapause");
    startDate = params.getParameter("addInsectsDate");

    int numKillDates = params.getParameter("numKillDates");
    std::vector< int> killDates = std::vector<int> (numKillDates);
    for(int i = 0; i < numKillDates; i++){
        int val = i + 1;
        std::stringstream sstm;
        sstm << "killDate" << val;
        killDates.at(i) = params.getParameter(sstm.str());
    }

	infile.close();
    
    int numDaysToSim = params.getParameter("numDaysToSim");
    int runTime = numDaysToSim;
    double dt = params.getParameter("integrationStep");  // integration step

	// set up and run the simulator
    SimulatorSingle sim( dt, params);
	sim.run( temperatures, runTime, ignoreDiapause, startDate, killDates);

	printCellInfo( outputFileName, sim.getCell(), params, temperatures); // print output to a file

	return 0;
}
