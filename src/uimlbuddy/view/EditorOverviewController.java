package uimlbuddy.view;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import uimlbuddy.model.Constant;
import uimlbuddy.UimlBuddy;
import uimlbuddy.model.Document;
import uimlbuddy.model.Part;
import uimlbuddy.model.Property;
import uimlbuddy.model.containers.HorizontalLayout;
import uimlbuddy.model.containers.VerticalLayout;
import uimlbuddy.model.controlls.UimlButton;
import uimlbuddy.model.controlls.UimlDropdown;
import uimlbuddy.model.controlls.UimlImageButton;
import uimlbuddy.model.controlls.UimlLabel;
import uimlbuddy.model.controlls.UimlTextInput;
import uimlbuddy.util.DocumentReader;
import uimlbuddy.util.DocumentWriter;
import uimlbuddy.util.Helper;

/**
 * FXML Controller class
 *
 * @author Lyuben
 */
public class EditorOverviewController implements Initializable {

    @FXML
    public TextArea sourceEditor;

    @FXML
    private Pane canvasEditor;

    // Reference to the main application.
    private UimlBuddy uimlBuddy;
    private VBox stVbox;
    private HBox stHbox;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        sourceEditor.setText("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
//                + "\n<uiml>\n<interface>"
//                + "\n\t<structure>\n\n\t</structure>"
//                + "\n\n\t<style>\n\n\t</style>"
//                + "\n\n\t<content>\n\n\t</content>"
//                + "\n\n\t<behavior>\n\n\t</behavior>"
//                + "\n</interface>"
//                + "\n</uiml>");
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param uimlBuddy
     */
    public void setMainApp(UimlBuddy uimlBuddy) {
        this.uimlBuddy = uimlBuddy;
    }

    /**
     * Loads a UIML document from a specified file.
     *
     * @param file
     * @throws java.io.IOException
     */
    public void loadUimlFile(File file) throws IOException {
        System.out.println("LoadUimlFile");
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis)) {
            while (bis.available() > 0) {
                sb.append((char) bis.read());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            sourceEditor.setText(sb.toString());
            sourceEditor.setWrapText(true);
            // Parsing file and loading into memory
            Document doc = DocumentReader.Parser(file, null);
            // Calling method for draw the component on canvas
            System.out.println("Calling Draw on canvas");
            drawOnCanvas(doc);
        } catch (Exception ex) {
            Logger.getLogger(RootLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles mouse click event for inserting a Vertical Layout
     *
     * @param event
     */
    @FXML
    private void handleVerticalLayout(MouseEvent event) {
        DocumentWriter.initialize();
        VerticalLayout vl = new VerticalLayout();
        boolean okClicked = uimlBuddy.showVerticalLayoutDialog(vl);
        if (okClicked) {
            uimlBuddy.getVerticalLayouts().add(vl);
        }
    }

    @FXML
    private void handleHorizontalLayout(MouseEvent event) {
        DocumentWriter.initialize();
        HorizontalLayout hl = new HorizontalLayout();
        boolean okClicked = uimlBuddy.showHorizontalLayoutDialog(hl);
        if (okClicked) {
            uimlBuddy.getHorizontalLayouts().add(hl);
        }
    }

    /**
     * Handles mouse click event for inserting a Button
     *
     * @param event
     */
    @FXML
    private void handleButtonNew(MouseEvent event) {
        UimlButton uimlButton = new UimlButton();
        boolean okClicked = uimlBuddy.showUimlButtonDialog(uimlButton);
        if (okClicked) {
            uimlBuddy.getUimlButtons().add(uimlButton);
        }
    }

    /**
     * Handles a mouse click event for inserting a Label
     *
     * @param event
     */
    @FXML
    private void handleImageButtonNew(MouseEvent event) {
        UimlImageButton uimlImageButton = new UimlImageButton();
        boolean okClicked = uimlBuddy.showUimlImageButtonDialog(uimlImageButton);
        if (okClicked) {
            uimlBuddy.getUimlImageButtons().add(uimlImageButton);
        }
    }

    /**
     * Handles a mouse click event for inserting a Label
     *
     * @param event
     */
    @FXML
    private void handleLabelNew(MouseEvent event) {
        UimlLabel uimlLabel = new UimlLabel();
        boolean okClicked = uimlBuddy.showUimlLabelDialog(uimlLabel);
        if (okClicked) {
            uimlBuddy.getUimlLabels().add(uimlLabel);
        }
    }

    /**
     * Handles a mouse click event for inserting a Text Input
     *
     * @param event
     */
    @FXML
    private void handleTextInputNew(MouseEvent event) {
        UimlTextInput uimlTextInput = new UimlTextInput();
        boolean okClicked = uimlBuddy.showUimlTextInputDialog(uimlTextInput);
        if (okClicked) {
            uimlBuddy.getUimlTextInputs().add(uimlTextInput);
        }
    }

    /**
     * Handles a mouse click event for inserting a Dropdown Button
     *
     * @param event
     */
    @FXML
    private void handleDropdownNew(MouseEvent event) {
        UimlDropdown dropdown = new UimlDropdown();
        boolean okClicked = uimlBuddy.showUimlDropdownDialog(dropdown);
        if (okClicked) {
            uimlBuddy.getUimlDropdowns().add(dropdown);
        }
    }

    /**
     * Handle parsing of uiml document and initialize initial values
     *
     * @param document
     */
    public void drawOnCanvas(Document document) {
        System.out.println("Inside Draw on Canvas Method");
        // Setting static variable to null because when second time uiml file is opened it should not append controls
        stHbox = null;
        stVbox = null;
        if (canvasEditor.getChildren().size() > 0) {
            canvasEditor.getChildren().remove(0);
        }
        //Retrieving Property from the style tag for the Part
        ArrayList<Property> properties = document.getStyle().getProperties();
        // Store Property in to helper class
        Helper.storePropety(properties);
        // Retrieving Constant
        ArrayList<Constant> contants = document.getContent().getConstants();
        //Convering Constants to map
        Helper.convertConstantListToMap(contants);
        //Retrieving Parrent Part from the style tag
        ArrayList<Part> layoutparts = document.getStructure().getParts();
        //Iterating over 
        for (int i = 0; i < layoutparts.size(); i++) {
            VBox vbox = null;
            HBox hbox = null;
            Part p = layoutparts.get(i);
            String layout = p.getClassType();
            switch (layout) {
                case "VerticalLayout":
                    vbox = new VBox();
                    break;
                case "HorizontalLayout":
                    hbox = new HBox();
                    break;
                default:
                    vbox = new VBox();
                    break;
            }
            System.out.println("part size " + p.getChildParts().size() + "  part id " + p.getId());
            if (p.getChildParts().size() > 0) {
                drawControls(p.getChildParts().iterator(), vbox, hbox, 0);
            }
        }
        System.out.println("Control drawing completed");
    }

    /**
     * drawControls method called by drawCanvas method to draw the component
     * this method will call itself It will do the recursive approach to draw
     * controls
     *
     * @param itr
     * @param vbox
     * @param hbox
     * @param i
     */
    private void drawControls(Iterator<Part> itr, VBox vbox, HBox hbox, int i) {
        Border border = new Border(new BorderStroke(Paint.valueOf("Black"), BorderStrokeStyle.SOLID, new CornerRadii(2), BorderWidths.DEFAULT));
        if (vbox != null && i == 0) {
            vbox.setPadding(new Insets(10));
            vbox.setSpacing(8);
            vbox.setBorder(border);
            stVbox = vbox;
            canvasEditor.getChildren().add(stVbox);
        } else if (hbox != null && i == 0) {
            hbox.setPadding(new Insets(15, 12, 15, 12));
            hbox.setSpacing(10);
            hbox.setBorder(border);
            stHbox = hbox;
            canvasEditor.getChildren().add(stHbox);
        } else {
            if (vbox != null) {
                vbox.setPadding(new Insets(10));
                vbox.setSpacing(8);
                vbox.setBorder(border);
                stVbox.getChildren().add(vbox);
            } else if (hbox != null) {
                hbox.setPadding(new Insets(15, 12, 15, 12));
                hbox.setSpacing(10);
                hbox.setBorder(border);
                stVbox.getChildren().add(hbox);
            }
        }
        // Looping over Parrent Conatainer which contains Layouts
        while (itr.hasNext()) {
            Part part = itr.next();
            System.out.println("--- " + part.getClassType() + "   ** " + part.getId());
            if (part.getClassType().equals("VerticalLayout")) {
                drawControls(part.getChildParts().iterator(), new VBox(), null, 1);
            } else if (part.getClassType().equals("HorizontalLayout")) {
                drawControls(part.getChildParts().iterator(), null, new HBox(), 1);
            } else if (part.getClassType().equals("Button")) {
                System.out.println("Class Type Button");
                Button btn = new Button();
                // Calling method for applying property
                applyPropertyOnButton(btn, part.getId());
                if (vbox != null) {
                    vbox.getChildren().add(btn);
                } else if (hbox != null) {
                    hbox.getChildren().add(btn);
                }
            } else if (part.getClassType().equals("Label")) {
                System.out.println("Class Type Label");
                Label lbl = new Label();
                // Calling method for applying property
                applyPropertyOnLabel(lbl, part.getId());
                if (vbox != null) {
                    vbox.getChildren().add(lbl);
                } else if (hbox != null) {
                    hbox.getChildren().add(lbl);
                }

            } else if (part.getClassType().equals("ImageButton")) {
                System.out.println("Class Type Image Button");
                Image img = new Image("/assets/ImageView@2x.png");
                // Calling method for applying property
                Canvas canvas = new Canvas(50, 50);
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.drawImage(img, 0, 0);
                if (vbox != null) {
                    vbox.getChildren().add(canvas);
                } else if (hbox != null) {
                    hbox.getChildren().add(canvas);
                }

            } else if (part.getClassType().equals("TextInput")) {
                System.out.println("Class type TextInput");
                TextField txtInp = new TextField();
                Label lbl = new Label();
                applyPropertyOnTextField(txtInp, part.getId());
                if (vbox != null) {
                    vbox.getChildren().add(txtInp);
                } else if (hbox != null) {
                    hbox.getChildren().add(txtInp);
                }
            } else if (part.getClassType().equals("Select")) {
                System.out.println("Class type Select - dropdown");
                ComboBox combo = new ComboBox();
                applyPropertyOnDropdown(combo, part.getId());
                if (vbox != null) {
                    vbox.getChildren().add(combo);
                } else if (hbox != null) {
                    hbox.getChildren().add(combo);
                }
            }
        }
    }

    private void applyPropertyOnButton(Button btn, String porpID) {
        System.out.println("Inside Apply Property On Button method porpId " + porpID);

        // Setting button common property
        btn.setTextFill(Paint.valueOf("Green"));
        Border bord = new Border(new BorderStroke(Paint.valueOf("Blue"), BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT));
        btn.setBorder(bord);
        Constant cons = Helper.getConstant().get(porpID);
        if (cons != null) {
            btn.setText(cons.getConstantName());
        }
        // Retrieving list of property
        List<Property> propLs = Helper.getProperty(porpID);
        Iterator<Property> propItr = propLs.iterator();
        while (propItr.hasNext()) {
            Property property = propItr.next();
            String name = property.getPropertyName();
            if (name != null && name.equals("label")) {
                btn.setText(property.getText());
            } else if (name != null && name.equals("style")) {
                btn.setStyle(property.getText());
            }
        }
    }

    private void applyPropertyOnLabel(Label lbl, String porpID) {
        System.out.println("Inside Apply Property On Label method porpId " + porpID);

        Constant cons = Helper.getConstant().get(porpID);
        if (cons != null) {
            lbl.setText(cons.getConstantName());
        }
        // Setting button common property
        lbl.setTextFill(Paint.valueOf("Green"));

        // Retrieving list of property
        List<Property> propLs = Helper.getProperty(porpID);
        Iterator<Property> propItr = propLs.iterator();
        while (propItr.hasNext()) {
            Property property = propItr.next();
            String name = property.getPropertyName();
            if (name != null && name.equals("label")) {
                lbl.setText(property.getText());
            } else if (name != null && name.equals("style")) {
                lbl.setStyle(property.getText());
            }
        }
    }

    private void applyPropertyOnTextField(TextField txtInp, String porpID) {
        Constant cons = Helper.getConstant().get(porpID);
        if (cons != null) {
            txtInp.setText(cons.getConstantName());
        }

        List<Property> propLs = Helper.getProperty(porpID);
        Iterator<Property> propItr = propLs.iterator();

        while (propItr.hasNext()) {
            Property property = propItr.next();
            String name = property.getPropertyName();
            if (name != null && name.equals("label")) {
                txtInp.setText(property.getText());
            } else if (name != null && name.equals("style")) {
                txtInp.setStyle(property.getText());
            }
        }
    }
    
    private void applyPropertyOnDropdown(ComboBox combo, String porpID) {
        Constant cons = Helper.getConstant().get(porpID);
        if (cons != null) {
            combo.getItems().addAll("Option", "Option");
        }

        List<Property> propLs = Helper.getProperty(porpID);
        Iterator<Property> propItr = propLs.iterator();
    }
}
