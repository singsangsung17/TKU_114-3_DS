public class BuildLinkedList {
    public static void main(String[] args) {
        IntNode head = new IntNode(10);
        head.next = new IntNode(20);
        head.next.next = new IntNode(30);
        head.next.next.next = new IntNode(40);

        System.out.println("=== 測試正常串列 ===");
        processList(head);

        System.out.println("\n=== 測試空串列 ===");
        IntNode emptyHead = null;
        processList(emptyHead);
    }

    public static void processList(IntNode head) {
        if (head == null) {
            System.out.println("目前為空串列。");
            System.out.println("節點數：0，總和：0");
            return;
        }

        IntNode current = head;
        int count = 0;
        int sum = 0;

        System.out.print("走訪輸出：");
        while (current != null) {
            System.out.print(current.data + " -> ");
            
            count++;
            sum += current.data;
            
            current = current.next;
        }
        System.out.println("null");
        
        System.out.println("節點數：" + count);
        System.out.println("總和：" + sum);
    }
}
