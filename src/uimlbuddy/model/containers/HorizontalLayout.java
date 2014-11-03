package uimlbuddy.model.containers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import uimlbuddy.model.Part;

/**
 * Model class for a Horizontal Layout
 *
 * @author Lyuben
 */
public class HorizontalLayout extends Part {

    private final StringProperty id;
    private final StringProperty style;

    /**
     * Default constructor.
     */
    public HorizontalLayout() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param id
     * @param style
     */
    public HorizontalLayout(String id, String style) {
        this.id = new SimpleStringProperty(id);
        this.style = new SimpleStringProperty(style);
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