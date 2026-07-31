import java.util.Scanner;

public class SeatNumberSearchPractice {
    public static void main(String[] args) {
        int[] seatNumbers = {
            101, 105, 108, 112, 120, 125,
            130, 145, 152, 168, 173, 190
        };

        System.out.println("座位編號（已排序，共 " + seatNumbers.length + " 筆）：");
        printArray(seatNumbers);

        System.out.println();
        System.out.println("=== 自動測試 ===");
        printResult(seatNumbers, seatNumbers[0]);
        printResult(seatNumbers, seatNumbers[seatNumbers.length - 1]);
        printResult(seatNumbers, seatNumbers[seatNumbers.length / 2]);
        printResult(seatNumbers, 999);

        System.out.println();
        System.out.println("=== 鍵盤查詢 ===");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("請輸入座位編號（輸入 -1 結束）：");
            int target = scanner.nextInt();
            if (target == -1) {
                break;
            }
            printResult(seatNumbers, target);
        }
        scanner.close();
        System.out.println("查詢結束");
    }

    public static int binarySearch(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            System.out.printf(
                "  low=%d, mid=%d, high=%d, values[mid]=%d%n",
                low, mid, high, values[mid]
            );

            if (values[mid] == target) {
                return mid;
            }
            if (target < values[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void printResult(int[] values, int target) {
        System.out.println("搜尋 " + target + "：");
        int index = binarySearch(values, target);
        if (index == -1) {
            System.out.println("  結果：找不到座位 " + target);
        } else {
            System.out.println("  結果：索引 " + index + "，座位 " + values[index]);
        }
    }

    public static void printArray(int[] values) {
        for (int index = 0; index < values.length; index++) {
            System.out.print("[" + index + "]" + values[index] + " ");
        }
        System.out.println();
    }
}
