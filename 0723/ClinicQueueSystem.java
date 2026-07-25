import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ClinicQueueSystem {
    private Deque<Patient> waitingQueue = new ArrayDeque<>();
    private List<String> issuedTickets = new ArrayList<>();
    private int totalServed = 0;

    public void register(String ticketNumber, String name, String department) {
        if (issuedTickets.contains(ticketNumber)) {
            System.out.println("掛號失敗：號碼 " + ticketNumber + " 已存在！");
            return;
        }
        Patient newPatient = new Patient(ticketNumber, name, department);
        waitingQueue.offer(newPatient);
        issuedTickets.add(ticketNumber);
        System.out.println("掛號成功：" + newPatient);
    }

    public void callNext() {
        Patient patient = waitingQueue.poll();
        if (patient == null) {
            System.out.println("目前無人等待看診。");
        } else {
            System.out.println("看診：" + patient.getTicketNumber() + " " + patient.getName() + " (" + patient.getDepartment() + ")");
            totalServed++;
        }
    }

    public void showNext() {
        Patient nextPatient = waitingQueue.peek();
        if (nextPatient == null) {
            System.out.println("目前無下一位等待者。");
        } else {
            System.out.println("下一位：" + nextPatient);
        }
    }

    public void showWaitingList() {
        System.out.println("等待清單：" + waitingQueue);
    }

    public void showStatistics() {
        System.out.println("總服務人數：" + totalServed);
        
        List<String> departments = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        
        for (Patient p : waitingQueue) {
            String dept = p.getDepartment();
            int index = departments.indexOf(dept);
            if (index == -1) {
                departments.add(dept);
                counts.add(1);
            } else {
                counts.set(index, counts.get(index) + 1);
            }
        }
        
        System.out.println("各科別等待人數：");
        if (departments.isEmpty()) {
            System.out.println("目前無等待人數");
        } else {
            for (int i = 0; i < departments.size(); i++) {
                System.out.println(departments.get(i) + "：" + counts.get(i) + " 人");
            }
        }
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();
        
        clinic.callNext();
        
        clinic.register("N001", "Alice", "內科");
        clinic.register("N002", "Bob", "外科");
        clinic.register("N001", "Charlie", "內科"); 
        clinic.register("N003", "David", "內科");
        clinic.register("N004", "Eve", "外科");
        
        clinic.showWaitingList();
        clinic.showNext();
        
        clinic.callNext();
        clinic.callNext();
        
        clinic.showStatistics();
    }
}
