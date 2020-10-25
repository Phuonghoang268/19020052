package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.awt.*;
import java.beans.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;

import com.darkprograms.speech.translator.GoogleTranslate;

import javax.sound.midi.Synthesizer;

public class Sentence_Translate implements Initializable {
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
