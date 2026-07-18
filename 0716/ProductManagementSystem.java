import java.util.Scanner;

public class ProductManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Product[] products = new Product[10];
        
        products[0] = new Product("Keyboard", 890, 12);
        products[1] = new Product("Mouse", 490, 20);
        products[2] = new Product("Monitor", 5200, 5);
        products[3] = new Product("USB Cable", 250, 30);
        products[4] = new Product("Headset", 1290, 8);

        int option = -1;
        int operationCount = 0;

        while (option != 0) {
            System.out.println("\n=== 物件導向商品管理系統 ===");
            System.out.println("1. 顯示全部商品");
            System.out.println("2. 依完整名稱搜尋");
            System.out.println("3. 新增商品");
            System.out.println("4. 出售商品");
            System.out.println("5. 補充庫存");
            System.out.println("6. 修改商品價格");
            System.out.println("7. 顯示低庫存商品");
            System.out.println("8. 顯示全部庫存總價值");
            System.out.println("0. 結束並顯示操作摘要");
            System.out.print("請選擇功能：");

            try {
                option = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("請輸入正確的數字選項！");
                continue;
            }

            switch (option) {
                case 1:
                    displayAllProducts(products);
                    break;
                case 2:
                    searchProductMenu(sc, products);
                    break;
                case 3:
                    addProduct(sc, products);
                    break;
                case 4:
                    sellProduct(sc, products);
                    break;
                case 5:
                    restockProduct(sc, products);
                    break;
                case 6:
                    changePrice(sc, products);
                    break;
                case 7:
                    displayLowStock(products);
                    break;
                case 8:
                    displayTotalValue(products);
                    break;
                case 0:
                    System.out.println("系統已結束。本次執行共操作了 " + operationCount + " 次功能。");
                    break;
                default:
                    System.out.println("無效選項，請重新輸入！");
                    break;
            }
            
            if (option >= 1 && option <= 8) {
                operationCount++;
            }
        }
        sc.close();
    }

    public static Product findProduct(Product[] products, String name) {
        String keyword = name.trim();
        for (Product product : products) {
            if (product != null && product.getName().equalsIgnoreCase(keyword)) {
                return product;
            }
        }
        return null;
    }

    public static void displayAllProducts(Product[] products) {
        System.out.println("\n=== 全部商品清單 ===");
        boolean hasProduct = false;
        for (Product product : products) {
            if (product != null) {
                System.out.println(product);
                hasProduct = true;
            }
        }
        if (!hasProduct) {
            System.out.println("目前沒有任何商品。");
        }
    }

    public static void searchProductMenu(Scanner sc, Product[] products) {
        System.out.print("\n請輸入要搜尋的商品名稱：");
        String name = sc.nextLine();
        Product found = findProduct(products, name);
        if (found != null) {
            System.out.println("搜尋結果：" + found);
        } else {
            System.out.println("找不到該商品。");
        }
    }

    public static void addProduct(Scanner sc, Product[] products) {
        System.out.println("\n=== 新增商品 ===");
        
        int emptyIndex = -1;
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                emptyIndex = i;
                break;
            }
        }
        if (emptyIndex == -1) {
            System.out.println("新增失敗：商品陣列已滿(最多 10 項)。");
            return;
        }

        System.out.print("請輸入新商品名稱：");
        String name = sc.nextLine();
        if (findProduct(products, name) != null) {
            System.out.println("新增失敗：商品名稱已存在！");
            return;
        }

        try {
            System.out.print("請輸入商品價格：");
            int price = Integer.parseInt(sc.nextLine().trim());
            System.out.print("請輸入商品庫存：");
            int stock = Integer.parseInt(sc.nextLine().trim());
            
            products[emptyIndex] = new Product(name, price, stock);
            System.out.println("新增成功：" + products[emptyIndex]);
        } catch (NumberFormatException e) {
            System.out.println("格式錯誤：價格與庫存必須為整數！");
        }
    }

    public static void sellProduct(Scanner sc, Product[] products) {
        System.out.print("\n請輸入要出售的商品名稱：");
        String name = sc.nextLine();
        Product found = findProduct(products, name);
        
        if (found == null) {
            System.out.println("找不到該商品。");
            return;
        }
        
        try {
            System.out.print("請輸入出售數量：");
            int quantity = Integer.parseInt(sc.nextLine().trim());
            if (found.sell(quantity)) {
                System.out.println("出售成功。最新狀態：" + found);
            } else {
                System.out.println("出售失敗：數量錯誤或庫存不足！");
            }
        } catch (NumberFormatException e) {
            System.out.println("格式錯誤：數量必須為整數！");
        }
    }

    public static void restockProduct(Scanner sc, Product[] products) {
        System.out.print("\n請輸入要補充的商品名稱：");
        String name = sc.nextLine();
        Product found = findProduct(products, name);
        
        if (found == null) {
            System.out.println("找不到該商品。");
            return;
        }
        
        try {
            System.out.print("請輸入補充數量：");
            int quantity = Integer.parseInt(sc.nextLine().trim());
            if (found.restock(quantity)) {
                System.out.println("補充成功。最新狀態：" + found);
            } else {
                System.out.println("補充失敗：數量錯誤！");
            }
        } catch (NumberFormatException e) {
            System.out.println("格式錯誤：數量必須為整數！");
        }
    }

    public static void changePrice(Scanner sc, Product[] products) {
        System.out.print("\n請輸入要修改的商品名稱：");
        String name = sc.nextLine();
        Product found = findProduct(products, name);
        
        if (found == null) {
            System.out.println("找不到該商品。");
            return;
        }
        
        try {
            System.out.print("請輸入新價格：");
            int price = Integer.parseInt(sc.nextLine().trim());
            if (found.setPrice(price)) {
                System.out.println("價格修改成功。最新狀態：" + found);
            } else {
                System.out.println("修改失敗：價格必須大於 0！");
            }
        } catch (NumberFormatException e) {
            System.out.println("格式錯誤：價格必須為整數！");
        }
    }

    public static void displayLowStock(Product[] products) {
        System.out.println("\n=== 低庫存商品 (庫存 < 10) ===");
        boolean found = false;
        for (Product product : products) {
            if (product != null && product.isLowStock()) {
                System.out.println(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前沒有低庫存商品。");
        }
    }

    public static void displayTotalValue(Product[] products) {
        long total = 0;
        for (Product product : products) {
            if (product != null) {
                total += product.getInventoryValue();
            }
        }
        System.out.println("\n全部庫存總價值：$" + total);
    }
}

/*
測試案例紀錄：
1.正常值(顯示商品)
輸入:1
預期:顯示初始的5個商品資料
實際:通過
2.正常值(搜尋存在商品)
輸入:2後輸入"Mouse"
預期:顯示Mouse的詳細資料
實際:通過
3.邊界值(搜尋忽略空白與大小寫)
輸入:2後輸入"kEyBoArD"
預期:顯示Keyboard的詳細資料
實際:通過
4.正常值(新增不重複商品)
輸入:3後依序輸入"Speaker"、"1500"、"10"
預期:顯示新增成功並印出Speaker資料
實際:通過
5.錯誤值(新增重複名稱)
輸入:3後輸入"Mouse"
預期:顯示「新增失敗：商品名稱已存在！」
實際:通過
6.正常值(成功出售)
輸入:4後輸入"Monitor"，數量輸入"2"
預期:出售成功，Monitor庫存變為3
實際:通過
7.錯誤值(出售數量大於庫存)
輸入:4後輸入"Monitor"，數量輸入"10"
預期:顯示「出售失敗：數量錯誤或庫存不足！」
實際:通過
8.正常值(成功補貨)
輸入:5後輸入"Keyboard"，數量輸入"5"
預期:補貨成功，Keyboard庫存變為17
實際:通過
9.錯誤值(補貨輸入負數)
輸入:5後輸入"Keyboard"，數量輸入"-5"
預期:顯示「補充失敗：數量錯誤！」
實際:通過
10.正常值(修改價格)
輸入:6後輸入"Mouse"，價格輸入"450"
預期:修改成功，Mouse價格變為450
實際:通過
*/

