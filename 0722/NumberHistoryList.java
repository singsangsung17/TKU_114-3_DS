public class NumberHistoryList {
    private IntNode head;
    private int size;

    public NumberHistoryList() {
        head = null;
        size = 0;
    }

    public void addFirst(int value) {
        IntNode newNode = new IntNode(value);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("前端新增：" + value);
    }

    public void addLast(int value) {
        IntNode newNode = new IntNode(value);
        if (head == null) {
            head = newNode;
        } else {
            IntNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("尾端新增：" + value);
    }

    public boolean contains(int target) {
        IntNode current = head;
        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean removeValue(int target) {
        if (head == null) {
            return false;
        }
        if (head.data == target) {
            head = head.next;
            size--;
            System.out.println("刪除成功：" + target);
            return true;
        }

        IntNode previous = head;
        IntNode current = head.next;
        while (current != null) {
            if (current.data == target) {
                previous.next = current.next;
                size--;
                System.out.println("刪除成功：" + target);
                return true;
            }
            previous = current;
            current = current.next;
        }
        System.out.println("刪除失敗，找不到：" + target);
        return false;
    }

    public void printList() {
        System.out.print("目前串列：");
        IntNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public void printStatistics() {
        if (head == null) {
            System.out.println("統計資料：目前為空串列 (size: 0, 總和: 0, 無最大值與最小值)");
            return;
        }

        int sum = 0;
        int max = head.data;
        int min = head.data;
        IntNode current = head;

        while (current != null) {
            sum += current.data;
            if (current.data > max) {
                max = current.data;
            }
            if (current.data < min) {
                min = current.data;
            }
            current = current.next;
        }
        System.out.println("統計資料：size=" + size + ", 總和=" + sum + ", 最大值=" + max + ", 最小值=" + min);
    }

    public static void main(String[] args) {
        NumberHistoryList list = new NumberHistoryList();

        // 測試 1：空串列統計
        System.out.println("--- 操作 1：空串列統計 ---");
        list.printStatistics();

        // 測試 2-4：新增資料
        System.out.println("\n--- 操作 2~4：新增資料 ---");
        list.addLast(50);
        list.addFirst(20);
        list.addLast(80);
        list.printList();

        // 測試 5：搜尋資料
        System.out.println("\n--- 操作 5：搜尋資料 ---");
        System.out.println("是否包含 50：" + list.contains(50));
        System.out.println("是否包含 99：" + list.contains(99));

        // 測試 6：一般統計
        System.out.println("\n--- 操作 6：一般統計 ---");
        list.printStatistics();

        // 測試 7：刪除中間資料
        System.out.println("\n--- 操作 7：刪除資料 ---");
        list.removeValue(50);
        list.printList();

        // 測試 8：刪除找不到的資料
        System.out.println("\n--- 操作 8：刪除不存在資料 ---");
        list.removeValue(99);
        list.printStatistics();
        
        // 額外測試：刪除 head 與清空
        System.out.println("\n--- 額外操作：清空測試 ---");
        list.removeValue(20);
        list.removeValue(80);
        list.printList();
        list.printStatistics();
    }
}
