package uimlbuddy.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import uimlbuddy.model.containers.VerticalLayout;
import uimlbuddy.util.DocumentWriter;

public class VerticalLayoutController {

    @FXML // fx:id="idField"
    private TextField idField; // Value injected by FXMLLoader
    
    @FXML
    private TextField styleField;
    
    private Stage dialogStage;
    private VerticalLayout vl;
    private boolean okClicked = false;
    private DocumentWriter dw;

    public VerticalLayoutController() {
        dw = new DocumentWriter();
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'VerticalLayout.fxml'.";
        assert styleField != null : "fx:id=\"styleField\" was not injected: check your FXML file 'VerticalLayout.fxml'.";
    }
    
    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Sets the vertical layout to be edited in the dialog.
     *
     * @param vl
     */
    public void setVerticalLayout(VerticalLayout vl) {
        this.vl = vl;

        idField.setText(vl.getId());
    }
    

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    /**
     * Called when the user clicks ok.
     */
    @FXML
    void handleOk() {
        if (isInputValid()) {
            vl.setId(idField.getText());
            
            DocumentWriter.addPart("VerticalLayout", idField.getText());
            // Adding Style
            if (styleField.getText().isEmpty()) {
                System.out.println("No style provided");
            } else {
                DocumentWriter.addProperty(idField.getText(), "style", styleField.getText());
            }
            
            // Drawing on Canvas
            DocumentWriter.updateCanvas();
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        // TODO - make check in future for unique IDs.
        if (idField.getText() == null || idField.getText().length() == 0) {
            errorMessage += "ID is required!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message
            Dialogs.create()
                    .title("Invalid ID Field")
                    .masthead("Please Enter a valid ID")
                    .message(errorMessage)
                    .showError();
            return false;
        }
    }
}
