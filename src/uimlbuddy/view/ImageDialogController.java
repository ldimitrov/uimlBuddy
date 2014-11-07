package uimlbuddy.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import uimlbuddy.model.controlls.UimlImage;
import uimlbuddy.util.DocumentWriter;

public class ImageDialogController {

    @FXML // fx:id="sourceField"
    private TextField sourceField;

    @FXML // fx:id="idField"
    private TextField idField;
    
    @FXML // fx:id="altField"
    private TextField altField;

    @FXML // fx:id="browseButton"
    private Button browseButton;

    @FXML // fx:id="styleField"
    private TextField styleField;
    
    private Stage dialogStage;
    private UimlImage uimlImage;
    private boolean okClicked = false;
    private DocumentWriter dw;

    public ImageDialogController() {
        dw = new DocumentWriter();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert sourceField != null : "fx:id=\"sourceField\" was not injected: check your FXML file 'ImageDialog.fxml'.";
        assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'ImageDialog.fxml'.";
        assert browseButton != null : "fx:id=\"browseButton\" was not injected: check your FXML file 'ImageDialog.fxml'.";
        assert styleField != null : "fx:id=\"styleField\" was not injected: check your FXML file 'ImageDialog.fxml'.";
        assert altField != null : "fx:id=\"altField\" was not injected: check your FXML file 'ImageDialog.fxml'.";
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
     * Sets the image to be edited in the dialog.
     *
     * @param uimlImage
     */
    public void setImage(UimlImage uimlImage) {
        this.uimlImage = uimlImage;

        idField.setText(uimlImage.getId());
        sourceField.setText(uimlImage.getSource());
        styleField.setText(uimlImage.getStyle());
    }
    
    @FXML
    void onBrowseClick() {

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
            uimlImage.setId(idField.getText());
            uimlImage.setSource(uimlImage.getSource());
            uimlImage.setStyle(styleField.getText());

            DocumentWriter.addPart("Image", idField.getText());
            // Adding Source content and alt content
            DocumentWriter.addContentSource(idField.getText(), sourceField.getText());
            DocumentWriter.addContentAlternative(idField.getText(), altField.getText());
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
        if (sourceField.getText() == null || sourceField.getText().length() == 0) {
            errorMessage += "Image Source cannot be empty!\n";
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
