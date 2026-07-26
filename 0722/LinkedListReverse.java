public class LinkedListReverse {
    public static void main(String[] args) {
        System.out.println("=== 測試多節點 ===");
        IntNode head = new IntNode(10);
        head.next = new IntNode(20);
        head.next.next = new IntNode(30);
        System.out.print("反轉前：");
        printList(head);
        head = reverse(head);
        System.out.print("反轉後：");
        printList(head);

        System.out.println("\n=== 測試單一節點 ===");
        IntNode singleHead = new IntNode(99);
        System.out.print("反轉前：");
        printList(singleHead);
        singleHead = reverse(singleHead);
        System.out.print("反轉後：");
        printList(singleHead);

        System.out.println("\n=== 測試空串列 ===");
        IntNode emptyHead = null;
        System.out.print("反轉前：");
        printList(emptyHead);
        emptyHead = reverse(emptyHead);
        System.out.print("反轉後：");
        printList(emptyHead);
    }

    public static IntNode reverse(IntNode head) {
        IntNode previous = null;
        IntNode current = head;

        while (current != null) {
            IntNode nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }
        return previous;
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
