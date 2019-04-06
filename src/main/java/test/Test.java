package test;

class BoxPrinter<T> {
    private T val;

    public BoxPrinter(T arg) {
        val = arg;
    }

    public String toString() {
        return "{" + val + "}";
    }

    public T getValue() {
        return val;
    }
}

class Test {
    public static void main(String[] args) {
        BoxPrinter<Integer> value1 = new BoxPrinter<Integer>(new Integer(10));
        System.out.println(value1);
        Integer intValue1 = (Integer) value1.getValue();
        BoxPrinter<String> value2 = new BoxPrinter<String>("Hello world");
        System.out.println(value2);

        // Здесь программист допустил ошибку, присваивая
        // переменной типа Integer значение типа String.
        String stringValue = value2.getValue();
    }
}