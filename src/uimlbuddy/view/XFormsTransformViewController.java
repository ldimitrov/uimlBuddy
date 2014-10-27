package uimlbuddy.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import uimlbuddy.UimlBuddy;

public class XFormsTransformViewController extends AnchorPane {

    @FXML // fx:id="resultTextArea"
    public TextArea resultTextArea;

    // Reference to the main application.
    private UimlBuddy uimlBuddy;
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert resultTextArea != null : "fx:id=\"resultTextArea\" was not injected: check your FXML file 'XFormsTransformView.fxml'.";
    }
    
        /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param uimlBuddy
     */
    public void setMainApp(UimlBuddy uimlBuddy) {
        this.uimlBuddy = uimlBuddy;
    }
}
