#include "Fitting.h"

/*
    Implementation of the Fitting class methods. Class definition and accessors are
    included in the Population header file. The overall class description is also
    included in the header file, right above the class definition.
*/

//constructor to initialize the decision variables, initialize the vectors based on the input file, and read the file into the vectors data fields
Fitting::Fitting(std::string fileName){
    
    //decision variable initial values
    a = 0.001;
    TL = 10;
    TU = 40;
    
    //initalizing the vectors
    int numLines = countLines(fileName);
    if(numLines < 0){
        std::cout << "File not found." << std::endl;
        return;
    }
    xData = std::vector<double> (numLines);
    yData = std::vector<double> (numLines);
    yFit = std::vector<double> (numLines);

    readFile(fileName);
    
    setyFit();
        
}

//constructor to initialize the decision variables, initialize vectors based on the input vectors, and set the vectors data fields to the input vectors
Fitting::Fitting(std::vector<double> x, std::vector<double> y){
    
    //decision variable initial values
    a = 0.001;
    TL = 10;
    TU = 40;
    
    for (int i = 0; i < x.size(); i++){
        xData.push_back(x.at(i));
        yData.push_back(y.at(i));
    }

    setyFit();

}

//method that updates the fit development rate values at each associated temperature value based on the current fit decision variable.
void Fitting::setyFit(){
    
    yFit.clear();
    for(int i = 0; i < xData.size(); i++){
        //development rate equation: d(T) = a * T * (T-TL) * (TU - T)^0.3333333
        yFit.push_back(  a * xData.at(i) * (xData.at(i)-TL) * pow((TU - xData.at(i)),(0.3333333))   );
    }
    
}

//method to modify one of the decision variables (a, TL, or TU) spesified by varID, to improve the development rate equation's fit to the input development values.
void Fitting::fitOneVar(int varID) {
        
    //size of the incrementation multiplier
    double incrementMult = 0.5;
    
    //decision variables
    double tempa = a;
    double tempTL = TL;
    double tempTU = TU;
    
    //objective functions
    double newF;
    double oldF;
    
    //difference between objective functions
    double dif = 100;   //std::abs(newF - oldF)
    
    //first calculate the first objective before the loop
    for(int i = 0; i < xData.size(); i++){
        double tempNewF = pow(yData.at(i) - (tempa * xData.at(i) * (xData.at(i)-tempTL) * pow((tempTU - xData.at(i)),(0.3333333)) ), 2);    //xData.at(i): T, yData.at(i): devRate(T)
        //check that non of the values are nan
        if(!isnan(tempNewF))
            newF += tempNewF;
    }
    
    //size of the incrementation
    double increment;
    if(varID == 1){
        increment = tempa * incrementMult;
        tempa += increment;
    }
    else if (varID == 2){
        increment = tempTL * incrementMult;
        tempTL += increment;
    }
    else if (varID == 3){
        increment = tempTU * incrementMult;
        tempTU += increment;
    }
    
    //keep changing the decision variables until there is little change
    while(std::abs(dif) > 0.000001){ //0.000001 bassed on how exact we want the value to be
        
        //set last loop's newF to this loops oldF
        oldF = newF;
        newF = 0;
        
        //want the minimum value of f(x)= sum[(yData - tempyFit)^2]
        for(int i = 0; i < xData.size(); i++){
            double tempNewF = pow(yData.at(i) - (tempa * xData.at(i) * (xData.at(i)-tempTL) * pow((tempTU - xData.at(i)), (0.3333333)) ), 2);
            //check that non of the values are nan
            if(!isnan(tempNewF))
                newF += tempNewF;
            else
                newF += pow(yData.at(i), 2);
        }
        
        dif = oldF - newF;

        //if it got worst
        if(dif < 0){
            //make the increment smaller and in the oposite direction.
            incrementMult /= -2;
        }
            
        if(varID == 1){
            //tempa
            increment = tempa * incrementMult;
            tempa += increment;
        }
        else if (varID == 2){
            //tempTL
            increment = tempTL * incrementMult;
            tempTL += increment;
        }
        else if (varID == 3){
            //tempTU
            increment = tempTU * incrementMult;
            tempTU += increment;
        }

    }
    
    //set the decision variable to the modified temperary decision variable
    if(varID == 1){
        a = tempa;
    }
    else if (varID == 2){
        TL = tempTL;
    }
    else if (varID == 3){
        TU = tempTU;
    }

}

//method that fits the decision variable (a, TL, TU) so that the development rate equation produces very similar values as the input constant temperatures development rates.
//the fitOneVar() method is called for each of the decision variable 50 times and returns the resulting fit decision variable
std::vector<double> Fitting::fit() {
    
    for(int i = 0; i < 50; i++){
        fitOneVar(1);
        fitOneVar(2);
        fitOneVar(3);
    }
    setyFit();
    
    std::vector<double> decisionVariables;
    decisionVariables.push_back(a);
    decisionVariables.push_back(TL);
    decisionVariables.push_back(TU);

    return decisionVariables;
}

//method to split a string based on a delimiting character and store the resulting split strings in a vector
std::vector<std::string> Fitting::splitBy(const char delim, const std::string toSplit) {
    
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

//method to count the number of lines contained in a file
int Fitting::countLines(std::string fileName){
    
    int numLines = 0;
    std::string line;
    std::ifstream myfile(fileName);

    //Check if the file good
    if (&fileName == NULL){
        std::cout << "No file entered" << std::endl;
        return -1;
    }
    std::ifstream fileIn(fileName.c_str());
    if (fileIn.bad()){
        std::cout << "File not found, using default parameters." << std::endl;
        return -1;
    }
    
    while (std::getline(myfile, line))
        numLines++;
    
    //do numLines - 1 since the first line is the file'S the header labes
    return numLines - 1;
}


//method to reads in a file and stores the data in the x & y vector data fields.
void Fitting::readFile(std::string fileName){
    
    int lineNumber = 1;
    xData.clear();
    yData.clear();
    
    //Check if the file good
    if (&fileName == NULL){
        std::cout << "No file entered" << std::endl;
        return;
    }
    std::ifstream fileIn(fileName.c_str());
    if (fileIn.bad()){
        std::cout << "File not found, using default parameters." << std::endl;
        return;
    }
    
    std::string line;
    while (std::getline(fileIn, line)) // read file line-per-line
    {
        std::vector<std::string> splitLine = splitBy(',', line); // has to be name : value
        if (splitLine.size() != 2){
            std::cout << "Invalide file formating." << std::endl;
            return;
        }
        
        //eat the first label line
        if(lineNumber > 1){
            
            //check for acceptable data values
            std::istringstream xiss(splitLine.at(0));
            std::istringstream yiss(splitLine.at(1));
            double xval;
            double yval;
            if(! (xiss >> xval) || ! (yiss >> yval)){
                std::stringstream sstm;
                sstm << "Input error - invalid value - data point " << lineNumber-1;
                std::cout << sstm.str() << std::endl;
                return;
            }
            
            //add the file data into the x and y vectors.
            xData.push_back(xval);
            yData.push_back(yval);
        }
        
        lineNumber ++;
    }
    fileIn.close();
}
