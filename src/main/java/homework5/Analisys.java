package homework5;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Analisys  {
    private static final int INITIAL_SIZE = 1_000_000;
    private static final int ADD_COUNT = 500_000;
    private static final int MIDDLE_INDEX = INITIAL_SIZE / 2;

    public static void main(String[] args) {
        System.out.println("Тест ArrayList: ");
        List<Integer> arrayList = createList(new ArrayList<>());
        testAddToMiddle(arrayList);
        testGetFromMiddle(arrayList);
        testRemoveFromMiddle(arrayList);

        System.out.println("Тест LinkedList: ");
        List<Integer> linkedList = createList(new LinkedList<>());
        testAddToMiddle(linkedList);
        testGetFromMiddle(linkedList);
        testRemoveFromMiddle(linkedList);
    }

    private static List<Integer> createList(List<Integer> list) {
        long startTime = System.nanoTime();
        for (int i = 0; i < INITIAL_SIZE; i++) {
            list.add(i);
        }
        long endTime = System.nanoTime();
        System.out.println("Время создания списка: " + (endTime - startTime) / 1_000_000 + "ms");
        return list;
    }

    private static void testAddToMiddle(List<Integer> list) {
        long startTime = System.nanoTime();
        for (int i = 0; i < ADD_COUNT; i++) {
            list.add(MIDDLE_INDEX, i);
        }
        long endTime = System.nanoTime();
        System.out.println("Время добавления " + ADD_COUNT + " элементов в середину: " + (endTime - startTime) / 1_000_000 + "ms");
    }

    private static void testGetFromMiddle(List<Integer> list) {
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            list.get(MIDDLE_INDEX);
        }
        long endTime = System.nanoTime();
        System.out.println("Время получения элемента из середины 1000 раз: " + (endTime - startTime) / 1_000_000 + "ms");
    }

    private static void testRemoveFromMiddle(List<Integer> list) {
        long startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            list.remove(MIDDLE_INDEX);
        }
        long endTime = System.nanoTime();
        System.out.println("Время удаления элемента из середины 1000 раз: " + (endTime - startTime) / 1_000_000 + "ms");
    }

}