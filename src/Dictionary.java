public class Dictionary {
    public static Word[] words = new Word[100];
    public static int count = 0;

    public static void addWord(Word newWord) {
        words[count] = newWord;
        count++;
    }


    public static void main(String[] args) {
        DictionaryCommanLine.dictionaryBasic();
    }
}
