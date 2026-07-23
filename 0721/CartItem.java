public class CartItem {
    private String code;
    private String name;
    private int price;
    private int quantity;

    public CartItem(String code, String name, int price, int quantity) {
        this.code = code;
        this.name = name;
        this.price = Math.max(price, 0);
        this.quantity = Math.max(quantity, 1);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean setQuantity(int quantity) {
        if (quantity <= 0) {
            return false; 
        }
        this.quantity = quantity;
        return true;
    }

    public void addQuantity(int amount) {
        if (amount > 0) {
            this.quantity += amount;
        }
    }

    public long getSubtotal() {
        return (long) price * quantity;
    }

    @Override
    public String toString() {
        return "代碼：" + code + " | 名稱：" + name + " | 單價：" + price + " | 數量：" + quantity + " | 小計：" + getSubtotal();
    }
}
