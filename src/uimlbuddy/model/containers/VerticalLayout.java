package uimlbuddy.model.containers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import uimlbuddy.model.Part;

/**
 * Model class for a Vertical Layout
 *
 * @author Lyuben
 */
public class VerticalLayout extends Part {

    private final StringProperty id;
    private final StringProperty style;

    /**
     * Default constructor.
     */
    public VerticalLayout() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param id
     */
    public VerticalLayout(String id, String style) {
        this.id = new SimpleStringProperty(id);
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
    
    public String getStyle() {
        return style.get();
    }
    
    public void setStyle(String style) {
        this.style.set(style);
    }
}