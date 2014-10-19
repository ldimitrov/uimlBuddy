package uimlbuddy.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import uimlbuddy.model.Constant;
import uimlbuddy.model.Content;
import uimlbuddy.model.Document;
import uimlbuddy.model.Interface;
import uimlbuddy.model.Part;
import uimlbuddy.model.Property;
import uimlbuddy.model.Reference;
import uimlbuddy.model.Structure;
import uimlbuddy.model.Style;

/**
 * Read UIML file into object model
 *
 * @author Lyuben
 *
 */
public class DocumentReader {

    /**
     * Parse UIML file and generate object model using jDOM
     *
     * @param file
     * @return Document object model
     * @throws IOException
     * @throws JDOMException
     */
    public static Document Parser(File file) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        org.jdom.Document doc = builder.build(file);
        Element root = doc.getRootElement(); //uiml tag
        Element interfaceNode = root.getChild("interface"); //interface tag

        // reading structure
        Element structureNode = interfaceNode.getChild("structure"); // structure tag
        Structure structure = new Structure();
        extractParts(null, structureNode.getChildren().iterator(), structure);
        // reading style
        Element styleNode = interfaceNode.getChild("style"); // style tag
        Style style = new Style();
        extractStyle(styleNode.getChildren(), style.getProperties());
        // reading contetn
        Element contentNode = interfaceNode.getChild("content"); // style tag
        Content content = new Content();
        if (contentNode != null) {
            extractContent(contentNode.getChildren(), content.getConstants());
        }
		//TODO
        // reading behaviour
        //TODO
        Interface interphace = new Interface(structure, style, content);

        Document document = new Document(interphace);
        return document;
    }

    /**
     * Recursively extract parts of UIML structure. Only the first level of parts are
     * added directly to object model. Subsequent levels are added recursively to these
     * first level objects
     *
     * @param parent null if this is first level
     * @param itr
     * @param struct
     */
    private static void extractParts(Part parent, Iterator itr, Structure struct) {
        while (itr.hasNext()) {
            Object next = itr.next();
            if (next instanceof Element) {
                Element element = ((Element) next);
                Part part = new Part(element.getAttribute("id").getValue(), element.getAttribute("class").getValue());
                if (parent == null && struct != null) // add first level parts to object model
                {
                    struct.addPart(part);
                } else if (parent != null) // add not first level part to hierachy
                {
                    parent.addChildPart(part);
                }
                extractParts(part, element.getChildren().listIterator(), null);
            }
        }
    }

    private static void extractStyle(List styleList, ArrayList list) {
        Iterator itr = styleList.iterator();
        while (itr.hasNext()) {
            Element element = ((Element) itr.next());
            Property property = new Property(element.getAttributeValue("part-name"),
                    element.getAttributeValue("name"), element.getText());
            // Retrieving Child element
            Element el = element.getChild("reference");
            if (el != null) {
                String constentNm = el.getAttribute("constant-name").getValue();
                Reference ref = new Reference();
                ref.setConstantName(constentNm);
                property.setReference(ref);
            }
            list.add(property);
        }
    }

    private static void extractContent(List contentList, ArrayList list) {
        Iterator itr = contentList.iterator();
        while (itr.hasNext()) {
            Element element = ((Element) itr.next());
            Constant cons = new Constant(element.getAttributeValue("id"), element.getAttributeValue("label"), null);
            list.add(cons);
        }
    }
}
