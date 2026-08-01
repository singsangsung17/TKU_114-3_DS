import java.util.Scanner;

public class ProductSortingSystem {
    public static void main(String[] args) {
        StoreProduct[] source = {
            new StoreProduct("S01", "Keyboard", 1290, 25),
            new StoreProduct("S02", "Mouse", 650, 40),
            new StoreProduct("S03", "Monitor", 5200, 8),
            new StoreProduct("S04", "Webcam", 1290, 15),
            new StoreProduct("S05", "Headset", 890, 30),
            new StoreProduct("S06", "USB Hub", 650, 55),
            new StoreProduct("S07", "Speaker", 2100, 12),
            new StoreProduct("S08", "Mousepad", 250, 90),
            new StoreProduct("S09", "Cable", 250, 120),
            new StoreProduct("S10", "Dock", 3800, 5)
        };

        System.out.println("原始資料：");
        printAll(source);

        System.out.println();
        System.out.println("=== 自動示範三種模式 ===");
        runMode(source, 1);
        runMode(source, 2);
        runMode(source, 3);

        System.out.println();
        System.out.println("=== 互動選單 ===");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            if (choice < 1 || choice > 3) {
                System.out.println("選項不存在，請重新輸入");
                continue;
            }
            runMode(source, choice);
        }
        scanner.close();
        System.out.println("系統結束");
    }

    public static void printMenu() {
        System.out.println("1. 價格升冪  2. 價格降冪  3. 庫存降冪  0. 結束");
        System.out.print("請選擇排序模式：");
    }

    public static void runMode(StoreProduct[] source, int mode) {
        StoreProduct[] working = copyOf(source);
        String field;
        String direction;

        if (mode == 1) {
            insertionSortByPriceAscending(working);
            field = "價格";
            direction = "升冪（由低到高）";
        } else if (mode == 2) {
            selectionSortByPriceDescending(working);
            field = "價格";
            direction = "降冪（由高到低）";
        } else {
            selectionSortByStockDescending(working);
            field = "庫存";
            direction = "降冪（由多到少）";
        }

        System.out.println();
        System.out.println("排序欄位：" + field + "，排序方向：" + direction);
        printAll(working);
    }

    public static StoreProduct[] copyOf(StoreProduct[] source) {
        StoreProduct[] result = new StoreProduct[source.length];
        for (int index = 0; index < source.length; index++) {
            result[index] = source[index];
        }
        return result;
    }

    public static void insertionSortByPriceAscending(StoreProduct[] products) {
        for (int index = 1; index < products.length; index++) {
            StoreProduct key = products[index];
            int position = index - 1;

            while (position >= 0
                && products[position].getPrice() > key.getPrice()) {
                products[position + 1] = products[position];
                position--;
            }

            products[position + 1] = key;
        }
    }

    public static void selectionSortByPriceDescending(StoreProduct[] products) {
        for (int start = 0; start < products.length - 1; start++) {
            int maxIndex = start;

            for (int index = start + 1; index < products.length; index++) {
                if (products[index].getPrice() > products[maxIndex].getPrice()) {
                    maxIndex = index;
                }
            }

            if (maxIndex != start) {
                StoreProduct temp = products[start];
                products[start] = products[maxIndex];
                products[maxIndex] = temp;
            }
        }
    }

    public static void selectionSortByStockDescending(StoreProduct[] products) {
        for (int start = 0; start < products.length - 1; start++) {
            int maxIndex = start;

            for (int index = start + 1; index < products.length; index++) {
                if (products[index].getStock() > products[maxIndex].getStock()) {
                    maxIndex = index;
                }
            }

            if (maxIndex != start) {
                StoreProduct temp = products[start];
                products[start] = products[maxIndex];
                products[maxIndex] = temp;
            }
        }
    }

    public static void printAll(StoreProduct[] products) {
        for (int index = 0; index < products.length; index++) {
            System.out.println("[" + index + "] " + products[index]);
        }
    }
}
