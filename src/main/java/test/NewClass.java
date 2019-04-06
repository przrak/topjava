package test;

import java.util.ArrayList;
import java.util.List;

public class NewClass {

    public static void writeToList(List<? super Apple> apples) {
        apples.add(new Apple());
        apples.add(new Golden());
//        apples.add(new Fruit()); //ошибка 1
    }

    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<Apple>();
        List<Fruit> fruits = new ArrayList<Fruit>();
        List<Plant> plants = new ArrayList<Plant>();
        List<Golden> goldens = new ArrayList<Golden>();
        writeToList(apples);
        writeToList(fruits);
        writeToList(plants);
//        writeToList(goldens);    //ошибка 2
    }
}

//super для Apple - Apple, Fruit, Plant
/*
ключевое слово super задает ограничение "снизу", т.е. в метод writeToList можем передавать любой список List<Класс>
где Класс - это любой класс базовый для Apple включительно.(ошибка 2).

Почему в методе мы не можем добавить экземпляр Fruit?(ошибка 1), по-тому, что там нам не известен конкретный тип
списка ведь мы могли передать туда ArrayList<Apple>(), а Fruit не является Apple.
 */

class Plant {
}

class Fruit extends Plant {
}

class Apple extends Fruit {
}

class Orange extends Fruit {
}

class Golden extends Apple {
}

class Jhonatan extends Apple {
}

/*
PECS (short for Producer extends and Consumer super)
 "PECS" is from the collection's point of view.
 PECS с точки зрения коллекции
 If you are only pulling items from a generic collection, it is a producer and you should use extends;
 Если вы только берете предметы из общей коллекции - это производитель и вы должны использовать EXTENDS
 if you are only stuffing items in, it is a consumer and you should use super.
 Если вы только кладете предметы в коллекцию - это потребитель и вы должны использовать SUPER
 If you do both with the same collection, you shouldn't use either extends or super.
 Если вы делаете с коллекцией и то и то вы не должны использовать ни extends ни super.
 */
