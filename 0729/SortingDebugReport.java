import java.util.Arrays;

public class SortingDebugReport {
    public static void main(String[] args) {
        int[] caseOne = {5, 3, 4, 1, 2};
        int[] caseTwo = {4, 3, 2, 1};
        int[] caseThree = {3, 1, 4, 1, 5};

        System.out.println("=== 錯誤一：Selection Sort 內層迴圈範圍錯誤 ===");
        report(caseOne, "buggyInnerRange");

        System.out.println();
        System.out.println("=== 錯誤二：Insertion Sort 未先保存 key ===");
        report(caseTwo, "buggyKeyNotSaved");

        System.out.println();
        System.out.println("=== 錯誤三：Insertion Sort 比較方向錯誤 ===");
        report(caseThree, "buggyWrongDirection");
    }

    public static void report(int[] source, String mode) {
        int[] buggyData = source.clone();
        int[] fixedData = source.clone();

        if (mode.equals("buggyInnerRange")) {
            buggySelectionSortInnerRange(buggyData);
            fixedSelectionSort(fixedData);
            System.out.println("錯誤原因：內層迴圈從索引 0 開始，重複掃描已排序區，");
            System.out.println("          minIndex 會指向左側已放好的更小值，導致把已確定的位置換走。");
            System.out.println("修正方式：內層迴圈改為 index = start + 1，只掃描未排序區。");
        } else if (mode.equals("buggyKeyNotSaved")) {
            buggyInsertionSortKeyNotSaved(buggyData);
            fixedInsertionSort(fixedData);
            System.out.println("錯誤原因：沒有先把 values[index] 存進 key，右移時 values[index] 已被覆蓋，");
            System.out.println("          之後比較與插入使用的都是被覆蓋後的錯誤值，資料會重複並遺失。");
            System.out.println("修正方式：進入 while 之前先 int key = values[index]，全程使用 key。");
        } else {
            buggyInsertionSortWrongDirection(buggyData);
            fixedInsertionSort(fixedData);
            System.out.println("錯誤原因：目標是升冪，但條件寫成 values[position] < key，");
            System.out.println("          只把較小的元素往右移，結果變成降冪，排序方向與需求相反。");
            System.out.println("修正方式：升冪的條件應為 values[position] > key。");
        }

        System.out.println("原始資料：" + Arrays.toString(source));
        System.out.println("修正前結果：" + Arrays.toString(buggyData));
        System.out.println("修正後結果：" + Arrays.toString(fixedData));
        System.out.println("是否正確：" + (isAscending(fixedData) ? "修正後為升冪" : "修正後仍有誤")
            + "，錯誤版本" + (isAscending(buggyData) ? "意外正確" : "未達升冪"));
    }

    // 錯誤一：內層迴圈從 0 開始，會重複處理左側已排序區，破壞已確定的位置
    public static void buggySelectionSortInnerRange(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;

            for (int index = 0; index < values.length; index++) {
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }

            int temp = values[start];
            values[start] = values[minIndex];
            values[minIndex] = temp;
        }
    }

    // 錯誤二：未先保存 key，values[index] 在右移過程中被覆蓋，比較與插入都取到錯誤值
    public static void buggyInsertionSortKeyNotSaved(int[] values) {
        for (int index = 1; index < values.length; index++) {
            int position = index - 1;

            while (position >= 0 && values[position] > values[index]) {
                values[position + 1] = values[position];
                position--;
            }

            values[position + 1] = values[index];
        }
    }

    // 錯誤三：升冪排序卻使用 <，只右移較小元素，結果變成降冪
    public static void buggyInsertionSortWrongDirection(int[] values) {
        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;

            while (position >= 0 && values[position] < key) {
                values[position + 1] = values[position];
                position--;
            }

            values[position + 1] = key;
        }
    }

    public static void fixedSelectionSort(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;

            for (int index = start + 1; index < values.length; index++) {
                if (values[index] < values[minIndex]) {
                    minIndex = index;
                }
            }

            if (minIndex != start) {
                int temp = values[start];
                values[start] = values[minIndex];
                values[minIndex] = temp;
            }
        }
    }

    public static void fixedInsertionSort(int[] values) {
        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;

            while (position >= 0 && values[position] > key) {
                values[position + 1] = values[position];
                position--;
            }

            values[position + 1] = key;
        }
    }

    public static boolean isAscending(int[] values) {
        for (int index = 1; index < values.length; index++) {
            if (values[index - 1] > values[index]) {
                return false;
            }
        }
        return true;
    }
}
