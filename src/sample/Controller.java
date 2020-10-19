package sample;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public ListView<String> listView;
    @FXML
    public TextArea meanings;
    @FXML
    public TextField word;

    ObservableList<String> items=FXCollections.observableArrayList();

    HashMap<String,String> list=new HashMap<String, String>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DictionaryManagement.insertFromFile();
        setMouse();

        word.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String t= word.getText();
                list.clear();
                listView.getItems().clear();
                view(t);
                meanings.setText(list.get(t));
            }
        });
        word.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event){
            }
        });
        word.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String t= event.getCharacter();
            }
        });
    }

    public void view(String s) {
        for (int i = 0; i < Dictionary.count; i++) {
            if(Dictionary.words[i].getWord_target().contains(s)) {
                list.put(Dictionary.words[i].getWord_target(), Dictionary.words[i].getWord_explain());
            }
        }
        listView.getItems().addAll(list.keySet());
    }

    public void setMouse() {
        listView.setOnMouseClicked(event -> {
            String search = listView.getSelectionModel().getSelectedItem();
            String mean = list.get(search);
            meanings.setText(mean);
        });
    }
}
