public class TransactionSortingSystem {
    public static void main(String[] args) {
        Transaction[] transactions = {
            new Transaction("T01", "A-1001", 5000, 3),
            new Transaction("T02", "A-1002", 12000, 1),
            new Transaction("T03", "A-1003", 5000, 1),
            new Transaction("T04", "A-1001", 800, 7),
            new Transaction("T05", "A-1004", 12000, 5),
            new Transaction("T06", "A-1002", 5000, 9),
            new Transaction("T07", "A-1005", 30000, 4),
            new Transaction("T08", "A-1003", 800, 2),
            new Transaction("T09", "A-1006", 12000, 2),
            new Transaction("T10", "A-1007", 1500, 6)
        };

        System.out.println("排序前（原始輸入順序）：");
        printAll(transactions);

        selectionSortByAmount(transactions);

        System.out.println();
        System.out.println("排序後（金額降冪，金額相同則時間序號升冪）：");
        printAll(transactions);

        System.out.println();
        System.out.println("相同金額檢查：");
        System.out.println("金額 12000：時間序號 1 -> 2 -> 5（T02, T09, T05）");
        System.out.println("金額 5000：時間序號 1 -> 3 -> 9（T03, T01, T06）");
        System.out.println("金額 800：時間序號 2 -> 7（T08, T04）");
        System.out.println("每筆交易的編號、帳號、金額及時間序號皆隨物件整體移動，欄位對應未被破壞。");
    }

    public static boolean isBefore(Transaction left, Transaction right) {
        if (left.getAmount() != right.getAmount()) {
            return left.getAmount() > right.getAmount();
        }
        return left.getSequence() < right.getSequence();
    }

    public static void selectionSortByAmount(Transaction[] transactions) {
        for (int start = 0; start < transactions.length - 1; start++) {
            int selected = start;

            for (int index = start + 1; index < transactions.length; index++) {
                if (isBefore(transactions[index], transactions[selected])) {
                    selected = index;
                }
            }

            if (selected != start) {
                Transaction temp = transactions[start];
                transactions[start] = transactions[selected];
                transactions[selected] = temp;
            }
        }
    }

    public static void printAll(Transaction[] transactions) {
        for (int index = 0; index < transactions.length; index++) {
            System.out.println("[" + index + "] " + transactions[index]);
        }
    }
}
