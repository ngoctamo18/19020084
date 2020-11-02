package graphic.scene.add;

import dictionary.Word;
import graphic.dialog.ConfirmDialog;
import graphic.dialog.ErrorDialog;
import graphic.dialog.InformationDialog;
import graphic.scene.primary.PrimaryController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.HTMLEditor;
import utility.ProjectConfig;
import utility.Utils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddWordController extends PrimaryController {
    @FXML
    private HTMLEditor htmlEditor;

    public void setSaveButton() throws IOException {
        ConfirmDialog addWordConfirm = new ConfirmDialog();
        boolean isConfirm = addWordConfirm.show("Add New Word",
                "Are you sure want to add this word?");
        if (isConfirm) {
            boolean isSave = addWord();
            if (isSave) {
                InformationDialog addDialog = new InformationDialog();
                addDialog.show("Add New Word", "Added successfully");
                setPrimaryStage();
            } else {
                ErrorDialog addDialog = new ErrorDialog();
                addDialog.show("Add New Word", "Error: The word already existed");
            }
        }
    }

    public void setPrimaryStage() throws IOException {
        ProjectConfig.primaryStage.setScene(PrimaryController.getScene());
    }

    public void setBackButton() throws IOException {
        ConfirmDialog cancelConfirm = new ConfirmDialog();
        boolean isConfirm = cancelConfirm.show("Add New Word",
                "Are you sure want to back?");
        if (isConfirm) {
            setPrimaryStage();
        }
    }

    public boolean addWord() {
        String html = htmlEditor.getHtmlText();
        String word = Utils.getWordFromHtlmText(html);
        Word newWord = new Word(word, html);
        return myDictionary.saveWord(newWord);
    }

    public static Scene getScene() throws IOException {
        Parent root = FXMLLoader.load(AddWordController.class.getResource("AddWordScene.fxml"));
        return new Scene(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
