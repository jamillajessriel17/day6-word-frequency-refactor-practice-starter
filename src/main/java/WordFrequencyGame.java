import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEW_LINE_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String SPACE_ONE = " 1";

    public String getResult(String inputString) {
        List<String> words = splitWords(inputString);

        if (hasSizeOf1(words)) {
            return inputString + SPACE_ONE;
        }
        try {
            List<WordFrequencyInfo> wordFrequencyInfoList = updateWordFrequencyInfo(words);
            sort(wordFrequencyInfoList);
            return generatePrintLines(wordFrequencyInfoList);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private static boolean hasSizeOf1(List<String> words) {
        return words.size() == 1;
    }

    private static void sort(List<WordFrequencyInfo> frequencyInfoList) {
        frequencyInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
    }

    private static List<String> splitWords(String inputString) {
        return new ArrayList<>(List.of(inputString.split(SPACE_DELIMITER)));
    }

    private static String generatePrintLines(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .map((wordFrequencyInfo) -> wordFrequencyInfo.getWord() + SPACE_CHAR + wordFrequencyInfo.getWordCount())
                .collect(Collectors.joining(NEW_LINE_DELIMITER));
    }

    private List<WordFrequencyInfo> updateWordFrequencyInfo(List<String> words) {
        Set<String> distinctWords = new HashSet<>(words);
        List<WordFrequencyInfo> wordFrequencyInfoList = new ArrayList<>();
        distinctWords.forEach(word -> wordFrequencyInfoList
                .add(new WordFrequencyInfo(word, Collections.frequency(words, word))));

        return wordFrequencyInfoList;
    }
}
