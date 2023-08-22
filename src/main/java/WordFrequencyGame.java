import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEW_LINE_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputString) {
        List<String> words = splitWords(inputString);

        try {
            List<WordFrequencyInfo> wordFrequencyInfoList = getWordFrequencyInfoList(words);
            return generateSortedPrintLines(wordFrequencyInfoList);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private List<String> splitWords(String inputString) {
        return new ArrayList<>(List.of(inputString.split(SPACE_DELIMITER)));
    }

    private String generateSortedPrintLines(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .sorted((wordFrequencyInfo1, wordFrequencyInfo2) -> wordFrequencyInfo2.getWordCount() - wordFrequencyInfo1.getWordCount())
                .map(wordFrequencyInfo -> wordFrequencyInfo.getWord() + SPACE_CHAR + wordFrequencyInfo.getWordCount())
                .collect(Collectors.joining(NEW_LINE_DELIMITER));
    }

    private List<WordFrequencyInfo> getWordFrequencyInfoList(List<String> words) {
        Set<String> distinctWords = new HashSet<>(words);
        List<WordFrequencyInfo> wordFrequencyInfoList = new ArrayList<>();
        distinctWords.forEach(word -> wordFrequencyInfoList
                .add(new WordFrequencyInfo(word, Collections.frequency(words, word))));

        return wordFrequencyInfoList;
    }
}
