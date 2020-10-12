import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    public static void fixed(String fixed_word, String data){
        for(int i=0;i<Dictionary.count;i++){
            if(Dictionary.words[i].getWord_target().equals(fixed_word)){
                Dictionary.words[i].setWord_explain(data);
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
    public static void dictionaryExportToFile(){
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
}


