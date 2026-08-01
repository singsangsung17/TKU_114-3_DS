import java.util.Arrays;

public class SortingExperiment {
    public static void main(String[] args) {
        int[] ascending = {10, 20, 30, 40, 50, 60, 70, 80};
        int[] descending = {80, 70, 60, 50, 40, 30, 20, 10};
        int[] random = {45, 12, 78, 33, 90, 5, 61, 27};

        String[] labels = {"已排序", "反向排序", "隨機排列"};
        int[][] dataSets = {ascending, descending, random};
        int[][] selectionResults = new int[3][];
        int[][] insertionResults = new int[3][];

        for (int group = 0; group < dataSets.length; group++) {
            int[] selectionData = dataSets[group].clone();
            int[] insertionData = dataSets[group].clone();

            selectionResults[group] = selectionSort(selectionData);
            insertionResults[group] = insertionSort(insertionData);

            System.out.println("=== " + labels[group] + " ===");
            System.out.println("原始資料：" + Arrays.toString(dataSets[group]));
            System.out.println("Selection 結果：" + Arrays.toString(selectionData));
            System.out.println("Insertion 結果：" + Arrays.toString(insertionData));
            System.out.printf(
                "Selection：比較 %d 次，交換 %d 次，移動 %d 次%n",
                selectionResults[group][0],
                selectionResults[group][1],
                selectionResults[group][2]
            );
            System.out.printf(
                "Insertion：比較 %d 次，交換 %d 次，移動 %d 次%n",
                insertionResults[group][0],
                insertionResults[group][1],
                insertionResults[group][2]
            );
            System.out.println();
        }

        System.out.println("=== 統計總表 ===");
        System.out.printf("%-10s %-24s %-24s%n", "資料組", "Selection(比較/交換/移動)", "Insertion(比較/交換/移動)");
        for (int group = 0; group < labels.length; group++) {
            System.out.printf(
                "%-10s %-24s %-24s%n",
                labels[group],
                selectionResults[group][0] + "/" + selectionResults[group][1]
                    + "/" + selectionResults[group][2],
                insertionResults[group][0] + "/" + insertionResults[group][1]
                    + "/" + insertionResults[group][2]
            );
        }

        System.out.println();
        System.out.println("=== 觀察結論 ===");
        System.out.println("已排序資料：Selection 仍然掃描完整未排序區，比較次數固定為 n(n-1)/2 = "
            + selectionResults[0][0] + " 次；");
        System.out.println("  Insertion 每輪比較 1 次就停止，只需 " + insertionResults[0][0]
            + " 次比較、0 次移動，是 best case。");
        System.out.println("反向排序資料：Insertion 每個 key 都要移動整個已排序區，移動 "
            + insertionResults[1][2] + " 次，是 worst case；");
        System.out.println("  Selection 的比較次數不變，交換 " + selectionResults[1][1]
            + " 次，可見它的工作量幾乎不受原始順序影響。");
        System.out.println("隨機排列資料：Insertion 的比較與移動次數落在 best 與 worst 之間，"
            + "移動 " + insertionResults[2][2] + " 次；");
        System.out.println("  Selection 比較次數仍為 " + selectionResults[2][0] + " 次。");
        System.out.println("整體結論：兩者都是 O(n^2)，但 Selection 的交換次數最多 n-1 次，"
            + "適合寫入成本高的情境；");
        System.out.println("  Insertion 對接近有序的資料明顯有利，且比較條件使用 > 時具有穩定性。");
        System.out.println("所有演算法都使用同一份原始資料的 clone()，確保比較基準一致。");
    }

    public static int[] selectionSort(int[] values) {
        int comparisons = 0;
        int swaps = 0;
        int moves = 0;

        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;

            for (int index = start + 1; index < values.length; index++) {
                comparisons++;
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }

            if (minIndex != start) {
                int temp = values[start];
                values[start] = values[minIndex];
                values[minIndex] = temp;
                swaps++;
                moves += 3;
            }
        }

        return new int[]{comparisons, swaps, moves};
    }

    public static int[] insertionSort(int[] values) {
        int comparisons = 0;
        int swaps = 0;
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
        }

        return new int[]{comparisons, swaps, moves};
    }
}
