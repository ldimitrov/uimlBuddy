package uimlbuddy.model.controlls;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import uimlbuddy.model.Part;

/**
 * Model class for a UIML Label
 *
 * @author Lyuben
 */
public class UimlLabel extends Part {

    private final StringProperty id;
    private final StringProperty text;
    private final StringProperty style;
    private final StringProperty classType;

    /**
     * Default constructor.
     */
    public UimlLabel() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param id
     * @param label
     */
    public UimlLabel(String id, String label) {
        this.id = new SimpleStringProperty(id);
        this.text = new SimpleStringProperty(label);

        this.style = new SimpleStringProperty();
        this.classType = new SimpleStringProperty();
    }

    @Override
    public String getId() {
        return id.get();
    }

    @Override
    public void setId(String id) {
        this.id.set(id);
    }

    public String getText() {
        return text.get();
    }

    public void setText(String label) {
        this.text.set(label);
    }

    public String getStyle() {
        return style.get();
    }

    public void setStyle(String style) {
        this.style.set(style);
    }

    @Override
    public String getClassType() {
        return "Label";
    }

    @Override
    public void setClassType(String classType) {
        this.classType.set(classType);
    }
}