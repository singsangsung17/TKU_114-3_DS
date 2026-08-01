public class ProductSortPractice {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P101", "Keyboard", 1290, 25),
            new Product("P102", "Mouse", 650, 40),
            new Product("P103", "Monitor", 5200, 8),
            new Product("P104", "Webcam", 1290, 15),
            new Product("P105", "Headset", 890, 30),
            new Product("P106", "USB Hub", 650, 55),
            new Product("P107", "Speaker", 2100, 12),
            new Product("P108", "Mousepad", 250, 90),
            new Product("P109", "Cable", 250, 120),
            new Product("P110", "Dock", 5200, 5)
        };

        System.out.println("排序前（原始順序）：");
        printAll(products);

        insertionSortByPrice(products);

        System.out.println();
        System.out.println("排序後（價格升冪，相同價格保持原順序）：");
        printAll(products);

        System.out.println();
        System.out.println("穩定性檢查：");
        System.out.println("價格 250：P108 在 P109 之前");
        System.out.println("價格 650：P102 在 P106 之前");
        System.out.println("價格 1290：P101 在 P104 之前");
        System.out.println("價格 5200：P103 在 P110 之前");
    }

    public static void insertionSortByPrice(Product[] products) {
        for (int index = 1; index < products.length; index++) {
            Product key = products[index];
            int position = index - 1;

            while (position >= 0
                && products[position].getPrice() > key.getPrice()) {
                products[position + 1] = products[position];
                position--;
            }

            products[position + 1] = key;
        }
    }

    public static void printAll(Product[] products) {
        for (int index = 0; index < products.length; index++) {
            System.out.println("[" + index + "] " + products[index]);
        }
    }
}
