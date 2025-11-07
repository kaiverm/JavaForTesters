package homework3;

public class Main {
    public static void main(String[] args) {
        Gift box = new Box();
        box.add(new Chocolate("Ritter Sport", 100, 200, "Горький"));
        box.add(new Toffie("Золотой ключик", 300, 150, 300));
        box.add(new Waffle("Вафли глазированная", 150, 85, "Кокос"));
        box.add(new Waffle("Тайяки", 150, 500, "Зеленый чай"));

        System.out.println("Изначальная коробка: ");
        box.printInfo();

        System.out.println("\nОптимизация по минимальному весу: ");
        box.reduceWeightByMinWeight(700);
        box.printInfo();

        box = new Box();
        box.add(new Chocolate("Ritter Sport", 100, 200, "Горький"));
        box.add(new Toffie("Золотой ключик", 300, 150, 300));
        box.add(new Waffle("Вафли глазированная", 150, 85, "Кокос"));
        box.add(new Waffle("Тайяки", 150, 500, "Зеленый чай"));


        System.out.println("\nОптимизация по минимальной цене: ");
        box.reduceWeightByMinPrice(700);
        box.printInfo();

        box.remove(0);
        box.removeLast();
        System.out.println("\nУдаляем по индексу и последний: ");
        box.printInfo();
    }
}