public class AllOccurrenceSearch {
    private static int comparisonCount = 0;
    public static void main(String[] args) {
        int[] values = {45, 12, 78, 12, 90, 33, 12, 78, 5};
        int[] emptyValues = {};

        System.out.println("資料（未排序）：");
        printArray(values);

        System.out.println();
        printResult(values, 12);
        printResult(values, 78);
        printResult(values, 5);
        printResult(values, 100);
        printResult(emptyValues, 12);
    }

    public static int[] findAll(int[] values, int target) {
        comparisonCount = 0;
        int[] buffer = new int[values.length];
        int count = 0;

        for (int index = 0; index < values.length; index++) {
            comparisonCount++;
            if (values[index] == target) {
                buffer[count] = index;
                count++;
            }
        }

        int[] result = new int[count];
        for (int index = 0; index < count; index++) {
            result[index] = buffer[index];
        }
        return result;
    }

    public static void printResult(int[] values, int target) {
        int[] positions = findAll(values, target);

        if (positions.length == 0) {
            System.out.println("搜尋 " + target + "：資料中不存在此數值，比較次數 "
                + comparisonCount);
            return;
        }

        StringBuilder indexText = new StringBuilder();
        for (int i = 0; i < positions.length; i++) {
            if (i > 0) {
                indexText.append(", ");
            }
            indexText.append(positions[i]);
        }

        System.out.println("搜尋 " + target + "：索引 [" + indexText + "]，出現 "
            + positions.length + " 次，比較次數 " + comparisonCount);
    }

    public static void printArray(int[] values) {
        for (int index = 0; index < values.length; index++) {
            System.out.print("[" + index + "]" + values[index] + " ");
        }
        System.out.println();
    }
}
