public class RecursiveDigitCounter {
    public static void main(String[] args) {
        printCount(12321, 2);
        printCount(5555, 5);
        printCount(10203, 0);
        printCount(0, 0);
        printCount(987654321, 1);
        printCount(44444, 7);
        printCount(9, 9);
    }

    public static int countDigit(int number, int target) {
        if (number < 0) {
            throw new IllegalArgumentException("number 不接受負數");
        }
        if (target < 0 || target > 9) {
            throw new IllegalArgumentException("target 必須介於 0 到 9");
        }

        if (number < 10) {
            return number == target ? 1 : 0;
        }

        int current = number % 10 == target ? 1 : 0;
        return current + countDigit(number / 10, target);
    }

    public static void printCount(int number, int target) {
        System.out.println(number + " 中 " + target + " 出現 "
            + countDigit(number, target) + " 次");
    }
}
