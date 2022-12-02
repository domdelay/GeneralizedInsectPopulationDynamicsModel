#include "Fitting.h"

/*
    This is a wrapper class for the development rate parameter's fitting
    algorithm (Fitting). It class reads constant temperature development rate values
    from a spesified .csv file, calls the Fitting class for each development stage to
    obtain the fitted development parameters, and prints the development parameteres
    to file ("devParams.txt").
 */

//function to split a string based on a delimiting character and store the resulting split strings in a vector
std::vector<std::string> splitBy(const char delim, const std::string toSplit) {
    std::vector<std::string> div;

    std::string temp = "";
    for (int i = 0; i < toSAddplit.size(); ++ i) {
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

//function to read in the constant temperatures development rate file and split it into one file for each stage. Delete all sub files at the end. The function also runs the fitting algorithm and returns a vector containing the fit parameters
std::vector<std::vector<double> > readDevFile(std::string devRateFileName, int numDevStages){
    
    //read file
    std::ifstream fileInLines(devRateFileName.c_str());
    int numLines = 0;
    std::string l;
    while (std::getline(fileInLines, l)){
        numLines ++;
    }
    fileInLines.close();
    
    std::vector<std::vector<double> > xData(numDevStages, std::vector<double>(numLines));
    std::vector<std::vector<double> > yData(numDevStages, std::vector<double>(numLines));
    
    std::ifstream fileIn(devRateFileName.c_str());
    if (fileIn.bad()){
        std::cout << "File not found, using default parameters." << std::endl;
    }
    else{
        int lineNumber = 1;
        std::string line;
        for(int i = 0; i < yData.size(); i++){
            xData.at(i).clear();
            yData.at(i).clear();
        }
        
        while (std::getline(fileIn, line)){ // read file line-per-line

            std::vector<std::string> splitLine = splitBy(',', line);
            if (splitLine.size() != numDevStages+1){
                std::cout << "Invalide file formating." << std::endl;
                break;
            }
            
            //eat the first label line
            if(lineNumber > 1){

                //check for acceptable data values
                double xval;
                double yval;
                std::istringstream xiss(splitLine.at(0));
                if(! (xiss >> xval)){
                    std::stringstream sstm;
                    sstm << "Input error - invalid value - data point " << lineNumber-1;
                    std::cout << sstm.str() << std::endl;
                    break;
                }
                
                for (int i = 0; i < numDevStages; i++){
                    std::istringstream yiss(splitLine.at(i+1));
                    if(! (yiss >> yval)){
                        std::stringstream sstm;
                        sstm << "Input error - invalid value - data point " << lineNumber-1;
                        std::cout << sstm.str() << std::endl;
                        break;
                    }
                    //add the file data into the y vectors
                    if(!isnan(yval)){
                        //add the file data into the x vectors
                        xData.at(i).push_back(xval);
                        yData.at(i).push_back(yval);
                    }
                }
            }
            lineNumber ++;
        }
        fileIn.close();
    }
    
    //call the fitting algorithm for each life stage
    std::vector<Fitting> fittingList;

    fittingList.clear();
    for(int i = 0; i < numDevStages; i++){
        fittingList.push_back(Fitting(xData.at(i), yData.at(i)));
    }
    
    std::vector<std::vector<double> > decisionVariables (numDevStages, std::vector<double>(3));
    
    decisionVariables.clear();
    for(int i = 0; i < fittingList.size(); i++){
        decisionVariables.push_back(fittingList.at(i).fit());
    }
    
    return decisionVariables;
}

//function to write the resulting development rate parameters to the output file
void writeResultsToFile(std::vector<std::vector<double> > devValues){
    std::ofstream fileOut("devParams.txt");
    
    for(int i = 0; i < devValues.size(); i++){
        for(int j = 0; j < devValues.at(i).size(); j++){
            fileOut << devValues.at(i).at(j) << " ";
        }
        fileOut << "\n";
    }
}



int main(int argc, char *argv[]) {
    
    if ( argc != 3){
        printf( "Error, exiting now\nUsage: ./exec inputFileName numstages\n");
        exit( 0);
    }
    //collect in-line arguments
    std::string inputFileName = argv[1];
    std::stringstream sstm;
    sstm << argv[2];
    int numDevStages;
    sstm >> numDevStages;

    //read file and run the fitting algorithm
    std::vector<std::vector<double> > devValues = readDevFile(inputFileName, numDevStages);
  
    //output parameters to file
    writeResultsToFile(devValues);
    
    return 0;
}
