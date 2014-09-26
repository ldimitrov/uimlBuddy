package uimlbuddy.model;

/**
 *
 * @author Lyuben
 */
class Property {
    private String id;
    private String propertyName;
    private String text;
    
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

}
