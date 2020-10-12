import java.util.Scanner;

public class DictionaryCommanLine  {
    public static void dictionaryBasic() {
        DictionaryManagement.insertFromFile();
        DictionaryCommandline.showAllWords();
    }

    public static void dictionaryAdvanced(){
        DictionaryManagement.insertFromFile();
        DictionaryCommandline.showAllWords();
        DictionaryManagement.dictionaryLookup();
    }

    public static void dictionarySearcher(String search){
            for(int i=0;i<Dictionary.count;i++){
            if(Dictionary.words[i].getWord_target().toLowerCase().contains(search.toLowerCase())){
                System.out.println(Dictionary.words[i].getWord_target());
            }
        }
    }
}
