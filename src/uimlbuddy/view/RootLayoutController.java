package uimlbuddy.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import uimlbuddy.UimlBuddy;

/**
 * FXML Controller class
 *
 * @author Lyuben
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
    public static String xslt = "xslt/main.xsl";
    //public 

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
//    public void setMainApp(UimlBuddy uimlBuddy) {
//        this.uimlBuddy = uimlBuddy;
//    }
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
    private void handleExit(ActionEvent event) {
        event.consume();
        Action response = Dialogs.create()
                .title("Looks like you are leaving uimlBuddy")
                .masthead("Exit uimlBuddy")
                .message("Are you sure you want to quit uimlBuddy?")
                .actions(Dialog.Actions.OK, Dialog.Actions.CANCEL)
                .showConfirm();

        if (response == Dialog.Actions.OK) {
            Stage stageClose = uimlBuddy.getPrimaryStage();
            stageClose.close();
            //System.exit(0);
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
        String uimlSource = uimlbuddy.UimlBuddy.editorOverviewController.sourceEditor.getText();
        try {
            UimlTransform(uimlSource, xslt);
            //Transformer transformer = tFactory.newTransformer(new StreamSource());
        } catch (Exception e) {
            // TODO: handle exception
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("XFormsTransformView.fxml"));
            Parent xformsView = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setTitle("XForms Result");
            stage.setScene(new Scene(xformsView));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(XFormsTransformViewController.class.getName()).log(Level.SEVERE, null, ex);
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

    private static void UimlTransform(String sourcePath, String xlstPath) {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xslt)));
//            transformer.transform(new StreamSource(new File(uimlSource)), 
//                    uimlbuddy.UimlBuddy.xFormsTransformViewController.resultTextArea.setText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
