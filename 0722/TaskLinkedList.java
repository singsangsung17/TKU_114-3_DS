public class TaskLinkedList {
    private TaskNode head;
    private int size;

    public TaskLinkedList() {
        head = null;
        size = 0;
    }

    public void addUrgentTask(String taskCode, String description) {
        TaskNode newNode = new TaskNode(taskCode, description);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("新增緊急工作：" + description + " [" + taskCode + "]");
    }

    public void addNormalTask(String taskCode, String description) {
        TaskNode newNode = new TaskNode(taskCode, description);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("新增一般工作：" + description + " [" + taskCode + "]");
    }

    public boolean completeTask(String taskCode) {
        TaskNode current = head;
        while (current != null) {
            if (current.taskCode.equals(taskCode)) {
                if (!current.isCompleted) {
                    current.isCompleted = true;
                    System.out.println("已完成工作：" + current.description + " [" + taskCode + "]");
                } else {
                    System.out.println("工作 [" + taskCode + "] 之前已經完成了。");
                }
                return true;
            }
            current = current.next;
        }
        System.out.println("完成失敗：找不到工作代碼 [" + taskCode + "]");
        return false;
    }

    public boolean removeTask(String taskCode) {
        if (head == null) {
            System.out.println("刪除失敗：目前無任何工作。");
            return false;
        }

        if (head.taskCode.equals(taskCode)) {
            System.out.println("已刪除工作：" + head.description + " [" + taskCode + "]");
            head = head.next;
            size--;
            return true;
        }

        TaskNode previous = head;
        TaskNode current = head.next;
        while (current != null) {
            if (current.taskCode.equals(taskCode)) {
                System.out.println("已刪除工作：" + current.description + " [" + taskCode + "]");
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        
        System.out.println("刪除失敗：找不到工作代碼 [" + taskCode + "]");
        return false;
    }

    public void printUncompletedTasks() {
        System.out.println("--- 未完成工作清單 ---");
        if (head == null) {
            System.out.println("(無)");
            return;
        }

        boolean hasUncompleted = false;
        TaskNode current = head;
        while (current != null) {
            if (!current.isCompleted) {
                System.out.println("[" + current.taskCode + "] " + current.description);
                hasUncompleted = true;
            }
            current = current.next;
        }
        
        if (!hasUncompleted) {
            System.out.println("(所有工作皆已完成)");
        }
    }

    public void printStatistics() {
        int uncompletedCount = 0;
        TaskNode current = head;
        while (current != null) {
            if (!current.isCompleted) {
                uncompletedCount++;
            }
            current = current.next;
        }
        System.out.println("統計資料：總工作數 = " + size + ", 未完成數量 = " + uncompletedCount);
    }
}
