import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<CartItem> cart = new ArrayList<>();
        int option = -1;

        while (option != 0) {
            System.out.println("\n=== 購物車系統 ===");
            System.out.println("1. 將商品加入購物車");
            System.out.println("2. 修改商品數量");
            System.out.println("3. 移除購物車商品");
            System.out.println("4. 檢視購物車與計算總額");
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
                    addItem(sc, cart);
                    break;
                case 2:
                    updateQuantity(sc, cart);
                    break;
                case 3:
                    removeItem(sc, cart);
                    break;
                case 4:
                    viewCartAndTotal(cart);
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

    public static CartItem findByCode(ArrayList<CartItem> cart, String code) {
        String target = code.trim();
        for (CartItem item : cart) {
            if (item.getCode().equalsIgnoreCase(target)) {
                return item;
            }
        }
        return null;
    }

    public static void addItem(Scanner sc, ArrayList<CartItem> cart) {
        System.out.print("請輸入商品代碼：");
        String code = sc.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("新增失敗：代碼不可為空白！");
            return;
        }

        CartItem existingItem = findByCode(cart, code);

        if (existingItem != null) {
            System.out.println("購物車已有此商品 (" + existingItem.getName() + ")，目前數量：" + existingItem.getQuantity());
            try {
                System.out.print("請輸入要【增加】的數量：");
                int addAmount = Integer.parseInt(sc.nextLine().trim());
                if (addAmount > 0) {
                    existingItem.addQuantity(addAmount);
                    System.out.println("增加成功！最新狀態：" + existingItem);
                } else {
                    System.out.println("新增失敗：增加的數量必須大於 0！");
                }
            } catch (NumberFormatException e) {
                System.out.println("格式錯誤：數量必須為整數！");
            }
            return;
        }

        System.out.print("請輸入商品名稱：");
        String name = sc.nextLine().trim();

        try {
            System.out.print("請輸入商品單價：");
            int price = Integer.parseInt(sc.nextLine().trim());
            System.out.print("請輸入購買數量：");
            int quantity = Integer.parseInt(sc.nextLine().trim());

            if (price < 0 || quantity <= 0) {
                System.out.println("新增失敗：單價不可為負，且數量必須大於 0！");
                return;
            }

            cart.add(new CartItem(code, name, price, quantity));
            System.out.println("加入購物車成功！");
        } catch (NumberFormatException e) {
            System.out.println("格式錯誤：單價與數量必須為整數！");
        }
    }

    public static void updateQuantity(Scanner sc, ArrayList<CartItem> cart) {
        System.out.print("請輸入要修改數量的商品代碼：");
        String code = sc.nextLine();
        CartItem found = findByCode(cart, code);

        if (found == null) {
            System.out.println("修改失敗：購物車內找不到該商品。");
            return;
        }

        try {
            System.out.print("請輸入【新的數量】：");
            int newQuantity = Integer.parseInt(sc.nextLine().trim());
            
            if (found.setQuantity(newQuantity)) {
                System.out.println("數量修改成功！最新狀態：" + found);
            } else {
                System.out.println("修改失敗：數量小於或等於 0，不接受更新！若需刪除請使用移除功能。");
            }
        } catch (NumberFormatException e) {
            System.out.println("格式錯誤：數量必須為整數！");
        }
    }

    public static void removeItem(Scanner sc, ArrayList<CartItem> cart) {
        System.out.print("請輸入要移除的商品代碼：");
        String code = sc.nextLine();
        CartItem found = findByCode(cart, code);

        if (found != null) {
            cart.remove(found);
            System.out.println("移除成功：已將 " + found.getName() + " 移出購物車。");
        } else {
            System.out.println("移除失敗：購物車內找不到該商品。");
        }
    }

    public static void viewCartAndTotal(ArrayList<CartItem> cart) {
        System.out.println("\n=== 購物車內容 ===");
        if (cart.isEmpty()) {
            System.out.println("您的購物車目前是空的。");
            return;
        }

        long totalAmount = 0;
        for (int i = 0; i < cart.size(); i++) {
            CartItem item = cart.get(i);
            System.out.println((i + 1) + ". " + item);
            totalAmount += item.getSubtotal();
        }
        
        System.out.println("-------------------------");
        System.out.println("購物車總額：$" + totalAmount);
    }
}
