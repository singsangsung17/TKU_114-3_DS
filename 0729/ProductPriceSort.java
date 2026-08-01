public class ProductPriceSort {
    public static void main(String[] args) {
        DemoProduct[] products = {
            new DemoProduct("P103", "Keyboard", 1290),
            new DemoProduct("P205", "Mouse", 650),
            new DemoProduct("P118", "Monitor", 5200),
            new DemoProduct("P310", "Webcam", 1290)
        };

        insertionSortByPrice(products);

        for (DemoProduct product : products) {
            System.out.println(product);
        }
    }

    public static void insertionSortByPrice(DemoProduct[] products) {
        for (int index = 1; index < products.length; index++) {
            DemoProduct key = products[index];
            int position = index - 1;

            while (position >= 0 &&
                   products[position].getPrice() > key.getPrice()) {
                products[position + 1] = products[position];
                position--;
            }
            products[position + 1] = key;
        }
    }
}

class DemoProduct {
    private String id;
    private String name;
    private int price;

    public DemoProduct(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return id + " " + name + " $" + price;
    }
}