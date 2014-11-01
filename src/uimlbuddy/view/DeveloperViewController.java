package uimlbuddy.view;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import uimlbuddy.UimlBuddy;

/**
 * FXML Controller class for Developer's View
 *
 * @author Lyuben
 */
public class DeveloperViewController implements Initializable {

    // Reference to the main application.
    private UimlBuddy uimlBuddy;

    @FXML // fx:id="filesTree"
    private TreeView<File> filesTree;

    @FXML // fx:id="mainTab"
    private Tab mainTab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File currentDir = new File("src/xslt"); // current directory
        findFiles(currentDir, null);
    }

    private void findFiles(File dir, TreeItem<File> parent) {
        TreeItem<File> root = new TreeItem<>(dir);
        root.setExpanded(true);
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                findFiles(file, root);
            } else {
                root.getChildren().add(new TreeItem<>(file));
            }
        }
        if (parent == null) {
            filesTree.setRoot(root);
        } else {
            parent.getChildren().add(root);
        }
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param uimlBuddy
     */
    public void setMainApp(UimlBuddy uimlBuddy) {
        this.uimlBuddy = uimlBuddy;
    }

    @FXML
    private void handleNewDev() {
        update(null, uimlBuddy);
    }

    @FXML
    void handleSaveDev(ActionEvent event) {

    }

    @FXML
    public void handleExitDev() {
        Action response = Dialogs.create()
                .title("Looks like you are leaving uimlBuddy Developer")
                .masthead("Exit uimlBuddy Developer")
                .message("Are you sure you want to quit uimlBuddy and close all windows?")
                .actions(Dialog.Actions.OK, Dialog.Actions.CANCEL)
                .showConfirm();
        if (response == Dialog.Actions.OK) {
            System.exit(0);
        } else if (response == Dialog.Actions.CANCEL) {
        }
    }

    @FXML
    void handleUimlView(ActionEvent event) throws Exception {
        EditorOverviewController editorController = (EditorOverviewController) replaceSceneContent("view/EditorOverview.fxml");
    }

    public Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = DeveloperViewController.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(DeveloperViewController.class.getResource(fxml));
        Parent page;
        try {
            page = (Parent) loader.load(in);
        } finally {
            in.close();
        }
        Stage stage = uimlBuddy.getPrimaryStage();
        stage.getScene().setRoot(page);
        return (Initializable) loader.getController();
    }

    @FXML
    private void handleAboutDev(ActionEvent event) {
        Dialogs.create()
                .title("uimlBuddy")
                .masthead("About")
                .message("A grapgical editor for UIML\n\nAuthor: Lyuben Dimitrov\nVersion: 1.0v")
                .showInformation();
    }

    public void update(Observable o, Object arg) {
        Platform.runLater(() -> {
            try {
                new UimlBuddy().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
