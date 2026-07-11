public class SubtotalCalculator {
    public static void main(String[] args) {
        
        int subtotal = calculateSubtotal(50, 3);
        
        System.out.println("本次小計：" + subtotal);
        
        int anotherSubtotal = calculateSubtotal(30, 5);
        System.out.println("另一筆小計：" + anotherSubtotal);
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }
}
