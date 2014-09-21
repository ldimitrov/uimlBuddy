package uimlbuddy.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import org.controlsfx.dialog.Dialogs;
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
        uimlEditor.setText("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "\n<uiml>\n<interface>"
                + "\n\t<structure>\n\n\t</structure>"
                + "\n\n\t<style>\n\n\t</style>"
                + "\n\n\t<content>\n\n\t</content>"
                + "\n\n\t<behavior>\n\n\t</behavior>"
                + "\n</interface>"
                + "\n</uiml>");
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
        Dialogs.create()
                .title("Test")
                .masthead("OMG OMG OMG")
                .message("So insert a layout a?")
                .showWarning();
    }

}
