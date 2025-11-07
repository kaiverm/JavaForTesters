package homework1;

class Programmer {
    // класс Programmer с полями:
    //       - String name - имя программиста
    //       - int age - возраст программиста
    //       - boolean isHasTask - наличие активной задачи
    //       - int tiredness - уровень усталости
    //       - boolean isKnowJava - знает ли программист Java
    String name;
    int age;
    boolean isHasTask;
    int tiredness;
    boolean isKnowJava;

    // конструктор Programmer(String name, int age, boolean isHasTask, int tiredness, boolean isKnowJava):
    public Programmer(String name, int age, boolean isHasTask, int tiredness, boolean isKnowJava) {
        this.name = name;
        this.age = age;
        this.isHasTask = isHasTask;
        this.tiredness = tiredness;
        this.isKnowJava = isKnowJava;
    }

    // метод goWork():
    //       - Если у программиста есть задача, выводит сообщение "[name] работает над задачей." и увеличить tiredness на 1
    //       - Если задачи нет, выводит сообщение "[name] не имеет активных задач, и пошел ее получать" и вызвать getTask()
    public void goWork() {
        if (isHasTask) {
            System.out.println(name + " работает над задачей.");
            tiredness++;
        } else {
            System.out.println(name + " не имеет активных задач, и пошел ее получать");
            getTask();
        }
    }
    // метод getTask():
    //       - Устанавливает isHasTask в true
    public void getTask() {
        isHasTask = true;
    }

    // метод finishTask():
    //       - Устанавливает isHasTask в false
    public void finishTask() {
        isHasTask = false;
    }

    // метод goSleep():
    //       - Выводит сообщение "[name] идет спать."
    //       - Уменьшает tiredness на 1
    public void goSleep() {
        System.out.println(name + " идет спать.");
        tiredness--;
    }

    // метод teachJava(Programmer anotherProgrammer):
    //       - Если текущий программист знает Java, а другой нет:
    //         - Обучает другого программиста (установить isKnowJava в true)
    //         - Выводит сообщение "[name] обучил [anotherProgrammer.name] языку Java."
    //       - Если текущий программист не знает Java, выводит "[name] не знает Java и не может обучить [anotherProgrammer.name]."
    //       - Если второй программист уже знает Java, выводит "[anotherProgrammer.name] уже знает Java."
    public void teachJava(Programmer anotherProgrammer) {
        if (isKnowJava && !anotherProgrammer.isKnowJava) {
            anotherProgrammer.isKnowJava = true;
            System.out.println(name + " обучил " + anotherProgrammer.name + " языку Java.");
            tiredness++;
        } else if (!isKnowJava) {
            System.out.println(name + " не знает Java и не может обучить " + anotherProgrammer.name + ".");
            getTask();
        } else {
            System.out.println(anotherProgrammer.name + " уже знает Java.");
        }
    }

    // проверка отработки методов в main
    //       - Создание двух программистов с разными характеристиками
    //       - Проверка работы всех методов класса
    public static void main(String[] args) {
        Programmer proger1 = new Programmer("Abraham", 34, false, 7, true);
        Programmer proger2 = new Programmer("Bella", 23, true, 5, false);

        proger1.goWork();
        proger2.goWork();

        proger1.getTask();
        proger2.getTask();

        proger1.goWork();
        proger2.goWork();

        proger1.finishTask();
        proger2.finishTask();

        proger1.goWork();
        proger2.goWork();

        proger1.goSleep();
        proger2.goSleep();

        proger2.teachJava(proger1);
        proger1.teachJava(proger2);
        proger2.teachJava(proger1);

    }

}
