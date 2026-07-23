import java.util.ArrayList;
import java.util.Scanner;

public class ContactBookSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();
        int option = -1;

        while (option != 0) {
            System.out.println("\n=== 聯絡人管理系統 ===");
            System.out.println("1. 新增聯絡人");
            System.out.println("2. 搜尋聯絡人");
            System.out.println("3. 修改聯絡人電話");
            System.out.println("4. 刪除聯絡人");
            System.out.println("5. 列出完整清單");
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
                    addContact(sc, contacts);
                    break;
                case 2:
                    searchContact(sc, contacts);
                    break;
                case 3:
                    updatePhone(sc, contacts);
                    break;
                case 4:
                    deleteContact(sc, contacts);
                    break;
                case 5:
                    listAllContacts(contacts);
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

    public static Contact findByCode(ArrayList<Contact> contacts, String code) {
        String target = code.trim();
        for (Contact c : contacts) {
            if (c.getCode().equalsIgnoreCase(target)) {
                return c;
            }
        }
        return null;
    }

    public static void addContact(Scanner sc, ArrayList<Contact> contacts) {
        System.out.print("請輸入聯絡人代碼：");
        String code = sc.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("新增失敗：代碼不可為空白！");
            return;
        }

        if (findByCode(contacts, code) != null) {
            System.out.println("新增失敗：此代碼已存在！");
            return;
        }

        System.out.print("請輸入聯絡人姓名：");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("新增失敗：姓名不可為空白！");
            return;
        }

        System.out.print("請輸入聯絡人電話：");
        String phone = sc.nextLine().trim();

        System.out.print("請輸入聯絡人信箱：");
        String email = sc.nextLine().trim();

        contacts.add(new Contact(code, name, phone, email));
        System.out.println("新增成功！");
    }

    public static void searchContact(Scanner sc, ArrayList<Contact> contacts) {
        System.out.print("請輸入要搜尋的聯絡人代碼：");
        String code = sc.nextLine();
        Contact found = findByCode(contacts, code);

        if (found != null) {
            System.out.println("搜尋結果：" + found);
        } else {
            System.out.println("找不到該代碼的聯絡人。");
        }
    }

    public static void updatePhone(Scanner sc, ArrayList<Contact> contacts) {
        System.out.print("請輸入要修改電話的聯絡人代碼：");
        String code = sc.nextLine();
        Contact found = findByCode(contacts, code);

        if (found == null) {
            System.out.println("修改失敗：找不到該代碼的聯絡人。");
            return;
        }

        System.out.print("請輸入新電話：");
        String newPhone = sc.nextLine().trim();
        found.setPhone(newPhone);
        System.out.println("修改成功！最新狀態：" + found);
    }

    public static void deleteContact(Scanner sc, ArrayList<Contact> contacts) {
        System.out.print("請輸入要刪除的聯絡人代碼：");
        String code = sc.nextLine();
        Contact found = findByCode(contacts, code);

        if (found != null) {
            contacts.remove(found);
            System.out.println("刪除成功：已移除代碼 " + code + " 的聯絡人。");
        } else {
            System.out.println("刪除失敗：找不到該代碼的聯絡人。");
        }
    }

    public static void listAllContacts(ArrayList<Contact> contacts) {
        System.out.println("\n=== 聯絡人完整清單 ===");
        if (contacts.isEmpty()) {
            System.out.println("目前沒有任何聯絡人資料。");
            return;
        }
        
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i));
        }
    }
}
