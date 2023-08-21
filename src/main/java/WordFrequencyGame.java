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

    public String getResult(String inputStr) {


        if (inputStr.split(SPACE_DELIMITER).length == 1) {
            return inputStr + " 1";
        } else {

            try {

                List<WordFrequencyInfo> wordFrequencyInfoList = getWordFrequencyInfos(inputStr);

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequencyInfo>> wordFrequencyMap = getListMap(wordFrequencyInfoList);

                List<WordFrequencyInfo> frequencyInfoList = updateWordFrequencyInfos(wordFrequencyMap);

                sort(frequencyInfoList);

                return generatePrintLines(frequencyInfoList);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }
    private static List<WordFrequencyInfo> updateWordFrequencyInfos(Map<String, List<WordFrequencyInfo>> wordFrequencyMap) {
        List<WordFrequencyInfo> frequencyInfoList = new ArrayList<>();
        for (Map.Entry<String, List<WordFrequencyInfo>> entry : wordFrequencyMap.entrySet()) {
            WordFrequencyInfo wordFrequencyInfo = new WordFrequencyInfo(entry.getKey(), entry.getValue().size());
            frequencyInfoList.add(wordFrequencyInfo);
        }
        return frequencyInfoList;
    }

    private static void sort(List<WordFrequencyInfo> frequencyInfoList) {
        frequencyInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
    }

    private static List<WordFrequencyInfo> getWordFrequencyInfos(String inputStr) {
        //split the input string with 1 to n pieces of spaces
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

    private Map<String, List<WordFrequencyInfo>> getListMap(List<WordFrequencyInfo> wordFrequencyInfoList) {
        Map<String, List<WordFrequencyInfo>> wordAndWordFrequencyInfoMap = new HashMap<>();

        wordFrequencyInfoList.forEach(wordFrequencyInfo -> {
            if (!wordAndWordFrequencyInfoMap.containsKey(wordFrequencyInfo.getWord())) {
                List<WordFrequencyInfo> frequencyInfoList = new ArrayList<>();
                frequencyInfoList.add(wordFrequencyInfo);
                wordAndWordFrequencyInfoMap.put(wordFrequencyInfo.getWord(), frequencyInfoList);
            } else {
                wordAndWordFrequencyInfoMap.get(wordFrequencyInfo.getWord()).add(wordFrequencyInfo);
            }
        });

        return wordAndWordFrequencyInfoMap;
    }
}
