public class TaskLinkedListSystem {
    public static void main(String[] args) {
        TaskLinkedList taskSystem = new TaskLinkedList();

        System.out.println("=== 1. 新增工作測試 ===");
        taskSystem.addNormalTask("T001", "準備會議資料");
        taskSystem.addNormalTask("T002", "回覆客戶信件");
        
        taskSystem.addUrgentTask("T003", "修復伺服器當機問題");
        
        taskSystem.addNormalTask("T004", "更新系統文件");

        System.out.println("\n=== 2. 初始統計與清單 ===");
        taskSystem.printStatistics();
        taskSystem.printUncompletedTasks();

        System.out.println("\n=== 3. 測試完成工作 ===");
        taskSystem.completeTask("T003");
        taskSystem.completeTask("T002");
        taskSystem.completeTask("T999");

        System.out.println("\n=== 4. 完成工作後的統計與清單 ===");
        taskSystem.printStatistics();
        taskSystem.printUncompletedTasks();

        System.out.println("\n=== 5. 測試刪除工作 ===");
        taskSystem.removeTask("T001");
        taskSystem.removeTask("T003");
        taskSystem.removeTask("T888");

        System.out.println("\n=== 6. 最終統計與清單 ===");
        taskSystem.printStatistics();
        taskSystem.printUncompletedTasks();
    }
}
