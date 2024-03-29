package uimlbuddy.model;

import java.util.ArrayList;

/**
 * Model 
 * @author Lyuben Dimitrov
 */
public class Content {

    private ArrayList constants = new ArrayList();

    public void addConstant(Constant constant) {
        constants.add(constant);
    }

    public ArrayList getConstants() {
        return constants;
    }
}
