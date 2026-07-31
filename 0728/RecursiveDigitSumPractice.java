
public class RecursiveDigitSumPractice {
    public static void main(String[] args) {
        printDigitSum(5729);
        printDigitSum(0);
        printDigitSum(7);
        printDigitSum(1000);
        printDigitSum(999);
        printDigitSum(123456);
    }

    public static int digitSum(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("digitSum 不接受負數");
        }

        if (number < 10) {
            return number;
        }

        return number % 10 + digitSum(number / 10);
    }

    public static void printDigitSum(int number) {
        System.out.println("digitSum(" + number + ") = " + digitSum(number));
    }
}

