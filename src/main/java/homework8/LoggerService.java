package homework8;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Service
public class LoggerService {
    private static final String LOG_FILE = "logs/app.log";
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public LoggerService() {
        try {
            Files.createDirectories(Paths.get("logs"));
        } catch (IOException e) {
            System.err.println("Не удалось создать папку: " + e.getMessage());
        }
    }

    public void writeLog(String infoMessage, Object sendObject) {
        String timestamp = LocalDateTime.now().format(DTF);
        String logLine = String.format("%s: %s: %s%n", timestamp, infoMessage, sendObject.toString());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(logLine);
        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    public List<String> readLog() {
        if (!Files.exists(Paths.get(LOG_FILE))) {
            return Collections.singletonList("Логов нет");
        }
        try {
            return Files.readAllLines(Paths.get(LOG_FILE));
        } catch (IOException e) {
            return Collections.singletonList("Ошибка чтения логов: " + e.getMessage());
        }
    }
}