#include "Fitting.h"

/*
    This is a wrapper class for the mortality rate parameter's fitting
    algorithm (Fitting). It class reads constant temperature mortality rate values
    from a spesified .csv file, calls the Fitting class for each development stage to
    obtain the fitted mortality parameters, and prints the mortality parameteres
    to file ("fecParams.txt").
*/

double maxCstTemp = -111;
std::vector<double> maxMortatStage;

//function to split a string based on a delimiting character and store the resulting split strings in a vector
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

//function to read in the constant temperatures mortality rate file and split it into one file for each stage. Delete all sub files at the end. The function also runs the fitting algorithm and returns a vector containing the fit parameters
std::vector<std::vector<double> > readMortFile(std::string mortRateFileName, int numMortStages){
    
    //read file
    std::ifstream fileInLines(mortRateFileName.c_str());
    int numLines = 0;
    std::string l;
    while (std::getline(fileInLines, l)){
        numLines ++;
    }
    fileInLines.close();
    
    std::vector<std::vector<double> > xData(numMortStages, std::vector<double>(numLines));
    std::vector<std::vector<double> > yData(numMortStages, std::vector<double>(numLines));
    
    std::ifstream fileIn(mortRateFileName.c_str());
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
        
        maxMortatStage = std::vector<double> (numMortStages);
        while (std::getline(fileIn, line)){ // read file line-per-line

            std::vector<std::string> splitLine = splitBy(',', line); // has to be name : value
            if (splitLine.size() != numMortStages+1){
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
                
                for (int i = 0; i < numMortStages; i++){
                    std::istringstream yiss(splitLine.at(i+1));
                    if(! (yiss >> yval)){
                        std::stringstream sstm;
                        sstm << "Input error - invalid value - data point " << lineNumber-1;
                        std::cout << sstm.str() << std::endl;
                        break;
                    }

                    //find max mort rate
                    if(yval > maxMortatStage.at(i)){
                        maxMortatStage.at(i) = yval;
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
    for(int i = 0; i < numMortStages; i++){
        fittingList.push_back(Fitting(xData.at(i), yData.at(i)));

    }
    
    std::vector<std::vector<double> > decisionVariables (numMortStages, std::vector<double>(3));
    
    decisionVariables.clear();
    for(int i = 0; i < fittingList.size(); i++){
        decisionVariables.push_back(fittingList.at(i).fit());
    }
    
    return decisionVariables;
}

//function to write the resulting mortality rate parameters to the output file
void writeResultsToFile(std::vector<std::vector<double> > parabolaValues){
    
    std::ofstream fileOut("mortParams.txt");
    
    for(int i = 0; i < parabolaValues.size(); i++){
        for(int j = 0; j < parabolaValues.at(i).size(); j++){
            fileOut << parabolaValues.at(i).at(j) << " ";
        }
        fileOut << "\n";
    }
}

//method to convert the fitted decision variables (a, b, c) to the general model's mortality rate equation parameters (Tlower, Tupper, minMort, maxMort).
std::vector<std::vector<double> > extractParabolaParams(std::vector<std::vector<double> > abc, int numMortStages){
    
    double a, b, c;
    std::vector<std::vector<double> > params (numMortStages, std::vector<double>(4));
    double Tlower, Tupper, Topt, minMort, maxMort, newC, result1, result2;
    
    for(int i = 0; i < abc.size(); i++){
        a = abc.at(i).at(0);
        b = abc.at(i).at(1);
        c = abc.at(i).at(2);
        
        maxMort = maxMortatStage.at(i);
        newC = c - 1;
        result1 = (-b + sqrt(pow(b,2) - 4*a*newC))/(2*a);
        result2 = (-b - sqrt(pow(b,2) - 4*a*newC))/(2*a);
        if(result1 < result2){
            Tlower = result1;
            Tupper = result2;
        }
        else{
            Tlower = result2;
            Tupper = result1;
        }
                
        //mortality at Topt (?, Topt)
        Topt = (Tupper-Tlower)/2 + Tlower;
        minMort = a*pow(Topt, 2) + b*Topt + c;
        
        params.at(i).at(0) = minMort;
        params.at(i).at(1) = maxMort;
        params.at(i).at(2) = Tlower;
        params.at(i).at(3) = Tupper;
        
    }
    
    return params;
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
    int numMortStages;
    sstm >> numMortStages;

    //run the fitting algorithm
    std::vector<std::vector<double> > parabolaValues = readMortFile(inputFileName, numMortStages);
  
    //convert dicision variable to equation parameters
    std::vector<std::vector<double> > mortValues = extractParabolaParams(parabolaValues, numMortStages);
    
    //output parameters to file
    writeResultsToFile(mortValues);
    
    return 0;
}
