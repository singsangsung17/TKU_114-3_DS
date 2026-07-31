public class RecursiveNameSearchPractice {
    public static void main(String[] args) {
        String[] names = {"Alice", "Bob", "Cathy", "David", "Emma"};
        String[] emptyNames = {};

        System.out.println("=== 一般陣列測試 ===");
        printResult(names, "Alice");
        printResult(names, "Cathy");
        printResult(names, "Emma");
        printResult(names, "Frank");

        System.out.println();
        System.out.println("=== 空陣列測試 ===");
        printResult(emptyNames, "Alice");
    }

    public static int search(String[] names, String target, int index) {
        if (names == null || index >= names.length) {
            return -1;
        }

        if (names[index].equals(target)) {
            return index;
        }

        return search(names, target, index + 1);
    }

    public static void printResult(String[] names, String target) {
        int index = search(names, target, 0);
        if (index == -1) {
            System.out.println(target + "：找不到（陣列長度 " + names.length + "）");
        } else {
            System.out.println(target + "：索引 " + index);
        }
    }
}
