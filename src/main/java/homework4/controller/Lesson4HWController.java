package homework4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Random;

@RestController
public class Lesson4HWController {

    @GetMapping("/test")
    public String test() {
        return "работает";
    }

    //метод для вычисления дня недели по переданной дате
    //       - Метод принимает строку с датой в формате "yyyy-MM-dd".
    //       - Строка преобразована в LocalDate, возвращается день недели (например, Понедельник, Вторник и т.д.)
    //       - Метод доступен по GET запросу на "/day-of-week"
    @GetMapping("/day-of-week")
    public String getDayOfWeek(@RequestParam("date") String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            System.out.println("DayOfWeek: " + dayOfWeek); // Отладка
            String nameDay;
            if (dayOfWeek == DayOfWeek.MONDAY) {
                nameDay = "Понедельник";
            } else if (dayOfWeek == DayOfWeek.TUESDAY) {
                nameDay = "Вторник";
            } else if (dayOfWeek == DayOfWeek.WEDNESDAY) {
                nameDay = "Среда";
            } else if (dayOfWeek == DayOfWeek.THURSDAY) {
                nameDay = "Четверг";
            } else if (dayOfWeek == DayOfWeek.FRIDAY) {
                nameDay = "Пятница";
            } else if (dayOfWeek == DayOfWeek.SATURDAY) {
                nameDay = "Суббота";
            } else if (dayOfWeek == DayOfWeek.SUNDAY) {
                nameDay = "Воскресенье";
            } else {
                nameDay = "Нет такого дня";
            }
            return nameDay;
        } catch (DateTimeParseException e) {
            return "Ошибка: неверный формат даты, используйте yyyy-MM-dd";
        }
    }

    // метод для генерации случайного пароля переданной длины
    //       - Метод принимает параметр "length" (длина пароля).
    //       - Генерируется случайный пароль из букв (верхний и нижний регистр), цифр и специальных символов.
    //       - Возвращается строка с результатом в формате "Случайный пароль: [randomPassword]"
    //       - Метод доступен по GET запросу на "/generate-password"
    @GetMapping("/generate-password")
    public String generatePassword(@RequestParam("length") int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_~@#$%^&*+";
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }
        return "Случайный пароль: " + password.toString();
    }

    //метод для вычисления факториала от числа
    //       - Метод принимает параметр "number" (целое число).
    //       - Рассчитывается факториал числа по формуле: factorial(n) = n * (n-1) * (n-2) * ... * 1.
    //       - Возвращается строка с результатом в формате "Факториал числа [number]: [factorial]"
    //       - Метод доступен по GET запросу на "/factorial"
    @GetMapping("/factorial")
    public String calculateFactorial (@RequestParam("number") long number) {
        if (number < 0) {
            return "Искомое число отрицательное!";
        }
        long factorial = 1;
        for (long i = 1; i <= number; i++) {
            factorial *= i;
        }
        return "Факториал числа " + number + ": " + factorial;
    }

    //метод для возведения числа в степень
    //       - Метод принимает два параметра: число и степень.
    //       - Число возводится в указанную степень.
    //       - Возвращается строка с результатом в формате "[number] в степени [power]: [result]"
    //       - Метод доступен по GET запросу на "/power"
    @GetMapping("/power")
    public String calculatePower(@RequestParam("number") double number, @RequestParam("power") double power) {
        double result = Math.pow(number, power);
        return number + " в степени " + power + ": " + result;
    }

    // метод для генерации случайной даты между двумя переданными
    //       - Метод принимает два параметра: startDate и endDate (в формате "yyyy-MM-dd").
    //       - Генерируется случайнуая дата в диапазоне между этими двумя датами.
    //       - Возвращается строка с результатом в формате "Случайная дата: [randomDate]"
    //       - Метод доступен по GET запросу на "/random-date"\
    @GetMapping("/random-date")
    public String generateRandomDate(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        long daysBetween = ChronoUnit.DAYS.between(start, end);
        if (daysBetween < 0) {
            return "Ошибка: начальная дата должна быть раньше конечной";
        }
        Random random = new Random();
        long randomDays = Math.abs(random.nextLong()) % (daysBetween + 1);
        LocalDate randomDate = start.plusDays(randomDays);
        return "Случайная дата: " + randomDate.toString();
    }

    // метод для сортировки массива с учетом параметра isAsc
    //       - Метод принимает массив чисел и параметр "isAsc" (логическое значение).
    //       - Если isAsc = true, массив сортируется по возрастанию, если false — по убыванию.
    //       - Возвращается строка с отсортированным массивом в формате "Отсортированный массив: [sortedArray]"
    //       - Метод доступен по GET запросу на "/sort-array"
    @GetMapping("/sort-array")
    public String sortArray(@RequestParam("numbers") String numbers, @RequestParam("isAsc") boolean isAsc) {
        String[] parts = numbers.split(",");
        int[] array = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            array[i] = Integer.parseInt(parts[i].trim());
        }
        Arrays.sort(array);
        if (!isAsc) {
            for (int i = 0; i < array.length / 2; i++) {
                int temp = array[i];
                array[i] = array[array.length - 1 - i];
                array[array.length - 1 - i] = temp;
            }
        }
        return "Отсортированный массив: " + Arrays.toString(array);
    }

    // метод для разделения строки по позиции и отправки части строки
    //       - Метод принимает строку (str), позицию (position) и флаг (isFirst).
    //       - Если isFirst = true, возвращается первая часть строки до указанной позиции, иначе — вторую часть после позиции.
    //       - Возвращается строка в формате "Часть строки: [substring]"
    //       - Метод доступен по GET запросу на "/substring"
    @GetMapping("/substring")
    public String getSubstring(@RequestParam("str") String str, @RequestParam("position") int position, @RequestParam("isFirst") boolean isFirst) {
        String substring;
        if (isFirst) {
            substring = str.substring(0, position);
        } else {
            substring = str.substring(position);
        }
        return "Часть строки: " + substring;
    }
}