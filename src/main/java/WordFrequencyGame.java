import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_DELIMITER = "\\s+";
    public static final String NEW_LINE_DELIMITER = "\n";
    public static final String SPACE_CHAR = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String inputString) {
        if (inputString.split(SPACE_DELIMITER).length == 1) {
            return inputString + " 1";
        }
        try {
            List<WordFrequencyInfo> wordFrequencyInfoList = getWordFrequencyInfos(inputString);
            Map<String, Integer> wordFrequencyMap = getListMap(wordFrequencyInfoList);
            List<WordFrequencyInfo> frequencyInfoList = updateWordFrequencyInfos(wordFrequencyMap);

            sort(frequencyInfoList);

            return generatePrintLines(frequencyInfoList);
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private static List<WordFrequencyInfo> updateWordFrequencyInfos(Map<String, Integer> wordFrequencyMap) {
        List<WordFrequencyInfo> frequencyInfoList = new ArrayList<>();
        for (Map.Entry<String, Integer> wordFrequency : wordFrequencyMap.entrySet()) {
            WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(wordFrequency.getKey(), wordFrequency.getValue());
            frequencyInfoList.add(wordFrequencyInfo);
        }
        return frequencyInfoList;
    }

    private static void sort(List<WordFrequencyInfo> frequencyInfoList) {
        frequencyInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
    }

    private static List<WordFrequencyInfo> getWordFrequencyInfos(String inputStr) {
        String[] words = inputStr.split(SPACE_DELIMITER);
        List<WordFrequencyInfo> wordFrequencyInfoList = new ArrayList<>();
        List.of(words).forEach(word -> wordFrequencyInfoList.add(new WordFrequencyInfo(word, 1)));
        return wordFrequencyInfoList;
    }

    private static String generatePrintLines(List<WordFrequencyInfo> wordFrequencyInfoList) {
        return wordFrequencyInfoList.stream()
                .map((wordFrequencyInfo) -> wordFrequencyInfo.getWord() + SPACE_CHAR + wordFrequencyInfo.getWordCount())
                .collect(Collectors.joining(NEW_LINE_DELIMITER));
    }

    private Map<String, Integer> getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        Map<String, Integer> wordAndWordFrequencyInfoMap = new HashMap<>();

        wordFrequencyInfoList.forEach(wordFrequencyInfo -> {
            if (!wordAndWordFrequencyInfoMap.containsKey(wordFrequencyInfo.getWord())) {
                wordAndWordFrequencyInfoMap.put(wordFrequencyInfo.getWord(), 1);
            } else {
                wordAndWordFrequencyInfoMap.computeIfPresent(wordFrequencyInfo.getWord(), (key, value) -> value + 1);
            }
        });

        return wordAndWordFrequencyInfoMap;
    }
}
