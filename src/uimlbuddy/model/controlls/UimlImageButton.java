package uimlbuddy.model.controlls;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import uimlbuddy.model.Part;

/**
 * Model class for a UIML Image Button
 * 
 * @author Lyuben
 */
public class UimlImageButton extends Part {
    
    private final StringProperty id;    
    private final StringProperty label;
    private final StringProperty source;
    private final StringProperty onClick;
    
    /**
     * Default constructor.
     */
    public UimlImageButton() {
        this(null, null);
    }
    
    /**
     * Constructor with some initial data.
     * 
     * @param id
     * @param label
     */
    public UimlImageButton(String id, String label) {
        this.id = new SimpleStringProperty(id);
        this.label = new SimpleStringProperty(label);
        
        this.source = new SimpleStringProperty();
        this.onClick = new SimpleStringProperty();
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
    
    public String getSource() {
        return source.get();
    }
    
    public void setSource(String style) {
        this.source.set(style);
    }
    
    public String getOnClick() {
        return onClick.get();
    }
    
    public void setOnClick(String onClick) {
        this.onClick.set(onClick);
    }
}
