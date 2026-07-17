import java.util.Scanner;

public class ProductSearchSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        int option = -1;

        while (option != 0) {
            System.out.println("\n=== 商品名稱搜尋系統 ===");
            System.out.println("1. 顯示全部商品");
            System.out.println("2. 完整名稱搜尋");
            System.out.println("3. 部分名稱搜尋");
            System.out.println("4. 顯示名稱最長的商品");
            System.out.println("5. 顯示商品名稱與搜尋關鍵字第一次出現的位置");
            System.out.println("6. 結束");
            System.out.print("請選擇功能：");
            
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    displayAllProducts(names, prices, stocks);
                    break;
                case 2:
                    exactSearch(sc, names, prices, stocks);
                    break;
                case 3:
                    partialSearch(sc, names, prices, stocks);
                    break;
                case 4:
                    findLongestName(names);
                    break;
                case 5:
                    findKeywordIndex(sc, names);
                    break;
                case 0:
                    System.out.println("系統已結束。");
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入。");
                    break;
            }
        }
        sc.close();
    }

    public static void displayAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n=== 全部商品 ===");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-15s 單價: $%d \t 庫存: %d\n", names[i], prices[i], stocks[i]);
        }
    }

    public static void exactSearch(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("\n請輸入完整商品名稱：");
        String keyword = sc.nextLine().trim();
        boolean found = false;

        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(keyword)) {
                System.out.printf("找到商品：%s (單價: $%d, 庫存: %d)\n", names[i], prices[i], stocks[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("找不到相符的商品。");
        }
    }

    public static void partialSearch(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("\n請輸入部分商品名稱：");
        String keyword = sc.nextLine().trim().toLowerCase();
        boolean found = false;

        for (int i = 0; i < names.length; i++) {
            if (names[i].toLowerCase().contains(keyword)) {
                System.out.printf("符合商品：%s (單價: $%d, 庫存: %d)\n", names[i], prices[i], stocks[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("找不到相符的商品。");
        }
    }

    public static void findLongestName(String[] names) {
        if (names.length == 0) return;

        String longest = names[0];
        for (int i = 1; i < names.length; i++) {
            if (names[i].length() > longest.length()) {
                longest = names[i];
            }
        }
        System.out.println("\n名稱最長的商品是：" + longest);
    }

    public static void findKeywordIndex(Scanner sc, String[] names) {
        System.out.print("\n請輸入搜尋關鍵字：");
        String keyword = sc.nextLine().trim().toLowerCase();
        boolean found = false;

        for (String name : names) {
            int index = name.toLowerCase().indexOf(keyword);
            if (index != -1) {
                System.out.printf("商品 '%s' 中，關鍵字第一次出現的位置索引為：%d\n", name, index);
                found = true;
            }
        }

        if (!found) {
            System.out.println("沒有任何商品名稱包含此關鍵字。");
        }
    }
}

/*
測試案例紀錄：
1. 正常值 (完整搜尋):
輸入: "Mouse"
預期結果: 找到商品：Mouse (單價: $490, 庫存: 20)
實際結果: 通過
2. 邊界值/空白處理 (完整搜尋):
輸入: "   Keyboard   " (前後多個空白)
預期結果: 找到商品：Keyboard (單價: $890, 庫存: 12)
實際結果: 通過
3. 大小寫處理 (完整搜尋):
輸入: "mOnItOr"
預期結果: 找到商品：Monitor (單價: $5200, 庫存: 5)
實際結果: 通過
4. 正常值 (部分搜尋):
輸入: "o"
預期結果: 顯示 Keyboard, Mouse, Monitor
實際結果: 通過
5. 錯誤值/不存在 (部分搜尋):
輸入: "xyz"
預期結果: 顯示「找不到相符的商品。」
實際結果: 通過
6. 空值 (搜尋位置):
輸入: "" (直接按 Enter)
預期結果: 所有商品的索引均為 0 (因為空字串在任何字串的開頭都算相符)
實際結果: 通過
*/

