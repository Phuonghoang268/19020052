import com.darkprograms.speech.translator.GoogleTranslate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class SentenceTranslate implements Initializable {
    @FXML
    public TextArea english;
    @FXML
    public TextArea vietnamese;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void search(ActionEvent event) throws Exception {
        String e= english.getText();
        String v= GoogleTranslate.translate("vi",e);
        vietnamese.setText(v);
    }
    public void speak(String text) {
        System.out.println(text);
    }
}
