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
}
