import java.util.Arrays;

public class SelectionSortPractice {
    public static void main(String[] args) {
        int[] values = {42, 18, 35, 7, 29, 14};
        int[] emptyValues = {};
        int[] singleValue = {99};

        System.out.println("=== 一般資料 ===");
        runSort(values);

        System.out.println();
        System.out.println("=== 空陣列 ===");
        runSort(emptyValues);

        System.out.println();
        System.out.println("=== 單一元素陣列 ===");
        runSort(singleValue);
    }

    public static int[] selectionSort(int[] values) {
        int comparisons = 0;
        int swaps = 0;

        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;

            for (int index = start + 1; index < values.length; index++) {
                comparisons++;
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }

            boolean swapped = false;
            if (minIndex != start) {
                swap(values, start, minIndex);
                swaps++;
                swapped = true;
            }

            System.out.printf(
                "第 %d 輪：start=%d, 選中索引=%d, 選中值=%d, %s -> %s%n",
                start + 1,
                start,
                minIndex,
                values[start],
                swapped ? "已交換" : "免交換",
                Arrays.toString(values)
            );
        }

        return new int[]{comparisons, swaps};
    }

    public static void swap(int[] values, int left, int right) {
        int temp = values[left];
        values[left] = values[right];
        values[right] = temp;
    }

    public static void runSort(int[] values) {
        System.out.println("排序前：" + Arrays.toString(values));

        if (values.length < 2) {
            System.out.println("資料量小於 2，不需要進入排序迴圈");
        }

        int[] counts = selectionSort(values);
        System.out.println("排序後：" + Arrays.toString(values));
        System.out.println("比較次數：" + counts[0] + "，實際交換次數：" + counts[1]);
    }
}
