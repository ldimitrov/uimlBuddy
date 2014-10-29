package uimlbuddy.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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

    public void showResult() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("XFormsTransformView.fxml"));
            Parent xformsView = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("XForms Result");
            stage.getIcons().add(new Image("/assets/preferences.png"));
            stage.setScene(new Scene(xformsView));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(XFormsTransformViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
