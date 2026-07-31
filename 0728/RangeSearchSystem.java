public class RangeSearchSystem {
    public static void main(String[] args) {
        int[] values = {10, 20, 20, 20, 30, 30, 40, 50, 50, 50, 50, 60};
        int[] emptyValues = {};

        System.out.println("已排序資料（含重複）：");
        printArray(values);

        System.out.println();
        printRange(values, 20);
        printRange(values, 50);
        printRange(values, 10);
        printRange(values, 60);
        printRange(values, 35);
        printRange(emptyValues, 20);
    }

    public static int findFirst(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;
        int answer = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (values[mid] == target) {
                answer = mid;
                high = mid - 1;
            } else if (target < values[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return answer;
    }

    public static int findLast(int[] values, int target) {
        int low = 0;
        int high = values.length - 1;
        int answer = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (values[mid] == target) {
                answer = mid;
                low = mid + 1;
            } else if (target < values[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return answer;
    }

    public static int[] searchRange(int[] values, int target) {
        if (values == null || values.length == 0) {
            return new int[]{-1, -1};
        }

        int first = findFirst(values, target);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        return new int[]{first, findLast(values, target)};
    }

    public static void printRange(int[] values, int target) {
        int[] range = searchRange(values, target);

        if (range[0] == -1) {
            System.out.println("搜尋 " + target + "：結果 [-1, -1]，出現 0 次（找不到）");
            return;
        }

        int count = range[1] - range[0] + 1;
        System.out.println("搜尋 " + target + "：結果 [" + range[0] + ", " + range[1]
            + "]，出現 " + count + " 次");
    }

    public static void printArray(int[] values) {
        for (int index = 0; index < values.length; index++) {
            System.out.print("[" + index + "]" + values[index] + " ");
        }
        System.out.println();
    }
}
