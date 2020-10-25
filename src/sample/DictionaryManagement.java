package sample;
import org.omg.CORBA.portable.OutputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class DictionaryManagement {
    public static void insertFromCommandline() {
        Scanner scan = new Scanner(System.in);
        int dem = scan.nextInt();
        String t = scan.nextLine();
        for (int i = 0; i < dem; i++) {
            String w1 = scan.nextLine();
            String w2 = scan.nextLine();
            Word temp = new Word(w1, w2);
            Dictionary.addWord(temp);
        }
    }

    public static void insertFromFile() {
        try {
            File file = new File("dic1.txt");
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()) {
                String w1 = scan.next();
                String w2 = scan.nextLine();
                String s=w2;
                while(scan.hasNextLine()&&s.equals("")==false){
                    s=scan.nextLine();
                    w2=w2+s+"\n";
                }
                w2 = w2.replaceAll("\t", "");
                Word temp = new Word(w1, w2);
                Dictionary.addWord(temp);
            }
        } catch (FileNotFoundException e) {
        }
    }

    public static void dictionaryLookup() {
        String s;
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();
        for (int i = 0; i < Dictionary.count; i++) {
            if (s.equals(Dictionary.words[i].getWord_target())) {
                System.out.println(Dictionary.words[i].getWord_explain());
                break;
            }
        }
    }
    public static void deleteWord(String s){
        for(int i=0;i<Dictionary.count;i++){
            if(Dictionary.words[i].getWord_target().equals(s)){
                for(int j=i;j<Dictionary.count-1;j++){
                    Dictionary.words[j].setWord_target(Dictionary.words[j+1].getWord_target());
                    Dictionary.words[j].setWord_explain(Dictionary.words[j+1].getWord_explain());
                }
                Dictionary.count--;
            }
        }
    }
    public static void fixed(String fixed_word, String data){
        for(int i=0;i<Dictionary.count;i++){
            if(Dictionary.words[i].getWord_target().equals(fixed_word)){
                Dictionary.words[i].setWord_explain(data);
            }
        }
    }
    public static void readFromMySQL(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/dictionary";// your db name
            String user = "root"; // your db username
            String password = ""; // your db password
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connect success!");
            }
            Statement statement = conn.createStatement();
            ResultSet sql = statement.executeQuery("select * from dictionary");
            while(sql.next()){
                String w1= sql.getString(1);
                String w2=sql.getString(2);
                Word temp = new Word(w1, w2);
                Dictionary.addWord(temp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

