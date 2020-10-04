import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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
    public static void insertFromFile()  {
        try{
            File file= new File("dic1.txt");
            Scanner scan = new Scanner(file);
            int dem = scan.nextInt();
            for (int i = 0; i < dem; i++) {
                String w1 = scan.next();
                String w2 = scan.nextLine();
                w2=w2.replaceAll("\t","");
                Word temp = new Word(w1, w2);
                Dictionary.addWord(temp);
            }
        }
        catch (FileNotFoundException e){
        }
    }

    public static void dictionaryLookup(){
        String s;
        Scanner sc=new Scanner(System.in);
        s=sc.nextLine();
        for(int i=0;i<Dictionary.count;i++){
            if(s.equals(Dictionary.words[i].getWord_target())) {
                System.out.println(Dictionary.words[i].getWord_explain());
                break;
            }
        }
    }
}


