package test;

class BoxPrinter1 {
    private Object val;

    public BoxPrinter1(Object arg) {
        val = arg;
    }

    public String toString() {
        return "{" + val + "}";
    }

    public Object getValue() {
        return val;
    }
}

class Test1 {
    public static void main(String[] args) {
        BoxPrinter1 value1 = new BoxPrinter1(new Integer(10));
        System.out.println(value1);
        Integer intValue1 = (Integer) value1.getValue();
        BoxPrinter1 value2 = new BoxPrinter1("Hello world");
        System.out.println(value2);

        // Здесь программист допустил ошибку, присваивая
        // переменной типа Integer значение типа String.
        Integer intValue2 = (Integer) value2.getValue();
    }
}