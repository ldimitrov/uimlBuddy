package uimlbuddy.model;

/**
 * Interface contains the main parts of the UIML Document
 *
 * @author Lyuben
 */
class Interface {

    private Structure structure;
    private Style style;
    private Content content;
    private Behavior behavior;

    public Interface(Structure structure) {
        this.structure = structure;
    }

    public Interface(Structure structure, Style style, Content content, Behavior behavior) {
        this.structure = structure;
        this.style = style;
        this.content = content;
        this.behavior = behavior;
    }

    public Structure getStruct() {
        return structure;
    }

    public Style getStyle() {
        return style;
    }

    public Content getContnet() {
        return content;
    }

    public Behavior getBehavior() {
        return behavior;
    }
}
