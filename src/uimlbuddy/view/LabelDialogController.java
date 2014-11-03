package uimlbuddy.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import uimlbuddy.model.controlls.UimlLabel;
import uimlbuddy.util.DocumentWriter;

public class LabelDialogController {

    @FXML // fx:id="idField"
    private TextField idField;

    @FXML // fx:id="styleField"
    private TextField styleField;

    @FXML // fx:id="labelField"
    private TextField textField;

    private Stage dialogStage;
    private UimlLabel uimlLabel;
    private boolean okClicked = false;
    private DocumentWriter dw;
    
    public LabelDialogController() {
        dw = new DocumentWriter();
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'LabelDialog.fxml'.";
        assert styleField != null : "fx:id=\"styleField\" was not injected: check your FXML file 'LabelDialog.fxml'.";
        assert textField != null : "fx:id=\"labelField\" was not injected: check your FXML file 'LabelDialog.fxml'.";
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
     * Sets the label to be edited in the dialog.
     *
     * @param uimlLabel
     */
    public void setLabel(UimlLabel uimlLabel) {
        this.uimlLabel = uimlLabel;

        idField.setText(uimlLabel.getId());
        textField.setText(uimlLabel.getText());
        styleField.setText(uimlLabel.getStyle());
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
            uimlLabel.setId(idField.getText());
            uimlLabel.setText(textField.getText());
            uimlLabel.setStyle(styleField.getText());

            DocumentWriter.addPart("Label", idField.getText());
            // Adding Label
            DocumentWriter.addProperty(idField.getText(), "label", textField.getText());
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
        if (textField.getText() == null || textField.getText().length() == 0) {
            errorMessage += "Label cannot be empty!\n";
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
