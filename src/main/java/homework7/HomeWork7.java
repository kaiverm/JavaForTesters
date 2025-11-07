package homework7;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hw7")

public class HomeWork7 {
    // TODO: Вернуть случайную дату между двумя переданными (передавать JSON)
    // 1. Принять JSON с полями "startDate" и "endDate" (формат YYYY-MM-DD)
    // 2. Сгенерировать случайную дату в этом диапазоне
    // 3. Вернуть JSON с ключом "randomDate"

    //обработка входящего тела запроса через Substring -> отдаем хардкод с нужными данными

    // TODO: Вернуть отсортированный массив, учитывая параметр isAsc (передавать JSON)
    // 1. Принять JSON с массивом "numbers" в Body и булевым флагом "isAsc" в параметре
    // 2. Отсортировать массив по возрастанию/убыванию в зависимости от isAsc
    // 3. Вернуть JSON с ключом "sortedNumbers"

    // TODO: Вернуть частоту символов в переданной строке (отсортировано по убыванию)
    // 1. Принять JSON с ключом "text"
    // 2. Подсчитать количество вхождений каждого символа (игнорировать пробелы)
    // 3. Вернуть JSON с отсортированным списком символов и их частот

    // TODO: Реализовать метод sum
    // 1. Принять JSON с массивом "numbers"
    // 2. Вычислить сумму всех элементов массива
    // 3. Вернуть JSON с ключом "sum"

    // TODO: Реализовать метод sumif
    // 1. Принять JSON с массивами "numbers" и "conditions" (boolean)
    // 2. Просуммировать только те элементы "numbers", где "conditions" равно true
    // 3. Вернуть JSON с ключом "sum"
}