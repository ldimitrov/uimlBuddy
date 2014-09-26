package uimlbuddy.model;

/**
 *
 * @author Lyuben
 */
class Constant {

    private String id;
    private String constantName;
    private String text;

    public Constant(String id, String propertyName, String text) {
        this.id = id;
        this.constantName = propertyName;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getConstantName() {
        return constantName;
    }

    public String getText() {
        return text;
    }

}
