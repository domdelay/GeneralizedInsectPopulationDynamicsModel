import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VaryingTextField {
    private String vtfLabel;
    private final StringProperty vtfValue = new SimpleStringProperty();
    
    public final StringProperty vtfValueProperty() {
        return this.vtfValue;
    }

	public String getVtfLabel(){
		return vtfLabel;
	}
    public final String getVtfValue() {
        return this.vtfValueProperty().get();
    }

	public void setVtfLabel(final String vtfLabel){
		this.vtfLabel = vtfLabel;	
	}
    public final void setVtfValue(final String vtfValue) {
        this.vtfValueProperty().set(vtfValue);
    }
}