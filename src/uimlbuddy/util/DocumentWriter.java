package uimlbuddy.util;

import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author kundan
 */
public class DocumentWriter {

    public static Document document;

    public DocumentWriter() {
        try {
            if (document == null) {
                SAXBuilder builder = new SAXBuilder();
                document = builder.build(this.getClass().getResourceAsStream("/uimltemplate.uiml"));
            }
        } catch (JDOMException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void addPart(String classType, String id) {

        Element root = document.getRootElement(); //uiml tag
        Element interfaceNode = root.getChild("interface"); //interface tag
        Element structureNode = interfaceNode.getChild("structure");
        Element partNode = structureNode.getChild("part");

//        NodeList nl = document.getElementsByTagName("part");
//        Node nd = nl.item(0);
//        Element part = document.createElement("part");
//        part.setAttribute("class", classType);
//        part.setAttribute("id", id);
//        nd.appendChild(part);
//        
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
        prop.setAttribute("name", name);
        prop.setText(text);
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
