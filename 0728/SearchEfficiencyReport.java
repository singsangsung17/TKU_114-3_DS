public class SearchEfficiencyReport {
    public static void main(String[] args) {
        int[] sizes = {16, 128, 1024};

        System.out.printf("%-8s %-12s %-14s %-14s%n",
            "資料量", "搜尋目標", "Sequential", "Binary");
        System.out.println("------------------------------------------------");

        for (int size : sizes) {
            int[] values = buildData(size);
            int firstValue = values[0];
            int lastValue = values[values.length - 1];
            int missingValue = -1;

            printRow(size, "第一筆(" + firstValue + ")", values, firstValue);
            printRow(size, "最後一筆(" + lastValue + ")", values, lastValue);
            printRow(size, "不存在(" + missingValue + ")", values, missingValue);
            System.out.println("------------------------------------------------");
        }

        System.out.println();
        System.out.println("觀察結果：");
        System.out.println("1. Sequential Search 找第一筆只要 1 次比較，但找最後一筆或找不到");
        System.out.println("   時比較次數等於資料量 n，屬於 O(n)，資料量變 64 倍次數也變 64 倍。");
        System.out.println("2. Binary Search 不論找哪一筆，比較次數都在 log2(n)+1 以內；");
        System.out.println("   16 筆約 5 次、128 筆約 8 次、1024 筆約 11 次，屬於 O(log n)。");
        System.out.println("3. 資料量從 16 成長到 1024（64 倍）時，Sequential 的最差次數成長 64 倍，");
        System.out.println("   Binary 只從 5 次成長到 11 次，差距隨資料量增加而快速拉大。");
        System.out.println("4. 但 Binary Search 的前提是資料必須已排序；若資料未排序且只查一次，");
        System.out.println("   先排序的成本可能高於直接使用 Sequential Search。");
        System.out.println("5. 本報告以實際比較次數統計，不以執行時間作為判斷依據，");
        System.out.println("   因為短程式的執行時間容易受到硬體與 JVM 暖機影響。");
    }

    public static int[] buildData(int size) {
        int[] values = new int[size];
        for (int index = 0; index < size; index++) {
            values[index] = (index + 1) * 2;
        }
        return values;
    }

    public static int sequentialChecks(int[] values, int target) {
        int checks = 0;
        for (int index = 0; index < values.length; index++) {
            checks++;
            if (values[index] == target) {
                break;
            }
        }
        return checks;
    }

    public static int binaryChecks(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;
        int checks = 0;

        while (low <= high) {
            checks++;
            int mid = low + (high - low) / 2;
            if (values[mid] == target) {
                break;
            }
            if (target < values[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return checks;
    }

    public static void printRow(int size, String label, int[] values, int target) {
        System.out.printf("%-8d %-12s %-14d %-14d%n",
            size, label, sequentialChecks(values, target), binaryChecks(values, target));
    }
}
