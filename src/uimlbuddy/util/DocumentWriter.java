package uimlbuddy.util;

import java.io.IOException;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Lyuben
 */
public class DocumentWriter {

    public static Document document;

    public static void initialize() {
        try {
            if (document == null) {
                document = DocumentReader.domDocument;
                System.out.println("-------- " + document);
                if (document == null) {
                    SAXBuilder builder = new SAXBuilder();
                    document = builder.build(DocumentWriter.class.getResourceAsStream("/uimltemplate.uiml"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void addPart(String classType, String id) {

        Element root = document.getRootElement(); //uiml tag
        Element interfaceNode = root.getChild("interface"); //interface tag
        Element structureNode = interfaceNode.getChild("structure");
        Element partNode = null;
        if (DocumentReader.domDocument == null) {
            partNode = structureNode.getChild("part");
        } else {
            List<Element> lsp = structureNode.getChildren("part");
            for (Element element : lsp) {
                String str = element.getAttribute("class").getValue();
                if (str.equalsIgnoreCase("VerticalLayout") || str.equalsIgnoreCase("HorizontalLayout")) {
                    partNode = element;
                    break;
                }
            }
            if (partNode == null) {
                partNode = structureNode;
            }
        }
        Element part = new Element("part");
        part.setAttribute("id", id);
        part.setAttribute("class", classType);

        partNode.addContent(part);
    }

    public static void addProperty(String partName, String name, String text) {
        Element root = document.getRootElement(); //uiml tag
        Element interfaceNode = root.getChild("interface"); //interface tag
        Element styleNode = interfaceNode.getChild("style");
        Element prop = new Element("property");
        prop.setAttribute("part-name", partName);
        if (name.equalsIgnoreCase("style")) {
            prop.setAttribute("name", name);
            prop.setText(text);
        } else {
            Element ref = new Element("reference");
            ref.setAttribute("constant-name", partName);
            prop.addContent(ref);
            addContent(partName, text);
        }
        // Add Content and Constant
        styleNode.addContent(prop);
    }

    public static void addBehaviour(String id, String classType, String name) {
        Element root = document.getRootElement(); //uiml tag
        Element interfaceNode = root.getChild("interface"); //interface tag
        Element behaviorNode = interfaceNode.getChild("behavior");
        // Creating rule node
        Element ruleNode = new Element("rule");
        behaviorNode.addContent(ruleNode);

        Element conditionNode = new Element("condition");
        ruleNode.addContent(conditionNode);

        Element eventNode = new Element("event");
        eventNode.setAttribute("part-name", id);
        eventNode.setAttribute("class", classType);
        conditionNode.addContent(eventNode);

        Element actionNode = new Element("action");
        ruleNode.addContent(actionNode);

        Element callNode = new Element("call");
        callNode.setAttribute("name", name);
        actionNode.addContent(callNode);
    }

    private static void addContent(String id, String label) {
        Element root = document.getRootElement(); //uiml tag
        Element interfaceNode = root.getChild("interface"); //interface tag
        Element contentNode = interfaceNode.getChild("content");
        Element constantNode = new Element("constant");
        constantNode.setAttribute("id", id);
        constantNode.setAttribute("label", label);
        contentNode.addContent(constantNode);

    }

    public static void updateCanvas() {
        try {
            // Updating Canvas
            uimlbuddy.model.Document docs = DocumentReader.Parser(null, document);
            uimlbuddy.UimlBuddy.editorOverviewController.drawOnCanvas(docs);

            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, System.out);
            String generatedXml = xmlOutput.outputString(document);
            uimlbuddy.UimlBuddy.editorOverviewController.sourceEditor.setText(generatedXml);
        } catch (JDOMException | IOException ex) {
            ex.printStackTrace();
        }
    }

}
