import java.util.Scanner;

public class ProductDataManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] records = {
            "Keyboard,890,12",
            "Mouse,490,20",
            "Monitor,5200,5",
            "USB Cable,250,30",
            "Headset,1290,8"
        };

        
        String[] names = new String[records.length];
        int[] prices = new int[records.length];
        int[] stocks = new int[records.length];

        parseRecords(records, names, prices, stocks);

        int option = -1;
        while (option != 0) {
            System.out.println("\n=== 商品文字資料管理器 ===");
            System.out.println("1. 顯示商品表格");
            System.out.println("2. 搜尋商品 (支援完整與部分名稱)");
            System.out.println("3. 顯示低庫存商品 (小於 10)");
            System.out.println("4. 顯示庫存總價值");
            System.out.println("5. 輸入並驗證新資料格式");
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
                    displayTable(names, prices, stocks);
                    break;
                case 2:
                    searchProduct(sc, names, prices, stocks);
                    break;
                case 3:
                    displayLowStock(names, stocks);
                    break;
                case 4:
                    displayTotalValue(prices, stocks);
                    break;
                case 5:
                    validateNewRecord(sc);
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

    public static void parseRecords(String[] records, String[] names, int[] prices, int[] stocks) {
        for (int i = 0; i < records.length; i++) {
            String[] parts = records[i].split(",");
            if (parts.length == 3) {
                names[i] = parts[0].trim();
                prices[i] = Integer.parseInt(parts[1].trim());
                stocks[i] = Integer.parseInt(parts[2].trim());
            }
        }
    }

    public static void displayTable(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n=== 商品資料表 ===");
        System.out.printf("%-15s %-10s %-10s\n", "商品名稱", "價格", "庫存");
        System.out.println("-----------------------------------");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-15s $%-9d %-10d\n", names[i], prices[i], stocks[i]);
        }
    }

    public static void searchProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("\n請輸入搜尋關鍵字：");
        String keyword = sc.nextLine().trim().toLowerCase();
        boolean found = false;

        System.out.println("--- 搜尋結果 ---");
        for (int i = 0; i < names.length; i++) {
            if (names[i].toLowerCase().contains(keyword)) {
                System.out.printf("%s (價格: $%d, 庫存: %d)\n", names[i], prices[i], stocks[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("找不到符合的商品。");
        }
    }

    public static void displayLowStock(String[] names, int[] stocks) {
        System.out.println("\n=== 低庫存商品 (庫存 < 10) ===");
        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.println(names[i] + " (庫存: " + stocks[i] + ")");
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前無低庫存商品。");
        }
    }

    public static void displayTotalValue(int[] prices, int[] stocks) {
        System.out.println("\n=== 庫存總價值 ===");
        int total = 0;
        for (int i = 0; i < prices.length; i++) {
            total += prices[i] * stocks[i];
        }
        System.out.println("全部商品庫存總價值：$" + total);
    }

    public static void validateNewRecord(Scanner sc) {
        System.out.print("\n請輸入新資料 (格式: 名稱,價格,庫存)：");
        String input = sc.nextLine();
        
        String[] parts = input.split(",", -1);

        if (parts.length != 3) {
            System.out.println("格式錯誤：資料必須包含 3 個欄位 (以逗號分隔)。");
            return;
        }

        String name = parts[0].trim();
        if (name.isEmpty()) {
            System.out.println("格式錯誤：商品名稱不可為空白。");
            return;
        }

        try {
            int price = Integer.parseInt(parts[1].trim());
            int stock = Integer.parseInt(parts[2].trim());
            
            if (price < 0 || stock < 0) {
                System.out.println("格式錯誤：價格與庫存不可為負數。");
            } else {
                System.out.println("驗證成功！資料格式完全正確。");
                System.out.printf("解析結果 -> 名稱: %s, 價格: $%d, 庫存: %d\n", name, price, stock);
            }
        } catch (NumberFormatException e) {
            System.out.println("數字轉換錯誤：價格與庫存必須是整數！");
        }
    }
}

/*
測試案例紀錄：
1.正常值(選單選項)
輸入:1
預期結果:顯示解析好的5筆商品表格
實際結果:通過
2.正常值(完整名稱搜尋)
輸入:搜尋關鍵字"Mouse"
預期結果:顯示Mouse(價格:$490,庫存:20)
實際結果:通過
3.邊界值(忽略大小寫與部分搜尋)
輸入:搜尋關鍵字"mONi"(含有前後空白與大小寫交錯)
預期結果:顯示Monitor(價格:$5200,庫存:5)
實際結果:通過
4.不存在(搜尋找不到的商品)
輸入:搜尋關鍵字"Speaker"
預期結果:顯示「找不到符合的商品。」
實際結果:通過
5.正常值(新增資料驗證成功)
輸入:"Speaker,1500,10"
預期結果:顯示「驗證成功！資料格式完全正確。」
實際結果:通過
6.格式錯誤(新增資料欄位不足)
輸入:"Speaker,1500"
預期結果:顯示「格式錯誤：資料必須包含3個欄位(以逗號分隔)。」
實際結果:通過
7.錯誤值/例外處理(新增資料文字轉數字失敗)
輸入:"Speaker,abc,10"
預期結果:觸發catch，顯示「數字轉換錯誤：價格與庫存必須是整數！」且程式不會崩潰中止
實際結果:通過
8.空值(新增資料名稱空白)
輸入:",1500,10"
預期結果:顯示「格式錯誤：商品名稱不可為空白。」
實際結果:通過
*/