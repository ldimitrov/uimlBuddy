package uimlbuddy.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import uimlbuddy.UimlBuddy;

/**
 * FXML Controller class for Developer's View
 *
 * @author Lyuben Dimitrov
 */
public class DeveloperViewController implements Initializable {

    // Reference to the main application.
    private UimlBuddy uimlBuddy;
    
    @FXML // fx:id="developerEditorTextArea"
    private TextArea developerEditorTextArea;

    @FXML // fx:id="filesTree"
    private TreeView<File> filesTree;
    
    @FXML
    private TabPane tabPane;

    @FXML // fx:id="mainTab"
    private Tab mainTab;
    
    private FileChooser fileChooser;
    private FileChooser xmlExporter;
    private File xsltFile;
    private FileChooser.ExtensionFilter extFilterXSL;
    private FileChooser.ExtensionFilter extFilterCSS;
    private FileChooser.ExtensionFilter extFilterAll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert filesTree != null : "fx:id=\"filesTree\" was not injected: check your FXML file 'DeveloperView.fxml'.";
        assert developerEditorTextArea != null : "fx:id=\"developerEditorTextArea\" was not injected: check your FXML file 'DeveloperView.fxml'.";
        
        File currentDir = new File("src/xslt"); // current directory
        findFiles(currentDir, null);
        
        StringBuilder contents = new StringBuilder();

        try {
            BufferedReader input = new BufferedReader(new FileReader("src/xslt/main.xsl"));
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
        developerEditorTextArea.setText(contents.toString());
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
        try {
            xmlExporter = new FileChooser();
            xmlExporter.setInitialDirectory(new File("."));
            extFilterXSL = new FileChooser.ExtensionFilter("XSLT Files (*.xsl)", "*.xsl");
            extFilterCSS = new FileChooser.ExtensionFilter("CSS Files (*.css)", "*.css");
            extFilterAll = new FileChooser.ExtensionFilter("All Files", "*.*");

            xmlExporter.getExtensionFilters().addAll(extFilterXSL, extFilterCSS, extFilterAll);

            xsltFile = xmlExporter.showSaveDialog(tabPane.getScene().getWindow());

            if (xsltFile != null) {
                String xml = uimlbuddy.UimlBuddy.developerController.developerEditorTextArea.getText();
                if (!xsltFile.exists()) {
                    xsltFile.createNewFile();
                }
                FileWriter fw = new FileWriter(xsltFile);
                fw.write(xml);
                fw.close();
                System.out.println("File Saved Successfully");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
