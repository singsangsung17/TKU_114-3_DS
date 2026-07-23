import java.util.ArrayList;
import java.util.Scanner;

public class EquipmentManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Equipment> equipments = new ArrayList<>();
        int option = -1;

        while (option != 0) {
            System.out.println("\n=== 設備管理系統 ===");
            System.out.println("1. 新增設備");
            System.out.println("2. 依代碼搜尋");
            System.out.println("3. 借出設備");
            System.out.println("4. 歸還設備");
            System.out.println("5. 列出可借設備");
            System.out.println("0. 結束系統");
            System.out.print("請選擇功能：");

            try {
                option = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("請輸入正確的數字選項！");
                continue;
            }

            switch (option) {
                case 1:
                    addEquipment(sc, equipments);
                    break;
                case 2:
                    searchEquipment(sc, equipments);
                    break;
                case 3:
                    borrowEquipment(sc, equipments);
                    break;
                case 4:
                    returnEquipment(sc, equipments);
                    break;
                case 5:
                    listAvailableEquipment(equipments);
                    break;
                case 0:
                    System.out.println("系統已結束。");
                    break;
                default:
                    System.out.println("無效選項，請重新輸入！");
                    break;
            }
        }
        sc.close();
    }

    public static Equipment findByCode(ArrayList<Equipment> equipments, String code) {
        String target = code.trim();
        for (Equipment eq : equipments) {
            if (eq.getCode().equalsIgnoreCase(target)) {
                return eq;
            }
        }
        return null;
    }


    public static void addEquipment(Scanner sc, ArrayList<Equipment> equipments) {
        System.out.print("請輸入設備代碼：");
        String code = sc.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("新增失敗：代碼不可為空白！");
            return;
        }

        if (findByCode(equipments, code) != null) {
            System.out.println("新增失敗：代碼已存在！");
            return;
        }

        System.out.print("請輸入設備名稱：");
        String name = sc.nextLine().trim();
        
        if (name.isEmpty()) {
            System.out.println("新增失敗：名稱不可為空白！");
            return;
        }

        equipments.add(new Equipment(code, name));
        System.out.println("新增成功！");
    }

    public static void searchEquipment(Scanner sc, ArrayList<Equipment> equipments) {
        System.out.print("請輸入要搜尋的設備代碼：");
        String code = sc.nextLine();
        Equipment found = findByCode(equipments, code);

        if (found != null) {
            System.out.println("搜尋結果：" + found);
        } else {
            System.out.println("找不到該代碼的設備。");
        }
    }

    public static void borrowEquipment(Scanner sc, ArrayList<Equipment> equipments) {
        System.out.print("請輸入要借出的設備代碼：");
        String code = sc.nextLine();
        Equipment found = findByCode(equipments, code);

        if (found == null) {
            System.out.println("借出失敗：找不到該設備。");
            return;
        }

        if (found.borrowItem()) {
            System.out.println("借出成功！最新狀態：" + found);
        } else {
            System.out.println("借出失敗：該設備目前已借出。");
        }
    }

    public static void returnEquipment(Scanner sc, ArrayList<Equipment> equipments) {
        System.out.print("請輸入要歸還的設備代碼：");
        String code = sc.nextLine();
        Equipment found = findByCode(equipments, code);

        if (found == null) {
            System.out.println("歸還失敗：找不到該設備。");
            return;
        }

        if (found.returnItem()) {
            System.out.println("歸還成功！最新狀態：" + found);
        } else {
            System.out.println("歸還失敗：該設備目前未被借出。");
        }
    }

    public static void listAvailableEquipment(ArrayList<Equipment> equipments) {
        System.out.println("\n=== 可借用設備清單 ===");
        boolean hasAvailable = false;
        
        for (Equipment eq : equipments) {
            if (eq.isAvailable()) {
                System.out.println(eq);
                hasAvailable = true;
            }
        }
        
        if (!hasAvailable) {
            System.out.println("目前沒有可借用的設備。");
        }
    }
}
