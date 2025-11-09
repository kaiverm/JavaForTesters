package homework7.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.ThreadLocalRandom;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/api/homework7")
public class HomeWork7 {
    // Вернуть случайную дату между двумя переданными (передавать JSON)
    // 1. Принять JSON с полями "startDate" и "endDate" (формат YYYY-MM-DD)
    // 2. Сгенерировать случайную дату в этом диапазоне
    // 3. Вернуть JSON с ключом "randomDate"
    @PostMapping("/random-date")
    public ResponseEntity<Map<String, String>> getRandomDate(@RequestBody Map<String, String> request) {
        Map<String, String> response = new HashMap<>();
        try {
            String startDateStr = request.get("startDate");
            String endDateStr = request.get("endDate");
            if (startDateStr == null || endDateStr == null) {
                response.put("error", "Не переданы startDate и endDate");
                return ResponseEntity.badRequest().body(response);
            }
            LocalDate start = LocalDate.parse(startDateStr);
            LocalDate end = LocalDate.parse(endDateStr);
            if (start.isAfter(end)) {
                response.put("error", "startDate должна быть раньше endDate");
                return ResponseEntity.badRequest().body(response);
            }
            long daysBetween = ChronoUnit.DAYS.between(start, end);
            long randomDays = ThreadLocalRandom.current().nextLong(daysBetween + 1);
            LocalDate randomDate = start.plusDays(randomDays);
            response.put("randomDate", randomDate.toString());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Ошибка: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Вернуть отсортированный массив, учитывая параметр isAsc (передавать JSON)
    // 1. Принять JSON с массивом "numbers" в Body и булевым флагом "isAsc" в параметре
    // 2. Отсортировать массив по возрастанию/убыванию в зависимости от isAsc
    // 3. Вернуть JSON с ключом "sortedNumbers"
    @PostMapping("/sort-array")
    public ResponseEntity<Map<String, Object>> sortArray(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Object numbersObj = request.get("numbers");
            Boolean isAsc = (Boolean) request.get("isAsc");
            if (numbersObj == null || isAsc == null) {
                response.put("error", "Поля не заполнены");
                return ResponseEntity.badRequest().body(response);
            }
            List<Integer> numbers = ((List<?>) numbersObj).stream()
                    .map(obj -> {
                        if (obj instanceof Integer) return (Integer) obj;
                        if (obj instanceof Number) return ((Number) obj).intValue();
                        throw new IllegalArgumentException("numbers должен содержать числа");
                    })
                    .collect(Collectors.toList());
            List<Integer> sorted = new ArrayList<>(numbers);
            sorted.sort(isAsc ? Comparator.naturalOrder() : Comparator.reverseOrder());
            response.put("sortedNumbers", sorted);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Ошибка: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Вернуть частоту символов в переданной строке (отсортировано по убыванию)
    // 1. Принять JSON с ключом "text"
    // 2. Подсчитать количество вхождений каждого символа (игнорировать пробелы)
    // 3. Вернуть JSON с отсортированным списком символов и их частот
    @PostMapping("/char-frequency")
    public ResponseEntity<Map<String, Object>> charFrequency(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            String text = request.get("text");
            if (text == null || text.trim().isEmpty()) {
                response.put("error", "Поле 'text' пустое");
                return ResponseEntity.badRequest().body(response);
            }
            Map<Character, Integer> freq = new HashMap<>();
            for (char c : text.toCharArray()) {
                if (c != ' ') {
                    freq.put(c, freq.getOrDefault(c, 0) + 1);
                }
            }
            List<Map.Entry<Character, Integer>> list = new ArrayList<>(freq.entrySet());
            list.sort((a, b) -> b.getValue().compareTo(a.getValue()));
            List<Map<String, Object>> result = list.stream()
                    .map(entry -> {
                        Map<String, Object> item = new HashMap<>();
                        item.put("char", entry.getKey().toString());
                        item.put("count", entry.getValue());
                        return item;
                    })
                    .collect(Collectors.toList());
            response.put("frequency", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Ошибка: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    // Реализовать метод sum
    // 1. Принять JSON с массивом "numbers"
    // 2. Вычислить сумму всех элементов массива
    // 3. Вернуть JSON с ключом "sum"
    @PostMapping("/sum")
    public ResponseEntity<Map<String, Object>> sum(@RequestBody Map<String, List<Number>> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Number> numbers = request.get("numbers");
            if (numbers == null || numbers.isEmpty()) {
                response.put("error", "Массив пустой");
                return ResponseEntity.badRequest().body(response);
            }
            double sum = numbers.stream()
                    .mapToDouble(Number::doubleValue)
                    .sum();
            response.put("sum", sum);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Ошибка: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Реализовать метод sumif
    // 1. Принять JSON с массивами "numbers" и "conditions" (boolean)
    // 2. Просуммировать только те элементы "numbers", где "conditions" равно true
    // 3. Вернуть JSON с ключом "sum"
    @PostMapping("/sumif")
    public ResponseEntity<Map<String, Object>> sumIf(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Number> numbers = (List<Number>) request.get("numbers");
            List<Boolean> conditions = (List<Boolean>) request.get("conditions");
            if (numbers == null || conditions == null) {
                response.put("error", "Поля не заполнены");
                return ResponseEntity.badRequest().body(response);
            }
            if (numbers.size() != conditions.size()) {
                response.put("error", "Массивы должны быть одинаковой длины");
                return ResponseEntity.badRequest().body(response);
            }
            double sum = 0;
            for (int i = 0; i < numbers.size(); i++) {
                if (Boolean.TRUE.equals(conditions.get(i))) {
                    sum += numbers.get(i).doubleValue();
                }
            }
            response.put("sum", sum);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Ошибка " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}

