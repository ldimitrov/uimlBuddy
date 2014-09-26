package uimlbuddy.model;

/**
 * Object model of UIML file
 *
 * @author Lyuben Dimitrov
 */
public class Document {

    private Interface interFace;

    /**
     *
     * @param interphace
     */
    public Document(Interface interphace) {
        this.interFace = interphace;
    }

    /**
     * Get part structure
     * @return 
     */
    public Structure getStructure() {
        return interFace.getStruct();
    }

    /**
     * Get style for parts
     * @return 
     */
    public Style getStyle() {
        return interFace.getStyle();
    }
    
    /**
     * Get content for parts
     * @return
     */
    public Content getContent() {
        return interFace.getContnet();
    }
    
    /**
     * Get behavior for parts
     * @return
     */
    public Behavior getBehavior() {
        return interFace.getBehavior();
    }
}
