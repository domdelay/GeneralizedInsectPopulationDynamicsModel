#ifndef Fitting_h
#define Fitting_h

#include <vector>
#include <string>
#include <iostream>
#include <fstream>
#include <sstream>
#include <math.h>
#include <cmath>

/*
    This class describes a Fitting object. This object modifies the decision
    variables (a, b, c) to make the general model's life stage mortality rate
    equation a good fit to input mortality rates at various constant temperatures.
*/

class Fitting {
    
    //decision variables
    double a;
    double b;
    double c;
    
    std::vector<double> xData;  //temperature values
    std::vector<double> yData;  //dev rate at associated temperature values
    std::vector<double> yFit;   //fitted dev rate at associated temperature values

public:
    
    Fitting(std::string fileName);
    Fitting(std::vector<double> x, std::vector<double> y);
    
    void setyFit();
    
    std::vector<double> getyFit(){return yFit;}
    std::vector<double> getxData(){return xData;}
    std::vector<double> getyData(){return yData;}
    
    double geta(){return a;}
    double getb(){return b;}
    double getc(){return c;}
    
    void fitOneVar(int varID);
    
    std::vector<double> fit();
    
    std::vector<std::string> splitBy(const char delim, const std::string toSplit);
    int countLines(std::string fileName);
    void readFile(std::string fileName);

};

#endif /* Fitting_h */
