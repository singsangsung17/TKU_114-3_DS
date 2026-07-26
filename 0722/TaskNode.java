public class TaskNode {
    String taskCode;
    String description;
    boolean isCompleted;
    TaskNode next;

    public TaskNode(String taskCode, String description) {
        this.taskCode = taskCode;
        this.description = description;
        this.isCompleted = false; // 預設為未完成
        this.next = null;
    }
}
