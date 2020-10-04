public class DictionaryCommanLine {
    public static void dictionaryBasic() {
        DictionaryManagement.insertFromFile();
        DictionaryCommandline.showAllWords();
    }

    public static void dictionaryAdvanced() {
        DictionaryManagement.insertFromFile();
        DictionaryCommandline.showAllWords();
        DictionaryManagement.dictionaryLookup();
    }
}
