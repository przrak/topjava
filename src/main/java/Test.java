public class Test {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        sum();
        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("программа выполнялась " + timeSpent + " миллисекунд");
    }

    private static long sum() {
        long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++)
            sum += i;

        return sum;
    }
}
