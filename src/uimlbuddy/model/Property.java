package uimlbuddy.model;

/**
 *
 * @author Lyuben Dimitrov
 */
public class Property {
    private String id;
    private String propertyName;
    private String text;
    private Reference reference;
    
    public Property(String id, String propertyName, String text) {
        this.id = id;
        this.propertyName = propertyName;
        this.text = text;
    }
    
    public String getId() {
        return id;
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public String getText() {
        return text;
    }

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }

}
