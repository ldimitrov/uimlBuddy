package uimlbuddy;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uimlbuddy.model.controlls.UimlButton;
import uimlbuddy.model.controlls.UimlImageButton;
import uimlbuddy.model.controlls.UimlLabel;
import uimlbuddy.model.controlls.UimlTextInput;
import uimlbuddy.view.ButtonDialogController;
import uimlbuddy.view.EditorOverviewController;
import uimlbuddy.view.ImageButtonDialogController;
import uimlbuddy.view.LabelDialogController;
import uimlbuddy.view.RootLayoutController;
import uimlbuddy.view.TextInputDialogController;

/**
 *
 * @author Lyuben
 */
public class UimlBuddy extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    // Controlls as an observable list collection - needed to sync the view with the data.
    private ObservableList<UimlButton> uimlButtons = FXCollections.observableArrayList();
    private ObservableList<UimlLabel> uimlLabels = FXCollections.observableArrayList();
    private ObservableList<UimlImageButton> uimlImageButtons = FXCollections.observableArrayList();
    private ObservableList<UimlTextInput> uimlTextInputs = FXCollections.observableArrayList();
    public static EditorOverviewController editorOverviewController;
    private ButtonDialogController buttonDialogController;
    private LabelDialogController labelDialogController;
    private ImageButtonDialogController imageButtonDialogController;
    private TextInputDialogController textInputDialogController;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("uimlBuddy");
        primaryStage.getIcons().add(new Image("/assets/app_icon.png"));

        initRootLayout();

        showEditorOverview();
    }

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    /**
     * Initializes the root layout.
     */
    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UimlBuddy.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
//            controller.setMainApp(this);            
            controller.selectedFileProperty().addListener((obs, oldFile, newFile) -> {
                if (newFile != null) {
                    try {
                        editorOverviewController.loadUimlFile(newFile);
                    } catch (IOException ex) {
                        Logger.getLogger(UimlBuddy.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the editor overview with all panels inside the root layout.
     */
    private void showEditorOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UimlBuddy.class.getResource("view/EditorOverview.fxml"));
            AnchorPane editorOverview = (AnchorPane) loader.load();

            // Set editor overview into the center of root layout.
            rootLayout.setCenter(editorOverview);

            UimlBuddy.editorOverviewController = loader.getController();
            editorOverviewController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the Buttons as an observable.
     *
     * @return
     */
    public ObservableList<UimlButton> getUimlButtons() {
        return uimlButtons;
    }

    /**
     * Returns the Labels as an observable.
     *
     * @return
     */
    public ObservableList<UimlLabel> getUimlLabels() {
        return uimlLabels;
    }

    /**
     * Returns the Image Buttons as an observable.
     *
     * @return
     */
    public ObservableList<UimlImageButton> getUimlImageButtons() {
        return uimlImageButtons;
    }
    
    /**
     * Returns the Image Buttons as an observable.
     *
     * @return
     */
    public ObservableList<UimlTextInput> getUimlTextInputs() {
        return uimlTextInputs;
    }

    /**
     * Opens a dialog to add/edit uiml button details. If the user clicks ok,
     * the changes are saved into the provided uimlButton object and true is
     * returned.
     *
     * @param uimlButton the uiml button object to be added/edited
     * @return true if the use clicked ok, false otherwise.
     */
    public boolean showUimlButtonDialog(UimlButton uimlButton) {
        try {
            // Load the FXML file and create a new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UimlBuddy.class.getResource("view/ButtonDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("UIML Button");
            dialogStage.getIcons().add(new Image("/assets/Button@2x.png"));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the uiml button into the controller
            //ButtonDialogController controller = loader.getController();
            this.buttonDialogController = loader.getController();
            buttonDialogController.setDialogStage(dialogStage);
            buttonDialogController.setButton(uimlButton);

            // Show the dialog and wait until user closes it.
            dialogStage.showAndWait();

            return buttonDialogController.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens a dialog to add/edit uiml label details. If the user clicks ok, the
     * changes are saved into the provided uimlButton object and true is
     * returned.
     *
     * @param uimlLabel the uiml button object to be added/edited
     * @return true if the use clicked ok, false otherwise.
     */
    public boolean showUimlLabelDialog(UimlLabel uimlLabel) {
        try {
            // Load the FXML file and create a new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UimlBuddy.class.getResource("view/LabelDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("UIML Label");
            dialogStage.getIcons().add(new Image("/assets/Label@2x.png"));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the uiml label into the controller
            this.labelDialogController = loader.getController();
            labelDialogController.setDialogStage(dialogStage);
            labelDialogController.setLabel(uimlLabel);

            // Show the dialog and wait until user closes it.
            dialogStage.showAndWait();

            return labelDialogController.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Opens a dialog to add/edit uiml text input details. If the user clicks ok,
     * the changes are saved into the provided uimlButton object and true is
     * returned.
     *
     * @param uimlTextInput the uiml button object to be added/edited
     * @return true if the use clicked ok, false otherwise.
     */
    public boolean showUimlTextInputDialog(UimlTextInput uimlTextInput) {
        try {
            // Load the FXML file and create a new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UimlBuddy.class.getResource("view/TextInputDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("UIML Text Input Field");
            dialogStage.getIcons().add(new Image("/assets/TextField@2x.png"));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the uiml button into the controller
            //ButtonDialogController controller = loader.getController();
            this.textInputDialogController = loader.getController();
            textInputDialogController.setDialogStage(dialogStage);
            textInputDialogController.setTextInput(uimlTextInput);

            // Show the dialog and wait until user closes it.
            dialogStage.showAndWait();

            return textInputDialogController.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Opens a dialog to add/edit uiml Image Buttons details. If the user clicks
     * ok, the changes are saved into the provided uimlButton object and true is
     * returned.
     *
     * @param uimlImageButton the uiml button object to be added/edited
     * @return true if the use clicked ok, false otherwise.
     */
    public boolean showUimlImageButtonDialog(UimlImageButton uimlImageButton) {
        try {
            // Load the FXML file and create a new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(UimlBuddy.class.getResource("view/ImageButtonDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("UIML Image Button");
            dialogStage.getIcons().add(new Image("/assets/ImageView@2x.png"));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the uiml label into the controller
            this.imageButtonDialogController = loader.getController();
            imageButtonDialogController.setDialogStage(dialogStage);
            imageButtonDialogController.setImageButton(uimlImageButton);

            // Show the dialog and wait until user closes it.
            dialogStage.showAndWait();

            return imageButtonDialogController.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
