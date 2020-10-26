package sample;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    @FXML
    public ListView<String> listView;
    @FXML
    public TextArea meanings;
    @FXML
    public TextField word;
    @FXML
    public TextField viet;
    @FXML
    public TextField eng;
    @FXML
            Button transSentence;

    ObservableList<String> items=FXCollections.observableArrayList();

    HashMap<String,String> list=new HashMap<String, String>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DictionaryManagement.insertFromFile() ;
        setMouse();
        view("");
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
    }

    public void view(String s) {
        for (int i = 0; i < Dictionary.count; i++) {
            if(Dictionary.words[i].getWord_target().contains(s)) {
                list.put(Dictionary.words[i].getWord_target(), Dictionary.words[i].getWord_explain());
            }
        }

        listView.getItems().addAll(list.keySet());
        java.util.Collections.sort(listView.getItems());
    }

    public void setMouse() {
        listView.setOnMouseClicked(event -> {
            String search = listView.getSelectionModel().getSelectedItem();
            String mean = list.get(search);
            meanings.setText(mean);
        });
    }
    public void addWord(ActionEvent event) throws Exception{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddWord.fxml"));
        Parent add = loader.load();
        Scene scene = new Scene(add);
        stage.setScene(scene);
    }
    public void search(){
        String t= word.getText();
        if (list.get(t)!=null){
            listView.getItems().clear();
            view(t);
            meanings.setText(list.get(t));
        }
        else{
            Alert a= new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Search");
            a.setContentText("This word does not exist in this listView. Do you want to search online");
            ButtonType d1= new ButtonType("YES",ButtonBar.ButtonData.YES);
            ButtonType d2= new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);
            a.getButtonTypes().setAll(d1,d2);
            Optional<ButtonType> result=a.showAndWait();
            if(result.get().getButtonData()==ButtonBar.ButtonData.YES) {
                transSentence.onActionProperty();
            }
        }
    }
    public void delete(){
        final ObservableList<String> delet= listView.getSelectionModel().getSelectedItems();
        String s=delet.toString();
        s=s.replace("[","");
        s=s.replace("]","");
        Alert a= new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("ConFirm Delete");
        a.setContentText("Do you want to delete this word");
        ButtonType d1= new ButtonType("YES",ButtonBar.ButtonData.YES);
        ButtonType d2= new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);
        a.getButtonTypes().setAll(d1,d2);
        Optional<ButtonType> result=a.showAndWait();
        if(result.get().getButtonData()==ButtonBar.ButtonData.YES) {
            listView.getItems().removeAll(delet);
            meanings.setText("");
            DictionaryManagement.deleteWord(s);
        }
        updateFile();
    }
    public void speak(){
        String speech=listView.getSelectionModel().getSelectedItems().toString();
        Voice v = VoiceManager.getInstance().getVoice("kevin16");
        v.allocate();
        v.speak(speech);
    }
    public void dictionaryExportToFile(){
        String data="";
        for(int i=0;i<Dictionary.count;i++){
            data=data+Dictionary.words[i].getWord_target()+"\t"+Dictionary.words[i].getWord_explain()+"\n";
        }
        FileOutputStream os= null;
        File file=new File("Dictionary_Export.txt");
        try {
            os=new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            os.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fix_word(){
        int k=listView.getSelectionModel().getSelectedIndex();
        String e=listView.getSelectionModel().getSelectedItems().toString();
        e=e.replace("[","");
        e=e.replace("]","");

        Alert a= new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("ConFirm Delete");
        a.setContentText("Do you want to fix this word");
        ButtonType d1= new ButtonType("YES",ButtonBar.ButtonData.YES);
        ButtonType d2= new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);
        a.getButtonTypes().setAll(d1,d2);
        Optional<ButtonType> result=a.showAndWait();

        if(result.get().getButtonData()==ButtonBar.ButtonData.YES) {
            eng.setText(e);
            viet.setText(list.get(e));
            listView.getItems().removeAll(e);
            meanings.setText("");
            DictionaryManagement.deleteWord(e);
        }
    }
    public void fix(){
        String fixE=eng.getText();
        String fixV=viet.getText();
        Word temp = new Word(fixE, fixV);
        Dictionary.addWord(temp);
        list.clear();
        listView.getItems().clear();
        view("");
        updateFile();
        eng.setText("");
        viet.setText("");
    }
    public void sentence(ActionEvent event) {
        try{
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Sentence_Translate.fxml"));
            Parent add = loader.load();
            Scene scene = new Scene(add);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void updateFile(){
        String data="";
        for(int i=0;i<Dictionary.count;i++){
            data=data+Dictionary.words[i].getWord_target()+"\t"+Dictionary.words[i].getWord_explain()+"\n";
        }
        FileOutputStream os= null;
        File file=new File("dic1.txt");
        try {
            os=new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            os.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
