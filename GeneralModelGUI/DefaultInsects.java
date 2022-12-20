import java.util.List;
import java.util.ArrayList;

public class DefaultInsects {
	private List<String> defaultInsectsList = new ArrayList<>();
	private List<String> defaultInsectsListStages = new ArrayList<>();

	public DefaultInsects(){
		defaultInsectsList.add("Spotted_Wing_Fruit_Fly");
		defaultInsectsList.add("Brown_Marmorated_Stink_Bug");
		defaultInsectsList.add("Mountain_Pine_Beetle");
		defaultInsectsList.add("Swede_Midge");
		defaultInsectsList.add("Cotton_Aphid");
		
		defaultInsectsListStages.add("3,1,7");
		defaultInsectsListStages.add("5,0,3");
		defaultInsectsListStages.add("4,1,2");
		defaultInsectsListStages.add("1,1,1");
		defaultInsectsListStages.add("4,0,3");
	}
	
	public void add(String name){
		defaultInsectsList.add(name);
	}
	
	public String at(int index){
		return defaultInsectsList.get(index);
	}
	
	public int length(){
		return defaultInsectsList.size();
	}
	
	public void setStage(String stages){
		defaultInsectsListStages.add(stages);
	}
	
	public String getStage(int index){
		return defaultInsectsListStages.get(index);
	}
}