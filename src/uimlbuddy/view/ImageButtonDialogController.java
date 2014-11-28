package uimlbuddy.view;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import uimlbuddy.model.controlls.UimlImageButton;
import uimlbuddy.util.DocumentWriter;

/**
 * FXML Controller class for ImageButton View
 *
 * @author Lyuben Dimitrov
 */

public class ImageButtonDialogController {

    @FXML // fx:id="idField"
    private TextField idField;

    @FXML // fx:id="onClickField"
    private TextField onClickField;

    @FXML // fx:id="labelField"
    private TextField labelField;

    @FXML // fx:id="sourceField"
    private TextField sourceField;

    @FXML // fx:id="styleField"
    private TextField styleField;

    @FXML // fx:id="browseButton"
    private Button browseButton;

    private Stage dialogStage;
    private UimlImageButton uimlImageButton;
    private boolean okClicked = false;
    private DocumentWriter dw;
    private File file;
    private FileChooser fileChooser;
    private FileChooser.ExtensionFilter extFilterAll;

    public ImageButtonDialogController() {
        dw = new DocumentWriter();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'ImageButtonDialog.fxml'.";
        assert onClickField != null : "fx:id=\"onClickField\" was not injected: check your FXML file 'ImageButtonDialog.fxml'.";
        assert labelField != null : "fx:id=\"labelField\" was not injected: check your FXML file 'ImageButtonDialog.fxml'.";
        assert browseButton != null : "fx:id=\"browseButton\" was not injected: check your FXML file 'ImageButtonDialog.fxml'.";
        assert sourceField != null : "fx:id=\"sourceField\" was not injected: check your FXML file 'ImageButtonDialog.fxml'.";
        assert styleField != null : "fx:id=\"styleField\" was not injected: check your FXML file 'ImageButtonDialog.fxml'.";
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
     * @param uimlImageButton
     */
    public void setImageButton(UimlImageButton uimlImageButton) {
        this.uimlImageButton = uimlImageButton;

        idField.setText(uimlImageButton.getId());
        labelField.setText(uimlImageButton.getLabel());
        styleField.setText(uimlImageButton.getStyle());
        sourceField.setText(uimlImageButton.getSource());
        onClickField.setText(uimlImageButton.getOnClick());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    void onBrowseClick() {
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        extFilterAll = new FileChooser.ExtensionFilter("All Files", "*.*");

        fileChooser.getExtensionFilters().addAll(
                extFilterAll,
                new FileChooser.ExtensionFilter("JPG (*.jpg)", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG (*.jpeg)", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG (*.png)", "*.png"),
                new FileChooser.ExtensionFilter("GIF (*.gif)", "*.gif")
        );

        file = fileChooser.showOpenDialog(dialogStage.getScene().getWindow());

        if (file != null) {
            System.out.println("Image path" + file.getAbsolutePath());
            sourceField.setText(file.getAbsolutePath());
        }
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            uimlImageButton.setId(idField.getText());
            uimlImageButton.setLabel(labelField.getText());
            uimlImageButton.setStyle(styleField.getText());
            uimlImageButton.setSource(sourceField.getText());
            uimlImageButton.setOnClick(onClickField.getText());

            DocumentWriter.addPart("ImageButton", idField.getText());
            // Adding Label
            DocumentWriter.addContentLabel(idField.getText(), labelField.getText());
            //Adding Style
            DocumentWriter.addProperty(idField.getText(), "style", styleField.getText());
            // Adding source to content
            DocumentWriter.addContentImageButton(idField.getText(), "src", sourceField.getText());
            //Adding Behavior
            DocumentWriter.addBehaviour(idField.getText(), "ButtonPressed", onClickField.getText());

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
            errorMessage += "Not a valid source for image!\n";
        }
        if (onClickField.getText() == null || onClickField.getText().length() == 0) {
            errorMessage += "An onClick event is required!\n";
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
