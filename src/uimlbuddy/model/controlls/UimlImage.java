package uimlbuddy.model.controlls;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import uimlbuddy.model.Part;

/**
 * Model class for a UIML Image
 *
 * @author Lyuben
 */
public class UimlImage extends Part {

    private final StringProperty id;
    private final StringProperty source;
    private final StringProperty style;
    private final StringProperty alt;

    /**
     * Default constructor.
     */
    public UimlImage() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param id
     * @param source
     */
    public UimlImage(String id, String source) {
        this.id = new SimpleStringProperty(id);
        this.source = new SimpleStringProperty(source);

        this.style = new SimpleStringProperty();
        this.alt = new SimpleStringProperty();
    }

    @Override
    public String getId() {
        return id.get();
    }

    @Override
    public void setId(String id) {
        this.id.set(id);
    }

    public String getSource() {
        return source.get();
    }

    public void setSource(String source) {
        this.source.set(source);
    }

    public String getStyle() {
        return style.get();
    }

    public void setStyle(String style) {
        this.style.set(style);
    }
    
    public String getAlt() {
        return alt.get();
    }

    public void setAlt(String alt) {
        this.alt.set(alt);
    }
}