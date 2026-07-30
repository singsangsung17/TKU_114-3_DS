package final_exam;

import java.util.ArrayList;

class Q12_Product {
    private String id;
    private String name;
    private int price;
    private int stock;

    public Q12_Product(
        String id,
        String name,
        int price,
        int stock
    ) {
        this.id = (id == null) ? "" : id.trim();
        this.name = (name == null) ? "" : name.trim();
        this.price = (price < 0) ? 0 : price;
        this.stock = (stock < 0) ? 0 : stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return id + " " + name +
            " price=" + price + " stock=" + stock;
    }
}

public class Q12_InventoryCatalog {
    private ArrayList<Q12_Product> products = new ArrayList<>();

    public boolean addProduct(Q12_Product product) {
        if (product == null) {
            return false;
        }

        String id = product.getId();
        if (id == null || id.isEmpty()) {
            return false;
        }

        for (int index = 0; index < products.size(); index++) {
            if (products.get(index).getId()
                    .equalsIgnoreCase(id)) {
                return false;
            }
        }

        products.add(product);
        return true;
    }

    public Q12_Product[] createSortedCopyById() {
        Q12_Product[] result =
            new Q12_Product[products.size()];

        for (int index = 0; index < products.size(); index++) {
            result[index] = products.get(index);
        }

        if (result.length <= 1) {
            return result;
        }

        Q12_Product[] temp = new Q12_Product[result.length];
        mergeSort(result, temp, 0, result.length - 1);
        return result;
    }

    private void mergeSort(
        Q12_Product[] data,
        Q12_Product[] temp,
        int left,
        int right
    ) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(data, temp, left, mid);
        mergeSort(data, temp, mid + 1, right);
        merge(data, temp, left, mid, right);
    }

    private void merge(
        Q12_Product[] data,
        Q12_Product[] temp,
        int left,
        int mid,
        int right
    ) {
        for (int index = left; index <= right; index++) {
            temp[index] = data[index];
        }

        int leftIndex = left;
        int rightIndex = mid + 1;
        int target = left;

        while (leftIndex <= mid && rightIndex <= right) {
            if (compareId(temp[leftIndex].getId(),
                    temp[rightIndex].getId()) <= 0) {
                data[target] = temp[leftIndex];
                leftIndex++;
            } else {
                data[target] = temp[rightIndex];
                rightIndex++;
            }
            target++;
        }

        while (leftIndex <= mid) {
            data[target] = temp[leftIndex];
            leftIndex++;
            target++;
        }

        while (rightIndex <= right) {
            data[target] = temp[rightIndex];
            rightIndex++;
            target++;
        }
    }

    private int compareId(String left, String right) {
        String a = (left == null) ? "" : left.toLowerCase();
        String b = (right == null) ? "" : right.toLowerCase();

        int limit = Math.min(a.length(), b.length());

        for (int index = 0; index < limit; index++) {
            char first = a.charAt(index);
            char second = b.charAt(index);
            if (first != second) {
                return first - second;
            }
        }
        return a.length() - b.length();
    }

    public Q12_Product binarySearchById(
        Q12_Product[] sortedProducts,
        String id
    ) {
        if (sortedProducts == null || id == null) {
            return null;
        }

        String key = id.trim();
        int low = 0;
        int high = sortedProducts.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Q12_Product product = sortedProducts[mid];

            if (product == null) {
                return null;
            }

            int compared = compareId(product.getId(), key);

            if (compared == 0) {
                return product;
            } else if (compared < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public ArrayList<Q12_Product> findByNameKeyword(
        String keyword
    ) {
        ArrayList<Q12_Product> result = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return result;
        }

        String key = keyword.trim().toLowerCase();

        for (int index = 0; index < products.size(); index++) {
            Q12_Product product = products.get(index);
            if (containsIgnoreCase(product.getName(), key)) {
                result.add(product);
            }
        }
        return result;
    }

    private boolean containsIgnoreCase(String source, String key) {
        if (source == null || key == null) {
            return false;
        }

        String lower = source.toLowerCase();
        int limit = lower.length() - key.length();

        for (int start = 0; start <= limit; start++) {
            int offset = 0;
            while (offset < key.length() &&
                   lower.charAt(start + offset) ==
                   key.charAt(offset)) {
                offset++;
            }
            if (offset == key.length()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Q12_Product> findLowStock(
        int maximumStock
    ) {
        ArrayList<Q12_Product> result = new ArrayList<>();

        for (int index = 0; index < products.size(); index++) {
            Q12_Product product = products.get(index);
            if (product.getStock() <= maximumStock) {
                result.add(product);
            }
        }
        return result;
    }

    public int totalInventoryValue() {
        int total = 0;

        for (int index = 0; index < products.size(); index++) {
            Q12_Product product = products.get(index);
            total += product.getPrice() * product.getStock();
        }
        return total;
    }
}

class Q12_InventoryDemo {
    public static void main(String[] args) {
        Q12_InventoryCatalog catalog =
            new Q12_InventoryCatalog();

        catalog.addProduct(
            new Q12_Product("P205", "Wireless Mouse", 650, 4)
        );
        catalog.addProduct(
            new Q12_Product("P101", "Keyboard", 1200, 8)
        );
        catalog.addProduct(
            new Q12_Product("P330", "Gaming Mouse", 1800, 2)
        );
        catalog.addProduct(
            new Q12_Product("P150", "Monitor", 5200, 5)
        );

        Q12_Product[] sorted = catalog.createSortedCopyById();
        System.out.println("依編號排序：");
        for (Q12_Product product : sorted) {
            System.out.println(product);
        }

        System.out.println("查詢 P150：" +
            catalog.binarySearchById(sorted, "p150"));
        System.out.println("名稱包含 mouse：" +
            catalog.findByNameKeyword("mouse"));
        System.out.println("低庫存：" +
            catalog.findLowStock(4));
        System.out.println("庫存總值：" +
            catalog.totalInventoryValue());
    }
}