package uimlbuddy.model;

import java.util.ArrayList;

/**
 * Model for style tag
 * @author Lyuben Dimitrov
 */
public class Style {

    private ArrayList properties = new ArrayList();

    public void addProperty(Property property) {
        properties.add(property);
    }

    public ArrayList getProperties() {
        return properties;
    }
}
