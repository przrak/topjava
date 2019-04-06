package test;

import java.util.ArrayList;
import java.util.List;

class Pair<T1, T2> {
    T1 object1;
    T2 object2;

    Pair(T1 one, T2 two) {
        object1 = one;
        object2 = two;
    }

    public T1 getFirst() {
        return object1;
    }

    public T2 getSecond() {
        return object2;
    }
}

class Test2 {
    public static void main(String[] args) {
        Pair<Integer, String> pair = new Pair<>(6,
                " Apr");
        System.out.println(pair.getFirst() + pair.getSecond());

        List<?> intList = new ArrayList<Integer>();
        System.out.println(intList);

//        List<? extends Number> numList = new ArrayList<String>();

        List<? extends Number> intList2 = new ArrayList<Integer>();

        ArrayList<Number> arrayList = new ArrayList<>();
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        ArrayList<Double> arrayList2 = new ArrayList<>();
        sum(arrayList);
        sum(arrayList1);
        sum(arrayList2);
    }

    /*
    Тем, что во втором случае Вы в методе знаете тип объекта (T),
    а в первом нет. И это Вас ограничивает в манипуляциях с коллекцией.
    Добавьте в каждый метод код:
     */

    public static double sum(ArrayList<? extends Number> numbers){
        double summ = 0.0;
        for(Number curNumber : numbers)
            summ += curNumber.doubleValue();

//        if (numbers.size() > 1)
//            numbers.add(numbers.remove(0));

        return summ;
    }

    public static <T extends Number> double sum1(ArrayList<T> numbers){
        double summ = 0.0;
        for(Number curNumber : numbers)
            summ += curNumber.doubleValue();

        if (numbers.size() > 1)
            numbers.add(numbers.remove(0));

        return summ;
    }
}