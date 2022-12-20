//CODE STRUCTURE:
	//IMPORTS       --- Line 25-58
	//GLOBAL PRIVATE VARIABLES       --- Line 63-369
	//HOME PAGE, SCENE1, SCENE2, SCENE3, SCENE4, SCENE5, SCENE6, SUBMODSCENE, SIMSCENE, OUTPUTSCENE       --- Line 375-2032
	//WINDOW SETUP       --- Line 2035-2038
	//SCENE SETUP (SCENE2, SCENE3, SCENE4, SCENE5, SCENE6, SCENE11, SCENE12, SETUP DEFAULT INSECTS)       --- Line 2046-2366
	//SCENE DATA COLLECTION       --- Line 2368-2670
	//SETUP SCENE LAYOUT       --- Line 2672-2680
	//TEXTFIELD LISTENERS       --- Line 2682-2732
	//OTHER STUFF       --- Line 2734-2763
	//WRITTE TO FILE       --- Line 2766-3012
	//SAVE DATA TABLES       --- Line 3014-3179
	//VARIABLE LENGTH TEXTFIELD ADD FUNCTIONS       --- Line 3181-3479
	//GRAPH STUFF       --- Line 3482-3875
	//INPUT DATA FILE       --- Line 3877-4074
	//RUNNING EXTERNAL PROGRAMS       --- Line 4076-4189
	//INPUT VALIDATION       --- Line 4192-4236
	//SAVE DATA FILE OUTPUT       --- Line 4239-4261
	//VISUAL STUFF       --- Line 4263-4278
	//ERROR WINDOW       --- Line 4280-4324
	//TOOL TIP SETUP       --- Line 4326-4340
	//INFO BUTTON       --- Line 4342-4729
	//MAIN       --- Line 4732-4737

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  

import javafx.application.*;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;	//other & tooltip
import javafx.scene.layout.*;
import javafx.collections.*;
import javafx.scene.text.*;
import javafx.stage.*;	//Stage
import javafx.scene.paint.*;
import javafx.geometry.*;
import javafx.scene.control.ComboBox;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.chart.*;	//for graph application
import java.util.concurrent.TimeUnit;	//for delay before graphing
import javafx.scene.input.KeyEvent;	//event listener keys
import javafx.stage.FileChooser;	//file browser
import javafx.scene.shape.*;
import javafx.scene.input.*;
import javafx.scene.Group; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  


public class javaUI extends Application {
	
	private Scene homeScene, scene1, scene2, scene3a, scene3b, scene4, scene4b, scene5, scene5b, scene6;
	private Scene sceneSubMod;
	private Scene simulationScene, outputScene;
	private VBox layout1 = new VBox(4);	//4 is spacing between
	private VBox layout2 = new VBox(4);
	private VBox layout3a = new VBox(4);
	private VBox layout3b = new VBox(4);
	private VBox layout4 = new VBox(4);
	private VBox layout4b = new VBox(4);
	private VBox layout5 = new VBox(4);
	private VBox layout5b = new VBox(4);
	private VBox layout6 = new VBox(4);
	private VBox layout7 = new VBox(4);
	private VBox layout8 = new VBox(4);
	private VBox layout9 = new VBox(4);
	private VBox layout10 = new VBox(4);
	private VBox layout11 = new VBox(4);
	private VBox layout12 = new VBox(4);
	private VBox layout13 = new VBox(4);
	private VBox layoutSub = new VBox(4);
	private VBox layoutSim = new VBox(4);
	private VBox layoutOut = new VBox(4);
	
	private GridPane gridP2 = new GridPane();
	private GridPane gridP3a = new GridPane();
	private GridPane gridP3b = new GridPane();
	private GridPane gridP4 = new GridPane();
	private GridPane gridP4b = new GridPane();
	private GridPane gridP5 = new GridPane();
	private GridPane gridP5b = new GridPane();
	private GridPane gridP6 = new GridPane();
	private GridPane gridP7 = new GridPane();
	private GridPane gridP8 = new GridPane();
	private GridPane gridP9 = new GridPane();
	private GridPane gridP10 = new GridPane();
	private GridPane gridP11 = new GridPane();
	private GridPane gridP11in = new GridPane();
	private GridPane gridP12 = new GridPane();
	private GridPane gridP12in = new GridPane();
	private GridPane gridP13 = new GridPane();
	private GridPane gridPSim = new GridPane();
	private GridPane gridPOut = new GridPane();
	
	private CheckBox checkOverwintering;
	private CheckBox checkMortTemp;
	private CheckBox checkKill;
	private CheckBox checkPredation;
	private CheckBox checkDiapause;
	private Scene[] sceneList;
	private boolean clicked;
	private VBox blank = new VBox(4);

	private DatePicker initialInsectDatePicker = new DatePicker();
	private ComboBox<String> stageComboBox = new ComboBox<>();
	private ComboBox<String> modelSections = new ComboBox<>();

	private TextField textDaysToSim, textTimeStep, textPrintInterval, textNumInstar, textNumFemale, textMaleProportion, textLatitude;
	private TextField textOWSuccess, textPercentKill, textOWCurrent, textOWDiaCritTempI, textOWDiaDayHoursI, textOWDiaCritTempT, textOWDiaDayHoursT, textDiaCritTemp, textDiaCritHours, textDiaHalf, textDiaRate;
	
	private CheckBox checkIncludePupa;
	
	private int stageWidth = 770;
	private int stageHight = 440;
	private TextField textPupa, textPupaDevA, textPupaDevTL, textPupaDevTU, textPupaMortMin, textPupaMortMax, textPupaMortMinTemp, textPupaMortMaxTemp;

	private ToggleGroup group;
	private TextField textCustomTemp;
	private TextField textConstantTemp;
	
	//model data field
	private String insectName = "";	
	private String runName = "";
	private int numInstar;
	private int hasPupalStage;
	private int numFemale;
	private double maleProportion;
	private double daysToSim;
	private double timeStep;
	private double printInterval;
	private double latitude;
	private String addInsectDate;
	private double initailEgg;
	private List<String> initalInstari = new ArrayList<>();
	private double initalPupa;
	private double initailMale;
	private List<String> initalFemalei = new ArrayList<>();
	private double eggDevA;
	private double eggDevTL;
	private double eggDevTU;
	private List<String> instarDevA = new ArrayList<>();
	private List<String> instarDevTL = new ArrayList<>();
	private List<String> instarDevTU = new ArrayList<>();
	private double pupaDevA;
	private double pupaDevTL;
	private double pupaDevTU;
	private double maleDevA;
	private double maleDevTL;
	private double maleDevTU;
	private List<String> femaleDevA = new ArrayList<>();
	private List<String> femaleDevTL = new ArrayList<>();
	private List<String> femaleDevTU = new ArrayList<>();
	private double eggMortMin;
	private double eggMortMax;
	private double eggMortMinTemp;
	private double eggMortMaxTemp;
	private List<String> instarMortMin = new ArrayList<>();
	private List<String> instarMortMax = new ArrayList<>();
	private List<String> instarMortMinTemp = new ArrayList<>();
	private List<String> instarMortMaxTemp = new ArrayList<>();
	private double pupaMortMin;
	private double pupaMortMax;
	private double pupaMortMinTemp;
	private double pupaMortMaxTemp;
	private List<String> femaleMortMin = new ArrayList<>();
	private List<String> femaleMortMax = new ArrayList<>();
	private List<String> femaleMortMinTemp = new ArrayList<>();
	private List<String> femaleMortMaxTemp = new ArrayList<>();
	private double maleMortMin;
	private double maleMortMax;
	private double maleMortMinTemp;
	private double maleMortMaxTemp;
	private List<String> femaleEgg = new ArrayList<>();
	private double fecundityMax;
	private double fecundityTmin;
	private double fecundityTmax;
	private String fecMax;
	private String fecTmin;
	private String fecTmax;
	private int ignoreOverwintering;
	private int ignoreMortTemp;
	private int ignoreKill;
	private int ignorePredation;
	private int ignoreDiapause;
	private int selectionPosition;
	private String OWSuccess;
	private String OWCurrent;
	private int numKillDates;
	private List<String> killDate = new ArrayList<>();
	private List<String> percentKill = new ArrayList<>();
	private List<String> selectionKillStage = new ArrayList<>();
	private String OWCritTempI;
	private String OWDayHoursI;
	private String OWCritTempT;
	private String OWDayHoursT;
	private String eggPredation;
	private List<String> instariPredation = new ArrayList<>();
	private String pupaPredation;
	private String malePredation;
	private List<String> femaleiPredation = new ArrayList<>();
	private String diaCritTemp;
	private String diaCritHours;
	private String diaHalf;
	private String diaRate;
		
	private final List<VaryingTextField> initalInstar = new ArrayList<>();
	private final List<VaryingTextField> initalFemale = new ArrayList<>();
	private final List<VaryingTextField> devAInstar = new ArrayList<>();
	private final List<VaryingTextField> devTLInstar = new ArrayList<>();
	private final List<VaryingTextField> devTUInstar = new ArrayList<>();
	private final List<VaryingTextField> devAFemale = new ArrayList<>();
	private final List<VaryingTextField> devTLFemale = new ArrayList<>();
	private final List<VaryingTextField> devTUFemale = new ArrayList<>();
	private final List<VaryingTextField> mortMinInstar = new ArrayList<>();
	private final List<VaryingTextField> mortMaxInstar = new ArrayList<>();
	private final List<VaryingTextField> mortMinTempInstar = new ArrayList<>();
	private final List<VaryingTextField> mortMaxTempInstar = new ArrayList<>();
	private final List<VaryingTextField> mortMinFemale = new ArrayList<>();
	private final List<VaryingTextField> mortMaxFemale = new ArrayList<>();
	private final List<VaryingTextField> mortMinTempFemale = new ArrayList<>();
	private final List<VaryingTextField> mortMaxTempFemale = new ArrayList<>();
	private final List<VaryingTextField> eggFemale = new ArrayList<>();
	private final List<VaryingTextField> predationInstar = new ArrayList<>();
	private final List<VaryingTextField> predationFemale = new ArrayList<>();;
	
	private Text textInitial;
	private TextField textInitalEgg, textInitalMale, textEggDevA, textEggDevTL, textEggDevTU, textMaleDevA, textMaleDevTL, textMaleDevTU;
	private Button button3b = new Button("Fit Data");
	private Button button3DataJ = new Button("Input Data");
	private Button button3DataA = new Button("Input Data");
	private TextField textEggMortMin, textEggMortMax, textEggMortMinTemp, textEggMortMaxTemp, textMaleMortMin, textMaleMortMax, textMaleMortMinTemp, textMaleMortMaxTemp;
	private Button button4Data = new Button("Input Data");
	private Button button4b = new Button("Fit Data");
	private TextField eggT, pupaT, maleT;
	private Button button5aData = new Button("Input Data");
	private Text tempFileText5a;
	private TextField textFecundityMax, textFecundityTmin, textFecundityTmax;
	private Button button5bData = new Button("Input Data");
	private Button button5b = new Button("Fit Data");
	private Text tempFileText5b;

	private boolean next1 = false;
	private boolean next2Date = false;
	private boolean next2Egg = false;
	private boolean next2Instar = false;
	private boolean next2Pupa = false;
	private boolean next2Female = false;
	private boolean next2Male = false;
	private boolean next2 = false;
	private boolean next3bEgg = false;
	private boolean next3bInstar = true;
	private boolean next3bPupa = false;
	private boolean next3bMale = false;
	private boolean next3bFemale = false;
	private boolean next3b = false;
	private boolean next4Egg = false;
	private boolean next4Instar = false;
	private boolean next4Pupa = false;
	private boolean next4Female = false;
	private boolean next4Male = false;
	private boolean next4 = false;
	private boolean next5Egg = false;
	private boolean next5Fec = false;
	private boolean next5 = false;
	private boolean nextSub = false;
	private boolean nextOverwintering = false;
	private boolean nextKill = false;
	private boolean nextPredation = false;
	private boolean nextPredationOther = false;
	private boolean nextPredationPupa = false;
	private boolean nextPredationInstar = false;
	private boolean nextPredationFemale = false;
	private boolean nextDiapause = false;


	//lists used when reading in the dev params from the file output by the fitting algorithm
	private List<String> devAs = new ArrayList<>();
	private List<String> devTLs = new ArrayList<>();
	private List<String> devTUs = new ArrayList<>();
	
	private List<String> mortMin = new ArrayList<>();
	private List<String> mortMax = new ArrayList<>();
	private List<String> minT = new ArrayList<>();
	private List<String> maxT = new ArrayList<>();
	
	private ComboBox<String> killStageComboBox = new ComboBox<>();
	private DatePicker killDatePicker = new DatePicker();
	
	private int numSelected; //tracks the number of sub-models that will be used
	private boolean skipSelected;
	
	private Scene graphScene;
	private VBox layoutLineGraph = new VBox();
	private VBox layoutBarGraph = new VBox();
	private VBox layoutBarNormalGraph = new VBox();
	
	private Text textNoteSpecies;
	private Text textNoteStages;
	private Text textNoteDays;
	
	private Text errorMessage;
	
	private Text tempFileText;
	private File selectedFile;
	
	//setup border pane bottom buttons
	private BorderPane borderPane1 = new BorderPane();
	private BorderPane borderPane2 = new BorderPane();
	private BorderPane borderPane3a = new BorderPane();
	private BorderPane borderPane4 = new BorderPane();
	private BorderPane borderPane5 = new BorderPane();
	private BorderPane borderPane6 = new BorderPane();
	private BorderPane borderPane7 = new BorderPane();
	private BorderPane borderPane8 = new BorderPane();
	private BorderPane borderPane9 = new BorderPane();
	private BorderPane borderPane10 = new BorderPane();
	private BorderPane borderPane11 = new BorderPane();
	private BorderPane borderPane12 = new BorderPane();
	private BorderPane borderPane13 = new BorderPane();
	private BorderPane borderPaneSub = new BorderPane();
	private BorderPane borderPaneSim = new BorderPane();
	private BorderPane borderPaneOut = new BorderPane();
	private HBox bPContent1 = new HBox(20);
	private HBox bPContent2 = new HBox(20);
	private HBox bPContent3a = new HBox(20);
	private HBox bPContent4 = new HBox(20);
	private HBox bPContent5 = new HBox(20);
	private HBox bPContent6 = new HBox(20);
	private HBox bPContent7 = new HBox(20);
	private HBox bPContent8 = new HBox(20);
	private HBox bPContent9 = new HBox(20);
	private HBox bPContent10 = new HBox(20);
	private HBox bPContent11 = new HBox(20);
	private HBox bPContent12 = new HBox(20);
	private HBox bPContent13 = new HBox(20);
	private HBox bPContentSub = new HBox(20);
	private HBox bPContentSim = new HBox(20);
	private HBox bPContentOut = new HBox(20);
	
	private double windowPosX;
	private double windowPosY;
	
	private int modify = 0;
	private Stage newModWindow;
	private int modifySelection;
	private String outputSimFileName;
	
	private Stage newCompWindow;
	
	private ProgressBar progress;
	private ProgressIndicator indicator;
	
	private String fitFilePath = "";

	private Button buttonSelectedSubModels = new Button("Next");
	private Button buttonModNumStages = new Button("Next");
	
	private DefaultInsects defaultInsects = new DefaultInsects();
	
	
	public void start(Stage primaryStage) {
    	primaryStage.setTitle("Generalized Insect Model");
    	
    	// ------------- HOME PAGE ------------- 
		BorderPane homeBorderPane = new BorderPane();
		
		Text homePagetitle = new Text("Welcome to the Generalized Insect Population Dynamics Model");
		homePagetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		Label homePageLabel = new Label("This computer model is intended for exploration and experimentation of \"what-if\" insect\n population scenarios. Simulated insect population dynamics are based on life cycle\n parameters and daily temperature data input by the user. This model can be used to\n explore a variety of insect species.");
		VBox homeTitleBox = new VBox(20);
		homeTitleBox.setPadding(new Insets(25, 25, 0, 25)); 
		homeTitleBox.getChildren().addAll(homePagetitle);
		homeTitleBox.getChildren().addAll(homePageLabel);
		homeBorderPane.setTop(homeTitleBox);
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(20);
		grid.setPadding(new Insets(-50, 25, 25, 25));  

		Label speciesSelectorLabel = new Label("\nSelect a default insect to simulate or input the name of the custom species.");
		grid.add(speciesSelectorLabel, 0, 0, 2, 1);

		//default option
		Label defaultInsect = new Label("Use default insect:");
		grid.add(defaultInsect, 0, 1);

		ComboBox<String> comboBox = new ComboBox<>();
		for(int i = 0; i < defaultInsects.length(); i++){
			comboBox.getItems().add(defaultInsects.at(i));
		}
		comboBox.getItems().add(null);
		HBox infoDefaultInsect = infoCoB(comboBox, "By selecting a default insect, all species specific insect\n parameter fields will automatically be filled. This data\n was obtained through numerous published sources", primaryStage);
		grid.add(infoDefaultInsect, 1, 1);
		
		//custom option
		Label customInsect = new Label("Use custom insect:");
		grid.add(customInsect, 0, 2);
		
		TextField insectTextField = new TextField();
		HBox infoCustomInsect = infoTF(insectTextField, "Costume species name", primaryStage);
		grid.add(infoCustomInsect, 1, 2);
		
		homeBorderPane.setCenter(grid);
		
		//set all scenes' grid layout
		setGrid(gridP2);
		setGrid(gridP3a);
		setGrid(gridP3b);
		setGrid(gridP4);
		setGrid(gridP4b);
		setGrid(gridP5);
		setGrid(gridP5b);
		setGrid(gridP6);
		setGrid(gridP7);
		setGrid(gridP8);
		setGrid(gridP9);
		setGrid(gridP10);
		setGrid(gridP11);
		setGrid(gridP11in);
		setGrid(gridP12);
		setGrid(gridP12in);
		setGrid(gridP13);
		setGrid(gridPSim);
		setGrid(gridPOut);
		
        //"Next" button
        Button homeNextButton = new Button("Next");
        homeNextButton.disableProperty().bind( comboBox.valueProperty().isNull() .and(insectTextField.textProperty().isEmpty()) );
        homeNextButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	
            	String inName;
            	if(!insectTextField.getText().trim().isEmpty())
            		inName = insectTextField.getText();
            	else
            		inName = (String) comboBox.getValue();
            	
            	//If insect name has a space change it to a underscore
            	insectName = "";
            	for(int i = 0; i < inName.length(); i++){
            		if(inName.charAt(i) == ' '){
            			insectName += "_";
            		}
            		else{
            			insectName += inName.charAt(i);
            		}
            	}
            	            	
            	//set the run's name and create a folder to store run files
            	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH.mm.ss");
				runName = insectName + "_" + java.time.LocalDate.now() + "_" + dtf.format(java.time.LocalTime.now());
				//create a new run folder
				File path = new File(runName);
				if(!path.mkdir()){
					System.out.println("An error has occurred while creating a new folder.");  
				} 
            	
            	//Write to file
            	writeToFile("Insect Species -- " + insectName);
            	writeToFile("");
            	
            	for(int i = 0; i < defaultInsects.length(); i++){
            		//set species values if default is selected
            		if(insectName.equals(defaultInsects.at(i))){
            		
            			String stage = defaultInsects.getStage(i);
            			String [] stageSplit = stage.split(",");

						textNumInstar.setText(stageSplit[0]);
						if(stageSplit[1].equals("1"))
							checkIncludePupa.setSelected(true);
						else
							checkIncludePupa.setSelected(false);

						textNumFemale.setText(stageSplit[2]);
					}
            	}
            	
                primaryStage.setScene(scene1);
            }
        } );  
        
        HBox homeButtonBox = new HBox(20);
        homeButtonBox.setAlignment(Pos.CENTER_RIGHT);
		homeButtonBox.setPadding(new Insets(0, 30, 10, 25));
		homeButtonBox.getChildren().addAll(homeNextButton);
		homeBorderPane.setBottom(homeButtonBox);
		

		homeScene = new Scene(homeBorderPane, stageWidth, stageHight);	
		
		
		// ------------- SCENE 1 ------------- 
		layout1.setPadding(new Insets(15, 5, 5, 25));
		//SIMULATION
		Text textSimulation = new Text("Simulation Values: ");
		textSimulation.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));

		//daysToSim
		Label lableDaysToSim = new Label("Days to simulate");
		textDaysToSim = new TextField("365");
		HBox infoDaysToSim = infoTF(textDaysToSim, "days - double\nNumber of days for the simulation to run", primaryStage);
		//timesteps
		Label lableTimeStep = new Label("Time step size");
		textTimeStep = new TextField("0.05");
		HBox infoTimeStep = infoTF(textTimeStep, "days - double\nSize of deferential equations time steps used to calculation \nthe population", primaryStage);
		//printInterval
		Label lablePrintInterval = new Label("Print interval");
		textPrintInterval = new TextField("1");
		HBox infoPrintInterval = infoTF(textPrintInterval, "days - double\nInterval between population values being output to file", primaryStage);
		//latitude
		Label lableLatitude = new Label("Latitude");
		textLatitude = new TextField();
		HBox infoLatitude = infoTF(textLatitude, "degrees - double\nThe latitude of the region where the populations will be \nsimulated (influences day light length) (North and South \ninterpreted the same way)", primaryStage);
		
		GridPane gp1a = new GridPane();
		setGrid(gp1a);
		layout1.getChildren().addAll(textSimulation);
		gp1a.add(lableDaysToSim, 0, 0);
		gp1a.add(infoDaysToSim, 1, 0);
		gp1a.add(lableTimeStep, 2, 0);
		gp1a.add(infoTimeStep, 3, 0);
		gp1a.add(lablePrintInterval, 0, 1);
		gp1a.add(infoPrintInterval, 1, 1);
		gp1a.add(lableLatitude, 2, 1);
		gp1a.add(infoLatitude, 3, 1);
		layout1.getChildren().addAll(gp1a);
		
		//SPECIES
		Text textSpecies = new Text("Insect Species Values: ");
		textSpecies.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		//numInstar
		Label lableNumInstar = new Label("Numer of instar stages");
		textNumInstar = new TextField();
		HBox infoNumInstar = infoTF(textNumInstar, "individuals - integer\nThe number of instar stages for the species", primaryStage);
		//includePupa
		Label lableIncludePupe = new Label("Has a pupal stage");
		checkIncludePupa = new CheckBox("");
		HBox infoIncludePupe = infoCB(checkIncludePupa, "dimensionless - boolean\nWhether the species has a pupal stage", primaryStage);
		//numFemale
		Label lableNumFemale = new Label("Numer of female stages");
		textNumFemale = new TextField();
		HBox infoNumFemale = infoTF(textNumFemale, "individuals - integer\nNumber of sub-stages that the adult stage should be split in \nbased on differences in egg viability", primaryStage);
		//maleProportion
		Label lableMaleProportion = new Label("Ratio of males to females");
		textMaleProportion = new TextField("0.5");
		HBox infoMaleProportion = infoTF(textMaleProportion, "male individuals/total individuals - double\nProportion of the total eggs laid that are male ", primaryStage);
		
		GridPane gp1b = new GridPane();
		setGrid(gp1b);
		layout1.getChildren().addAll(textSpecies);
		gp1b.add(lableNumInstar, 0, 0);
		gp1b.add(infoNumInstar, 1, 0);
		gp1b.add(lableIncludePupe, 2, 0);
		gp1b.add(infoIncludePupe, 3, 0);
		gp1b.add(lableNumFemale, 0, 1);
		gp1b.add(infoNumFemale, 1, 1);
		gp1b.add(lableMaleProportion, 2, 1);
		gp1b.add(infoMaleProportion, 3, 1);
		layout1.getChildren().addAll(gp1b);


		//TEMPERATURE
		Text textTemperature = new Text("Temperature Data: ");
		textTemperature.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		//temperature data
		group = new ToggleGroup();
		RadioButton rb1 = new RadioButton("tempA_sin");
		rb1.setToggleGroup(group);
		RadioButton rb2 = new RadioButton("tempB_sin");
		rb2.setToggleGroup(group);
		RadioButton rb3 = new RadioButton("tempC_sin");
		rb3.setToggleGroup(group);
		RadioButton rb4 = new RadioButton("tempD_sin");
		rb4.setToggleGroup(group);
		RadioButton rb5 = new RadioButton("custom temperature file");
		rb5.setToggleGroup(group);
		tempFileText = new Text("");
		FileChooser fileChooser = new FileChooser();
        Button fileButton = new Button("Select File");
        fileButton.setOnAction(e -> {
            selectedFile = fileChooser.showOpenDialog(primaryStage);
            String name = selectedFile.getName();
            String path = selectedFile.getAbsolutePath();
			tempFileText.setText(path);
        });
        fileButton.disableProperty().bind( rb5.selectedProperty().not() );
		RadioButton rb6 = new RadioButton("constant temperature");
		rb6.setToggleGroup(group);
		textConstantTemp = new TextField();
		textConstantTemp.disableProperty().bind( rb6.selectedProperty().not() );
		
		GridPane gp1c = new GridPane();
		setGrid(gp1c);
		
		HBox tempInfo = infoT(textTemperature, "Here's some info about temperature and how it should be used...", primaryStage);
		layout1.getChildren().addAll(tempInfo);
		gp1c.add(rb1, 0, 0);
		gp1c.add(rb2, 0, 1);
		gp1c.add(rb3, 0, 2);
		gp1c.add(rb4, 0, 3);
		gp1c.add(rb5, 1, 0);
		FlowPane hb1c = new FlowPane();
		hb1c.getChildren().addAll(fileButton, tempFileText);
		gp1c.add(hb1c, 1, 1);
		gp1c.add(rb6, 1, 2);
		gp1c.add(textConstantTemp, 1, 3);
		layout1.getChildren().addAll(gp1c);

		//"Next" button
		Button button1 = new Button("Next");
		button1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
				//INPUT VALIDATION
				next1 = false;
				
				String lab = "label";
				String val = "value";
				String message = "error witch " + lab + ". Value: " + val + " invalid. Ensure to use the correct type.";
				errorMessage = new Text(message);
				errorMessage.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
				
				boolean inputValidation = checkForDouble(textDaysToSim) && checkForDouble(textTimeStep)
					&& checkForDouble(textPrintInterval) && checkForInt(textNumInstar)
					&& checkForInt(textNumFemale) && checkForRate(textMaleProportion)
					&& checkForDouble(textLatitude);
				
				//check that text fields are filled
				if(!textDaysToSim.getText().trim().isEmpty() && !textTimeStep.getText().trim().isEmpty() && 
				!textPrintInterval.getText().trim().isEmpty() && !textNumInstar.getText().trim().isEmpty() && 
				!textNumFemale.getText().trim().isEmpty() && !textLatitude.getText().trim().isEmpty() &&
				!textMaleProportion.getText().trim().isEmpty() ){
					//check that a temperature radio button is selected and appropriate fields are filled if required
					if(group.getSelectedToggle() != null){
						if(group.getSelectedToggle() == rb5){
							if(!tempFileText.getText().trim().isEmpty())
								next1 = true;
						}
						else if(group.getSelectedToggle() == rb6){
							if(!textConstantTemp.getText().trim().isEmpty())
								next1 = true;
						}
						else{
							next1 = true;
						}
					}
				}
				
            	if(inputValidation && next1){   
            		scene1aData();
            		scene1bData();
            		scene1cData();

            		//setting up other scenes (based on first scene input)
            		setScenes(primaryStage);
					//setting the scene to the next page
            		primaryStage.setScene(scene2);

            	}
            	else{
            		openErrorWindow("Must fill all data fields");
            	}
            }
        });
        //action listener for all text fields
        textActionListener(textDaysToSim, textTimeStep);
        textActionListener(textTimeStep, textPrintInterval);
        textActionListener(textPrintInterval, textNumInstar);
        textActionListener(textNumInstar, checkIncludePupa);
        textActionListener(checkIncludePupa, textNumFemale);
        textActionListener(textNumFemale, textMaleProportion);
        textActionListener(textMaleProportion, textLatitude);
        textActionListener(textLatitude, rb1);
        textActionListener(rb1, button1);

        
        //back button
        Button backButton1 = new Button("Back");
		backButton1.setOnAction(e -> primaryStage.setScene(homeScene));
		setSceneLayout(borderPane1, layout1, bPContent1, backButton1, button1, "Progress1.png");
		//Scene
		scene1 = new Scene(borderPane1, stageWidth, stageHight);	
		
		
		// ------------- SCENE 2 ------------- 
		//INITIAL INSECT POPULATION
		textInitial = new Text("Initial Insect Population Values: ");
		textInitial.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
							
		Button button2 = new Button("Next");
		button2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	//check that all data fields have values
				next2 = false;
				next2Date = false;
				next2Egg = false;
				next2Instar = false;
				next2Pupa = false;
				next2Female = false;
				next2Male = false;
				if(initialInsectDatePicker.getValue() != null){
					next2Date = true;
				}
				if(!textInitalEgg.getText().trim().isEmpty()){
					next2Egg = true;
				}
				next2Instar = true;
				for(int i = 0; i < numInstar; i++){
					if(initalInstar.get(i).getVtfValue() != null && !initalInstar.get(i).getVtfValue().trim().isEmpty()){
						if(next2Instar)
							next2Instar = true;
						else
							next2Instar = false;
					}
					else{
						next2Instar = false;
						break;
					}
				}
				if(hasPupalStage == 1){
					if(!textPupa.getText().trim().isEmpty()){
						next2Pupa = true;
					}
				}
				else
					next2Pupa = true;
				next2Female = true;
				if(!textInitalMale.getText().trim().isEmpty()){
					next2Male = true;
				}
				for(int i = 0; i < numFemale; i++){
					if (initalFemale.get(i).getVtfValue() != null && !initalFemale.get(i).getVtfValue().trim().isEmpty()){
						if(next2Female)
							next2Female = true;
						else
							next2Female = false;
					}
					else{
						next2Female = false;
						break;
					}
				}
				
				if(next2Date && next2Egg && next2Instar && next2Pupa && next2Female && next2Male)
					next2 = true;
					
				//INPUT VALIDATION
				boolean inputValidation2 = true;
				if (!checkForDouble(textInitalEgg))
					inputValidation2 = false;
				for(int i = 0; i < numInstar; i++){
					String temp = initalInstar.get(i).getVtfValue();
					if(!temp.matches("\\d+(\\.\\d+)?")) //formating
						inputValidation2 = false;
				}
				if(hasPupalStage == 1){
					if (!checkForDouble(textPupa))
						inputValidation2 = false;
				}
				if(!checkForDouble(textInitalMale))
					inputValidation2 = false;
				for(int i = 0; i < numFemale; i++){
					String temp = initalFemale.get(i).getVtfValue();
					if(!temp.matches("\\d+(\\.\\d+)?"))
						inputValidation2 = false;
				}

					

				if (next2 && inputValidation2){
            		//COLLECTING DATA
            		scene2Data();

            		//setting the scene to the next page
            		primaryStage.setScene(scene3a);
            	}
            	else{
            		openErrorWindow("Must fill all data fields");
				}
            }
        });
        //back button
        Button backButton2 = new Button("Back");
		backButton2.setOnAction(e -> primaryStage.setScene(scene1));
		layout2.setPadding(new Insets(15, 5, 5, 25));
		HBox initInfo = infoT(textInitial, "individuals - integer\nThe number of individuals that will compos the population on \nthe \"start insect date\" (Note: if starting population is I diapause,\n do not include in these values. See overwintering submodel) ", primaryStage);
		layout2.getChildren().addAll(initInfo, gridP2);
		setSceneLayout(borderPane2, layout2, bPContent2, backButton2, button2, "Progress2.png");
		scene2 = new Scene(borderPane2, stageWidth, stageHight);
		
		
		// ------------- SCENE 3 ------------- 
		Text textFitTitle = new Text("Insect Development ");
		textFitTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		Label label3a = new Label("If you do not know the specie's temperature dependent development rate - constant temperature development time data can be fitted to obtain development rates. (must have a min of 6 data point at diff const temp.) ");
		
		button3b.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// New window (Stage)
				Stage newFittingDevWindow = new Stage();
				newFittingDevWindow.setTitle("Fitting Data");
				
				//add stuff for fit dev window
				Text textFitDataTitle = new Text("Constant temperature development data fitting");
				textFitDataTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
    			Label labelFit = new Label("Data file name (e.g. devRateDataAll.txt)");
        		
        		FileChooser fitFileChooser = new FileChooser();   
				Button fitFileButton = new Button("Select file");
				fitFileButton.setOnAction(e -> {		
					File selectedFitFile = fitFileChooser.showOpenDialog(newFittingDevWindow);
					fitFilePath = selectedFitFile.getAbsolutePath();
				});
        		
        		Text textFitMessage = new Text("File should be a comma separated data table, formated with constant temperatures \nin the left column and life stage on top row");
				textFitMessage.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
				
				Button buttonSaveFitData = new Button("Enter");		
				
				buttonSaveFitData.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
					
      					File tryFile = new File(fitFilePath);
      					boolean exists = tryFile.exists();
						
						if(exists){
							callFittingDev(fitFilePath);
										
							//read the file output by the program (devParams.txt)
							try{
								File file = new File("devParams.txt");
								Scanner fileIn = new Scanner(file);								
								devAs.clear();
								devTLs.clear();
								devTUs.clear();
								while(fileIn.hasNextLine()) {
									String line = fileIn.nextLine();
									String[] devVars = line.split(" ");
									devAs.add(devVars[0]);
									devTLs.add(devVars[1]);
									devTUs.add(devVars[2]);
								}
								
								//delete devParams.txt file
								if(!file.delete())
									System.out.println("file couldn't be deleted");
									
								fileIn.close();
							}
							catch(IOException e) {
								System.out.println("Cannot open file.");
							}	

							
							int pointerA = 0;
							int pointerTL = 0;
							int pointerTU = 0;
							//set the read values to the textfields in scene3a
							textEggDevA.setText(devAs.get(pointerA));
							textEggDevTL.setText(devTLs.get(pointerTL));
							textEggDevTU.setText(devTUs.get(pointerTU));
							pointerA ++;
							pointerTL ++;
							pointerTU ++;

							for(int i = 0; i < numInstar; i++){
								devAInstar.get(i).setVtfValue(devAs.get(pointerA));
								devTLInstar.get(i).setVtfValue(devTLs.get(pointerTL));
								devTUInstar.get(i).setVtfValue(devTUs.get(pointerTU));
								
								pointerA ++;
								pointerTL ++;
								pointerTU ++;
							}
							if(hasPupalStage == 1){
								textPupaDevA.setText(devAs.get(pointerA));
								textPupaDevTL.setText(devTLs.get(pointerTL));
								textPupaDevTU.setText(devTUs.get(pointerTU));
								
								pointerA ++;
								pointerTL ++;
								pointerTU ++;
							}
							textMaleDevA.setText(devAs.get(pointerA));
							textMaleDevTL.setText(devTLs.get(pointerTL));
							textMaleDevTU.setText(devTUs.get(pointerTU));
							pointerA ++;
							pointerTL ++;
							pointerTU ++;
							for(int i = 0; i < numFemale; i++){
								devAFemale.get(i).setVtfValue(devAs.get(pointerA));
								devTLFemale.get(i).setVtfValue(devTLs.get(pointerTL));
								devTUFemale.get(i).setVtfValue(devTUs.get(pointerTU));
								
								pointerA ++;
								pointerTL ++;
								pointerTU ++;
							}
							
							//close window
							newFittingDevWindow.close();
						}
						else{
							openErrorWindow("File doesn't exist");
						}
					}
				} );
				//can only fit data once
				gridP3b.add(textFitMessage, 0, 0);
				gridP3b.add(fitFileButton, 0, 1);
				gridP3b.add(buttonSaveFitData, 1, 2);

				layout3b.setPadding(new Insets(15, 5, 5, 25));
				layout3b.getChildren().addAll(textFitDataTitle, gridP3b);

				// Set scene
				scene3b = new Scene(layout3b, 500, 300);
				newFittingDevWindow.setScene(scene3b);

				// Set position of second window, related to primary window.
				newFittingDevWindow.setX(primaryStage.getX() + 200);
				newFittingDevWindow.setY(primaryStage.getY() + 100);

				newFittingDevWindow.show();
				
				newFittingDevWindow.setOnCloseRequest(e -> layout3b.getChildren().clear());
			}
		});
		
		FileChooser devDatFileChooserJ = new FileChooser();        		
        button3DataJ.setOnAction(e -> {
        	File selectedFile = devDatFileChooserJ.showOpenDialog(primaryStage);
			String devDataFileName = selectedFile.getName();
			String devDataFilePath = selectedFile.getAbsolutePath();
			getDevData(devDataFilePath);
        });

		Button button3a = new Button("Next");
		button3a.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				//check that all data fields have values
				next3b = false;
				next3bEgg = false;
				next3bInstar = false;
				next3bPupa = false;
				next3bMale = false;
				next3bFemale = false;
				if(!textEggDevA.getText().trim().isEmpty() && !textEggDevTL.getText().trim().isEmpty() && !textEggDevTU.getText().trim().isEmpty()){
						next3bEgg = true;
				}
				next3bInstar = true;
				for(int i = 0; i < numInstar; i++){
					if(devAInstar.get(i).getVtfValue() != null && devTLInstar.get(i).getVtfValue() != null && devTUInstar.get(i).getVtfValue() != null && 
						!devAInstar.get(i).getVtfValue().trim().isEmpty() && !devTLInstar.get(i).getVtfValue().trim().isEmpty() && !devTUInstar.get(i).getVtfValue().trim().isEmpty()){
						if (next3bInstar)
							next3bInstar = true;
						else 
							next3bInstar = false;
					}
					else{
						next3bInstar = false;
						break;
					}
				}
				if(hasPupalStage == 1){
					if(!textPupaDevA.getText().trim().isEmpty() && !textPupaDevTL.getText().trim().isEmpty() && !textPupaDevTU.getText().trim().isEmpty()){
						next3bPupa = true;
					}
				}
				else
					next3bPupa = true;
				if(!textMaleDevA.getText().trim().isEmpty() && !textMaleDevTL.getText().trim().isEmpty() && !textMaleDevTU.getText().trim().isEmpty()){
						next3bMale = true;
				}
				next3bFemale = true;
				for(int i = 0; i < numFemale; i++){
					if (devAFemale.get(i).getVtfValue() != null && devTLFemale.get(i).getVtfValue() != null  && devTUFemale.get(i).getVtfValue() != null &&
						!devAFemale.get(i).getVtfValue().trim().isEmpty() && !devTLFemale.get(i).getVtfValue().trim().isEmpty() && !devTUFemale.get(i).getVtfValue().trim().isEmpty() ){
						if(next3bFemale)
							next3bFemale = true;
						else
							next3bFemale = false;
					}
					else {
						next3bFemale = false;
						break;
					}
				}
				
				if(next3bEgg && next3bInstar && next3bPupa && next3bMale && next3bFemale)
					next3b = true;


				if (next3b){
					//COLLECTING DATA
					scene3Data();
					saveJuvDev();
					saveFemDev();

					primaryStage.setScene(scene4);
				}
				else{
					openErrorWindow("Must fill all data fields");
				}
			}
		} );
		
		//back button
        Button backButton3 = new Button("Back");
		backButton3.setOnAction(e -> primaryStage.setScene(scene2));
		layout3a.getChildren().addAll(textFitTitle, gridP3a);
		layout3a.setPadding(new Insets(15, 5, 5, 25));
		setSceneLayout(borderPane3a, layout3a, bPContent3a, backButton3, button3a, "Progress3.png");		
		scene3a = new Scene(borderPane3a, stageWidth, stageHight);	
		
		
		// ------------- SCENE 4 ------------- 
		Text text4 = new Text("Insect Mortality");
		text4.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		
		button4b.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// New window (Stage)
				Stage newFittingMortWindow = new Stage();
				newFittingMortWindow.setTitle("Fitting Data");
				
				//add stuff for fit mort window
				Text textFitDataTitle = new Text("Constant temperature mortality data fitting");
				textFitDataTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        		
        		FileChooser fitFileChooser = new FileChooser();   
				Button fitFileButton = new Button("Select file");
				fitFileButton.setOnAction(e -> {		
					File selectedFitFile = fitFileChooser.showOpenDialog(newFittingMortWindow);
					fitFilePath = selectedFitFile.getAbsolutePath();
				});
        		
        		Text textFitMessage = new Text("File should be a comma separated data table, formated with constant temperatures \nin the left column and life stage on top row");
				textFitMessage.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
				
				Button buttonSaveFitData = new Button("Enter");		
				
				buttonSaveFitData.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
					
      					File tryFile = new File(fitFilePath);
      					boolean exists = tryFile.exists();
						
						if(exists){
							callFittingMort(fitFilePath);
										
							//read the file output by the program (mortParams.txt)
							try{
								File file = new File("mortParams.txt");
								Scanner fileIn = new Scanner(file);								
								mortMin.clear();
								mortMax.clear();
								minT.clear();
								maxT.clear();
								while(fileIn.hasNextLine()) {
									String line = fileIn.nextLine();
									String[] devVars = line.split(" ");
									mortMin.add(devVars[0]);
									mortMax.add(devVars[1]);
									minT.add(devVars[2]);
									maxT.add(devVars[3]);
								}
								
								//delete mortParams.txt file
								if(!file.delete())
									System.out.println("file couldn't be deleted");
								
								fileIn.close();
							}
							catch(IOException e) {
								System.out.println("Cannot open file.");
							}							
							
							int pointerMinMort = 0;
							int pointerMaxMort = 0;
							int pointerMinT = 0;
							int pointerMaxT = 0;
							//set the read values to the textfields in scene4a
							textEggMortMin.setText(mortMin.get(pointerMinMort));
							textEggMortMax.setText(mortMax.get(pointerMaxMort));
							textEggMortMinTemp.setText(minT.get(pointerMinT));
							textEggMortMaxTemp.setText(maxT.get(pointerMaxT));
							pointerMinMort ++;
							pointerMaxMort ++;
							pointerMinT ++;
							pointerMaxT ++;

							for(int i = 0; i < numInstar; i++){
								mortMinInstar.get(i).setVtfValue(mortMin.get(pointerMinMort));
								mortMaxInstar.get(i).setVtfValue(mortMax.get(pointerMaxMort));
								mortMinTempInstar.get(i).setVtfValue(minT.get(pointerMinT));
								mortMaxTempInstar.get(i).setVtfValue(maxT.get(pointerMaxT));
								
								pointerMinMort ++;
								pointerMaxMort ++;
								pointerMinT ++;
								pointerMaxT ++;
							}
							if(hasPupalStage == 1){
								textPupaMortMin.setText(mortMin.get(pointerMinMort));
								textPupaMortMax.setText(mortMax.get(pointerMaxMort));
								textPupaMortMinTemp.setText(minT.get(pointerMinT));
								textPupaMortMaxTemp.setText(maxT.get(pointerMaxT));
								
								pointerMinMort ++;
								pointerMaxMort ++;
								pointerMinT ++;
								pointerMaxT ++;
							}
							textMaleMortMin.setText(mortMin.get(pointerMinMort));
							textMaleMortMax.setText(mortMax.get(pointerMaxMort));
							textMaleMortMinTemp.setText(minT.get(pointerMinT));
							textMaleMortMaxTemp.setText(maxT.get(pointerMaxT));
							pointerMinMort ++;
							pointerMinT ++;
							pointerMaxT ++;
							for(int i = 0; i < numFemale; i++){
								mortMinFemale.get(i).setVtfValue(mortMin.get(pointerMinMort));
								mortMaxFemale.get(i).setVtfValue(mortMax.get(pointerMaxMort));
								mortMinTempFemale.get(i).setVtfValue(minT.get(pointerMinT));
								mortMaxTempFemale.get(i).setVtfValue(maxT.get(pointerMaxT));
								
								pointerMinMort ++;
								pointerMaxMort ++;
								pointerMinT ++;
								pointerMaxT ++;
							}
							
							
							//close window
							newFittingMortWindow.close();
						}
						else{
							openErrorWindow("File doesn't exist");
						}
					}
				} );
				
				gridP4b.add(textFitMessage, 0, 0);
				gridP4b.add(fitFileButton, 0, 1);
				gridP4b.add(buttonSaveFitData, 1, 2);

				layout4b.setPadding(new Insets(15, 5, 5, 25));				
				layout4b.getChildren().addAll(textFitDataTitle, gridP4b);

				// Set scene
				scene4b = new Scene(layout4b, 500, 300);
				newFittingMortWindow.setScene(scene4b);

				// Set position of second window, related to primary window.
				newFittingMortWindow.setX(primaryStage.getX() + 200);
				newFittingMortWindow.setY(primaryStage.getY() + 100);

				newFittingMortWindow.show();
				
				newFittingMortWindow.setOnCloseRequest(e -> layout4b.getChildren().clear());
			}
		});
		
		FileChooser mortDatFileChooser = new FileChooser();        		
        button4Data.setOnAction(e -> {
        	File selectedFile = mortDatFileChooser.showOpenDialog(primaryStage);
			String mortDataFileName = selectedFile.getName();
			String mortDataFilePath = selectedFile.getAbsolutePath();
			getMortData(mortDataFilePath);
        });
		
		Button button4 = new Button("Next");
		button4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				//check that all data fields have values
				next4 = false;
				next4Egg = false;
				next4Instar = false;
				next4Pupa = false;
				next4Female = false;
				next4Male = false;
				if(!textEggMortMin.getText().trim().isEmpty() && !textEggMortMax.getText().trim().isEmpty() && !textEggMortMinTemp.getText().trim().isEmpty() && !textEggMortMaxTemp.getText().trim().isEmpty()){
						next4Egg = true;
				}
				next4Instar = true;
				for(int i = 0; i < numInstar; i++){
					if(mortMinInstar.get(i).getVtfValue() != null && mortMaxInstar.get(i).getVtfValue() != null && mortMinTempInstar.get(i).getVtfValue() != null && mortMaxTempInstar.get(i).getVtfValue() != null &&
						!mortMinInstar.get(i).getVtfValue().trim().isEmpty() && !mortMaxInstar.get(i).getVtfValue().trim().isEmpty() && !mortMinTempInstar.get(i).getVtfValue().trim().isEmpty() && !mortMaxTempInstar.get(i).getVtfValue().trim().isEmpty()){
						if (next4Instar)
							next4Instar = true;
						else 
							next4Instar = false;
					}
					else{
						next4Instar = false;
						break;
					}
				}
				if(hasPupalStage == 1){
					if(!textPupaMortMin.getText().trim().isEmpty() && !textPupaMortMax.getText().trim().isEmpty() && !textPupaMortMinTemp.getText().trim().isEmpty() && !textPupaMortMaxTemp.getText().trim().isEmpty()){
						next4Pupa = true;
					}
				}
				else
					next4Pupa = true;
				next4Female = true;
				for(int i = 0; i < numFemale; i++){
					if(mortMinFemale.get(i).getVtfValue() != null && mortMaxFemale.get(i).getVtfValue() != null && mortMinTempFemale.get(i).getVtfValue() != null && mortMaxTempFemale.get(i).getVtfValue() != null &&
						!mortMinFemale.get(i).getVtfValue().trim().isEmpty() && !mortMaxFemale.get(i).getVtfValue().trim().isEmpty() && !mortMinTempFemale.get(i).getVtfValue().trim().isEmpty() && !mortMaxTempFemale.get(i).getVtfValue().trim().isEmpty()){
						if(next4Female)
							next4Female = true;
						else
							next4Female = false;
					}
					else{
						next4Female = false;
						break;
					}
				}
				if(!textMaleMortMin.getText().trim().isEmpty() && !textMaleMortMax.getText().trim().isEmpty() && !textMaleMortMinTemp.getText().trim().isEmpty() && !textMaleMortMaxTemp.getText().trim().isEmpty()){
						next4Male = true;
				}
				
				if(next4Egg && next4Instar && next4Pupa && next4Female && next4Male)
					next4 = true;


				if (next4){
					//COLLECTING DATA
					scene4Data();
					saveMort();

					primaryStage.setScene(scene5);
				}
				else{
					openErrorWindow("Must fill all data fields");
				}
			}
		} );

		//back button
        Button backButton4 = new Button("Back");
		backButton4.setOnAction(e -> primaryStage.setScene(scene3a));
		layout4.getChildren().addAll(text4, gridP4);
		layout4.setPadding(new Insets(15, 5, 5, 25));
		setSceneLayout(borderPane4, layout4, bPContent4, backButton4, button4, "Progress4.png");		
		scene4 = new Scene(borderPane4, stageWidth, stageHight);		
    	
    	
    	// ------------- SCENE 5 ------------- 
    	Text text5 = new Text("Insect Egg Viability");
		text5.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		
		tempFileText5a = new Text("");
		FileChooser eggViaDataFileChooser = new FileChooser();        		
        button5aData.setOnAction(e -> {
        	File selectedFile = eggViaDataFileChooser.showOpenDialog(primaryStage);
			String eggViaDataFileName = selectedFile.getName();
			String eggViaDataFilePath = selectedFile.getAbsolutePath();
			tempFileText5a.setText(eggViaDataFilePath);
			getEggViaData(eggViaDataFilePath);
        });

        tempFileText5b = new Text("");
		FileChooser fecundityDataFileChooser = new FileChooser();        		
        button5bData.setOnAction(e -> {
        	File selectedFile = fecundityDataFileChooser.showOpenDialog(primaryStage);
			String fecundityDataFileName = selectedFile.getName();
			String fecundityDataFilePath = selectedFile.getAbsolutePath();
			tempFileText5b.setText(fecundityDataFilePath);
			getFecundityData(fecundityDataFilePath);
        });


        button5b.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// New window (Stage)
				Stage newFittingFecWindow = new Stage();
				newFittingFecWindow.setTitle("Fitting Data");
				
				//add stuff for fit fec window
				Text textFitDataTitle = new Text("Constant temperature fecundity data fitting");
				textFitDataTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
    			Label labelFit = new Label("Data file name (e.g. fecRateDataAll.txt)");
        		
        		FileChooser fitFileChooser = new FileChooser();   
				Button fitFileButton = new Button("Select file");
				fitFileButton.setOnAction(e -> {		
					File selectedFitFile = fitFileChooser.showOpenDialog(newFittingFecWindow);
					fitFilePath = selectedFitFile.getAbsolutePath();
				});
        		
        		Text textFitMessage = new Text("File should be a comma separated data table, formated with constant temperatures \nin the left column and life stage on top row");
				textFitMessage.setFont(Font.font("Tahoma", FontWeight.NORMAL, 10));
				
				Button buttonSaveFitData = new Button("Enter");		
				
				buttonSaveFitData.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
					
      					File tryFile = new File(fitFilePath);
      					boolean exists = tryFile.exists();
						
						if(exists){
							callFittingFec(fitFilePath);
										
							//read the file output by the program (fecParams.txt)
							try{
								File file = new File("fecParams.txt");
								Scanner fileIn = new Scanner(file);								
								while(fileIn.hasNextLine()) {
									String line = fileIn.nextLine();
									String[] devVars = line.split(" ");
									fecMax = devVars[0];
									fecTmin = devVars[1];
									fecTmax = devVars[2];
								}
								
								//delete fecParams.txt file
								if(!file.delete())
									System.out.println("file couldn't be deleted");
									
								fileIn.close();
							}
							catch(IOException e) {
								System.out.println("Cannot open file.");
							}	

							textFecundityMax.setText(fecMax);
							textFecundityTmin.setText(fecTmin);
							textFecundityTmax.setText(fecTmax);
							
							//close window
							newFittingFecWindow.close();
						}
						else{
							openErrorWindow("File doesn't exist");
						}
					}
				} );
				//can only fit data once
				gridP5b.add(textFitMessage, 0, 0);
				gridP5b.add(fitFileButton, 0, 1);
				gridP5b.add(buttonSaveFitData, 1, 2);

				layout5b.setPadding(new Insets(15, 5, 5, 25));
				layout5b.getChildren().addAll(textFitDataTitle, gridP5b);

				// Set scene
				scene5b = new Scene(layout5b, 500, 300);
				newFittingFecWindow.setScene(scene5b);

				// Set position of second window, related to primary window.
				newFittingFecWindow.setX(primaryStage.getX() + 200);
				newFittingFecWindow.setY(primaryStage.getY() + 100);

				newFittingFecWindow.show();
				
				newFittingFecWindow.setOnCloseRequest(e -> layout5b.getChildren().clear());
			}
		});

		
		Button button5 = new Button("Next");
		button5.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				next5 = false;
				next5Egg = false;
				next5Fec = false;
				//check egg viability input
				next5Egg = true;
				for(int i = 0; i < numFemale; i++){
					if(eggFemale.get(i).getVtfValue() != null && !eggFemale.get(i).getVtfValue().trim().isEmpty()){
						if(next5Egg)
							next5Egg = true;
						else
							next5Egg = false;
					}
					else{
						next5Egg = false;
						break;
					}
				}
				//check fecundity input
				if(!textFecundityMax.getText().trim().isEmpty() && !textFecundityTmin.getText().trim().isEmpty() && !textFecundityTmax.getText().trim().isEmpty() ){
						next5Fec = true;
				}				
				if(next5Egg && next5Fec)
					next5 = true;
				
				if (next5){
					//COLLECTING DATA
					scene5Data();
					saveEggVia();

					primaryStage.setScene(scene6);
				}
				else{
					openErrorWindow("Must fill all data fields");
				}
			}
		} );
		
		//back button
        Button backButton5 = new Button("Back");
		backButton5.setOnAction(e -> primaryStage.setScene(scene4));
		layout5.getChildren().addAll(text5, gridP5);
		layout5.setPadding(new Insets(15, 5, 5, 25));
		setSceneLayout(borderPane5, layout5, bPContent5, backButton5, button5, "Progress5.png");		
		scene5 = new Scene(borderPane5, stageWidth, stageHight);	
	
	
		// ------------- SCENE 6 -------------
		Text text6 = new Text("Sub-models");
		text6.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		//these values are all the negatives of ignore____ parameters
		Label description = new Label("Select all sub-models to include in the simulation");
		checkOverwintering = new CheckBox("Overwintering");
		checkMortTemp = new CheckBox("Ignore Mortality Temperature");
		checkKill = new CheckBox("Mortality Event");
		checkPredation = new CheckBox("Predation");
		checkDiapause = new CheckBox("Diapause");

		clicked = false;
		Button button6 = new Button("Next");
		button6.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				//COLLECTING DATA
				scene6Data();

				if (numSelected == 0 || (numSelected <= 1 && skipSelected))
					primaryStage.setScene(simulationScene);
				else
					primaryStage.setScene(sceneSubMod);
			}
		});
		gridP6.add(description, 0, 0);
		gridP6.add(checkOverwintering, 0, 1);
		gridP6.add(checkMortTemp, 0, 2);
		gridP6.add(checkKill, 0, 3);
		gridP6.add(checkPredation, 0, 4);
		gridP6.add(checkDiapause, 0, 5);

		layout6.getChildren().addAll(text6, gridP6);
		layout6.setPadding(new Insets(15, 5, 5, 25));
		//back button
        Button backButton6 = new Button("Back");
		backButton6.setOnAction(e -> primaryStage.setScene(scene5));
		setSceneLayout(borderPane6, layout6, bPContent6, backButton6, button6, "Progress6.png");		
		scene6 = new Scene(borderPane6, stageWidth, stageHight);


		//------------------ SUBMODELS GRIDS ------------------
		//overwintering
		Text title7 = new Text("Overwintering");
		title7.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		Label labelOWStage = new Label("Overwintering Stage");
		Label labelOWCurrent = new Label("Initial Overwintering Insects");
		Label labelOWSuccess = new Label("Overwintering Success");
		Label labelOWDiaCritTempI = new Label("Overwintering Induction Critical Temperature");
		Label labelOWDiaDayHoursI = new Label("Overwintering Induction Critical Daylight Hours");
		Label labelOWDiaCritTempT = new Label("Overwintering Termination Critical Temperature");
		Label labelOWDiaDayHoursT = new Label("Overwintering Termination Critical Daylight Hours");
		textOWCurrent = new TextField();
		textOWSuccess = new TextField();
		textOWDiaCritTempI = new TextField();
		textOWDiaDayHoursI = new TextField();
		textOWDiaCritTempT = new TextField();
		textOWDiaDayHoursT = new TextField();
		gridP7.add(title7, 0, 0);
		gridP7.add(labelOWStage, 0, 1);
		HBox infoOWStage = infoCoB(stageComboBox, "dimensionless - String\nThe life stage that is able to overwinter in this species", primaryStage);
		gridP7.add(infoOWStage, 1, 1);
		gridP7.add(labelOWCurrent, 0, 2);
		HBox infoOWCurrent = infoTF(textOWCurrent, "individuals - integer\nThe number of individuals that are overwintering on day 0 \nof the simulation. These insects will leave overwintering when \nconditions are favorable", primaryStage);
		gridP7.add(infoOWCurrent, 1, 2);		
		gridP7.add(labelOWSuccess, 0, 3);
		HBox infoOWSuccess = infoTF(textOWSuccess, "successful individuals/total individuals - double\nRate at which insects survive the overwintering process \n(Note: this is not dependent on winter temperatures)", primaryStage);
		gridP7.add(infoOWSuccess, 1, 3);
		gridP7.add(labelOWDiaCritTempI, 0, 4);
		HBox infoOWDiaCritTempI = infoTF(textOWDiaCritTempI, "degrees Celsius - double\nThe temperature at which insect enter overwintering", primaryStage);
		gridP7.add(infoOWDiaCritTempI, 1, 4);
		gridP7.add(labelOWDiaDayHoursI, 0, 5);
		HBox infoOWDiaDayHoursI = infoTF(textOWDiaDayHoursI, "hours - double\nThe number of daylight hours at which insects enter \noverwintering", primaryStage);
		gridP7.add(infoOWDiaDayHoursI, 1, 5);
		gridP7.add(labelOWDiaCritTempT, 0, 6);
		HBox infoOWDiaCritTempT = infoTF(textOWDiaCritTempT, "degrees Celsius - double\nThe temperature at which insect leaves overwintering", primaryStage);
		gridP7.add(infoOWDiaCritTempT, 1, 6);
		gridP7.add(labelOWDiaDayHoursT, 0, 7);
		HBox infoOWDiaDayHoursT = infoTF(textOWDiaDayHoursT, "hours - double\nThe number of daylight hours at which insects leaves \noverwintering", primaryStage);
		gridP7.add(infoOWDiaDayHoursT, 1, 7);
		//kill event
		Text title9 = new Text("Mortality Event");
		title9.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		Label lableKillData = new Label("Mortality Event Date");
		Label lableKillStage = new Label("Mortality Event Life Stage");
		Label lablePercentKill = new Label("Mortality Event percent Killed");
		textPercentKill = new TextField();
		gridP9.add(title9, 0, 0);
		gridP9.add(lableKillData, 0, 1);
		HBox infoKillData = infoDP(killDatePicker, "dd/mm/yyyy - Syring\nThe date when part of a life stage's population will be killed", primaryStage);
		gridP9.add(infoKillData, 1, 1);
		gridP9.add(lableKillStage, 0, 2);
		HBox infoKillStage = infoCoB(killStageComboBox, "dimensionless - String\nThe life stage that will lose part of its population ", primaryStage);
		gridP9.add(infoKillStage, 1, 2);
		gridP9.add(lablePercentKill, 0, 3);
		HBox infoPercentKill = infoTF(textPercentKill, "percentage - double\nThe percentage of the kill life stage population to die on \nthe kill event date", primaryStage);
		gridP9.add(infoPercentKill, 1, 3);
		//predation
		Text title11 = new Text("Mortality due to predation");
		title11.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		HBox infoPredation = infoT(title11, "individuals/day - double\nThe ratio of each life stage that will be lost each day", primaryStage);
		gridP11.add(infoPredation, 0, 0);
		gridP11.add(gridP11in, 0, 1);	//gridP11in done in scene setup
		//diapause
		textDiaCritTemp = new TextField();
		textDiaCritHours = new TextField();
		textDiaHalf = new TextField();
		textDiaRate = new TextField();
		Text title13 = new Text("Diapause");
		title13.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		Label diapauseCritTemp = new Label("Diapause Critical Temperature");
		HBox infoDiaCritTemp = infoTF(textDiaCritTemp, "degrees Celsius - double\nThe temperature at which insect enter and leave \nreproductive diapause", primaryStage);
		Label diapauseCritHours = new Label("Diapause Critical Daylight Hours");
		HBox infoDiaCritHours = infoTF(textDiaCritHours, "hours - double\nThe number of daylight hours at which insects enter \nand leave reproductive diapause", primaryStage);
		Label diapauseHalf = new Label("Daylight for Half in Diapause ");
		HBox infoDiaHalf = infoTF(textDiaHalf, "hours - double\nThe number of daylight hours for half of the female \npopulation to be in reproductive diapause", primaryStage);
		Label diapauseRate = new Label("Diapause Rate per Daylight Hours");
		HBox infoDiaRate = infoTF(textDiaRate, "individuals per day - double\nThe rate at which insects enter and leave diapause \nbase on daylight hours", primaryStage);
		gridP13.add(title13, 0, 0);
		gridP13.add(diapauseCritTemp, 0, 1);
		gridP13.add(infoDiaCritTemp, 1, 1);		
		gridP13.add(diapauseCritHours, 0, 2);		
		gridP13.add(infoDiaCritHours, 1, 2);		
		gridP13.add(diapauseHalf, 0, 3);		
		gridP13.add(infoDiaHalf, 1, 3);		
		gridP13.add(diapauseRate, 0, 4);		
		gridP13.add(infoDiaRate, 1, 4);		
		
		
		//---------------- SCENE SUB MODEL---------------------
		Text textSub = new Text("Sub Models");
		textSub.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		Button buttonSub = new Button("Next");
		buttonSub.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
			
				nextSub = false;
				nextOverwintering = false;
				nextKill = false;
				nextPredation = false;
				nextPredationOther = false;
				nextPredationPupa = false;
				nextPredationInstar = false;
				nextPredationFemale = false;
				nextDiapause = false;
				
				if(ignoreOverwintering == 0){
					if(!textOWSuccess.getText().trim().isEmpty() && !textOWCurrent.getText().trim().isEmpty() &&
					!textOWDiaCritTempI.getText().trim().isEmpty() && !textOWDiaDayHoursI.getText().trim().isEmpty() &&
					!textOWDiaCritTempT.getText().trim().isEmpty() && !textOWDiaDayHoursT.getText().trim().isEmpty() &&
					stageComboBox.getValue() != null){
						nextOverwintering = true;
					}
				}
				else{
					nextOverwintering = true;
				}
				if(ignoreKill == 0){
					if(killDatePicker.getValue() != null && killStageComboBox.getValue() != null &&
					!textPercentKill.getText().trim().isEmpty()){
						nextKill = true;
					}
				}
				else{
					nextKill = true;
				}
				if(ignorePredation == 0){
					if(!eggT.getText().trim().isEmpty() && !maleT.getText().trim().isEmpty() ){
						nextPredationOther = true;
					}
					if(hasPupalStage == 1){
						if(!pupaT.getText().trim().isEmpty())
							nextPredationPupa = true;
					}
					else
						nextPredationPupa = true;
					nextPredationInstar = true;
					for(int i = 0; i < numInstar; i++){
						if(predationInstar.get(i).getVtfValue() != null && !predationInstar.get(i).getVtfValue().trim().isEmpty()){
							if(nextPredationInstar)
								nextPredationInstar = true;
							else
								nextPredationInstar = false;
						}
						else{
							nextPredationInstar = false;
							break;
						}
					}
					nextPredationFemale = true;
					for(int i = 0; i < numFemale; i++){
						if(predationFemale.get(i).getVtfValue() != null && !predationFemale.get(i).getVtfValue().trim().isEmpty()){
							if(nextPredationFemale)
								nextPredationFemale = true;
							else
								nextPredationFemale = false;
						}
						else{
							nextPredationFemale = false;
							break;
						}
					}
					
					if(nextPredationOther && nextPredationPupa && nextPredationInstar && nextPredationFemale)
						nextPredation = true;
						
				}
				else{
					nextPredation = true;
				}
				if(ignoreDiapause == 0){
					if(!textDiaCritTemp.getText().trim().isEmpty() && !textDiaCritHours.getText().trim().isEmpty() && 
						!textDiaHalf.getText().trim().isEmpty() && !textDiaRate.getText().trim().isEmpty() ){
						nextDiapause = true;	
					}
				}
				else{	
					nextDiapause = true;
				}		
				
				if(nextOverwintering && nextKill && nextPredation && nextDiapause)
					nextSub = true;
				
				if (nextSub){
					//COLLECTING DATA
					sceneSubData();
					
					primaryStage.setScene(simulationScene);
				}
				else{
					openErrorWindow("Must fill all data fields");
				}
	
			}			
		});
		Button backButtonSub = new Button("Back");
		backButtonSub.setOnAction(e -> primaryStage.setScene(scene6));
		layoutSub.getChildren().addAll(textSub);
		layoutSub.setPadding(new Insets(15, 5, 5, 25));

		setSceneLayout(borderPaneSub, layoutSub, bPContentSub, backButtonSub, buttonSub, "Progress7.png");		
		ScrollPane scrollPaneSub = new ScrollPane();
		scrollPaneSub.setContent(borderPaneSub);
		sceneSubMod = new Scene(scrollPaneSub, stageWidth, stageHight);

		
		// ------------- SIMULATION SCENE ------------- 
		Text textSim = new Text("Simulation");
		textSim.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		Label simLabel1 = new Label("All input steps have been completed. Click \"Simulate\" button to begin simulation. \nSimulation time will vary.");
		Label simLabel2 = new Label("\n\nInput data and model results can be found in a new folder labeled with the modelled species name and the \ncurrent data and time. This included \"configParams.txt\" which contains all input species specific parameters, all input \ndevelopment, mortality and egg viability data tables and the model results file. All files are labeled by run number.");
		Button buttonSim = new Button("Simulate");
		buttonSim.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				//save modified data before simulating
				if(modify >= 1){
					if(modifySelection == 0){
						//Simulation Value
						scene1aData();
					}
					else if(modifySelection == 1){
						//Insect Species Values
						scene1bData();
						scene2Data();
						scene3Data();
						saveJuvDev();
						saveFemDev();
						scene4Data();
						saveMort();
					}
					else if(modifySelection == 2){
						//Temperature Data
						scene1cData();
					}
					else if(modifySelection == 3){
						//Initial Population Values
						scene2Data();
					}
					else if(modifySelection == 4){
						//Insect Development
						scene3Data();
						saveJuvDev();
						saveFemDev();
					}
					else if(modifySelection == 5){
						//Insect Mortality
						scene4Data();
						saveMort();
					}
					else if(modifySelection == 6){
						//Egg Viability
						scene5Data();
						saveEggVia();
					}
					else if(modifySelection == 7){
						//Sub-Model
						sceneSubData();
						
					}

				}
		
				//error message appears on window if something went wrong with running the model
				Text error = new Text("An error occurred while running the model.");
				layoutSim.getChildren().addAll(error);
				
				writeAllToFile();

				//run the c++ general insect model with the data
				simulate(primaryStage);

				//set up graph
				graphLine(outputSimFileName, insectName + " population", "Time", "Stage probability", layoutLineGraph);
				graphBar(outputSimFileName, insectName + " population", "Time", "Total Population by Stage", layoutBarGraph);
				graphBarNormal(outputSimFileName, insectName + " population", "Population Stage Ratio", "Time", layoutBarNormalGraph);

				//set up output values:
				textNoteSpecies.setText("Species name: " + insectName);
    			String printStage = "Stages: Egg, " + numInstar + " Instars, ";
    			if (hasPupalStage == 1)
    				printStage += ("Pupae, ");
    			printStage += ("Male, " + numFemale + " Females");
    			textNoteStages.setText(printStage);
    			textNoteDays.setText("Simulated for " + daysToSim + " days");
				
				//when the data is being modified close the modification window
				if(modify >= 1)
					newModWindow.close();
				modify += 1;
				
				primaryStage.setScene(outputScene);
			}
		} );
		gridPSim.add(simLabel1, 0, 0);
		gridPSim.add(simLabel2, 0, 1);
		layoutSim.getChildren().addAll(textSim, gridPSim);

		
		//back button
        Button backButtonSim = new Button("Back");
        backButtonSim.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
			
				if (numSelected == 0 || (numSelected <= 1 && skipSelected))
					primaryStage.setScene(scene6);
				else
					primaryStage.setScene(sceneSubMod);

			}
		});
		layoutSim.getChildren().addAll(buttonSim, backButtonSim);
		layoutSim.setPadding(new Insets(15, 5, 5, 25));
		
		setSceneLayout(borderPaneSim, layoutSim, bPContentSim, backButtonSim, buttonSim, "Progress8.png");

		simulationScene = new Scene(borderPaneSim, stageWidth, stageHight);
    	
    	
    	
		// ------------- OUTPUT SCENE ------------- 
    	Text textRes = new Text("Results");
    	textRes.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
    	//next 3 texts set when the sim button is pressed
    	textNoteSpecies = new Text("");
    	textNoteStages = new Text("");
    	textNoteDays = new Text("");
    	Button saveDataButton = new Button("Save As");
    	
    	saveDataButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				//open browser window 
				FileChooser fileChooser = new FileChooser();
				
				//Set extension filter for text files
            	FileChooser.ExtensionFilter extFilterTxt = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            	FileChooser.ExtensionFilter extFilterCsv = new FileChooser.ExtensionFilter("CVS files (*.csv)", "*.csv");

            	fileChooser.getExtensionFilters().add(extFilterTxt);
            	fileChooser.getExtensionFilters().add(extFilterCsv);
 
            	//Show save file dialog
            	File file = fileChooser.showSaveDialog(primaryStage);
 
            	if (file != null) {
            	    saveDataFile(file);
            	}
            	
			}
		});


		Text textMod = new Text("Model Modification");
    	textMod.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));	
    	HBox textModInfo = infoT(textMod, "Modify elements of the model and simulated again.", primaryStage);
		
		modelSections.getItems().add("Simulation Value");
    	modelSections.getItems().add("Insect Species Values");
    	modelSections.getItems().add("Temperature Data");
    	modelSections.getItems().add("Initial Population Values");
    	modelSections.getItems().add("Insect Development");
    	modelSections.getItems().add("Insect Mortality");
    	modelSections.getItems().add("Egg Viability");
    	modelSections.getItems().add("Sub-Models");
		
		Button modifyButton = new Button("Modify");
    	modifyButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				VBox layoutModSub = new VBox(4);
				VBox layoutModStages = new VBox(4);

				modifySelection = modelSections.getSelectionModel().getSelectedIndex();
				
				Button cancelButton = new Button("Cancel");
				
				VBox secondaryLayout = new VBox();
				secondaryLayout.setPadding(new Insets(5, 5, 5, 5));  

				if(modifySelection == 0){
					//Simulation Value
					Label changeLableSimVal = new Label("Modify Simulation Values");
					secondaryLayout.getChildren().addAll(changeLableSimVal, gp1a);
				}
				else if(modifySelection == 1){
					//Insect Species Values
					Label changeLableSimVal = new Label("Modify Insect Species Values");
					secondaryLayout.getChildren().addAll(changeLableSimVal, gp1b);
					
					buttonModNumStages.setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							layoutModStages.setPadding(new Insets(15, 5, 5, 25));
							//collect what sub-models to use
							scene1bDataMod(layoutModStages, primaryStage);
							layoutModStages.getChildren().addAll(gridP2, gridP3a, gridP4);
							layoutModStages.getChildren().addAll(buttonSim, cancelButton);
							//display selected
							Scene modSubScene = new Scene(layoutModStages, stageWidth, stageHight);

							newModWindow.setScene(modSubScene);
						}
					});
					
				}
				else if(modifySelection == 2){
					//Temperature Data
					Label changeLableSimVal = new Label("Modify Temperature Data");
					secondaryLayout.getChildren().addAll(changeLableSimVal, gp1c);
				}
				else if(modifySelection == 3){
					//Initial Population Values
					Label changeLableSimVal = new Label("Modify Initial Population Values");
					secondaryLayout.getChildren().addAll(changeLableSimVal, gridP2);
				}
				else if(modifySelection == 4){
					//Insect Development
					Label changeLableSimVal = new Label("Modify Insect Development");
					secondaryLayout.getChildren().addAll(changeLableSimVal, gridP3a);
				}
				else if(modifySelection == 5){
					//Insect Mortality
					Label changeLableSimVal = new Label("Modify Insect Mortality");
					secondaryLayout.getChildren().addAll(changeLableSimVal, gridP4);
				}
				else if(modifySelection == 6){
					//Egg Viability
					Label changeLableSimVal = new Label("Modify Egg Viability");
					secondaryLayout.getChildren().addAll(changeLableSimVal, gridP5);
				}
				else if(modifySelection == 7){
					//Submodels
					Label changeLableSimVal = new Label("Modify Sub-Models");
					secondaryLayout.getChildren().addAll(changeLableSimVal, gridP6);

					buttonSelectedSubModels.setOnAction(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							layoutModSub.setPadding(new Insets(15, 5, 5, 25));
							//collect what sub-models to use
							scene6ModData(layoutModSub);
							layoutModSub.getChildren().addAll(buttonSim, cancelButton);
							//display selected
							Scene modSubScene = new Scene(layoutModSub, stageWidth, stageHight);

							newModWindow.setScene(modSubScene);
						}
					});
					
				}

				HBox modSimCancelBox = new HBox(20);
				modSimCancelBox.setPadding(new Insets(25, 25, 25, 25));
				modSimCancelBox.setAlignment(Pos.BOTTOM_RIGHT);
				if(modifySelection == 1){
					modSimCancelBox.getChildren().addAll(buttonModNumStages, cancelButton);
				}
				else if(modifySelection == 7){
					modSimCancelBox.getChildren().addAll(buttonSelectedSubModels, cancelButton);
				}
				else{
					modSimCancelBox.getChildren().addAll(buttonSim, cancelButton);
				}
				secondaryLayout.getChildren().addAll(modSimCancelBox);

				cancelButton.setOnAction(e -> newModWindow.close());
				//add scrolling 
				ScrollPane scrollPaneMod = new ScrollPane();
				scrollPaneMod.setContent(secondaryLayout);
				scrollPaneMod.setFitToWidth(true);
				Scene secondScene = new Scene(scrollPaneMod, stageWidth, stageHight);

				// New window (Stage)
				newModWindow = new Stage();
				newModWindow.setTitle("Modification");
				newModWindow.setScene(secondScene);

				// Set position of second window, related to primary window.
				newModWindow.setX(primaryStage.getX() + stageWidth);
				newModWindow.setY(primaryStage.getY() + stageHight);

				if(modifySelection == 0 || modifySelection == 1 || modifySelection == 2 || modifySelection == 3 || modifySelection == 4 || modifySelection == 5 || modifySelection == 6 || modifySelection == 7)
					newModWindow.show();
				
				newModWindow.setOnCloseRequest(e -> layoutModSub.getChildren().clear());

			}
		});
		
		Text textComp = new Text("Compare Model");
    	textComp.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		HBox textCompInfo = infoT(textComp, "Compare this simulation's results to another model result file.", primaryStage);
		
		Button compareButton = new Button("Compare To");
		compareButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				//open browser window 				
				FileChooser fileChooser = new FileChooser();
				
            	//Show save file dialog
            	File file = fileChooser.showOpenDialog(primaryStage);
            	String compareFilePath = file.getAbsolutePath();
            	String compareFileName = file.getName();

            	if (file != null) {
					VBox graph1 = new VBox(20);
					VBox graph2 = new VBox(20);
					graphLine(outputSimFileName, insectName + " population", "Time", "Stage probability", graph1);
					graphLine(compareFilePath, "compared " + compareFileName + " population", "Time", "Stage probability", graph2);

					HBox hBox = new HBox(20);
					hBox.getChildren().addAll(graph1, graph2);
					
					Button compareCancelButton = new Button("Cancel");
					compareCancelButton.setOnAction(e -> newCompWindow.close());
					HBox cancelButtonH = new HBox(20);
					cancelButtonH.setPadding(new Insets(15, 15, 15, 15));
					cancelButtonH.getChildren().addAll(compareCancelButton);
					BorderPane compareLayout = new BorderPane();
					compareLayout.setTop(hBox);
					compareLayout.setRight(cancelButtonH);
				
					Scene compScene = new Scene(compareLayout);
				
					// New window (Stage)
					newCompWindow = new Stage();
					newCompWindow.setTitle("Compare");
					newCompWindow.setScene(compScene);

					// Set position of second window, related to primary window.
					newCompWindow.setX(primaryStage.getX() + stageWidth);
					newCompWindow.setY(primaryStage.getY() + stageHight);

					newCompWindow.show();
					
					newCompWindow.setOnCloseRequest(e -> compareLayout.getChildren().clear());
				}
			}
		});
		
		
    	TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("Line Graph");
        tab1.setContent(layoutLineGraph);
        Tab tab2 = new Tab("Bar Graph"); 
        tab2.setContent(layoutBarGraph);
        Tab tab3 = new Tab("Normalized Graph");
        tab3.setContent(layoutBarNormalGraph);
        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);

        //Insets(top, right, bottom, left)
        VBox layoutNoteOut = new VBox(textRes, textNoteSpecies, textNoteStages, textNoteDays, saveDataButton);
        layoutNoteOut.setPadding(new Insets(0, 0, 20, 0));
        VBox layoutModOut = new VBox(textModInfo, modelSections, modifyButton);
        layoutModOut.setPadding(new Insets(0, 0, 20, 0));
        VBox layoutCompOut = new VBox(textCompInfo, compareButton);
        layoutCompOut.setPadding(new Insets(0, 0, 20, 0));
        VBox layoutLeftOut = new VBox(layoutNoteOut, layoutModOut, layoutCompOut);
        layoutLeftOut.setPadding(new Insets(0, 10, 0, 10));
        VBox layoutGraphOut = new VBox(tabPane);
        GridPane layoutOut = new GridPane();
        layoutOut.add(layoutLeftOut, 0, 0);
        layoutOut.add(layoutGraphOut, 1, 0);
        layoutOut.setPadding(new Insets(10, 10, 10, 10));

        outputScene = new Scene(layoutOut, stageWidth, stageHight);

        primaryStage.setScene(outputScene);
    	
    	
		// ------------- SETTING UP WINDOW ------------- 
		primaryStage.setScene(homeScene);
		primaryStage.show();
    }
    
    
//================================================================
//======================= FUNCTION SECTION =======================
//================================================================


//------------------------ SCENE SETUP -------------------------
	public void setScenes(Stage primaryStage){

		gridP2.getChildren().clear();
		gridP3a.getChildren().clear();
		gridP4.getChildren().clear();
		gridP5.getChildren().clear();
		gridP11in.getChildren().clear();
		gridP12in.getChildren().clear();

		// ------------- SETTING UP SCENE 2 ------------- 
		//set up the number of text fields in SCENE 2 (initial)
		int numStages = 1 + numInstar + hasPupalStage + 1 + numFemale +1;
		int length = (int) Math.ceil((numStages+1)/2);

		int pos = 1;
		//initial population
		Label labelInitalEgg = new Label("Initial eggs");
		gridP2.add(labelInitalEgg, ((pos-1)/length)*2, ((pos-1)%length));
		textInitalEgg = new TextField("0");
		gridP2.add(textInitalEgg, ((pos-1)/length)*2+1, ((pos-1)%length));
		pos++;
		initalInstar.clear();
		for(int i = 0; i < numInstar; i++){
			addInitialInstar(gridP2, Integer.toString(i+1), ((pos-1)/length), ((pos-1)%length));
			pos++;
		}
		if(hasPupalStage == 1){
			Label lablePupa = new Label("initial pupa");
			textPupa = new TextField("0");
			gridP2.add(lablePupa, ((pos-1)/length)*2, ((pos-1)%length));
			gridP2.add(textPupa, ((pos-1)/length)*2+1, ((pos-1)%length));
			pos++;
		}
		Label labelInitalMale = new Label("Initial males");
		gridP2.add(labelInitalMale, ((pos-1)/length)*2, ((pos-1)%length));
		textInitalMale = new TextField("0");
		gridP2.add(textInitalMale, ((pos-1)/length)*2+1, ((pos-1)%length));
		pos++;
		initalFemale.clear();
		for(int i = 0; i < numFemale; i++){
			addInitialFemale(gridP2, Integer.toString(i+1), ((pos-1)/length)*2, ((pos-1)%length));
			pos++;
		}
		//addInsectDate
		Label lableAddInsectDate = new Label("Add insect date");
		gridP2.add(lableAddInsectDate, ((pos-1)/length)*2, ((pos-1)%length));
		HBox infoInitialInsectDatePicker = infoDP(initialInsectDatePicker, "dd/mm/yyyy - String\nThe data when the initial insects will be first added to \nthe model population", primaryStage);
		gridP2.add(infoInitialInsectDatePicker, ((pos-1)/length)*2+1, ((pos-1)%length));

		// ------------- SETTING UP SCENE 3 ------------- 
		//set up the number of text fields in scene 3a (development)
		Label lableDevA = new Label("development a");
		lableDevA.setMinWidth(110);
		HBox infoDevA = infoL(lableDevA, "dimensionless - double\nDevelopment curve shape parameters that affects the maximum \ndevelopment rate (1/days) and the sharpness of the development \nrate curve (seen in Briere 1, development rate equation - \nsource) *?I should look at that for a better description?*", primaryStage);
		gridP3a.add(infoDevA, 0, 1);
		Label lableDevTL = new Label("development tmin");
		lableDevTL.setMinWidth(110);
		HBox infoDevTL = infoL(lableDevTL, "degrees Celsius - double\nThe minimum temperature below which the species development stops", primaryStage);
		gridP3a.add(infoDevTL, 0, 2);
		Label lableDevTU = new Label("development tmax");
		lableDevTU.setMinWidth(110);
		HBox infoDevTU = infoL(lableDevTU, "degrees Celsius - double\nThe maximum temperature above which the species development stops", primaryStage);
		gridP3a.add(infoDevTU, 0, 3);
			
		Label lableEggDev = new Label("eggs");
		gridP3a.add(lableEggDev, 1, 0);
		textEggDevA = new TextField();
		textEggDevA.setPrefWidth(50);
		gridP3a.add(textEggDevA, 1, 1);
		textEggDevTL = new TextField();
		textEggDevTL.setPrefWidth(50);
		gridP3a.add(textEggDevTL, 1, 2);
		textEggDevTU = new TextField();
		textEggDevTU.setPrefWidth(50);
		gridP3a.add(textEggDevTU, 1, 3);
		devAInstar.clear();
		devTLInstar.clear();
		devTUInstar.clear();
		for(int i = 0; i < numInstar; i++){
			for(int j = 0; j < 3; j++){
				addDevInstar(gridP3a, Integer.toString(i+1), j, i+2, j+1);
			}
		}
		int gripPos3a = 2 + numInstar;
		if(hasPupalStage == 1){
			Label lablePupaDev = new Label("pupae");
			gridP3a.add(lablePupaDev, gripPos3a, 0);
			textPupaDevA = new TextField();
			textPupaDevA.setPrefWidth(50);
			gridP3a.add(textPupaDevA, gripPos3a, 1);
			textPupaDevTL = new TextField();
			textPupaDevTL.setPrefWidth(50);
			gridP3a.add(textPupaDevTL, gripPos3a, 2);
			textPupaDevTU = new TextField();
			textPupaDevTU.setPrefWidth(50);
			gridP3a.add(textPupaDevTU, gripPos3a, 3);
			gripPos3a += 1;
		}

		Label lableMaleDev = new Label("male");
		gridP3a.add(lableMaleDev, gripPos3a, 0);
		textMaleDevA = new TextField();
		textMaleDevA.setPrefWidth(50);
		gridP3a.add(textMaleDevA, gripPos3a, 1);
		textMaleDevTL = new TextField();
		textMaleDevTL.setPrefWidth(50);
		gridP3a.add(textMaleDevTL, gripPos3a, 2);
		textMaleDevTU = new TextField();
		textMaleDevTU.setPrefWidth(50);
		gridP3a.add(textMaleDevTU, gripPos3a, 3);
		gripPos3a += 1;
			
		int positionFemale = 1 + 1 + numInstar + hasPupalStage + 1;
		for(int i = 0; i < numFemale; i++){
			for(int j = 0; j < 3; j++){
				addDevFemale(gridP3a, Integer.toString(i+1), j, i+gripPos3a, j+1);
			}
		}

		HBox infoButton3Data = infoB(button3DataJ, "Use \"Input Data\" to quickly fill data fields with data \nfrom file (must follow the same formating as seen above)", primaryStage);
		gridP3a.add(infoButton3Data, 0, 5);
		HBox infoButton3b = infoB(button3b, "When development rate at different constant temperatures \nis available, use \"Fit Data\" to fit your data to the model \ndevelopment curve equation.", primaryStage);
		gridP3a.add(infoButton3b, 0, 6);

		// ------------- SETTING UP SCENE 4 ------------- 
		//set up the number of text fields in scene 4 (mortality)
		Label lableMortMin = new Label("mortality min");
		lableMortMin.setMinWidth(110);
		HBox infoMortMin = infoL(lableMortMin, "individuals/day - double\n*** review this --- is it really still min mort????? (wrong in \nUI and param name)", primaryStage);
		gridP4.add(infoMortMin, 0, 1);
		Label lableMortMax = new Label("mortality max");
		lableMortMax.setMinWidth(110);
		HBox infoMortMax = infoL(lableMortMax, "individuals/day - double\n*** review this --- is it really still max mort????? (wrong in \nUI and param name)", primaryStage);
		gridP4.add(infoMortMax, 0, 2);
		Label lableMortMinTemp = new Label("mortality min temp");
		lableMortMinTemp.setMinWidth(110);
		HBox infoMortMinTemp = infoL(lableMortMinTemp, "degrees Celsius - double\nThe minimum temperature below which the species have a \nmortality rate of 1 (all die)", primaryStage);
		gridP4.add(infoMortMinTemp, 0, 3);
		Label lableMortMaxTemp = new Label("mortality max temp");
		lableMortMaxTemp.setMinWidth(110);
		HBox infoMortMaxTemp = infoL(lableMortMaxTemp, "degrees Celsius - double\nThe maximum temperature above which the species have a \nmortality rate of 1 (all die)", primaryStage);
		gridP4.add(infoMortMaxTemp, 0, 4);
		//egg mortality
		Label lableEggMort = new Label("eggs");
		gridP4.add(lableEggMort, 1, 0);
		textEggMortMin = new TextField();
		textEggMortMin.setPrefWidth(50);
		gridP4.add(textEggMortMin, 1, 1);
		textEggMortMax = new TextField();
		textEggMortMax.setPrefWidth(50);
		gridP4.add(textEggMortMax, 1, 2);
		textEggMortMinTemp = new TextField();
		textEggMortMinTemp.setPrefWidth(50);
		gridP4.add(textEggMortMinTemp, 1, 3);
		textEggMortMaxTemp = new TextField();
		textEggMortMaxTemp.setPrefWidth(50);
		gridP4.add(textEggMortMaxTemp, 1, 4);
		//instar mortality
		mortMinInstar.clear();
		mortMaxInstar.clear();
		mortMinTempInstar.clear();
		mortMaxTempInstar.clear();
		for(int i = 0; i < numInstar; i++){
			for(int j = 0; j < 4; j++){
				addMortInstar(gridP4, Integer.toString(i+1), j, i+2, j+1);
			}
		}
		int gripPos4 = 2 + numInstar;
		if(hasPupalStage == 1){
			Label lablePupaMort = new Label("pupa");
			gridP4.add(lablePupaMort, gripPos4, 0);
			textPupaMortMin = new TextField();
			textPupaMortMin.setPrefWidth(50);
			gridP4.add(textPupaMortMin, gripPos4, 1);
			textPupaMortMax = new TextField();
			textPupaMortMax.setPrefWidth(50);
			gridP4.add(textPupaMortMax, gripPos4, 2);
			textPupaMortMinTemp = new TextField();
			textPupaMortMinTemp.setPrefWidth(50);
			gridP4.add(textPupaMortMinTemp, gripPos4, 3);
			textPupaMortMaxTemp = new TextField();
			textPupaMortMaxTemp.setPrefWidth(50);
			gridP4.add(textPupaMortMaxTemp, gripPos4, 4);
			gripPos4 ++;
		}
		//male mortality
		Label lableMaleMortMax = new Label("male");
		gridP4.add(lableMaleMortMax, gripPos4, 0);
		textMaleMortMin = new TextField();
		textMaleMortMin.setPrefWidth(50);
		gridP4.add(textMaleMortMin, gripPos4, 1);
		textMaleMortMax = new TextField();
		textMaleMortMax.setPrefWidth(50);
		gridP4.add(textMaleMortMax, gripPos4, 2);
		textMaleMortMinTemp = new TextField();
		textMaleMortMinTemp.setPrefWidth(50);
		gridP4.add(textMaleMortMinTemp, gripPos4, 3);
		textMaleMortMaxTemp = new TextField();
		textMaleMortMaxTemp.setPrefWidth(50);
		gridP4.add(textMaleMortMaxTemp, gripPos4, 4);
		gripPos4 ++;
		mortMinFemale.clear();
		mortMaxFemale.clear();
		mortMinTempFemale.clear();
		mortMaxTempFemale.clear();
		//female mortality
		for(int i = 0; i < numFemale; i++){
			for(int j = 0; j < 4; j++){
				addMortFemale(gridP4, Integer.toString(i+1), j, gripPos4 + i, j+1);
			}
		}
		HBox infoButton4Data = infoB(button4Data, "Use \"Input Data\" to quickly fill data fields with data \nfrom file (must follow the same formating as seen above)", primaryStage);
		gridP4.add(infoButton4Data, 0, 5);
		HBox infoButton4b = infoB(button4b, "When mortality rate at different constant temperatures \nis available, use \"Fit Data\" to fit your data to the model \nmortality curve equation.", primaryStage);
		gridP4.add(infoButton4b, 0, 6);
		
		// ------------- SETTING UP SCENE 5 ------------- 
		//set up the number of text fields in scene 5 (egg viability)
		Label lableEggViability = new Label("egg viability");
		lableEggViability.setMinWidth(90);
		HBox infoEggViability = infoL(lableEggViability, "viable egg/total eggs - double\nRatio of eggs laid that are viable", primaryStage);
		gridP5.add(infoEggViability, 0, 1);
		eggFemale.clear();
		for(int i = 0; i < numFemale; i++){
			addEggFemale(gridP5, Integer.toString(i+1), i+1, 0);
		}
		HBox infoButton5aData = infoB(button5aData, "Use \"Input Data\" to quickly fill data fields with data \nfrom file (must follow the same formating as seen above)", primaryStage);
		gridP5.add(infoButton5aData, 0, 3);

		Label lableFecundityMax = new Label("fecundity max");
		lableFecundityMax.setMinWidth(90);
		Label lableFecundityTmin = new Label("fecundity tmin");
		lableFecundityTmin.setMinWidth(90);
		Label lableFecundityTmax = new Label("fecundity tmax");
		lableFecundityTmax.setMinWidth(90);
		HBox infoFecundityMax = infoL(lableFecundityMax, "eggs/day - double\nThe maximum number of eggs laid by a female in one day ", primaryStage);
		HBox infoFecundityTmin = infoL(lableFecundityTmin, "degrees Celsius - double\nThe minimum temperature below which insects are infertile", primaryStage);
		HBox infoFecundityTmax = infoL(lableFecundityTmax, "degrees Celsius - double\nThe maximum temperature above which insects are infertile", primaryStage);
		textFecundityMax = new TextField();
		textFecundityTmin = new TextField();
		textFecundityTmax = new TextField();
		gridP5.add(infoFecundityMax, 0, 4);
		gridP5.add(textFecundityMax, 1, 4);
		gridP5.add(infoFecundityTmin, 2, 4);
		gridP5.add(textFecundityTmin, 3, 4);
		gridP5.add(infoFecundityTmax, 4, 4);
		gridP5.add(textFecundityTmax, 5, 4);
		
		HBox infoButton5bData = infoB(button5bData, "Use \"Input Data\" to quickly fill data fields with data \nfrom file (must follow the same formating as seen above)", primaryStage);
		gridP5.add(infoButton5bData, 0, 5);
		HBox infoButton5b = infoB(button5b, "When fecundity rate at different constant temperatures \nis available, use \"Fit Data\" to fit your data to the model \nfecundity curve equation.", primaryStage);
		gridP5.add(infoButton5b, 0, 6);

		skipSelected = false;

		// ------------- SETTING UP SCENE 6 ------------- 
		//set up the number of text fields in scene 6 (overwintering)
		stageComboBox.getItems().add("Egg");
		killStageComboBox.getItems().add("Egg");
		for(int i = 0; i < numInstar; i++){
			stageComboBox.getItems().add("Instar " + (i+1));
			killStageComboBox.getItems().add("Instar " + (i+1));
		}
		if(hasPupalStage == 1){
			stageComboBox.getItems().add("Pupae");
			killStageComboBox.getItems().add("Pupae");
		}
		for(int i = 0; i < numFemale; i++){
			stageComboBox.getItems().add("Females" + (i+1));
		}
		killStageComboBox.getItems().add("Adult");
		
		// ------------- SETTING UP SCENE 11 ------------- 				
		int predationPos = 1;
		Label eggL = new Label("eggs predation mortality");
		eggT = new TextField("0");
		gridP11in.add(eggL, ((predationPos-1)/length)*2, ((predationPos-1)%length));
		gridP11in.add(eggT, ((predationPos-1)/length)*2+1, ((predationPos-1)%length));
		predationPos++;
		predationInstar.clear();
		for(int i = 0; i < numInstar; i++){
			addPredationInstar(gridP11in, Integer.toString(i+1), ((predationPos-1)/length)*2, ((predationPos-1)%length));
			predationPos++;
		}
		if(hasPupalStage == 1){
			Label pupaL = new Label("pupae predation mortality");
			pupaT = new TextField("0");
			gridP11in.add(pupaL, ((predationPos-1)/length)*2, ((predationPos-1)%length));
			gridP11in.add(pupaT, ((predationPos-1)/length)*2+1, ((predationPos-1)%length));
			predationPos++;
		}
		Label maleL = new Label("males predation mortality");
		maleT = new TextField("0");
		gridP11in.add(maleL, ((predationPos-1)/length)*2, ((predationPos-1)%length));
		gridP11in.add(maleT, ((predationPos-1)/length)*2+1, ((predationPos-1)%length));
		predationPos++;
		predationFemale.clear();
		for(int i = 0; i < numFemale; i++){
			addPredationFemale(gridP11in, Integer.toString(i+1), ((predationPos-1)/length)*2, ((predationPos-1)%length));
			predationPos++;
		}

		// ------------- SETTING UP FOR DEFAULT INSECT -------------
		for(int i = 0 ; i < defaultInsects.length(); i++){ 
			if(insectName.equals(defaultInsects.at(i))){
				//set dev
				getDevData("builtInData/" + defaultInsects.at(i) +"_dev.csv");
			
				//set mort
				getMortData("builtInData/" + defaultInsects.at(i) +"_mort.csv");
	
				//set egg via
				getEggViaData("builtInData/" + defaultInsects.at(i) +"_eggVia.csv");
			
				//set fecundity
				getFecundityData("builtInData/" + defaultInsects.at(i) +"_fecundity.csv");
			}
		}
		
	}
	
//------------------------ SCENE DATA COLLECTION ------------------------

	public void scene1aData(){
        //COLLECTING DATA
        //for days to sim textField
        daysToSim = getValueFromTextField(textDaysToSim);
        //for time step size textField
        timeStep = getValueFromTextField(textTimeStep);
        //for print interval textField
        printInterval = getValueFromTextField(textPrintInterval);
        //for latitude textField
		latitude = getValueFromTextField(textLatitude);
	}
	
	public void scene1bData(){
		//for instar textField
        numInstar = (int)getValueFromTextField(textNumInstar);
        //for pupa checkBox
        if (checkIncludePupa.isSelected())
			hasPupalStage = 1;	//save the value to the data field
		else
			hasPupalStage = 0;	//save the value to the data field
        //for female textField
        numFemale = (int)getValueFromTextField(textNumFemale);
        maleProportion = (double)getValueFromTextField(textMaleProportion);
	}
	
	public void scene1bDataMod(VBox layoutModStages, Stage primaryStage){
	
		//for instar textField
        numInstar = (int)getValueFromTextField(textNumInstar);
        //for pupa checkBox
        if (checkIncludePupa.isSelected())
			hasPupalStage = 1;	//save the value to the data field
		else
			hasPupalStage = 0;	//save the value to the data field
        //for female textField
        numFemale = (int)getValueFromTextField(textNumFemale);
        maleProportion = (double)getValueFromTextField(textMaleProportion);
        
        //modify the gridP2, gridP3a & gridP4
        setScenes(primaryStage);
        
	}
	
	public void scene1cData(){
		//for temperature data radioBox
	}

	public void scene2Data(){
		//for add insect data 
        addInsectDate = Integer.toString(initialInsectDatePicker.getValue().getDayOfYear());
        //for initial eggs
        initailEgg = getValueFromTextField(textInitalEgg);
        //for initial instar
        initalInstari.clear();
        for(int i = 0; i < numInstar; i++){
            initalInstari.add(initalInstar.get(i).getVtfValue());
		}
        //for initial pupa
		if(hasPupalStage == 1){
			initalPupa = getValueFromTextField(textPupa);
		}
		//for initial male
        initailMale = getValueFromTextField(textInitalMale);
        //for initial female
        initalFemalei.clear();
		for(int i = 0; i < numFemale; i++){
			initalFemalei.add(initalFemale.get(i).getVtfValue());
		}
	}
	
	public void scene3Data(){
		//for egg dev variables 
		eggDevA = getValueFromTextField(textEggDevA);
        eggDevTL = getValueFromTextField(textEggDevTL);
        eggDevTU = getValueFromTextField(textEggDevTU);
		//for instar dev variables
		instarDevA.clear();
		instarDevTL.clear();
		instarDevTU.clear();
        for(int i = 0; i < numInstar; i++){
        	instarDevA.add(devAInstar.get(i).getVtfValue());
        	instarDevTL.add(devTLInstar.get(i).getVtfValue());
			instarDevTU.add(devTUInstar.get(i).getVtfValue());
		}
		//for pupa dev variables
		if(hasPupalStage == 1){
			pupaDevA = getValueFromTextField(textPupaDevA);
        	pupaDevTL = getValueFromTextField(textPupaDevTL);
        	pupaDevTU = getValueFromTextField(textPupaDevTU);
		}
		//for male dev variables
		maleDevA = getValueFromTextField(textMaleDevA);
        maleDevTL = getValueFromTextField(textMaleDevTL);
        maleDevTU = getValueFromTextField(textMaleDevTU);
		//for female dev variables
		femaleDevA.clear();
		femaleDevTL.clear();
		femaleDevTU.clear();
		for(int i = 0; i < numFemale; i++){
			femaleDevA.add(devAFemale.get(i).getVtfValue());
			femaleDevTL.add(devTLFemale.get(i).getVtfValue());
			femaleDevTU.add(devTUFemale.get(i).getVtfValue());
		}
	}
	
	public void scene4Data(){
		//for egg mort variable 
		eggMortMin = getValueFromTextField(textEggMortMin);
		eggMortMax = getValueFromTextField(textEggMortMax);
        eggMortMinTemp = getValueFromTextField(textEggMortMinTemp);
        eggMortMaxTemp = getValueFromTextField(textEggMortMaxTemp);
		//for instar mort variables
		instarMortMin.clear();
		instarMortMax.clear();
		instarMortMinTemp.clear();
		instarMortMaxTemp.clear();
        for(int i = 0; i < numInstar; i++){
        	instarMortMin.add(mortMinInstar.get(i).getVtfValue());
        	instarMortMax.add(mortMaxInstar.get(i).getVtfValue());
        	instarMortMinTemp.add(mortMinTempInstar.get(i).getVtfValue());
			instarMortMaxTemp.add(mortMaxTempInstar.get(i).getVtfValue());
		}
		//for pupa mort variables
		if(hasPupalStage == 1){
			pupaMortMin = getValueFromTextField(textPupaMortMin);
			pupaMortMax = getValueFromTextField(textPupaMortMax);
        	pupaMortMinTemp = getValueFromTextField(textPupaMortMinTemp);
        	pupaMortMaxTemp = getValueFromTextField(textPupaMortMaxTemp);
		}
		//for female mort variables
		femaleMortMin.clear();
		femaleMortMax.clear();
		femaleMortMinTemp.clear();
		femaleMortMaxTemp.clear();
		for(int i = 0; i < numFemale; i++){
			femaleMortMin.add(mortMinFemale.get(i).getVtfValue());
			femaleMortMax.add(mortMaxFemale.get(i).getVtfValue());
        	femaleMortMinTemp.add(mortMinTempFemale.get(i).getVtfValue());
			femaleMortMaxTemp.add(mortMaxTempFemale.get(i).getVtfValue());
		}
		//for male mort variable 
		maleMortMin = getValueFromTextField(textMaleMortMin);
		maleMortMax = getValueFromTextField(textMaleMortMax);
        maleMortMinTemp = getValueFromTextField(textMaleMortMinTemp);
        maleMortMaxTemp = getValueFromTextField(textMaleMortMaxTemp);
	}
	
	public void scene5Data(){
		//for egg viability
		femaleEgg.clear();
		for(int i = 0; i < numFemale; i++){
			femaleEgg.add(eggFemale.get(i).getVtfValue());
		}
		//for fecundity
		fecundityMax = getValueFromTextField(textFecundityMax);
		fecundityTmin = getValueFromTextField(textFecundityTmin);
		fecundityTmax = getValueFromTextField(textFecundityTmax);
	}

	public void scene6Data(){
		//for ignoreOverwintering
		numSelected = 0;
		layoutSub.getChildren().clear();
		if (checkOverwintering.isSelected()){
			ignoreOverwintering = 0;
			numSelected++;
			layoutSub.getChildren().addAll(gridP7);
		}
		else
			ignoreOverwintering = 1;
		//for ignoreMortTemp
		if (checkMortTemp.isSelected()){
			skipSelected = true;
			ignoreMortTemp = 1;
			numSelected++;
			layoutSub.getChildren().addAll(gridP8);
		}
		else{
			skipSelected = false;
			ignoreMortTemp = 0;
		}
		//for ignoreKill
		if (checkKill.isSelected()){
			ignoreKill = 0;
			numSelected++;
			layoutSub.getChildren().addAll(gridP9);
		}
		else
			ignoreKill = 1;
		//for mortality due to predation
		if(checkPredation.isSelected()){
			ignorePredation = 0;
			numSelected++;
			layoutSub.getChildren().addAll(gridP11);
		}
		else
			ignorePredation = 1;
		//for ignoreDiapause
		if (checkDiapause.isSelected()){
			ignoreDiapause = 0;
			numSelected++;
			layoutSub.getChildren().addAll(gridP13);
		}
		else
			ignoreDiapause = 1;
			
	}
	
	public void scene6ModData(VBox layoutModSub){
		//for ignoreOverwintering
		numSelected = 0;
		layoutModSub.getChildren().clear();
		if (checkOverwintering.isSelected()){
			ignoreOverwintering = 0;
			numSelected++;
			layoutModSub.getChildren().addAll(gridP7);
		}
		else
			ignoreOverwintering = 1;
		//for ignoreMortTemp
		if (checkMortTemp.isSelected()){
			ignoreMortTemp = 1;
			numSelected++;
			layoutModSub.getChildren().addAll(gridP8);
		}
		else
			ignoreMortTemp = 0;
		//for ignoreKill
		if (checkKill.isSelected()){
			ignoreKill = 0;
			numSelected++;
			layoutModSub.getChildren().addAll(gridP9);
		}
		else
			ignoreKill = 1;
		//for mortality due to predation
		if(checkPredation.isSelected()){
			ignorePredation = 0;
			numSelected++;
			layoutModSub.getChildren().addAll(gridP11);
		}
		else
			ignorePredation = 1;
		//for ignoreDiapause
		if (checkDiapause.isSelected()){
			ignoreDiapause = 0;
			numSelected++;
			layoutModSub.getChildren().addAll(gridP13);
		}
		else
			ignoreDiapause = 1;
			
	}
	
	public void sceneSubData(){
		//collect data from sub models
		if(ignoreOverwintering  == 0){
			selectionPosition = stageComboBox.getSelectionModel().getSelectedIndex();
            OWSuccess = textOWSuccess.getText();
            OWCurrent = textOWCurrent.getText();
            OWCritTempI = textOWDiaCritTempI.getText();
            OWDayHoursI = textOWDiaDayHoursI.getText();
            OWCritTempT = textOWDiaCritTempT.getText();
            OWDayHoursT = textOWDiaDayHoursT.getText();
		}
		if(ignoreMortTemp == 1){
			
		}
		if(ignoreKill == 0){
			numKillDates = 1;
			killDate.clear();
			percentKill.clear();
			selectionKillStage.clear();
			for(int i = 0; i < numKillDates; i++){
				killDate.add(Integer.toString(killDatePicker.getValue().getDayOfYear()) );
				percentKill.add(textPercentKill.getText());
				selectionKillStage.add(Integer.toString(killStageComboBox.getSelectionModel().getSelectedIndex()));
			}
		}
		if(ignorePredation == 0){
			eggPredation = eggT.getText();
			instariPredation.clear();
			for(int i = 0; i < numInstar; i++){
				instariPredation.add(predationInstar.get(i).getVtfValue());
			}
			if(hasPupalStage == 1)
				pupaPredation = pupaT.getText();
			malePredation = maleT.getText();
			femaleiPredation.clear();
			for(int i = 0; i < numFemale; i++){
				femaleiPredation.add(predationFemale.get(i).getVtfValue());
			}
		}
		if(ignoreDiapause == 0){
			diaCritTemp = textDiaCritTemp.getText();
			diaCritHours = textDiaCritHours.getText();
			diaHalf = textDiaHalf.getText();
			diaRate = textDiaRate.getText();
		}

	}
	
//------------------------- SETUP SCENE LAYOUT -------------------------
    public void setSceneLayout(BorderPane layout, VBox center, HBox bottom, Button backButton, Button nextButton, String progressImage){
		ImageView imageView = addImage(progressImage);
		bottom.getChildren().addAll(backButton, imageView, nextButton);
		bottom.setAlignment(Pos.BOTTOM_CENTER);
		bottom.setPadding(new Insets(10, 10, 10, 10));
		layout.setCenter(center);
		layout.setBottom(bottom);
	}
	
//----------------- TEXT FIELD LISTENER -----------------

	public void textActionListener(TextField first, TextField second){
		first.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                second.requestFocus();
            }
        } );
    }
    public void textActionListener(CheckBox first, TextField second){
		first.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                second.requestFocus();
            }
        } );
    }
    public void textActionListener(TextField first, CheckBox second){
		first.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                second.requestFocus();
            }
        } );
    }
    public void textActionListener(RadioButton first, RadioButton second){
		first.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                second.requestFocus();
            }
        } );
    }
    public void textActionListener(TextField first, RadioButton second){
		first.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                second.requestFocus();
            }
        } );
    }
    public void textActionListener(TextField first, Button second){
		first.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                second.requestFocus();
            }
        } );
    }
    public void textActionListener(RadioButton first, Button second){
		first.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                second.requestFocus();
            }
        } );
    }
    
//-------------------- OTHER STUFF --------------------

//function to check if a text field has been filled 
	public void checkFilled(Button button, ComboBox comboBox, TextField textField, RadioButton radioButton){
		button.disableProperty().bind( comboBox.valueProperty().isNull() 
		.or(textField.textProperty().isEmpty()) 
		.or(radioButton.selectedProperty())
		);
	}
	
    public double getValueFromTextField(TextField tf){
    	String stringVal = tf.getText();	//save the value to the data field
		double doubleVal = -999;
		try{
            doubleVal = Double.parseDouble(stringVal);
        }
        catch (NumberFormatException ex){
        	ex.printStackTrace();
        }
        return doubleVal;
    }
        
    //setting up gridPane layouts
    public void setGrid(GridPane grid){
		grid.setAlignment(Pos.CENTER_LEFT);
		grid.setHgap(5);
		grid.setVgap(5);
		//Insets(top, right, bottom, left)
		grid.setPadding(new Insets(5, 5, 5, 25));
    }
    
    
//-------------------- WRITE TO FILE --------------------
	
	//append String to file 
	public void writeToFile(String str){
		if(modify >= 1){
			boolean lineFound = false;
			//append to file 
			try {
				File file = new File(runName + "/configParams" + (modify) + ".txt");
				
				Scanner inputFile = new Scanner(file);
				Writer outputFile = new BufferedWriter(new FileWriter(runName + "/configParams" + (modify+1) + ".txt", true));

				while(inputFile.hasNextLine()) {
					String line = inputFile.nextLine();
					String[] splitLine = line.split(":");
					String[] splitInput = str.split(":");
				}
				
				if(lineFound == false){
					outputFile.append(str + "\n");
				}

				inputFile.close();
				outputFile.close();
			
			}
			catch(IOException e) {
				System.out.println("A file error has occurred. 1");
			}
		}
		else{
			try {
				Writer outputFile = new BufferedWriter(new FileWriter(runName + "/configParams" + (modify+1) + ".txt", true));
				outputFile.append(str + "\n");
				outputFile.close();
			}
			catch(IOException e) {
				System.out.println("A file error has occurred. 2");
			}
			
		}
	}
	
	public void renameFile(String oldName, String newName){
	
		File file = new File(oldName);
		File tempFile = new File(newName);
		
		//change file's name
		if (!file.renameTo(tempFile))
			System.out.println("file couldn't be renamed");
	}
	
	//Write everything to file
	public void writeAllToFile(){
		//scene1 - line 549 to 578
        writeToFile("numDaysToSim: " + daysToSim);
		//for time step size textField
		writeToFile("integrationStep: " + timeStep);
		//for print interval textField
		writeToFile("printInterval: " + printInterval);
		writeToFile("");
		//for instar textField
		writeToFile("numInstars: " + numInstar);
		//for pupa checkBox
		writeToFile("hasPupalStage: " + hasPupalStage);
		//for female textField
		writeToFile("numFemStages: " + numFemale);
		writeToFile("");
		//for male proportion
        writeToFile("male proportion: " + maleProportion);
		//for latitude textField
		writeToFile("latitude: " + latitude);
		//temperature is an in line argument
		writeToFile("");
		
		//scene2 - line 999 to 1022
		writeToFile("addInsectsDate: " + addInsectDate);
		//for initial eggs
		writeToFile("initial eggs: " + initailEgg);
		//for initial instar
		for(int i = 0; i < numInstar; i++){
			writeToFile("initial instar" + (i+1) + ": " + initalInstari.get(i));
		}
		//for initial pupa
		if(hasPupalStage == 1){
			writeToFile("initial pupae: " + initalPupa);
		}
		//for initial male
		writeToFile("initial males: " + initailMale);
		//for initial female
		for(int i = 0; i < numFemale; i++){
			writeToFile("initial females" + (i+1) + ": " + initalFemalei.get(i));
		}
		writeToFile("");

		//scene3 - line 1235 to 1267
		writeToFile("eggs development a: " + eggDevA);
		writeToFile("eggs development tmin: " + eggDevTL);
		writeToFile("eggs development tmax: " + eggDevTU);
		//for instar dev variables
		for(int i = 0; i < numInstar; i++){
			writeToFile("instar" + (i+1) + " development a: " + instarDevA.get(i));
			writeToFile("instar" + (i+1) + " development tmin: " + instarDevTL.get(i));
			writeToFile("instar" + (i+1) + " development tmax: " + instarDevTU.get(i));
		}
		//for pupa dev variables
		if(hasPupalStage == 1){
			writeToFile("pupae development a: " + pupaDevA);
			writeToFile("pupae development tmin: " + pupaDevTL);
			writeToFile("pupae development tmax: " + pupaDevTU);
		}
		//for female dev variables
		for(int i = 0; i < numFemale; i++){
			writeToFile("females" + (i+1) + " development a: " + femaleDevA.get(i));
			writeToFile("females" + (i+1) + " development tmin: " + femaleDevTL.get(i));
			writeToFile("females" + (i+1) + " development tmax: " + femaleDevTU.get(i));
		}

		writeToFile("");

		//scene4 - line 1350 to 1391
		writeToFile("eggs mortality min: " + eggMortMin);
		writeToFile("eggs mortality max: " + eggMortMax);
		writeToFile("eggs mortality min temp: " + eggMortMinTemp);
		writeToFile("eggs mortality max temp: " + eggMortMaxTemp);
		//for instar mort variables
		for(int i = 0; i < numInstar; i++){
			writeToFile("instar" + (i+1) + " mortality min: " + instarMortMin.get(i));
			writeToFile("instar" + (i+1) + " mortality max: " + instarMortMax.get(i));
			writeToFile("instar" + (i+1) + " mortality min temp: " + instarMortMinTemp.get(i));
			writeToFile("instar" + (i+1) + " mortality max temp: " + instarMortMaxTemp.get(i));
		}
		//for pupa mort variables
		if(hasPupalStage == 1){
			writeToFile("pupae mortality min: " + pupaMortMin);
			writeToFile("pupae mortality max: " + pupaMortMax);
			writeToFile("pupae mortality min temp: " + pupaMortMinTemp);
			writeToFile("pupae mortality max temp: " + pupaMortMaxTemp);
		}
		//for female mort variables
		for(int i = 0; i < numFemale; i++){
			writeToFile("females" + (i+1) + " mortality min: " + femaleMortMin.get(i));
			writeToFile("females" + (i+1) + " mortality max: " + femaleMortMax.get(i));
			writeToFile("females" + (i+1) + " mortality min temp: " + femaleMortMinTemp.get(i));
			writeToFile("females" + (i+1) + " mortality max temp: " + femaleMortMaxTemp.get(i));
		}
		//for male mort variable 
		writeToFile("males mortality min: " + maleMortMin);
		writeToFile("males mortality max: " + maleMortMax);
		writeToFile("males mortality min temp: " + maleMortMinTemp);
		writeToFile("males mortality max temp: " + maleMortMaxTemp);
		writeToFile("");

		//scene5 - line 1428 to 1433
		for(int i = 0; i < numFemale; i++){
			writeToFile("females" + (i+1) + " egg viability: " + femaleEgg.get(i));
		}
		writeToFile("fecundity max: " + fecundityMax);
		writeToFile("fecundity tmin: " + fecundityTmin);
		writeToFile("fecundity tmax: " + fecundityTmax);
		writeToFile("");

		//scene6 - line 1468 to 1543
		writeToFile("ignoreOverwintering: " + ignoreOverwintering);
		writeToFile("ignoreMortTemp: " + ignoreMortTemp);
		writeToFile("ignoreKill: " + ignoreKill);
		writeToFile("ignorePredation: " + ignorePredation);
		writeToFile("ignoreDiapause: " + ignoreDiapause);
		writeToFile("");


		if(ignoreOverwintering == 0){
			writeToFile("overwinteringStage: " + selectionPosition);
			writeToFile("initialOverwintering: " + OWCurrent);
    		writeToFile("overwintering success: " + OWSuccess);
    		writeToFile("overwintering critical induction temp: " + OWCritTempI);
    		writeToFile("overwintering induction daylight hours: " + OWDayHoursI);
    		writeToFile("overwintering critical termination temp: " + OWCritTempT);
    		writeToFile("overwintering termination daylight hours: " + OWDayHoursT);
		}		
		else{
			writeToFile("overwinteringStage: " + 0);
			writeToFile("initialOverwintering: " + 0);
    		writeToFile("overwintering success: " + 0);
    		writeToFile("overwintering critical induction temp: " + 0);
    		writeToFile("overwintering induction daylight hours: " + 0);
    		writeToFile("overwintering critical termination temp: " + 0);
    		writeToFile("overwintering termination daylight hours: " + 0);
		}	
		writeToFile("");
		if(ignoreKill == 0){
			writeToFile("numKillDates: " + numKillDates);
			for(int i = 0; i < numKillDates; i++){
				writeToFile("killDate" + (i+1) + ": " + killDate.get(i));
				writeToFile("stageKill" + (i+1) + ": " + selectionKillStage.get(i));
				writeToFile("percentKill" + (i+1) + ": " + percentKill.get(i));
			}
        	
		}
		else{
			writeToFile("numKillDates: " + 0);
			writeToFile("killDate" + 1 + ": " + 0);
        	writeToFile("stageKill" + 1 + ": " + 0);
			writeToFile("percentKill" + 1 + ": "  + 0);
		}
		writeToFile("");
		if(ignorePredation == 0){
			writeToFile("eggs mortality due to predation: " + eggPredation);
			for(int i = 0; i < numInstar; i++){
				writeToFile("instar" + (i+1) + " mortality due to predation: " + instariPredation.get(i));
			}
			if(hasPupalStage == 1)
				writeToFile("pupae mortality due to predation: " + pupaPredation);
			writeToFile("males mortality due to predation: " + malePredation);
			for(int i = 0; i < numFemale; i++){
				writeToFile("females" + (i+1) + " mortality due to predation: " + femaleiPredation.get(i));
			}
		}
		else{
			writeToFile("eggs mortality due to predation: " + 0);
			for(int i = 0; i < numInstar; i++){
				writeToFile("instar" + (i+1) + " mortality due to predation: " + 0);
			}
			if(hasPupalStage == 1)
				writeToFile("pupae mortality due to predation: " + 0);
			writeToFile("males mortality due to predation: " + 0);
			for(int i = 0; i < numFemale; i++){
				writeToFile("females" + (i+1) + " mortality due to predation: " + 0);
			}
		}
		writeToFile("");
		if(ignoreDiapause == 0){
			writeToFile("diapause critical temp: " + diaCritTemp);
			writeToFile("diapause daylight hours: " + diaCritHours);
			writeToFile("daylight hours half in diapause: " + diaHalf);
			writeToFile("diapause rate per daylight hours: " + diaRate);
		}
		else{
			writeToFile("diapause critical temp: " + 0);
			writeToFile("diapause daylight hours: " + 0);
			writeToFile("daylight hours half in diapause: " + 0);
			writeToFile("diapause rate per daylight hours: " + 0);
		}
		writeToFile("");
	}
	
//---------------------- SAVE DATA TABELS ----------------------

	//collect data from textfields and save them to file
	public void saveJuvDev(){
				
		//data table array
		String[] table = new String[4];
		
		table[0] = " ,";
		table[1] = "a,";
		table[2] = "TL,";
		table[3] = "TU,";
		
		//first row
		table[0] += "egg,";
		for(int i = 0; i < numInstar; i ++)
			table[0] += ("instar"+(i+1)+",");
		if(hasPupalStage == 1)
			table[0] += ("pupae,");
			
		//second row
		table[1] += (eggDevA + ",");
		for(int i = 0; i < numInstar; i ++)
			table[1] += (instarDevA.get(i) + ",");
		if(hasPupalStage == 1)
			table[1] += (pupaDevA + ",");
			
		//third row
		table[2] += (eggDevTL + ",");
		for(int i = 0; i < numInstar; i ++)
			table[2] += (instarDevTL.get(i) + ",");
		if(hasPupalStage == 1)
			table[2] += (pupaDevTL + ",");
		
		//fourth row
		table[3] += (eggDevTU + ",");
		for(int i = 0; i < numInstar; i ++)
			table[3] += (instarDevTU.get(i) + ",");
		if(hasPupalStage == 1)
			table[3] += (pupaDevTU + ",");
		
		String fileName = runName + "/" + insectName + (modify+1) + "_JuvDev.csv";
		writeDataTableFile(table, fileName);
		
	}
	
	public void saveFemDev(){
		//data table array
		String[] table = new String[4];
		
		table[0] = " ,";
		table[1] = "a,";
		table[2] = "TL,";
		table[3] = "TU,";
		
		//first row
		for(int i = 0; i < numFemale; i ++)
			table[0] += ("female"+(i+1)+",");
			
		//second row
		for(int i = 0; i < numFemale; i ++)
			table[1] += (femaleDevA.get(i) + ",");
			
		//third row
		for(int i = 0; i < numFemale; i ++)
			table[2] += (femaleDevTL.get(i) + ",");
		
		//fourth row
		for(int i = 0; i < numFemale; i ++)
			table[3] += (femaleDevTU.get(i) + ",");

		String fileName = runName + "/" + insectName + (modify+1) + "_FemDev.csv";
		writeDataTableFile(table, fileName);

	}
	
	public void saveMort(){
		
		//data table array
		String[] table = new String[4];
		
		table[0] = " ,";
		table[1] = "a,";
		table[2] = "TL,";
		table[3] = "TU,";
		
		//first row
		table[0] += "egg,";
		for(int i = 0; i < numInstar; i ++)
			table[0] += ("instar"+(i+1)+",");
		if(hasPupalStage == 1)
			table[0] += ("pupae,");
		table[0] += ("male,");
		for(int i = 0; i < numFemale; i ++){
			table[0] += ("female" + (i+1) + ",");
		}
		
		//second row
		table[1] += (eggMortMax + ",");
		for(int i = 0; i < numInstar; i ++)
			table[1] += (instarMortMax.get(i) + ",");
		if(hasPupalStage == 1)
			table[1] += (pupaMortMax + ",");
		table[1] += (maleMortMax + ",");
		for(int i = 0; i < numFemale; i ++)
			table[1] += (femaleMortMax.get(i) + ",");
		
		//third row
		table[2] += (eggMortMinTemp + ",");
		for(int i = 0; i < numInstar; i ++)
			table[2] += (instarMortMinTemp.get(i) + ",");
		if(hasPupalStage == 1)
			table[2] += (pupaMortMinTemp + ",");
		table[2] += (maleMortMinTemp + ",");
		for(int i = 0; i < numFemale; i ++)
			table[2] += (femaleMortMinTemp.get(i) + ",");
		
		//fourth row
		table[3] += (eggMortMaxTemp + ",");
		for(int i = 0; i < numInstar; i ++)
			table[3] += (instarMortMaxTemp.get(i) + ",");
		if(hasPupalStage == 1)
			table[3] += (pupaMortMaxTemp + ",");
		table[3] += (maleMortMaxTemp + ",");
		for(int i = 0; i < numFemale; i ++)
			table[3] += (femaleMortMaxTemp.get(i) + ",");
		
		String fileName = runName + "/" + insectName + (modify+1) + "_Mort.csv";
		writeDataTableFile(table, fileName);
		
	}
	
	public void saveEggVia(){

		//data table array
		String[] table = new String[2];
		
		table[0] = " ,";
		table[1] = "egg viability,";
		
		//first and second row
		for(int i = 0; i < numFemale; i ++){
			table[0] += ("female" + (i+1) + ",");
			table[1] += (femaleEgg.get(i) +",");
		}
		
		String fileName = runName + "/" + insectName + (modify+1) + "_EggVia.csv";
		writeDataTableFile(table, fileName);

	}
	
	//write data to file (.cvs formate)
	public void writeDataTableFile(String[] table, String fileName){
		try{
			Writer outputFile = new BufferedWriter(new FileWriter(fileName, true));
			
			for (int i = 0; i < table.length; i++){
				outputFile.append(table[i] + "\n");
			}
			outputFile.close();
		}
		catch(IOException e) {
			System.out.println("A file error has occurred.");
		}
		
	}

//---------- VARIABLE LENGHT TEXTFIELD ADD FUNCTIONS ----------
	public void addInitialInstar(GridPane layout, String i, int h, int v) {

        // create a new varying text field, and add to list:
        VaryingTextField initial = new VaryingTextField();
        initalInstar.add(initial);

        // create controls:
        Label labelInitalInstar = new Label("initial instar" + i);
        TextField textInitalInstar = new TextField();
        initial.setVtfValue("0");

        // bind controls to person:
        textInitalInstar.textProperty().bindBidirectional(initial.vtfValueProperty());

        // add controls to layout:
        layout.add(labelInitalInstar, h, v);
        layout.add(textInitalInstar, h+1, v);
    }
    
    public void addInitialFemale(GridPane layout, String i, int h, int v) {
		// create a new varying text field, and add to list:
        VaryingTextField initial = new VaryingTextField();
        initalFemale.add(initial);

        // create controls:
        Label labelInitalFemale = new Label("initial females" + i);
        TextField textInitalFemale = new TextField();
        initial.setVtfValue("0");

        // bind controls to person:
        textInitalFemale.textProperty().bindBidirectional(initial.vtfValueProperty());

        // add controls to layout:
        layout.add(labelInitalFemale, h, v);
        layout.add(textInitalFemale, h+1, v);
	}
	
	public void addDevInstar(GridPane layout, String i, int variable, int h, int v) {
		//variable 0 = a, 1 = TL, 2 = TU
		if (variable == 0){	// a
			//print top label
			Label labelDevInstar = new Label("instar" + i);
			layout.add(labelDevInstar, h, v-1);
		
			VaryingTextField dev = new VaryingTextField();
        	devAInstar.add(dev);
        	
        	TextField textDevAInstar = new TextField("");
        	textDevAInstar.setPrefWidth(50);
        	
        	textDevAInstar.textProperty().bindBidirectional(dev.vtfValueProperty());

        	layout.add(textDevAInstar, h, v);
		}
		else if (variable == 1){	//TL
			VaryingTextField dev = new VaryingTextField();
        	devTLInstar.add(dev);
        	
        	TextField textDevTLInstar = new TextField();
        	textDevTLInstar.setPrefWidth(50);
        	
        	textDevTLInstar.textProperty().bindBidirectional(dev.vtfValueProperty());

        	layout.add(textDevTLInstar, h, v);
		}
		else if (variable == 2){	//TU
			VaryingTextField dev = new VaryingTextField();
        	devTUInstar.add(dev);
        	
        	TextField textDevTUInstar = new TextField();
        	textDevTUInstar.setPrefWidth(50);
        	
        	textDevTUInstar.textProperty().bindBidirectional(dev.vtfValueProperty());

        	layout.add(textDevTUInstar, h, v);
		}
    }
    
    public void addDevFemale(GridPane layout, String i, int variable, int h, int v) {
    	//variable 0 = a, 1 = TL, 2 = TU
		if (variable == 0){	// a
			//print top label
			Label labelDevFemale = new Label("female" + i);
			layout.add(labelDevFemale, h, v-1);
		
			VaryingTextField dev = new VaryingTextField();
        	devAFemale.add(dev);
        	
        	TextField textDevAFemale = new TextField("");
        	textDevAFemale.setPrefWidth(50);
        	
        	textDevAFemale.textProperty().bindBidirectional(dev.vtfValueProperty());

        	layout.add(textDevAFemale, h, v);
		}
		else if (variable == 1){	//TL
			VaryingTextField dev = new VaryingTextField();
        	devTLFemale.add(dev);
        	
        	TextField textDevTLFemale = new TextField();
        	textDevTLFemale.setPrefWidth(50);
        	
        	textDevTLFemale.textProperty().bindBidirectional(dev.vtfValueProperty());

        	layout.add(textDevTLFemale, h, v);
		}
		else if (variable == 2){	//TU
			VaryingTextField dev = new VaryingTextField();
        	devTUFemale.add(dev);
        	
        	TextField textDevTUFemale = new TextField();
        	textDevTUFemale.setPrefWidth(50);
        	
        	textDevTUFemale.textProperty().bindBidirectional(dev.vtfValueProperty());

        	layout.add(textDevTUFemale, h, v);
		}
    }
	
	public void addMortInstar(GridPane layout, String i, int variable, int h, int v) {
		//variable 0 = max, 1 = minTemp, 2 = maxTemp
		
		if (variable == 0){	// min
			Label labelMortInstar = new Label("instar" + i);
			layout.add(labelMortInstar, h, v-1);
			
			VaryingTextField mort = new VaryingTextField();
        	mortMinInstar.add(mort);
        	
        	TextField textmortMinInstar = new TextField();
        	textmortMinInstar.setPrefWidth(50);
        	
        	textmortMinInstar.textProperty().bindBidirectional(mort.vtfValueProperty());

        	layout.add(textmortMinInstar, h, v);
		}
		else if (variable == 1){	// max
			VaryingTextField mort = new VaryingTextField();
        	mortMaxInstar.add(mort);
        	
        	TextField textmortMaxInstar = new TextField();
        	textmortMaxInstar.setPrefWidth(50);
        	
        	textmortMaxInstar.textProperty().bindBidirectional(mort.vtfValueProperty());

        	layout.add(textmortMaxInstar, h, v);
		}
		else if (variable == 2){	//minTemp
			VaryingTextField mort = new VaryingTextField();
        	mortMinTempInstar.add(mort);
        	
        	TextField textMortMinTempInstar = new TextField();
        	textMortMinTempInstar.setPrefWidth(50);
        	
        	textMortMinTempInstar.textProperty().bindBidirectional(mort.vtfValueProperty());

        	layout.add(textMortMinTempInstar, h, v);
		}
		else if (variable == 3){	//maxTemp
			VaryingTextField mort = new VaryingTextField();
        	mortMaxTempInstar.add(mort);
        	
        	TextField textMortMaxTempInstar = new TextField();
        	textMortMaxTempInstar.setPrefWidth(50);
        	
        	textMortMaxTempInstar.textProperty().bindBidirectional(mort.vtfValueProperty());

        	layout.add(textMortMaxTempInstar, h, v);
		}
    }
	
	public void addMortFemale(GridPane layout, String i, int variable, int h, int v) {
		//variable 0 = max, 1 = minTemp, 2 = maxTemp
		
		if (variable == 0){	// min
        	Label labelMortFemale = new Label("fem" + i);
			layout.add(labelMortFemale, h, v-1);

			VaryingTextField mort = new VaryingTextField();
        	mortMinFemale.add(mort);
        	
        	TextField textmortMinFemale = new TextField();
        	textmortMinFemale.setPrefWidth(50);
        	
        	textmortMinFemale.textProperty().bindBidirectional(mort.vtfValueProperty());

        	layout.add(textmortMinFemale, h, v);
		}
		else if (variable == 1){	// max
			VaryingTextField mort = new VaryingTextField();
        	mortMaxFemale.add(mort);
        	
        	TextField textmortMaxFemale = new TextField();
        	textmortMaxFemale.setPrefWidth(50);
        	
        	textmortMaxFemale.textProperty().bindBidirectional(mort.vtfValueProperty());

        	layout.add(textmortMaxFemale, h, v);
		}
		else if (variable == 2){	//minTemp
			VaryingTextField mort = new VaryingTextField();
        	mortMinTempFemale.add(mort);
        	
        	TextField textMortMinTempFemale = new TextField();
        	textMortMinTempFemale.setPrefWidth(50);
        	
        	textMortMinTempFemale.textProperty().bindBidirectional(mort.vtfValueProperty());

        	layout.add(textMortMinTempFemale, h, v);
		}
		else if (variable == 3){	//maxTemp
			VaryingTextField mort = new VaryingTextField();
        	mortMaxTempFemale.add(mort);
        	
        	TextField textMortMaxTempFemale = new TextField();
        	textMortMaxTempFemale.setPrefWidth(50);
        	
        	textMortMaxTempFemale.textProperty().bindBidirectional(mort.vtfValueProperty());

        	layout.add(textMortMaxTempFemale, h, v);
		}
    }
    
    public void addEggFemale(GridPane layout, String i, int h, int v) {
    	// create a new varying text field, and add to list:
        VaryingTextField egg = new VaryingTextField();
        eggFemale.add(egg);

        // create controls:
        Label labelEggFemale = new Label("females" + i);
        TextField textEggFemale = new TextField();

        // bind controls to person:
        textEggFemale.textProperty().bindBidirectional(egg.vtfValueProperty());

        // add controls to layout:
        layout.add(labelEggFemale, h, v);
        layout.add(textEggFemale, h, v+1);
    }
    
    public void addPredationInstar (GridPane layout, String i, int h, int v) {
        VaryingTextField pred = new VaryingTextField();
		predationInstar.add(pred);
		
		Label instarL = new Label("instar" + i + " predation mortality");
        TextField instarI = new TextField();
        pred.setVtfValue("0");

        instarI.textProperty().bindBidirectional(pred.vtfValueProperty());

        layout.add(instarL, h, v);
        layout.add(instarI, h+1, v);
    }
    
    public void addPredationFemale (GridPane layout, String i, int h, int v) {
        VaryingTextField pred = new VaryingTextField();
    	predationFemale.add(pred);
    	
    	Label femaleL = new Label("females" + i + " predation mortality");
    	TextField femaleT = new TextField();
    	pred.setVtfValue("0");

        femaleT.textProperty().bindBidirectional(pred.vtfValueProperty());

        layout.add(femaleL, h, v);
        layout.add(femaleT, h+1, v);
    }
    
    public void addOutputInstar (GridPane layout, int i, int variable, String[][] values, int h, int v) {
	
        if (variable == 0){	// Cumulative
        	Label labelCumulativeInstar = new Label("Instar" + i);
			layout.add(labelCumulativeInstar, h, v-1);

			VaryingTextField out = new VaryingTextField();
        	
        	String value = values[0][i];
        	Label textcumulativeInstar = new Label(value);
        	
        	layout.add(textcumulativeInstar, h, v);
		}
		else if (variable == 1){	// Peak
			VaryingTextField out = new VaryingTextField();
        	
        	String value = values[1][i];
        	Label textpeakInstar = new Label(value);        	

        	layout.add(textpeakInstar, h, v);
		}
		else if (variable == 2){	// PeakDay
			VaryingTextField out = new VaryingTextField();
        	
        	String value = values[2][i];
        	Label textpeakDayInstar = new Label(value);

        	layout.add(textpeakDayInstar, h, v);
		}
    }
   
    
//------------------------- GRAPH STUFF -------------------------
    public void graphLine(String dataFileName, String title, String xTitle, String yTitle, VBox layout){
    	  	
    	double[][] values;
    	values = readDataFile(dataFileName);  

    	NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel(xTitle);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yTitle);
        
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(title);
        lineChart.setAnimated(false);
        lineChart.setCreateSymbols(false); //hide dots
        
        //Egg
        int stage = 1;
        XYChart.Series<Number, Number> dataEgg = new XYChart.Series<Number, Number>();
        dataEgg.setName("Egg");
		for(int j = 0; j < values.length; j++){
			dataEgg.getData().add(new XYChart.Data<>(values[j][0], values[j][stage]));
		}
		lineChart.getData().add(dataEgg);
		stage++;
		//Instars
        for(int i = 1; i <= numInstar; i++){
        	XYChart.Series<Number, Number> dataInstar = new XYChart.Series<Number, Number>();
        	String name = "Instar" + i;
        	dataInstar.setName(name);

			for(int j = 0; j < values.length; j++){
				dataInstar.getData().add(new XYChart.Data<>(values[j][0], values[j][stage]));
			}
			lineChart.getData().add(dataInstar);
			stage++;
		}
		//Pupae
		if (hasPupalStage == 1){
			XYChart.Series<Number, Number> dataPupa = new XYChart.Series<Number, Number>();
			dataPupa.setName("Pupae");
			for(int j = 0; j < values.length; j++){
				dataPupa.getData().add(new XYChart.Data<>(values[j][0], values[j][stage]));
			}
			lineChart.getData().add(dataPupa);
			stage++;
		}
		//Male
		XYChart.Series<Number, Number> dataMale = new XYChart.Series<Number, Number>();
		dataMale.setName("Male");
		for(int j = 0; j < values.length; j++){
			dataMale.getData().add(new XYChart.Data<>(values[j][0], values[j][stage]));
		}
		lineChart.getData().add(dataMale);
		stage++;
		//Females
		XYChart.Series<Number, Number> dataFemale = new XYChart.Series<Number, Number>();
		dataFemale.setName("Female");
		for(int j = 0; j < values.length; j++){
			dataFemale.getData().add(new XYChart.Data<>(values[j][0], values[j][stage]));
		}
		lineChart.getData().add(dataFemale);
		stage++;
        
        layout.getChildren().clear();
        layout.getChildren().add(lineChart);
		dataSummary(dataFileName, layout);
    }
    
    public void graphBar(String dataFileName, String title, String xTitle, String yTitle, VBox layout){  	
    	
    	double[][] values;
    	values = readDataFile(dataFileName); 
    	
    	String catagoryArray[] = new String [values.length];
    	for(int i = 0; i < values.length; i++){
    		catagoryArray[i] = Double.toString(values[i][0]);
    	}
        
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String> observableArrayList(Arrays.asList(catagoryArray)));
		xAxis.setTickLabelRotation(90);
        xAxis.setLabel(xTitle);

        NumberAxis yAxis = new NumberAxis();	//NumberAxis yAxis = new NumberAxis(min, max, step);
        yAxis.setLabel(yTitle);
        
		StackedBarChart<String, Number> barChart = new StackedBarChart<>(xAxis, yAxis);
		barChart.setTitle(title);
		barChart.setAnimated(false);
		barChart.setCategoryGap(0);
        
        //Egg
        int stage = 1;
        XYChart.Series<String, Number> dataEgg = new XYChart.Series<String, Number>();
        dataEgg.setName("Egg");
		for(int j = 0; j < values.length; j++){
			dataEgg.getData().add(new XYChart.Data<>(catagoryArray[j], values[j][stage]));
		}
		barChart.getData().add(dataEgg);
		
		stage++;
		//Instars
        for(int i = 1; i <= numInstar; i++){
        	XYChart.Series<String, Number> dataInstar = new XYChart.Series<String, Number>();
        	String name = "Instar" + i;
        	dataInstar.setName(name);

			for(int j = 0; j < values.length; j++){
				dataInstar.getData().add(new XYChart.Data<>(catagoryArray[j], values[j][stage]));
			}
			barChart.getData().add(dataInstar);
			stage++;
		}
		//Pupae
		if (hasPupalStage == 1){
			XYChart.Series<String, Number> dataPupa = new XYChart.Series<String, Number>();
			dataPupa.setName("Pupae");
			for(int j = 0; j < values.length; j++){
				dataPupa.getData().add(new XYChart.Data<>(catagoryArray[j], values[j][stage]));
			}
			barChart.getData().add(dataPupa);
			stage++;
		}
		//Male
		XYChart.Series<String, Number> dataMale = new XYChart.Series<String, Number>();
		dataMale.setName("Male");
		for(int j = 0; j < values.length; j++){
			dataMale.getData().add(new XYChart.Data<>(catagoryArray[j], values[j][stage]));
		}
		barChart.getData().add(dataMale);
		stage++;
		//Females
		XYChart.Series<String, Number> dataFemale = new XYChart.Series<String, Number>();
		dataFemale.setName("Female");
		for(int j = 0; j < values.length; j++){
			dataFemale.getData().add(new XYChart.Data<>(catagoryArray[j], values[j][stage]));
		}
		barChart.getData().add(dataFemale);
		stage++;
                
        layout.getChildren().clear();
        layout.getChildren().add(barChart);
        dataSummary(dataFileName, layout);
    }
        
    public void graphBarNormal(String dataFileName, String title, String xTitle, String yTitle, VBox layout){  	
    	
    	double[][] values;
    	values = readDataFile(dataFileName);
    	
    	String catagoryArray[] = new String [values.length];
    	for(int i = 0; i < values.length; i++){
    		catagoryArray[i] = Double.toString(values[i][0]);
    	}
    	
    	//total population at each step
		double [] total = new double [values.length];
		for(int i = 0; i < values.length; i++){
			for(int j = 1; j < values[i].length; j++){
				total[i] += values[i][j];
			}
		}
        
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String> observableArrayList(Arrays.asList(catagoryArray)));
        xAxis.setTickLabelRotation(90);
        xAxis.setLabel(yTitle);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(xTitle);
        yAxis.setAutoRanging(false);
		yAxis.setLowerBound(0);
		yAxis.setUpperBound(1);
        
		StackedBarChart<String, Number> barChartNormal = new StackedBarChart<>(xAxis, yAxis);
		barChartNormal.setTitle(title);
		barChartNormal.setAnimated(false);
		barChartNormal.setCategoryGap(0);
        
        //Egg
        int stage = 1;
        XYChart.Series<String, Number> dataEgg = new XYChart.Series<String, Number>();
        dataEgg.setName("Egg");
		for(int j = 0; j < values.length; j++){
			if(total[j] != 0)
				dataEgg.getData().add(new XYChart.Data<>(catagoryArray[j], values[j][stage]/total[j]));
			else
				dataEgg.getData().add(new XYChart.Data<>(catagoryArray[j], 0));
		}
		barChartNormal.getData().add(dataEgg);
		
		stage++;
		//Instars
        for(int i = 1; i <= numInstar; i++){
        	XYChart.Series<String, Number> dataInstar = new XYChart.Series<String, Number>();
        	String name = "Instar" + i;
        	dataInstar.setName(name);

			for(int j = 0; j < values.length; j++){
				if(total[j] != 0)
					dataInstar.getData().add(new XYChart.Data<>(catagoryArray[j], values[j][stage]/total[j]));
				else
					dataInstar.getData().add(new XYChart.Data<>(catagoryArray[j], 0));
			}
			barChartNormal.getData().add(dataInstar);
			stage++;
		}
		//Pupae
		if (hasPupalStage == 1){
			XYChart.Series<String, Number> dataPupa = new XYChart.Series<String, Number>();
			dataPupa.setName("Pupae");
			for(int j = 0; j < values.length; j++){
				if(total[j] != 0)
					dataPupa.getData().add(new XYChart.Data<>(catagoryArray[j], values[j][stage]/total[j]));
				else
					dataPupa.getData().add(new XYChart.Data<>(catagoryArray[j], 0));
			}
			barChartNormal.getData().add(dataPupa);
			stage++;
		}
		//Male
		XYChart.Series<String, Number> dataMale = new XYChart.Series<String, Number>();
		dataMale.setName("Male");
		for(int j = 0; j < values.length; j++){
			if(total[j] != 0)
				dataMale.getData().add(new XYChart.Data<>(catagoryArray[j], values[j][stage]/total[j]));
			else
				dataMale.getData().add(new XYChart.Data<>(catagoryArray[j], 0));
		}
		barChartNormal.getData().add(dataMale);
		stage++;
		//Females
		XYChart.Series<String, Number> dataFemale = new XYChart.Series<String, Number>();
		dataFemale.setName("Female");
		for(int j = 0; j < values.length; j++){
			if(total[j] != 0)
				dataFemale.getData().add(new XYChart.Data<>(catagoryArray[j], values[j][stage]/total[j]));
			else
				dataFemale.getData().add(new XYChart.Data<>(catagoryArray[j], 0));
		}
		barChartNormal.getData().add(dataFemale);
		stage++;
                
        layout.getChildren().clear();
        layout.getChildren().add(barChartNormal);
		dataSummary(dataFileName, layout);
    }
    
    public double[][] readDataFile(String dataFileName){
		
		int numStages = 1 + numInstar + hasPupalStage + 1 + 1;
		double numSteps = daysToSim/printInterval;
		int inumSteps = (int) numSteps;

    	//initialize an empty array of the right size
    	double[][] values = new double[inumSteps][numStages+1];

    	try{
			Scanner fileIn = new Scanner(new File(dataFileName));

			//eat first line
			fileIn.nextLine();
			
			for(int i = 0; i < inumSteps; i++){
				String line = fileIn.nextLine();		
				String[] splitLine = line.split("\t");

				for(int j = 0; j < numStages+1; j++){
					try{
						values[i][j] = Double.parseDouble(splitLine[j]);
					}catch (Exception e){
						System.out.println("problem with stage: " + j + ", time step: " + i);
					}
				}
			}
			fileIn.close();
		}
		catch(IOException e) {
			System.out.println("Cannot open file. -graph");
		}
			
		return values;
    }

    public String[][] readDataFileSummary(String dataFileName){
		
		int numCumulative = 1 + numInstar + hasPupalStage + 1 + 1;
		int numPeak = numCumulative + 1;
		int numPeakDay = numPeak;

		String[][] values = new String[3][numPeak];
		
    	try{
			Scanner fileIn = new Scanner(new File(dataFileName));
			
			for(int i = 0; i < daysToSim + 5; i++){
				fileIn.nextLine();	//eat lines until it reaches the summary section
			}
			
			String lineCumulative = fileIn.nextLine();		
			String[] splitLineCumulative = lineCumulative.split("\t");
			//eat three lines
			fileIn.nextLine();
			fileIn.nextLine();
			fileIn.nextLine();
			String linePeak = fileIn.nextLine();	
			String[] splitLinePeak = linePeak.split("\t");
			//eat three lines
			fileIn.nextLine();
			fileIn.nextLine();
			fileIn.nextLine();
			String linePeakDay = fileIn.nextLine();		
			String[] splitLinePeakDay = linePeakDay.split("\t");
			for(int i = 1; i < splitLinePeak.length-1; i++){
				if(i != splitLinePeak.length-2)
					values[0][i-1] = splitLineCumulative[i];
				values[1][i-1] = splitLinePeak[i];
				values[2][i-1] = splitLinePeakDay[i];
			}

			fileIn.close();
		}
		catch(IOException e) {
			System.out.println("Cannot open file. -graph");
		}
			
		return values;
	}
	
	public void dataSummary(String dataFileName, VBox layout){
        int pos = 0;
        GridPane summary = new GridPane();
        summary.setHgap(10);
        String[][] values1 = readDataFileSummary(dataFileName);
        
        //setup left labels
        Label cum = new Label("Cumulative");
        Label peak = new Label("Peak");
        Label peakDay = new Label("PeakDay");
        summary.add(cum, 0, 1);
        summary.add(peak, 0, 2);
        summary.add(peakDay, 0, 3);

		//eggs
		Label labE = new Label("Egg");
		Label cumE = new Label(values1[0][pos]);
        Label peakE = new Label(values1[1][pos]);
        Label peakDayE = new Label(values1[2][pos]);
        pos++;
        summary.add(labE, pos, 0);
        summary.add(cumE, pos, 1);
        summary.add(peakE, pos, 2);
        summary.add(peakDayE, pos, 3);
		//instar
		for(int i = 0; i < numInstar; i++){
		    pos++;
			for(int j = 0; j < 3; j++){
        		addOutputInstar(summary, i+1, j, values1, pos, j+1);
        	}
        }
        //male
        Label labM = new Label("Male");
        Label cumM = new Label(values1[0][pos]);
        Label peakM = new Label(values1[1][pos]);
        Label peakDayM = new Label(values1[2][pos]);
        pos++;
        summary.add(labM, pos, 0);
        summary.add(cumM, pos, 1);
        summary.add(peakM, pos, 2);
        summary.add(peakDayM, pos, 3);
        //female
        Label labF = new Label("Female");
        Label cumF = new Label(values1[0][pos]);
        Label peakF = new Label(values1[1][pos]);
        Label peakDayF = new Label(values1[2][pos]);
        pos++;
        summary.add(labF, pos, 0);
        summary.add(cumF, pos, 1);
        summary.add(peakF, pos, 2);
        summary.add(peakDayF, pos, 3);
    	//temperature
    	Label labT = new Label("Temperature");
    	Label cumT = new Label(values1[0][pos]);
        Label peakT = new Label(values1[1][pos]);
        Label peakDayT = new Label(values1[2][pos]);
        pos++;
        summary.add(labT, pos, 0);
        summary.add(cumT, pos, 1);
        summary.add(peakT, pos, 2);
        summary.add(peakDayT, pos, 3);
        layout.getChildren().add(summary);
	}

//----------------------- INPUT DATA FILES -----------------------
	public void getDevData(String fileName){
		try{
			Scanner fileIn = new Scanner(new File(fileName));
			
			//eat first line
			String line = fileIn.nextLine();
			
			//development a line
			line = fileIn.nextLine();
			String[] fileDevAs = line.split(",");
			//development TL line
			line = fileIn.nextLine();
			String[] fileDevTLs = line.split(",");
			//development TU line
			line = fileIn.nextLine();
			String[] fileDevTUs = line.split(",");
		
			//start at 1 to eat the label column
			int pointerA = 1;
			int pointerTL = 1;
			int pointerTU = 1;
			//set the read values to the textfields in scene3a
			textEggDevA.setText(fileDevAs[pointerA]);
			textEggDevTL.setText(fileDevTLs[pointerTL]);
			textEggDevTU.setText(fileDevTUs[pointerTU]);
			pointerA ++;
			pointerTL ++;
			pointerTU ++;

			for(int i = 0; i < numInstar; i++){
				devAInstar.get(i).setVtfValue(fileDevAs[pointerA]);
				devTLInstar.get(i).setVtfValue(fileDevTLs[pointerTL]);
				devTUInstar.get(i).setVtfValue(fileDevTUs[pointerTU]);
								
				pointerA ++;
				pointerTL ++;
				pointerTU ++;
			}
			if(hasPupalStage == 1){
				textPupaDevA.setText(fileDevAs[pointerA]);
				textPupaDevTL.setText(fileDevTLs[pointerTL]);
				textPupaDevTU.setText(fileDevTUs[pointerTU]);
								
				pointerA ++;
				pointerTL ++;
				pointerTU ++;
			}

			textMaleDevA.setText(fileDevAs[pointerA]);
			textMaleDevTL.setText(fileDevTLs[pointerTL]);
			textMaleDevTU.setText(fileDevTUs[pointerTU]);
			
			pointerA ++;
			pointerTL ++;
			pointerTU ++;
			
			for(int i = 0; i < numFemale; i++){
				devAFemale.get(i).setVtfValue(fileDevAs[pointerA+i]);
				devTLFemale.get(i).setVtfValue(fileDevTLs[pointerTL+i]);
				devTUFemale.get(i).setVtfValue(fileDevTUs[pointerTU+i]);
			}
			
		}
		catch(IOException e) {
			System.out.println("Cannot open file.");
		}
	}
	
	public void getMortData(String fileName){
		try{
			Scanner fileIn = new Scanner(new File(fileName));
			
			//eat first line
			String line = fileIn.nextLine();
				
			//mort min line
			line = fileIn.nextLine();
			String[] fileMortMin = line.split(",");
			//mort max line
			line = fileIn.nextLine();
			String[] fileMortMax = line.split(",");
			//mort min temp line
			line = fileIn.nextLine();
			String[] fileMortMinTemp = line.split(",");
			//mort max temp line
			line = fileIn.nextLine();
			String[] fileMortMaxTemp = line.split(",");
		
			//start at 1 to eat the label column
			int pointer1 = 1;
			int pointer2 = 1;
			int pointer3 = 1;
			int pointer4 = 1;
			//set the read values to the textfields in scene3a
			textEggMortMin.setText(fileMortMin[pointer1]);
			textEggMortMax.setText(fileMortMax[pointer2]);
			textEggMortMinTemp.setText(fileMortMinTemp[pointer3]);
			textEggMortMaxTemp.setText(fileMortMaxTemp[pointer4]);
			pointer1 ++;
			pointer2 ++;
			pointer3 ++;
			pointer4 ++;
			for(int i = 0; i < numInstar; i++){
				mortMinInstar.get(i).setVtfValue(fileMortMin[pointer1]);
				mortMaxInstar.get(i).setVtfValue(fileMortMax[pointer2]);
				mortMinTempInstar.get(i).setVtfValue(fileMortMinTemp[pointer3]);
				mortMaxTempInstar.get(i).setVtfValue(fileMortMaxTemp[pointer4]);
								
				pointer1 ++;
				pointer2 ++;
				pointer3 ++;
				pointer4 ++;
			}
			if(hasPupalStage == 1){
				textPupaMortMin.setText(fileMortMin[pointer1]);
				textPupaMortMax.setText(fileMortMax[pointer2]);
				textPupaMortMinTemp.setText(fileMortMinTemp[pointer3]);
				textPupaMortMaxTemp.setText(fileMortMaxTemp[pointer4]);
								
				pointer1 ++;
				pointer2 ++;
				pointer3 ++;
				pointer4 ++;
			}
			textMaleMortMin.setText(fileMortMin[pointer1]);
			textMaleMortMax.setText(fileMortMax[pointer2]);
			textMaleMortMinTemp.setText(fileMortMinTemp[pointer3]);
			textMaleMortMaxTemp.setText(fileMortMaxTemp[pointer4]);
			pointer1 ++;
			pointer2 ++;
			pointer3 ++;
			pointer4 ++;
			for(int i = 0; i < numFemale; i++){
				mortMinFemale.get(i).setVtfValue(fileMortMin[pointer1]);
				mortMaxFemale.get(i).setVtfValue(fileMortMax[pointer2]);
				mortMinTempFemale.get(i).setVtfValue(fileMortMinTemp[pointer3]);
				mortMaxTempFemale.get(i).setVtfValue(fileMortMaxTemp[pointer4]);
								
				pointer1 ++;
				pointer2 ++;
				pointer3 ++;
				pointer4 ++;
			}

		}
		catch(IOException e) {
			System.out.println("Cannot open file.");
		}
	}
	
	public void getEggViaData(String fileName){
		try{
			Scanner fileIn = new Scanner(new File(fileName));
			
			//eat first line
			String line = fileIn.nextLine();
			
			//egg viability line
			line = fileIn.nextLine();
			String[] fileEggVia = line.split(",");
			
			for(int i = 0; i < numFemale; i++){
				eggFemale.get(i).setVtfValue(fileEggVia[i+1]);
			}
		
		}
		catch(IOException e) {
			System.out.println("Cannot open file.");
		}
	}
	
	public void getFecundityData(String fileName){
		try{
			Scanner fileIn = new Scanner(new File(fileName));
			
			//eat first line
			String line = fileIn.nextLine();
			
			//fecundity max line
			line = fileIn.nextLine();
			String[] fileMax = line.split(",");
			textFecundityMax.setText(fileMax[1]);
			
			//fecundity min temp line
			line = fileIn.nextLine();
			String[] fileMinT = line.split(",");
			textFecundityTmin.setText(fileMinT[1]);
			
			//fecundity max temp line
			line = fileIn.nextLine();
			String[] fileMaxT = line.split(",");
			textFecundityTmax.setText(fileMaxT[1]);
		}
		catch(IOException e) {
			System.out.println("Cannot open file.");
		}
	}
    
//----------------------- RUNNING EXTERNAL PROGRAM -----------------------
    public void callFittingDev(String fileName){    	
    	int numStages = 1 + numInstar + hasPupalStage + 1 + numFemale;
		String command = "./execFittingDev " + fileName + " " + numStages;

        // Running the above command
        Runtime run  = Runtime.getRuntime();
		try
        {
            Process proc = run.exec(command);
            while(proc.isAlive()){
            	//keep control until external program is done running
  			}
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("problem");
        }
    }
    
    public void callFittingMort(String fileName){    	
    	int numStages = 1 + numInstar + hasPupalStage + 1 + numFemale;
		String command = "./execFittingMort " + fileName + " " + numStages;

        // Running the above command
        Runtime run  = Runtime.getRuntime();
		try
        {
            Process proc = run.exec(command);
            while(proc.isAlive()){
            	//keep control until external program is done running
  			}
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void callFittingFec(String fileName){    	
    	int numStages = numFemale;
		String command = "./execFittingFec " + fileName + " " + 1;

        // Running the above command
        Runtime run  = Runtime.getRuntime();
		try
        {
            Process proc = run.exec(command);
            while(proc.isAlive()){
            	//keep control until external program is done running
  			}
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void simulate(Stage primaryStage){
    	//getting the temperature file
    	String selectedToggle = ((RadioButton)group.getSelectedToggle()).getText();
    	String tempFile;
    	//custom temperature file input
    	if( selectedToggle.equals("custom temperature file")){
    		tempFile = tempFileText.getText();
    	}
    	//constant temperature input
    	else if( selectedToggle.equals("constant temperature")){
    		tempFile = "constantTemperature.txt";
    		try {
    			//delete the constant temperature file if it exists
    			File f = new File("constantTemperature.txt"); 
				if(f.delete()){
					System.out.println(f.getName() + " deleted");
				}  
				else {  
					System.out.println("failed");  
				}  
				//write the new constant temperature file
				Writer outputFile = new BufferedWriter(new FileWriter(tempFile, true));
				outputFile.append(textConstantTemp.getText());
				outputFile.close();
			}
			catch(IOException e) {
				System.out.println("A file error has occurred.");
			}
    	}
    	//built in sin curve temperature file
    	else{
    		tempFile = "builtInData/" + selectedToggle + ".txt";
    	}

    	//set the name of the output sim file
    	outputSimFileName = runName + "/" + insectName + (modify+1) + ".txt";
    	
		//changed this path so that it can be run by anyone
		String command = "./execSingleCellRunner " + runName + "/configParams" + (modify+1) + ".txt " + tempFile + " " + outputSimFileName;
		
        // Running the above command
        Runtime run  = Runtime.getRuntime();
		try
        {
            Process proc = run.exec(command);

            while(proc.isAlive()){
            	//keep control until external program is done running
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    
//------------------------- INPUT VALIDATION -------------------------

    //check if a input sting is a String (for: )
    public boolean checkForString(TextField text){
    	String input = text.getText();
    	return true;	//NOT SURE THAT I WILL ACCTUALY NEED THIS FUNCTION.
    } 
    
    //check if a input sting is a file name (includes a .csv or .txt) (for: customeTempFile, ...)
    public boolean checkForFileName(TextField text){
    	String input = text.getText();
    	String pattern = "*\\.(txt|csv)";
		if (input.matches(pattern))	//is a txt file of cvs
    		return true;
    	else 
    		return false;
    }
    
    //check if a input sting is a int (for: numInstars, numFemales...)
    public boolean checkForInt(TextField text){
    	String input = text.getText();
    	//return input.matches("[1-9]\\d*");	//positive int
    	return input.matches("[1-9][1-9]?");	//positive int
    }
    
    //check if a input sting is a Double (for: daysToSim, timeSteps, printIntervales, latitude, all initial values, all dev, all mort, ...)
    public boolean checkForDouble(TextField text){
    	String input = text.getText();
    	return input.matches("\\d+(\\.\\d+)?");	//positive double
    }
    
    //check if a input sting is a rate (val < 1) (for: devA, maxDev, maxMort, eggViability, OWSuccess...)
    public boolean checkForRate(TextField text){
    	String input = text.getText();
    	return input.matches("(?:0*(?:\\.\\d+)?|1(\\.0*)?)");
    }
    
    //check all values are >= 0 (for: all values - not string)
    public boolean checkPositive(double value){
    	//called in all input check functions that are values
    	if (value >= 0)
    		return true;
    	else
    		return false;
    }
    
	
//------------------------ SAVE DATA OUTPUT FILE ------------------------
	public void saveDataFile(File file){
		//copy the content of the here.txt file to the user specified file - then delete here
		try{
			Scanner inSimFile = new Scanner(new File(outputSimFileName));
			Writer outDataFile = new BufferedWriter(new FileWriter(file, true));								
			while(inSimFile.hasNextLine()) {
				String line = inSimFile.nextLine();
				if(file.getName().contains(".txt")){
					String lineNew = line.replaceAll(",","\t");
					outDataFile.append(lineNew + "\n");
				}
				else{
					outDataFile.append(line + "\n");
				}
			}
			inSimFile.close();
			outDataFile.close();
		}
		catch(IOException e) {
			System.out.println("Cannot open file.");
		}		
	}
		
// ------------------------- VISUAL STUFF -------------------------
	public ImageView addImage(String imageFile){
		//Creating an image 
    	Image image = new Image("/assets/"+imageFile, true);
      
    	//Setting the image view 
    	ImageView imageView = new ImageView(image); 
            
    	//Setting the fit height and width of the image view 
    	imageView.setFitHeight(55); 
      
    	//Setting the preserve ratio of the image view 
    	imageView.setPreserveRatio(true);  
    	return imageView;
      	
	}

//------------------------- ERROR WINDOW -------------------------
	public void openErrorWindow(String message){

		//error title
		Text errorTitle = new Text("Error");
		errorTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		//error message
		Label errorMessageLabel = new Label(message);
		//OK button
		Button okErrorButton = new Button("OK");
		//error image
		Image errorImage = new Image("/assets/error.png", true);
    	ImageView errorImageView = new ImageView(errorImage); 
    	errorImageView.setFitHeight(55); 
    	errorImageView.setPreserveRatio(true); 
		
		//layout setup
		BorderPane errorLayout = new BorderPane();
		HBox center = new HBox();
		//(top, right, bottom, left)
		center.setPadding(new Insets(20, 0, 0, 20));
		VBox stack = new VBox();
		stack.setPadding(new Insets(0, 0, 0, 20));
		stack.getChildren().addAll(errorTitle, errorMessageLabel);
		center.getChildren().addAll(errorImageView, stack);
		HBox bottom = new HBox();
		bottom.getChildren().addAll(okErrorButton);
		bottom.setAlignment(Pos.BOTTOM_RIGHT);
		bottom.setPadding(new Insets(10, 10, 10, 10));
		errorLayout.setCenter(center);
		errorLayout.setBottom(bottom);
		
		//scene setup
		Scene errorScene = new Scene(errorLayout, 300, 120);
		
		//window setup
		Stage errorWindow = new Stage();
		errorWindow.setTitle("Error");
		errorWindow.setScene(errorScene);
		okErrorButton.setOnAction(e -> errorWindow.close()); 
		
		//open a error window
		errorWindow.show();
		
	}
	
//------------------------ TOOLTIP SETUP -------------------------
	public static void showTooltip(Stage owner, Control control, String tooltipText){
    	Point2D p = control.localToScene(0.0, 0.0);

    	final Tooltip customTooltip = new Tooltip();
    	customTooltip.setText(tooltipText);

    	control.setTooltip(customTooltip);
    	customTooltip.setAutoHide(true);

    	customTooltip.show(owner, p.getX()
        	+ control.getScene().getX() + control.getScene().getWindow().getX(), p.getY()
        	+ control.getScene().getY() + control.getScene().getWindow().getY());

	}

//--------------------------- INFO BUTTON ---------------------------
	public HBox infoT(Text title, String message, Stage stage){
	
		Circle infoCircle = new Circle();
		//change info button colours
		infoCircle.setRadius(9.0f);
		infoCircle.setStroke(Color.BLACK);
		infoCircle.setFill(Color.TRANSPARENT);
		Text infoText = new Text("?");
		infoText.setBoundsType(TextBoundsType.VISUAL); 
		StackPane infoStack = new StackPane(infoText, infoCircle);

		Tooltip infoTooltip1 = new Tooltip();
		infoTooltip1.setText(message);

		//Creating the mouse event handler 
		infoCircle.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				windowPosX = stage.getX() + 15;
				windowPosY = stage.getY() + 20;
			    Point2D p = infoCircle.localToScene(windowPosX, windowPosY);
			    infoTooltip1.show(stage, p.getX(), p.getY());
			}
		});
		infoText.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				windowPosX = stage.getX() + 15;
				windowPosY = stage.getY() + 20;
			    Point2D p = infoCircle.localToScene(windowPosX, windowPosY);
			    infoTooltip1.show(stage, p.getX(), p.getY());
			}
		});
		infoCircle.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
    			infoTooltip1.hide();
			}
		});
		infoText.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
    			infoTooltip1.hide();
			}
		});
		
		HBox info = new HBox();
		info.getChildren().addAll(title, infoStack);
		
		return info;
	}
	
	public HBox infoL(Label label, String message, Stage stage){
	
		Circle infoCircle = new Circle();
		//change info button colours
		infoCircle.setRadius(9.0f);
		infoCircle.setStroke(Color.BLACK);
		infoCircle.setFill(Color.TRANSPARENT);
		Text infoText = new Text("?");
		infoText.setBoundsType(TextBoundsType.VISUAL); 
		StackPane infoStack = new StackPane(infoText, infoCircle);

		Tooltip infoTooltip1 = new Tooltip();
		infoTooltip1.setText(message);

		//Creating the mouse event handler 
		infoCircle.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				windowPosX = stage.getX() + 15;
				windowPosY = stage.getY() + 20;
			    Point2D p = infoCircle.localToScene(windowPosX, windowPosY);
			    infoTooltip1.show(stage, p.getX(), p.getY());
			}
		});
		infoText.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				windowPosX = stage.getX() + 15;
				windowPosY = stage.getY() + 20;
			    Point2D p = infoCircle.localToScene(windowPosX, windowPosY);
			    infoTooltip1.show(stage, p.getX(), p.getY());
			}
		});
		infoCircle.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
    			infoTooltip1.hide();
			}
		});
		infoText.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
    			infoTooltip1.hide();
			}
		});
		
		HBox info = new HBox();
		StackPane labelStack = new StackPane(label);
		info.getChildren().addAll(labelStack, infoStack);
		
		return info;
	}
	
	public HBox infoTF(TextField textField, String message, Stage stage){
	
		Circle infoCircle = new Circle();
		//change info button colours
		infoCircle.setRadius(9.0f);
		infoCircle.setStroke(Color.BLACK);
		infoCircle.setFill(Color.TRANSPARENT);
		Text infoText = new Text("?");
		infoText.setBoundsType(TextBoundsType.VISUAL); 
		StackPane infoStack = new StackPane(infoText, infoCircle);

		Tooltip infoTooltip1 = new Tooltip();
		infoTooltip1.setText(message);

		//Creating the mouse event handler 
		infoCircle.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				windowPosX = stage.getX() + 15;
				windowPosY = stage.getY() + 20;
			    Point2D p = infoCircle.localToScene(windowPosX, windowPosY);
			    infoTooltip1.show(stage, p.getX(), p.getY());
			}
		});
		infoText.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				windowPosX = stage.getX() + 15;
				windowPosY = stage.getY() + 20;
			    Point2D p = infoCircle.localToScene(windowPosX, windowPosY);
			    infoTooltip1.show(stage, p.getX(), p.getY());
			}
		});
		infoCircle.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
    			infoTooltip1.hide();
			}
		});
		infoText.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
    			infoTooltip1.hide();
			}
		});
		
		HBox info = new HBox();
		info.setSpacing(5);
		info.getChildren().addAll(textField, infoStack);
		
		return info;
	}
	
	
	public HBox infoCB(CheckBox checkBox, String message, Stage stage){
	
		Circle infoCircle = new Circle();
		//change info button colours
		infoCircle.setRadius(9.0f);
		infoCircle.setStroke(Color.BLACK);
		infoCircle.setFill(Color.TRANSPARENT);
		Text infoText = new Text("?");
		infoText.setBoundsType(TextBoundsType.VISUAL); 
		StackPane infoStack = new StackPane(infoText, infoCircle);

		Tooltip infoTooltip1 = new Tooltip();
		infoTooltip1.setText(message);

		//Creating the mouse event handler 
		infoCircle.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				windowPosX = stage.getX() + 15;
				windowPosY = stage.getY() + 20;
			    Point2D p = infoCircle.localToScene(windowPosX, windowPosY);
			    infoTooltip1.show(stage, p.getX(), p.getY());
			}
		});
		infoText.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				windowPosX = stage.getX() + 15;
				windowPosY = stage.getY() + 20;
			    Point2D p = infoCircle.localToScene(windowPosX, windowPosY);
			    infoTooltip1.show(stage, p.getX(), p.getY());
			}
		});
		infoCircle.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
    			infoTooltip1.hide();
			}
		});
		infoText.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
    			infoTooltip1.hide();
			}
		});
		
		HBox info = new HBox();
		info.setSpacing(5);
		info.getChildren().addAll(checkBox, infoStack);
		
		return info;
	}

	public HBox infoDP(DatePicker datePicker, String message, Stage stage){
	
		Circle infoCircle = new Circle();
		//change info button colours
		infoCircle.setRadius(9.0f);
		infoCircle.setStroke(Color.BLACK);
		infoCircle.setFill(Color.TRANSPARENT);
		Text infoText = new Text("?");
		infoText.setBoundsType(TextBoundsType.VISUAL); 
		StackPane infoStack = new StackPane(infoText, infoCircle);

		Tooltip infoTooltip1 = new Tooltip();
		infoTooltip1.setText(message);

		//Creating the mouse event handler 
		infoCircle.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				windowPosX = stage.getX() + 15;
				windowPosY = stage.getY() + 20;
			    Point2D p = infoCircle.localToScene(windowPosX, windowPosY);
			    infoTooltip1.show(stage, p.getX(), p.getY());
			}
		});
		infoText.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				windowPosX = stage.getX() + 15;
				windowPosY = stage.getY() + 20;
			    Point2D p = infoCircle.localToScene(windowPosX, windowPosY);
			    infoTooltip1.show(stage, p.getX(), p.getY());
			}
		});
		infoCircle.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
    			infoTooltip1.hide();
			}
		});
		infoText.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
    			infoTooltip1.hide();
			}
		});
		
		HBox info = new HBox();
		info.setSpacing(5);
		info.getChildren().addAll(datePicker, infoStack);
		
		return info;
	}
	
	public HBox infoCoB(ComboBox comboBox, String message, Stage stage){
	
		Circle infoCircle = new Circle();
		//change info button colours
		infoCircle.setRadius(9.0f);
		infoCircle.setStroke(Color.BLACK);
		infoCircle.setFill(Color.TRANSPARENT);
		Text infoText = new Text("?");
		infoText.setBoundsType(TextBoundsType.VISUAL); 
		StackPane infoStack = new StackPane(infoText, infoCircle);

		Tooltip infoTooltip1 = new Tooltip();
		infoTooltip1.setText(message);

		//Creating the mouse event handler 
		infoCircle.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				windowPosX = stage.getX() + 15;
				windowPosY = stage.getY() + 20;
			    Point2D p = infoCircle.localToScene(windowPosX, windowPosY);
			    infoTooltip1.show(stage, p.getX(), p.getY());
			}
		});
		infoText.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				windowPosX = stage.getX() + 15;
				windowPosY = stage.getY() + 20;
			    Point2D p = infoCircle.localToScene(windowPosX, windowPosY);
			    infoTooltip1.show(stage, p.getX(), p.getY());
			}
		});
		infoCircle.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
    			infoTooltip1.hide();
			}
		});
		infoText.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
    			infoTooltip1.hide();
			}
		});
		
		HBox info = new HBox();
		info.setSpacing(5);
		info.getChildren().addAll(comboBox, infoStack);
		
		return info;
	}
	
	public StackPane infoNon(String message, Stage stage){
	
		Circle infoCircle = new Circle();
		//change info button colours
		infoCircle.setRadius(9.0f);
		infoCircle.setStroke(Color.BLACK);
		infoCircle.setFill(Color.TRANSPARENT);
		Text infoText = new Text("?");
		infoText.setBoundsType(TextBoundsType.VISUAL); 
		StackPane infoStack = new StackPane(infoText, infoCircle);

		Tooltip infoTooltip1 = new Tooltip();
		infoTooltip1.setText(message);

		//Creating the mouse event handler 
		infoCircle.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				windowPosX = stage.getX() + 15;
				windowPosY = stage.getY() + 20;
			    Point2D p = infoCircle.localToScene(windowPosX, windowPosY);
			    infoTooltip1.show(stage, p.getX(), p.getY());
			}
		});
		infoText.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				windowPosX = stage.getX() + 15;
				windowPosY = stage.getY() + 20;
			    Point2D p = infoCircle.localToScene(windowPosX, windowPosY);
			    infoTooltip1.show(stage, p.getX(), p.getY());
			}
		});
		infoCircle.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
    			infoTooltip1.hide();
			}
		});
		infoText.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
    			infoTooltip1.hide();
			}
		});
		
		return infoStack;
	}
	
	public HBox infoB(Button button, String message, Stage stage){
	
		Circle infoCircle = new Circle();
		//change info button colours
		infoCircle.setRadius(9.0f);
		infoCircle.setStroke(Color.BLACK);
		infoCircle.setFill(Color.TRANSPARENT);
		Text infoText = new Text("?");
		infoText.setBoundsType(TextBoundsType.VISUAL); 
		StackPane infoStack = new StackPane(infoText, infoCircle);

		Tooltip infoTooltip1 = new Tooltip();
		infoTooltip1.setText(message);

		//Creating the mouse event handler 
		infoCircle.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				windowPosX = stage.getX() + 15;
				windowPosY = stage.getY() + 20;
			    Point2D p = infoCircle.localToScene(windowPosX, windowPosY);
			    infoTooltip1.show(stage, p.getX(), p.getY());
			}
		});
		infoText.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				windowPosX = stage.getX() + 15;
				windowPosY = stage.getY() + 20;
			    Point2D p = infoCircle.localToScene(windowPosX, windowPosY);
			    infoTooltip1.show(stage, p.getX(), p.getY());
			}
		});
		infoCircle.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
    			infoTooltip1.hide();
			}
		});
		infoText.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
    			infoTooltip1.hide();
			}
		});
		
		HBox info = new HBox();
		info.setSpacing(5);
		info.getChildren().addAll(button, infoStack);
		
		return info;
	}
	

//------------------------------- MAIN -------------------------------
	public static void main (String [] args){					
		//launch GUI
		launch(args);
	}
}