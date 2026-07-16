import java.util.Scanner;

public class SalesMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] sales = new int[3][4];

        inputSales(sc, sales);
        printMatrix(sales);
        calculateProductTotals(sales);
        calculateDayTotals(sales);
        findBestSellingProduct(sales);

        sc.close();
    }

    public static void inputSales(Scanner sc, int[][] sales) {
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                do {
                    System.out.print("請輸入商品 " + (i + 1) + " 在第 " + (j + 1) + " 天的銷售量：");
                    sales[i][j] = sc.nextInt();
                    if (sales[i][j] < 0) {
                        System.out.println("銷售量不能小於 0，請重新輸入！");
                    }
                } while (sales[i][j] < 0);
            }
        }
    }

    public static void printMatrix(int[][] sales) {
        System.out.println("\n=== 銷售量報表 ===");
        System.out.print("       ");
        for (int j = 0; j < sales[0].length; j++) {
            System.out.printf("Day %d\t", (j + 1));
        }
        System.out.println();

        for (int i = 0; i < sales.length; i++) {
            System.out.printf("商品 %d:\t", (i + 1));
            for (int j = 0; j < sales[i].length; j++) {
                System.out.printf("%d\t", sales[i][j]);
            }
            System.out.println();
        }
    }

    public static void calculateProductTotals(int[][] sales) {
        System.out.println("\n=== 每項商品銷售總量 ===");
        for (int i = 0; i < sales.length; i++) {
            int total = 0;
            for (int j = 0; j < sales[i].length; j++) {
                total += sales[i][j];
            }
            System.out.println("商品 " + (i + 1) + " 總銷售量：" + total);
        }
    }

    public static void calculateDayTotals(int[][] sales) {
        System.out.println("\n=== 每天全部商品銷售總量 ===");
        for (int j = 0; j < sales[0].length; j++) {
            int total = 0;
            for (int i = 0; i < sales.length; i++) {
                total += sales[i][j];
            }
            System.out.println("第 " + (j + 1) + " 天總銷售量：" + total);
        }
    }

    public static void findBestSellingProduct(int[][] sales) {
        int maxTotal = -1;
        int bestProductIndex = -1;

        for (int i = 0; i < sales.length; i++) {
            int total = 0;
            for (int j = 0; j < sales[i].length; j++) {
                total += sales[i][j];
            }
            
            if (total > maxTotal) {
                maxTotal = total;
                bestProductIndex = i;
            }
        }
        System.out.println("\n總銷售量最高的商品是：商品 " + (bestProductIndex + 1) + " (總共 " + maxTotal + " 件)");
    }
}
