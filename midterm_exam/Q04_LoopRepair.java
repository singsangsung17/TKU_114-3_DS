package midterm_exam;

public class Q04_LoopRepair {
    public static void main(String[] args) {
        System.out.println(sumOddRange(3, 7));   // 預期：15
        System.out.println(sumOddRange(7, 3));   // 預期：15
        System.out.println(sumOddRange(2, 2));   // 預期：0
        System.out.println(sumOddRange(-3, 3));  // 預期：0
    }

    public static int sumOddRange(int start, int end) {
        int lower = Math.min(start, end);
        int upper = Math.max(start, end);
        
        int sum = 0;
        
        for (int i = lower; i <= upper; i++) {
            if (i % 2 != 0) {
                sum += i;
            }
        }
        
        return sum;
    }
}
