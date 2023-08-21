public class WordFrequencyInfo {
    private final String word;
    private int count;

    public WordFrequencyInfo(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public WordFrequencyInfo(String word) {
        this.word = word;
    }

    public String getWord() {
        return this.word;
    }

    public int getWordCount() {
        return this.count;
    }

}
