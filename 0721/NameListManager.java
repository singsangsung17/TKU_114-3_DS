import java.util.ArrayList;
import java.util.Scanner;

public class NameListManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();
        int option = -1;

        while (option != 0) {
            System.out.println("\n=== 名單管理系統 ===");
            System.out.println("1. 新增姓名");
            System.out.println("2. 搜尋姓名");
            System.out.println("3. 修改姓名");
            System.out.println("4. 刪除姓名");
            System.out.println("5. 列出全部名單");
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
                    addName(sc, names);
                    break;
                case 2:
                    searchName(sc, names);
                    break;
                case 3:
                    updateName(sc, names);
                    break;
                case 4:
                    deleteName(sc, names);
                    break;
                case 5:
                    listNames(names);
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

    public static int findIndex(ArrayList<String> names, String keyword) {
        String target = keyword.trim();
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1; // 找不到回傳 -1
    }

    public static void addName(Scanner sc, ArrayList<String> names) {
        System.out.print("請輸入要新增的姓名：");
        String name = sc.nextLine().trim();
        
        if (name.isEmpty()) {
            System.out.println("新增失敗：不得加入空白姓名！");
            return;
        }
        
        names.add(name);
        System.out.println("新增成功！");
    }

    public static void searchName(Scanner sc, ArrayList<String> names) {
        System.out.print("請輸入要搜尋的姓名：");
        String keyword = sc.nextLine();
        int index = findIndex(names, keyword);
        
        if (index != -1) {
            System.out.println("找到相符的姓名：" + names.get(index) + " (位於索引 " + index + ")");
        } else {
            System.out.println("找不到該姓名。");
        }
    }

    public static void updateName(Scanner sc, ArrayList<String> names) {
        System.out.print("請輸入要修改的【原姓名】：");
        String oldName = sc.nextLine();
        int index = findIndex(names, oldName);

        if (index == -1) {
            System.out.println("修改失敗：找不到該姓名。");
            return;
        }

        System.out.print("請輸入【新姓名】：");
        String newName = sc.nextLine().trim();
        
        if (newName.isEmpty()) {
            System.out.println("修改失敗：不得更改為空白姓名！");
            return;
        }

        names.set(index, newName);
        System.out.println("修改成功！");
    }

    public static void deleteName(Scanner sc, ArrayList<String> names) {
        System.out.print("請輸入要刪除的姓名：");
        String target = sc.nextLine();
        int index = findIndex(names, target);

        if (index != -1) {
            String removed = names.remove(index);
            System.out.println("刪除成功：已移除 " + removed);
        } else {
            System.out.println("刪除失敗：找不到該姓名。");
        }
    }

    public static void listNames(ArrayList<String> names) {
        System.out.println("\n=== 全部名單 ===");
        if (names.isEmpty()) {
            System.out.println("目前沒有任何名單。");
            return;
        }
        for (int i = 0; i < names.size(); i++) {
            System.out.println((i + 1) + ". " + names.get(i));
        }
    }
}
