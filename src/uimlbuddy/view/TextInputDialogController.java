package uimlbuddy.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import uimlbuddy.model.controlls.UimlTextInput;
import uimlbuddy.util.DocumentWriter;

public class TextInputDialogController {
    
    @FXML // fx:id="idField"
    private TextField idField;

    @FXML // fx:id="styleField"
    private TextField styleField;

    @FXML // fx:id="labelField"
    private TextField labelField;
    
    private Stage dialogStage;
    private UimlTextInput uimlTextInput;
    private boolean okClicked = false;
    private DocumentWriter dw;

    public TextInputDialogController() {
        dw = new DocumentWriter();
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'TextInputDialog.fxml'.";
        assert styleField != null : "fx:id=\"styleField\" was not injected: check your FXML file 'TextInputDialog.fxml'.";
        assert labelField != null : "fx:id=\"labelField\" was not injected: check your FXML file 'TextInputDialog.fxml'.";
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
     * Sets the button to be edited in the dialog.
     *
     * @param uimlTextInput
     */
    public void setTextInput(UimlTextInput uimlTextInput) {
        this.uimlTextInput = uimlTextInput;

        idField.setText(uimlTextInput.getId());
        labelField.setText(uimlTextInput.getLabel());
        styleField.setText(uimlTextInput.getStyle());
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
    private void handleOk() {
        if (isInputValid()) {
            uimlTextInput.setId(idField.getText());
            uimlTextInput.setLabel(labelField.getText());
            uimlTextInput.setStyle(styleField.getText());

            DocumentWriter.addPart("TextInput", idField.getText());
            // Adding Label
            DocumentWriter.addProperty(idField.getText(), "label", labelField.getText());
            // Adding Style
            DocumentWriter.addProperty(idField.getText(), "style", styleField.getText());

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
        if (labelField.getText() == null || labelField.getText().length() == 0) {
            errorMessage += "Not a valid label!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message
            Dialogs.create()
                    .title("Invalid Fields")
                    .masthead("Please correct the invalid fields")
                    .message(errorMessage)
                    .showError();
            return false;
        }
    }

   
}
