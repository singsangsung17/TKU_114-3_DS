import java.util.ArrayDeque;
import java.util.Deque;

public class DeliveryProcessingSystem {
    private Deque<DeliveryTask> waitingQueue = new ArrayDeque<>();
    private Deque<DeliveryTask> completedStack = new ArrayDeque<>();

    public void addTask(String taskId, String destination) {
        DeliveryTask task = new DeliveryTask(taskId, destination);
        waitingQueue.offer(task);
        System.out.println("新增配送工作：" + task);
    }

    public void completeNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前無待配送工作。");
        } else {
            DeliveryTask task = waitingQueue.poll();
            completedStack.push(task);
            System.out.println("完成配送：" + task);
        }
    }

    public void showNext() {
        DeliveryTask task = waitingQueue.peek();
        if (task == null) {
            System.out.println("目前無下一筆待配送工作。");
        } else {
            System.out.println("下一筆配送工作：" + task);
        }
    }

    public void undoLastCompleted() {
        if (completedStack.isEmpty()) {
            System.out.println("沒有可復原的完成紀錄。");
        } else {
            DeliveryTask task = completedStack.pop();
            waitingQueue.offer(task);
            System.out.println("復原最近完成的工作，重新加入等待列尾端：" + task);
        }
    }

    public void showStatus() {
        System.out.println("等待配送數：" + waitingQueue.size());
        System.out.println("已完成數：" + completedStack.size());
        System.out.println("所有完成處理紀錄：" + completedStack);
    }

    public static void main(String[] args) {
        DeliveryProcessingSystem system = new DeliveryProcessingSystem();

        system.completeNext();

        system.addTask("D001", "Taipei");
        system.addTask("D002", "Taichung");
        system.addTask("D003", "Kaohsiung");

        system.showNext();
        system.showStatus();

        system.completeNext();
        system.completeNext();
        
        system.showStatus();

        system.undoLastCompleted();
        system.showStatus();

        system.completeNext();
        system.showStatus();
    }
}
