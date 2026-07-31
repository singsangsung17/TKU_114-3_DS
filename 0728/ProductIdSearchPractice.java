import java.util.Scanner;

public class ProductIdSearchPractice {
    private static int comparisonCount = 0;

    public static void main(String[] args) {
        int[] productIds = {450, 105, 326, 203, 118, 780, 512, 640, 118};

        System.out.println("商品編號清單（未排序）：");
        printArray(productIds);

        System.out.println();
        System.out.println("=== 自動測試 ===");
        printResult(productIds, productIds[0]);
        printResult(productIds, productIds[productIds.length - 1]);
        printResult(productIds, 999);

        System.out.println();
        System.out.println("=== 鍵盤查詢 ===");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("請輸入要搜尋的商品編號（輸入 -1 結束）：");
            int target = scanner.nextInt();
            if (target == -1) {
                break;
            }
            printResult(productIds, target);
        }
        scanner.close();
        System.out.println("查詢結束");
    }

    public static int sequentialSearch(int[] values, int target) {
        comparisonCount = 0;
        for (int index = 0; index < values.length; index++) {
            comparisonCount++;
            if (values[index] == target) {
                return index;
            }
        }
        return -1;
    }

    public static void printResult(int[] values, int target) {
        int index = sequentialSearch(values, target);
        if (index == -1) {
            System.out.println("編號 " + target + "：找不到此商品，比較次數 " + comparisonCount);
        } else {
            System.out.println("編號 " + target + "：索引 " + index
                + "，實際值 " + values[index] + "，比較次數 " + comparisonCount);
        }
    }

    public static void printArray(int[] values) {
        for (int index = 0; index < values.length; index++) {
            System.out.println("索引 " + index + " -> " + values[index]);
        }
    }
}
