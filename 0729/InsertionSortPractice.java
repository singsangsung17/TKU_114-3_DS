import java.util.Arrays;

public class InsertionSortPractice {
    public static void main(String[] args) {
        int[] original = {30, 10, 20, 50, 40, 5};
        int[] sorted = {5, 10, 20, 30, 40, 50};
        int[] reversed = {50, 40, 30, 20, 10, 5};

        System.out.println("=== 指定資料 ===");
        int[] originalCounts = runSort(original.clone(), true);

        System.out.println();
        System.out.println("=== 已排序資料 ===");
        int[] sortedCounts = runSort(sorted.clone(), false);

        System.out.println();
        System.out.println("=== 反向排序資料 ===");
        int[] reversedCounts = runSort(reversed.clone(), false);

        System.out.println();
        System.out.println("=== 移動次數比較 ===");
        System.out.printf("%-14s %-10s %-10s%n", "資料組", "比較次數", "右移次數");
        System.out.printf("%-14s %-10d %-10d%n", "指定資料", originalCounts[0], originalCounts[1]);
        System.out.printf("%-14s %-10d %-10d%n", "已排序", sortedCounts[0], sortedCounts[1]);
        System.out.printf("%-14s %-10d %-10d%n", "反向排序", reversedCounts[0], reversedCounts[1]);

        System.out.println();
        System.out.println("說明：反向排序資料的移動次數最多，共 " + reversedCounts[1] + " 次。");
        System.out.println("原因是每個 key 都比左側所有元素小，while 條件每次都成立，");
        System.out.println("必須把已排序區全部右移，屬於 Insertion Sort 的 worst case，");
        System.out.println("移動次數為 n(n-1)/2。已排序資料則每輪比較 1 次就停止，右移 0 次，");
        System.out.println("屬於 best case，比較次數為 n-1。");
    }

    public static int[] insertionSort(int[] values, boolean showTrace) {
        int comparisons = 0;
        int moves = 0;

        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;

            while (position >= 0) {
                comparisons++;
                if (values[position] <= key) {
                    break;
                }
                values[position + 1] = values[position];
                moves++;
                position--;
            }

            values[position + 1] = key;

            if (showTrace) {
                System.out.printf(
                    "第 %d 輪：key=%d, 插入位置=%d, %s%n",
                    index,
                    key,
                    position + 1,
                    Arrays.toString(values)
                );
            }
        }

        return new int[]{comparisons, moves};
    }

    public static int[] runSort(int[] values, boolean showTrace) {
        System.out.println("排序前：" + Arrays.toString(values));
        int[] counts = insertionSort(values, showTrace);
        System.out.println("排序後：" + Arrays.toString(values));
        System.out.println("比較次數：" + counts[0] + "，右移次數：" + counts[1]);
        return counts;
    }
}
