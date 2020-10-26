public class Dictionary {
    public static Word[] words = new Word[200000];
    public static int count = 0;

    public static void addWord(Word newWord) {
        words[count] = newWord;
        count++;
    }
}
