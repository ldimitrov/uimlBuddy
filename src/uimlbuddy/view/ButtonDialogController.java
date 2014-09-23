package uimlbuddy.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import uimlbuddy.model.controlls.UimlButton;

/**
 * Dialog to create/edit UIML buttons
 *
 * @author Lyuben
 */
public class ButtonDialogController implements Initializable {
    @FXML
    private TextField idField;
    @FXML
    private TextField labelField;
    @FXML
    private TextField styleField;
    @FXML
    private TextField onClickField;
    
    private Stage dialogStage;
    private UimlButton uimlButton;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
     * @param uimlButton
     */
    public void setButton(UimlButton uimlButton) {
        this.uimlButton = uimlButton;

        idField.setText(uimlButton.getId());
        labelField.setText(uimlButton.getLabel());
        styleField.setText(uimlButton.getStyle());
        onClickField.setText(uimlButton.getOnClick());
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
           uimlButton.setId(idField.getText());
           uimlButton.setLabel(labelField.getText());
           uimlButton.setStyle(styleField.getText());
           uimlButton.setOnClick(onClickField.getText());

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
        if(idField.getText() == null || idField.getText().length() == 0) {
            errorMessage += "ID is required!\n";
        }
        if(labelField.getText() == null || labelField.getText().length() == 0) {
            errorMessage += "Not a valid label!\n";
        }
        if(onClickField.getText() == null || onClickField.getText().length() == 0) {
            errorMessage += "An onClick event is required!\n";
        }
        
        if(errorMessage.length() == 0) {
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
