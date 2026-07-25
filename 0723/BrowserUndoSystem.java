import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserUndoSystem {
    private String currentPage = null;
    private Deque<String> history = new ArrayDeque<>();

    public void visit(String url) {
        if (currentPage != null) {
            history.push(currentPage);
        }
        currentPage = url;
        System.out.println("開啟新頁：" + currentPage);
    }

    public void back() {
        if (history.isEmpty()) {
            System.out.println("返回失敗：沒有上一頁可供返回！");
        } else {
            String previousPage = history.pop();
            System.out.println("從 [" + currentPage + "] 返回到 [" + previousPage + "]");
            currentPage = previousPage;
        }
    }

    public void showCurrentPage() {
        if (currentPage == null) {
            System.out.println("目前無開啟任何頁面。");
        } else {
            System.out.println("目前頁面：" + currentPage);
        }
    }

    public static void main(String[] args) {
        BrowserUndoSystem browser = new BrowserUndoSystem();

        System.out.println("=== 瀏覽操作復原測試開始 ===");
        
        browser.visit("Home");
        browser.visit("Courses");
        browser.visit("Java");
        
        browser.showCurrentPage();
        browser.back();
        browser.showCurrentPage();
        browser.back();
        browser.back();
        
        System.out.println("=== 測試結束 ===");
    }
}
