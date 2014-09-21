package uimlbuddy.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import uimlbuddy.UimlBuddy;

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
    private TextArea uimlEditor;

    // Reference to the main application.
    private UimlBuddy uimlBuddy;
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param uimlBuddy
     */
    public void setMainApp(UimlBuddy uimlBuddy) {
        this.uimlBuddy = uimlBuddy;
    }

    @FXML
    private void handleVerticalLayout(MouseEvent event) {
    }
    
}
