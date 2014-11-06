package uimlbuddy.model.controlls;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import uimlbuddy.model.Part;

/**
 * Model class for a UIML Drop Down Button
 *
 * @author Lyuben
 */
public class UimlDropdown extends Part {

    private final StringProperty id;
    private final StringProperty label;
    private final StringProperty optionOne;
    private final StringProperty optionTwo;
    private final StringProperty style;

    /**
     * Default constructor.
     */
    public UimlDropdown() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param id
     * @param label
     */
    public UimlDropdown(String id, String label) {
        this.id = new SimpleStringProperty(id);
        this.label = new SimpleStringProperty(label);
        this.optionOne = new SimpleStringProperty();
        this.optionTwo = new SimpleStringProperty();
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
        return label.get();
    }

    public void setStyle(String style) {
        this.style.set(style);
    }

    public String getOptionOne() {
        return optionOne.get();
    }

    public void setOptionOne(String optionOne) {
        this.optionOne.set(optionOne);
    }

    public String getOptionTwo() {
        return optionTwo.get();
    }

    public void setOptioTwo(String optionTwo) {
        this.optionTwo.set(optionTwo);
    }
}
