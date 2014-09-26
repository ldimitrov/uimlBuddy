package uimlbuddy.model;

import java.util.ArrayList;

/**
 *
 * @author Lyuben
 */
class Structure {
    private ArrayList parts = new ArrayList();
    
    public void addPart(Part part) {
        parts.add(part);
    }
    
    public ArrayList getParts() {
        return parts;
    }
}
