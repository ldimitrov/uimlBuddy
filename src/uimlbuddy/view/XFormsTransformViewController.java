package uimlbuddy.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import uimlbuddy.UimlBuddy;
/**
 * FXML Controller class for Transformations process and window
 *
 * @author Lyuben Dimitrov
 */

public class XFormsTransformViewController extends AnchorPane {

    @FXML // fx:id="resultTextArea"
    public TextArea resultTextArea;

    // Reference to the main application.
    private UimlBuddy uimlBuddy;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert resultTextArea != null : "fx:id=\"resultTextArea\" was not injected: check your FXML file 'XFormsTransformView.fxml'.";
        StringBuilder contents = new StringBuilder();

        try {
            BufferedReader input = new BufferedReader(new FileReader("src/output.html"));
            try {
                String line = null;
                while ((line = input.readLine()) != null) {
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        resultTextArea.setText(contents.toString());
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
