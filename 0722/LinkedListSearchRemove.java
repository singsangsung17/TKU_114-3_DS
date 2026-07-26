public class LinkedListSearchRemove {
    public static void main(String[] args) {
        IntNode head = new IntNode(10);
        head.next = new IntNode(20);
        head.next.next = new IntNode(30);
        head.next.next.next = new IntNode(40);

        System.out.print("初始串列：");
        printList(head);

        System.out.println("contains(20): " + contains(head, 20));
        System.out.println("contains(99): " + contains(head, 99));

        System.out.print("刪除 head (10)：");
        head = removeValue(head, 10);
        printList(head);

        System.out.print("刪除中間 (30)：");
        head = removeValue(head, 30);
        printList(head);

        System.out.print("刪除最後 (40)：");
        head = removeValue(head, 40);
        printList(head);

        System.out.print("刪除找不到 (99)：");
        head = removeValue(head, 99);
        printList(head);

        System.out.print("空串列測試刪除 (10)：");
        IntNode emptyHead = null;
        emptyHead = removeValue(emptyHead, 10);
        printList(emptyHead);
    }

    public static boolean contains(IntNode head, int target) {
        IntNode current = head;
        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public static IntNode removeValue(IntNode head, int target) {
        if (head == null) {
            return null;
        }
        if (head.data == target) {
            return head.next;
        }
        IntNode previous = head;
        IntNode current = head.next;
        while (current != null) {
            if (current.data == target) {
                previous.next = current.next;
                break;
            }
            previous = current;
            current = current.next;
        }
        return head;
    }

    public static void printList(IntNode head) {
        IntNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
