package uimlbuddy.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import uimlbuddy.UimlBuddy;

/**
 * FXML Controller class for main window
 *
 * @author Lyuben Dimitrov
 */
public class RootLayoutController implements Initializable, Observer {

    @FXML
    private BorderPane root;
    // Reference to the main application.
    private UimlBuddy uimlBuddy;
    private FileChooser fileChooser;
    private FileChooser xmlExporter;
    private File uimlFile;
    private FileChooser.ExtensionFilter extFilter1;
    private FileChooser.ExtensionFilter extFilter2;
    private final ObjectProperty<File> selectedFile = new SimpleObjectProperty<>(this, "selectedFile");
    private EditorOverviewController editor;
    private static String xsltPath = "src/xslt/main.xsl";
    public static String resultDir = "src/xslt/";

    public final ObjectProperty<File> selectedFileProperty() {
        return selectedFile;
    }

    public final File getSelectedFile() {
        return selectedFile.get();
    }

    public final void setSelectedFile(File file) {
        this.selectedFile.set(file);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void handleNew() {
        update(null, uimlBuddy);
    }

    /**
     * Opens a FileChooser to let the user select a UIML file to be loaded.
     */
    @FXML
    private void handleOpen() {
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        extFilter1 = new FileChooser.ExtensionFilter("UIML Documents (*.uiml)", "*.uiml");
        extFilter2 = new FileChooser.ExtensionFilter("All Files", "*.*");

        fileChooser.getExtensionFilters().addAll(extFilter1, extFilter2);

        uimlFile = fileChooser.showOpenDialog(root.getScene().getWindow());

        if (uimlFile != null) {
            setSelectedFile(uimlFile);
        }
    }

    @FXML
    private void handleSave(ActionEvent event) {
        try {
            xmlExporter = new FileChooser();
            xmlExporter.setInitialDirectory(new File("."));
            extFilter1 = new FileChooser.ExtensionFilter("UIML Documents (*.uiml)", "*.uiml");
            extFilter2 = new FileChooser.ExtensionFilter("All Files", "*.*");

            xmlExporter.getExtensionFilters().addAll(extFilter1, extFilter2);

            uimlFile = xmlExporter.showSaveDialog(root.getScene().getWindow());

            if (uimlFile != null) {
                String xml = uimlbuddy.UimlBuddy.editorOverviewController.sourceEditor.getText();
                if (!uimlFile.exists()) {
                    uimlFile.createNewFile();
                }
                FileWriter fw = new FileWriter(uimlFile);
                fw.write(xml);
                fw.close();
                System.out.println("File Saved Successfully");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleSaveAs(ActionEvent event) {
    }

    @FXML
    private void handleDeveloperView() throws Exception {
        DeveloperViewController c = (DeveloperViewController) replaceSceneContent("view/DeveloperView.fxml");
    }
    
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = UimlBuddy.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(UimlBuddy.class.getResource(fxml));
        Parent page;
        try {
            page = (Parent) loader.load(in);
        } finally {
            in.close();
        }
        Stage stage = uimlBuddy.getPrimaryStage();
        stage.getScene().setRoot(page);
        //primaryStage.getScene().setRoot(page);
        return (Initializable) loader.getController();
    }


    @FXML
    public void handleExit() {
        Action response = Dialogs.create()
                .title("Looks like you are leaving uimlBuddy")
                .masthead("Exit uimlBuddy")
                .message("Are you sure you want to quit uimlBuddy and close all windows?")
                .actions(Dialog.Actions.OK, Dialog.Actions.CANCEL)
                .showConfirm();
        if (response == Dialog.Actions.OK) {
            System.exit(0);
        } else if (response == Dialog.Actions.CANCEL) {
        }
    }

    @FXML
    private void handleAbout(ActionEvent event) {
        Dialogs.create()
                .title("uimlBuddy")
                .masthead("About")
                .message("A grapgical editor for UIML\n\nAuthor: Lyuben Dimitrov\nVersion: 1.0v")
                .showInformation();
    }

    @FXML
    private void handleTransform() {
        String source = uimlbuddy.UimlBuddy.editorOverviewController.sourceEditor.getText();
        String result = "src/output.html";
//        StringWriter sw = new StringWriter();
//        StreamResult result = new StreamResult(sw);
        TransformerFactory tFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
            transformer.transform(new StreamSource(new StringBufferInputStream(source)), new StreamResult(new File(result)));
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("XFormsTransformView.fxml"));
            Parent xformsView = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("XForms Result");
            stage.getIcons().add(new Image("/assets/preferences.png"));
            stage.setScene(new Scene(xformsView));
            stage.show();
//            xtc.showResult(sw.toString());
//            XFormsTransformViewController controller = new XFormsTransformViewController();
//            controller.showResult(sw);
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    @Override
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
