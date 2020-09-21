public class DictionaryCommandline {
    public static void showAllWords() {
        System.out.printf("%-5s|%-15s|%-15s\n", "No", "English", "Vietnamese");
        for (int i = 0; i < Dictionary.count; i++) {
            System.out.printf("%-5s|%-15s|%-15s\n", i + 1, Dictionary.words[i].getWord_target(), Dictionary.words[i].getWord_explain());
        }
    }
}
