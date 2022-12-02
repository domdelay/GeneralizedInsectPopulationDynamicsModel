execFittingFec: FittingWrapper.o Fitting.o 
	g++ -o execFittingFec FittingWrapper.o Fitting.o

FittingWrapper.o: FittingWrapper.cpp Fitting.h
	g++ -c FittingWrapper.cpp

Fitting.o: Fitting.cpp Fitting.h 
	g++ -c Fitting.cpp