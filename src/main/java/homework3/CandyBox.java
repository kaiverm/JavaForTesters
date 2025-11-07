package homework3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

abstract class Sweets {
    private String name;
    private double weight;
    private double price;

    public Sweets(String name, double weight, double price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public  String getName() {
        return name;
    }
    public double getWeight() {
        return weight;
    }
    public double getPrice() {
        return price;
    }
    public abstract String getUniqueParameter();

    @Override
    public String toString() {
        return "Название: " + name + ", Вес: " + weight + "г, Цена: " + price + "руб, Уникальный парамерт: " + getUniqueParameter();
    }
}

class Chocolate extends Sweets {
    private String type;
    public Chocolate(String name, double weight, double price, String type) {
        super(name, weight, price);
        this.type = type;
    }
    @Override
    public String getUniqueParameter() {
        return "Тип: " + type;
    }
}

class Toffie extends Sweets {
    private int amount;
    public Toffie(String name, double weight, double price, int amount) {
        super(name, weight, price);
        this.amount = amount;
    }
    @Override
    public String getUniqueParameter() {
        return "Штук в упаковке: " + amount;
    }
}

class Waffle extends Sweets {
    private String flavor;
    public Waffle(String name, double weight, double price, String flavor) {
        super(name, weight, price);
        this.flavor = flavor;
    }
    @Override
    public String getUniqueParameter() {
        return  "Вкус: " + flavor;
    }
}

////ИНТЕРФЕЙС КОРОБКИ////
interface Gift {
    void add(Sweets sweets);
    void remove(int index);
    void removeLast();
    double getWeight();
    double getPrice();
    void printInfo();
    void reduceWeightByMinWeight(double maxWeight);
    void reduceWeightByMinPrice(double maxWeight);
}

class Box implements Gift {
    private List<Sweets> allsweets = new ArrayList<>();
    @Override
    public void add(Sweets sweets) {
        allsweets.add(sweets);
    }
    @Override
    public void remove(int index) {
        if (index >= 0 && index < allsweets.size()) {
            allsweets.remove(index);
        } else {
            System.out.println("Неправильный индекс");
        }
    }
    @Override
    public void removeLast() {
        if (!allsweets.isEmpty()) {
            allsweets.remove(allsweets.size() - 1);
        } else {
            System.out.println("Коробка пуста");
        }
    }
    @Override
    public double getWeight() {
        double totalWeight = 0;
        for (Sweets sweets : allsweets) {
            totalWeight += sweets.getWeight();
        }
        return totalWeight;
    }
    @Override
    public double getPrice() {
        double totalPrice = 0;
        for (Sweets sweets : allsweets) {
            totalPrice += sweets.getPrice();
        }
        return totalPrice;
    }
    @Override
    public void printInfo() {
        if (allsweets.isEmpty()) {
            System.out.println("Коробка пуста");
            return;
        }
        for (Sweets sweets : allsweets) {
            System.out.println(sweets);
        }
        System.out.println("Общий вес: " + getWeight() + " г");
        System.out.println("Общая стоимость: " + getWeight() + " руб");
    }

    @Override
    public void reduceWeightByMinWeight(double maxWeight) {
        while (getWeight() >= maxWeight && !allsweets.isEmpty()) {
            Sweets minWeightSweet = Collections.min(allsweets, Comparator.comparingDouble(Sweets::getWeight));
            allsweets.remove(minWeightSweet);
        }
    }
    @Override
    public void reduceWeightByMinPrice(double maxWeight) {
        while (getWeight() >= maxWeight && !allsweets.isEmpty()) {
            Sweets minPriceSweet = Collections.min(allsweets, Comparator.comparingDouble(Sweets::getPrice));
            allsweets.remove(minPriceSweet);
        }
    }
}