import java.util.Scanner;

public class ProductArraySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};
        
        int option = -1;
        int totalSpent = 0;

        while (option != 0) {
            printMenu();
            System.out.print("請選擇功能：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    displayAllProducts(names, prices, stocks);
                    break;
                case 2:
                    queryProduct(sc, names, prices, stocks);
                    break;
                case 3:
                    totalSpent += buyProduct(sc, names, prices, stocks);
                    break;
                case 4:
                    restockProduct(sc, names, stocks);
                    break;
                case 5:
                    displayLowStock(names, stocks);
                    break;
                case 6:
                    displayTotalValue(prices, stocks);
                    break;
                case 0:
                    printSummary(totalSpent);
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入。");
                    break;
            }
        }
        sc.close();
    }

    public static void printMenu() {
        System.out.println("\n=== 商品陣列管理系統 ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品編號查詢");
        System.out.println("3. 購買商品並扣除庫存");
        System.out.println("4. 補充商品庫存");
        System.out.println("5. 顯示低庫存商品");
        System.out.println("6. 顯示全部庫存總價值");
        System.out.println("0. 結束並顯示操作摘要");
    }

    public static int readValidId(Scanner sc, int maxId) {
        int id;
        do {
            System.out.print("請輸入商品編號 (1~" + maxId + ")：");
            id = sc.nextInt();
            if (id < 1 || id > maxId) {
                System.out.println("編號錯誤，請重新輸入！");
            }
        } while (id < 1 || id > maxId);
        return id;
    }

    public static void displayAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n=== 全部商品列表 ===");
        System.out.printf("%-5s %-15s %-8s %-5s\n", "編號", "商品名稱", "單價", "庫存");
        System.out.println("----------------------------------------");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-6d %-15s $%-7d %-5d\n", (i + 1), names[i], prices[i], stocks[i]);
        }
    }

    public static void queryProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.println("\n=== 查詢商品 ===");
        int id = readValidId(sc, names.length);
        int index = id - 1;
        System.out.println("商品名稱：" + names[index]);
        System.out.println("單價：$" + prices[index]);
        System.out.println("目前庫存：" + stocks[index]);
    }

    public static int buyProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.println("\n=== 購買商品 ===");
        int id = readValidId(sc, names.length);
        int index = id - 1;
        int qty;
        
        do {
            System.out.print("請輸入購買數量：");
            qty = sc.nextInt();
            if (qty <= 0) {
                System.out.println("購買數量必須大於 0！");
            } else if (qty > stocks[index]) {
                System.out.println("庫存不足！目前庫存僅剩：" + stocks[index]);
            }
        } while (qty <= 0 || qty > stocks[index]);
        
        stocks[index] -= qty;
        int cost = prices[index] * qty;
        System.out.println("成功購買 " + qty + " 個 " + names[index] + "。");
        System.out.println("本次消費金額：$" + cost);
        
        return cost;
    }

    public static void restockProduct(Scanner sc, String[] names, int[] stocks) {
        System.out.println("\n=== 補充庫存 ===");
        int id = readValidId(sc, names.length);
        int index = id - 1;
        int qty;
        
        do {
            System.out.print("請輸入補充數量：");
            qty = sc.nextInt();
            if (qty <= 0) {
                System.out.println("補充數量必須大於 0！");
            }
        } while (qty <= 0);
        
        stocks[index] += qty;
        System.out.println("已成功為 " + names[index] + " 補充 " + qty + " 個庫存。");
        System.out.println("最新庫存量：" + stocks[index]);
    }

    public static void displayLowStock(String[] names, int[] stocks) {
        System.out.println("\n=== 低庫存商品警報 (庫存 < 10) ===");
        boolean hasLowStock = false;
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.println(names[i] + " (目前庫存: " + stocks[i] + ")");
                hasLowStock = true;
            }
        }
        if (!hasLowStock) {
            System.out.println("目前沒有低庫存商品。");
        }
    }

    public static void displayTotalValue(int[] prices, int[] stocks) {
        System.out.println("\n=== 庫存總價值 ===");
        int totalValue = 0;
        for (int i = 0; i < prices.length; i++) {
            totalValue += (prices[i] * stocks[i]);
        }
        System.out.println("全部商品的庫存總價值為：$" + totalValue);
    }

    public static void printSummary(int totalSpent) {
        System.out.println("\n=== 系統關閉 ===");
        System.out.println("操作摘要：您本次執行的總消費金額為 $" + totalSpent);
        System.out.println("感謝您的使用，再見！");
    }
}
