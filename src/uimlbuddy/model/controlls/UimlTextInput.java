package uimlbuddy.model.controlls;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import uimlbuddy.model.Part;

/**
 * Model class for a UIML Text Input
 * 
 * @author Lyuben Dimitrov
 */ 
public class UimlTextInput extends Part {
    
    private final StringProperty id;    
    private final StringProperty label;
    private final StringProperty style;
    
    /**
     * Default constructor.
     */
    public UimlTextInput() {
        this(null, null);
    }
    
    /**
     * Constructor with some initial data.
     * 
     * @param id
     * @param label
     */
    public UimlTextInput(String id, String label) {
        this.id = new SimpleStringProperty(id);
        this.label = new SimpleStringProperty(label);
        
        this.style = new SimpleStringProperty();
    }
    
    @Override
    public String getId() {
        return id.get();
    }
    
    @Override
    public void setId(String id) {
        this.id.set(id);
    }
    
    public String getLabel() {
        return label.get();
    }
    
    public void setLabel(String label) {
        this.label.set(label);
    }
    
    public String getStyle() {
        return style.get();
    }
    
    public void setStyle(String style) {
        this.style.set(style);
    }
}