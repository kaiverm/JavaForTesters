package homework6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class WordsCountingApp {
    public static void main(String[] args) {
        String filename = Paths.get("file_l6.txt").toAbsolutePath().toString();
        System.out.println("Ищем файл по пути: " + filename);
        String text = "";

        try {
            text = String.join(" ", Files.readAllLines(Paths.get(filename)));
        } catch (IOException e) {
            System.out.println("Не удалось прочитать файл: " + filename);
            System.out.println("Файл не найден");
            return;
        }

        List<String> words = extractWords(text);

        if (words.isEmpty()) {
            System.out.println("Файл пустой или не содержит слова");
            return;
        }

        Set<String> uniqueWords = new HashSet<>(words);
        List<String> sortedList = new ArrayList<>(uniqueWords);

        sortedList.sort((a, b) -> {
            if (b.length() != a.length()) {
                return Integer.compare(b.length(), a.length());
            } else {
                return a.compareTo(b);
            }
        });

        System.out.println("Отсортированные слова:");
        for (String word : sortedList) {
            System.out.println(word);
        }
        System.out.println();

        Map<String, Integer> frequency = new HashMap<>();
        for (String word : words) {
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }

        Map<String, Integer> sortedFrequency = new TreeMap<>(frequency);
        System.out.println("Статистика повторений:");
        for (Map.Entry<String, Integer> entry : sortedFrequency.entrySet()) {
            System.out.println(entry.getKey() + " — " + entry.getValue());
        }
        System.out.println();

        int maxLength = 0;
        for (String word : words) {
            if (word.length() > maxLength) {
                maxLength = word.length();
            }
        }
        List<String> longestWords = new ArrayList<>();
        for (String word : uniqueWords) {
            if (word.length() == maxLength) {
                longestWords.add(word);
            }
        }
        longestWords.sort(String::compareTo);

        System.out.println("Самое длинное слово (длина " + maxLength + "):");
        for (String word : longestWords) {
            System.out.println(word);
        }
    }
    private static List<String> extractWords(String text) {
        List<String> words = new ArrayList<>();

        if (text == null || text.trim().isEmpty()) {
            return words;
        }

        String cleaned = text.replaceAll("[^а-яА-ЯёЁa-zA-Z0-9]+", " ");

        String[] parts = cleaned.split("\\s+");
        for (String part : parts) {
            String word = part.trim().toLowerCase();
            if (!word.isEmpty()) {
                words.add(word);
            }
        }

        return words;
    }
}