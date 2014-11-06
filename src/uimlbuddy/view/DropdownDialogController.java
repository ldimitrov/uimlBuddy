package uimlbuddy.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import uimlbuddy.model.controlls.UimlDropdown;
import uimlbuddy.util.DocumentWriter;

public class DropdownDialogController {

    @FXML // fx:id="idField"
    private TextField idField;

    @FXML // fx:id="firstOptionField"
    private TextField firstOptionField;

    @FXML // fx:id="secondOptionField"
    private TextField secondOptionField;

    @FXML // fx:id="styleField"
    private TextField styleField;

    @FXML // fx:id="labelField"
    private TextField labelField;

    private Stage dialogStage;
    private UimlDropdown dropdown;
    private boolean okClicked = false;
    private DocumentWriter dw;

    public DropdownDialogController() {
        dw = new DocumentWriter();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'DropdownDialog.fxml'.";
        assert firstOptionField != null : "fx:id=\"firstOptionField\" was not injected: check your FXML file 'DropdownDialog.fxml'.";
        assert secondOptionField != null : "fx:id=\"secondOptionField\" was not injected: check your FXML file 'DropdownDialog.fxml'.";
        assert styleField != null : "fx:id=\"styleField\" was not injected: check your FXML file 'DropdownDialog.fxml'.";
        assert labelField != null : "fx:id=\"labelField\" was not injected: check your FXML file 'DropdownDialog.fxml'.";
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
     * @param dropdown
     */
    public void setDropdown(UimlDropdown dropdown) {
        this.dropdown = dropdown;

        idField.setText(dropdown.getId());
        labelField.setText(dropdown.getLabel());
        styleField.setText(dropdown.getStyle());
        firstOptionField.setText(dropdown.getOptionOne());
        secondOptionField.setText(dropdown.getOptionTwo());
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
            dropdown.setId(idField.getText());
            dropdown.setLabel(labelField.getText());
            dropdown.setStyle(styleField.getText());
            dropdown.setOptionOne(firstOptionField.getText());
            dropdown.setOptioTwo(secondOptionField.getText());

            DocumentWriter.addComplexPart("Select", idField.getText());
            //DocumentWriter.addPart("Select", idField.getText());
            DocumentWriter.addContentDropDownOptions("opt1", firstOptionField.getText());
            DocumentWriter.addContentDropDownOptions("opt2", secondOptionField.getText());
            // Adding Label
            DocumentWriter.addContentLabel(idField.getText(), labelField.getText());
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
