import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorUndoSystem {
    private String text = "";
    private Deque<String> history = new ArrayDeque<>();

    public void append(String str) {
        history.push(text);
        text += str;
    }

    public void delete(int count) {
        history.push(text);
        if (count >= text.length()) {
            text = "";
        } else {
            text = text.substring(0, text.length() - count);
        }
    }

    public void undo() {
        if (history.isEmpty()) {
            System.out.println("無法復原：目前沒有歷史紀錄。");
        } else {
            text = history.pop();
        }
    }

    public void showContent() {
        System.out.println("目前內容：" + text);
    }

    public static void main(String[] args) {
        TextEditorUndoSystem editor = new TextEditorUndoSystem();

        editor.append("Java");
        editor.append(" Stack");
        editor.append(" Demo");
        editor.showContent();

        editor.delete(5);
        editor.showContent();

        editor.undo();
        editor.showContent();

        editor.undo();
        editor.showContent();

        editor.undo();
        editor.showContent();

        editor.undo();
    }
}
