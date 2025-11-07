package homework2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

// класс Lesson2HWController с аннотацией @RestController для обработки HTTP запросов
@RestController
public class Lesson2HWController {
    // метод currentTime():
    //       - Используя LocalDateTime.now(), получаем текущее время
    //       - Возвращаем строку с текущим временем в формате "Текущее время: [currentTime]"
    //       - Метод доступен по GET запросу на "/current-datetime"
    @GetMapping("/current-datetime")
    public String currentTime() {
        LocalDateTime now = LocalDateTime.now();
        return "Текущее время: " + now;
    }

    // метод currentSeason():
    //       - Получаем текущий месяц через LocalDate.now().getMonthValue()
    //       - В зависимости от месяца возвращаем сезон:
    //         - Зима (декабрь, январь, февраль)
    //         - Весна (март, апрель, май)
    //         - Лето (июнь, июль, август)
    //         - Осень (сентябрь, октябрь, ноябрь)
    //       - Метод доступен по GET запросу на "/current-season"
    @GetMapping("/current-season")
    public String currentSeason() {
        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        String season;
        if (month == 12 || month == 1 || month == 2) {
            season = "Зима";
        } else if (month >= 3 && month <= 5) {
            season = "Весна";
        } else if (month >= 6 && month <= 8) {
            season = "Лето";
        } else {
            season = "Осень";
        }
        return season;
    }

    // метод futureDate():
    //       - Получаем текущую дату через LocalDate.now()
    //       - Генерируем случайное количество дней в пределах 30 (с использованием Random)
    //       - Добавляем эти дни к текущей дате для получения будущей даты
    //       - Возвращаем строку с будущей датой в формате "Случайная дата в будущем: [futureDate]"
    //       - Метод доступен по GET запросу на "/future-date"
    @GetMapping("/future-date")
    public String futureDate() {
        LocalDate now = LocalDate.now();
        Random random = new Random();
        int randomNumberDays = random.nextInt(30);
        LocalDate future = now.plusDays(randomNumberDays);
        return "Случайная дата в будущем: " + future;
    }
}