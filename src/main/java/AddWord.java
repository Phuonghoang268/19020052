import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddWord implements Initializable {
    @FXML
    public TextArea english;
    @FXML
    public TextArea vietnamese;
    @FXML
    public Button add;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void add(ActionEvent event) throws Exception {
        String e=english.getText();
        String v= vietnamese.getText();
        Word temp = new Word(e, v);
        Dictionary.addWord(temp);
        Controller.updateFile();
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent add = loader.load();
        Scene scene = new Scene(add);
        stage.setScene(scene);
    }

}
