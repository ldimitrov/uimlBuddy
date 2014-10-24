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

    /**
     * Default constructor.
     */
    public VerticalLayout() {
        this(null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param id
     */
    public VerticalLayout(String id) {
        this.id = new SimpleStringProperty(id);
    }

    @Override
    public String getId() {
        return id.get();
    }

    @Override
    public void setId(String id) {
        this.id.set(id);
    }
}