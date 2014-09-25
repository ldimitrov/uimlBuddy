package uimlbuddy.view;

import java.awt.Canvas;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.controlsfx.dialog.Dialogs;
import uimlbuddy.UimlBuddy;
import uimlbuddy.model.controlls.UimlButton;

/**
 * FXML Controller class
 *
 * @author Lyuben
 */
public class EditorOverviewController implements Initializable {

    @FXML
    private Accordion accordion;
    @FXML
    private TitledPane accContainers;
    @FXML
    private TitledPane accControlls;
    @FXML
    private TitledPane accMiscellaneous;
    @FXML
    private TextArea sourceEditor;
    @FXML 
    private Canvas canvasEditor;

    // Reference to the main application.
    private UimlBuddy uimlBuddy;
    
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
        } catch (Exception ex) {
            Logger.getLogger(RootLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles mouse click event for inserting a Vertical Layout
     * @param event 
     */
    @FXML
    private void handleVerticalLayout(MouseEvent event) {
        Dialogs.create()
                .title("Test")
                .masthead("OMG OMG OMG")
                .message("So insert a layout a?")
                .showWarning();
    }
    
    /**
     * Handles mouse click event for inserting a Button
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
     * Handles a mause click event for inserting a Label
     * @param event 
     */
    @FXML
    private void handleLabelNew(MouseEvent event) {
        Dialogs.create()
                .title("Insert Label")
                .masthead("NOT YET DONE")
                .message("Write the code, bitch!")
                .showWarning();
    }   

}
