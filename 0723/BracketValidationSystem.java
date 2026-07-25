import java.util.ArrayDeque;
import java.util.Deque;

public class BracketValidationSystem {

    public static void main(String[] args) {
        System.out.println(isBalanced("public void test() { int[] arr = new int[5]; }"));
        System.out.println(isBalanced("([)]"));
        System.out.println(isBalanced("((System.out.println(1);)"));
        System.out.println(isBalanced("System.out.println(1);)"));
    }

    public static boolean isBalanced(String text) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty() || !matches(stack.pop(), ch)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static boolean matches(char left, char right) {
        return (left == '(' && right == ')')
            || (left == '[' && right == ']')
            || (left == '{' && right == '}');
    }
}