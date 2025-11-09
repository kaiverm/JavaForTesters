package homework8.controller;

import homework8.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/homework8")
public class HomeWork8 {
    @Autowired
    private LoggerService loggerService;

    // 1. Случайная дата
    @PostMapping("/random-date")
    public ResponseEntity<Map<String, String>> getRandomDate(@RequestBody Map<String, String> request) {
        Map<String, String> response = new HashMap<>();
        try {
            String startStr = request.get("startDate");
            String endStr = request.get("endDate");
            if (startStr == null || endStr == null) {
                response.put("error", "Не переданы startDate и endDate");
                loggerService.writeLog("random-date", response);
                return ResponseEntity.badRequest().body(response);
            }
            LocalDate start = LocalDate.parse(startStr);
            LocalDate end = LocalDate.parse(endStr);
            if (start.isAfter(end)) {
                response.put("error", "startDate должна быть раньше endDate");
                loggerService.writeLog("random-date", response);
                return ResponseEntity.badRequest().body(response);
            }
            long daysBetween = ChronoUnit.DAYS.between(start, end);
            long randomDays = ThreadLocalRandom.current().nextLong(daysBetween + 1);
            LocalDate randomDate = start.plusDays(randomDays);
            response.put("randomDate", randomDate.toString());
            loggerService.writeLog("random-date", response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Ошибка: " + e.getMessage());
            loggerService.writeLog("random-date", response);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 2. Сортировка массива
    @PostMapping("/sort-array")
    public ResponseEntity<Map<String, Object>> sortArray(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Integer> numbers = (List<Integer>) request.get("numbers");
            Boolean isAsc = (Boolean) request.get("isAsc");
            if (numbers == null || isAsc == null) {
                response.put("error", "Не переданы numbers и isAsc");
                loggerService.writeLog("sort-array", response);
                return ResponseEntity.badRequest().body(response);
            }
            List<Integer> sorted = new ArrayList<>(numbers);
            sorted.sort(isAsc ? Comparator.naturalOrder() : Comparator.reverseOrder());
            response.put("sortedNumbers", sorted);
            loggerService.writeLog("sort-array", response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            loggerService.writeLog("sort-array", response);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 3. Частота символов
    @PostMapping("/char-frequency")
    public ResponseEntity<Map<String, Object>> charFrequency(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            String text = request.get("text");
            if (text == null || text.trim().isEmpty()) {
                response.put("error", "text обязателен");
                loggerService.writeLog("char-frequency", response);
                return ResponseEntity.badRequest().body(response);
            }
            Map<Character, Integer> freq = new HashMap<>();
            for (char c : text.toCharArray()) {
                if (c != ' ') freq.merge(c, 1, Integer::sum);
            }
            List<Map<String, Object>> result = freq.entrySet().stream()
                    .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                    .map(e -> {
                        Map<String, Object> item = new HashMap<>();
                        item.put("char", e.getKey().toString());
                        item.put("count", e.getValue());
                        return item;
                    })
                    .collect(Collectors.toList());
            response.put("frequency", result);
            loggerService.writeLog("char-frequency", response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            loggerService.writeLog("char-frequency", response);
            return ResponseEntity.status(500).body(response);
        }
    }

    // 4. Сумма
    @PostMapping("/sum")
    public ResponseEntity<Map<String, Double>> sum(@RequestBody Map<String, List<Number>> request) {
        Map<String, Double> response = new HashMap<>();
        try {
            List<Number> numbers = request.get("numbers");
            if (numbers == null || numbers.isEmpty()) {
                response.put("error", 1.0);
                loggerService.writeLog("sum", response);
                return ResponseEntity.badRequest().body(response);
            }
            double sum = numbers.stream().mapToDouble(Number::doubleValue).sum();
            response.put("sum", sum);
            loggerService.writeLog("sum", response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", -1.0);
            loggerService.writeLog("sum", response);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 5. sumif
    @PostMapping("/sumif")
    public ResponseEntity<Map<String, Double>> sumIf(@RequestBody Map<String, Object> request) {
        Map<String, Double> response = new HashMap<>();
        try {
            List<Number> numbers = (List<Number>) request.get("numbers");
            List<Boolean> conditions = (List<Boolean>) request.get("conditions");
            if (numbers == null || conditions == null || numbers.size() != conditions.size()) {
                response.put("error", -1.0);
                loggerService.writeLog("sumif", response);
                return ResponseEntity.badRequest().body(response);
            }
            double sum = 0;
            for (int i = 0; i < numbers.size(); i++) {
                if (Boolean.TRUE.equals(conditions.get(i))) {
                    sum += numbers.get(i).doubleValue();
                }
            }
            response.put("sum", sum);
            loggerService.writeLog("sumif", response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", -1.0);
            loggerService.writeLog("sumif", response);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/getLogs")
    public ResponseEntity<List<String>> getLogs() {
        List<String> logs = loggerService.readLog();
        return ResponseEntity.ok(logs);
    }
}