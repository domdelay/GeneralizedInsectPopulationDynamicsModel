execSingleCellRunner: SingleCellRunner.o SimulatorSingle.o CellSingle.o Population.o EulersMethod.o SolveParameters.o Parameters.o UtilityMethods.o Daylight.o
	g++ -o execSingleCellRunner SingleCellRunner.o SimulatorSingle.o CellSingle.o Population.o EulersMethod.o SolveParameters.o Parameters.o UtilityMethods.o Daylight.o

SingleCellRunner.o: SingleCellRunner.cpp SimulatorSingle.h
	g++ -c SingleCellRunner.cpp

SimulatorSingle.o: SimulatorSingle.cpp SimulatorSingle.h
	g++ -c SimulatorSingle.cpp

CellSingle.o: CellSingle.cpp CellSingle.h
	g++ -c CellSingle.cpp

Population.o: Population.cpp Population.h
	g++ -c Population.cpp

EulersMethod.o: EulersMethod.cpp EulersMethod.h
	g++ -c EulersMethod.cpp

SolveParameters.o: SolveParameters.cpp SolveParameters.h
	g++ -c SolveParameters.cpp

Parameters.o: Parameters.cpp Parameters.h
	g++ -c Parameters.cpp

UtilityMethods.o: UtilityMethods.cpp UtilityMethods.h
	g++ -c UtilityMethods.cpp

Daylight.o: Daylight.cpp Daylight.h
	g++ -c Daylight.cpp
