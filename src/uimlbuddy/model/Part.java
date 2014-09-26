package uimlbuddy.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Lyuben
 */
public class Part {

    private final StringProperty id;
    private final StringProperty classType;

    /**
     * Default constructor.
     */
    public Part() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param id
     * @param classType
     */
    public Part(String id, String classType) {
        this.id = new SimpleStringProperty(id);
        this.classType = new SimpleStringProperty(classType);
    }
    
    public String getId() {
        return id.get();
    }
    
    public void setId(String id) {
        this.id.set(id);
    }
    
    public String getClassType() {
        return classType.get();
    }
    
    public void setClassType(String label) {
        this.classType.set(label);
    }
}
