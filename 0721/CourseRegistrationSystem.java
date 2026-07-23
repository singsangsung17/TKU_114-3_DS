import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();
        int option = -1;

        while (option != 0) {
            System.out.println("\n=== 選課管理系統 ===");
            System.out.println("1. 新增課程");
            System.out.println("2. 搜尋課程");
            System.out.println("3. 進行選課");
            System.out.println("4. 進行退選");
            System.out.println("5. 刪除課程");
            System.out.println("6. 列出課程與統計摘要");
            System.out.println("0. 結束系統");
            System.out.print("請選擇功能：");

            try {
                option = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("請輸入正確的數字選項！");
                continue;
            }

            switch (option) {
                case 1:
                    addCourse(sc, courses);
                    break;
                case 2:
                    searchCourse(sc, courses);
                    break;
                case 3:
                    enrollCourse(sc, courses);
                    break;
                case 4:
                    dropCourse(sc, courses);
                    break;
                case 5:
                    deleteCourse(sc, courses);
                    break;
                case 6:
                    displaySummary(courses);
                    break;
                case 0:
                    System.out.println("系統已結束，感謝您的使用。");
                    break;
                default:
                    System.out.println("無效選項，請重新輸入！");
                    break;
            }
        }
        sc.close();
    }

    public static Course findByCode(ArrayList<Course> courses, String code) {
        String target = code.trim();
        for (Course c : courses) {
            if (c.getCode().equalsIgnoreCase(target)) {
                return c;
            }
        }
        return null;
    }

    public static void addCourse(Scanner sc, ArrayList<Course> courses) {
        System.out.print("請輸入課程代碼：");
        String code = sc.nextLine().trim();

        if (code.isEmpty()) {
            System.out.println("新增失敗：代碼不可為空白！");
            return;
        }

        if (findByCode(courses, code) != null) {
            System.out.println("新增失敗：此課程代碼已存在！");
            return;
        }

        System.out.print("請輸入課程名稱：");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("新增失敗：名稱不可為空白！");
            return;
        }

        try {
            System.out.print("請輸入課程容量：");
            int capacity = Integer.parseInt(sc.nextLine().trim());
            
            if (capacity <= 0) {
                System.out.println("新增失敗：容量必須大於 0！");
                return;
            }

            courses.add(new Course(code, name, capacity));
            System.out.println("課程新增成功！");
        } catch (NumberFormatException e) {
            System.out.println("格式錯誤：容量必須為整數！");
        }
    }

    public static void searchCourse(Scanner sc, ArrayList<Course> courses) {
        System.out.print("請輸入要搜尋的課程代碼：");
        String code = sc.nextLine();
        Course found = findByCode(courses, code);

        if (found != null) {
            System.out.println("搜尋結果：" + found);
        } else {
            System.out.println("找不到該代碼的課程。");
        }
    }

    public static void enrollCourse(Scanner sc, ArrayList<Course> courses) {
        System.out.print("請輸入要選修的課程代碼：");
        String code = sc.nextLine();
        Course found = findByCode(courses, code);

        if (found == null) {
            System.out.println("選課失敗：找不到該課程。");
            return;
        }

        if (found.enroll()) {
            System.out.println("選課成功！最新狀態：" + found);
        } else {
            System.out.println("選課失敗：該課程已額滿！");
        }
    }

    public static void dropCourse(Scanner sc, ArrayList<Course> courses) {
        System.out.print("請輸入要退選的課程代碼：");
        String code = sc.nextLine();
        Course found = findByCode(courses, code);

        if (found == null) {
            System.out.println("退選失敗：找不到該課程。");
            return;
        }

        if (found.drop()) {
            System.out.println("退選成功！最新狀態：" + found);
        } else {
            System.out.println("退選失敗：該課程目前修課人數為 0。");
        }
    }

    public static void deleteCourse(Scanner sc, ArrayList<Course> courses) {
        System.out.print("請輸入要刪除的課程代碼：");
        String code = sc.nextLine();
        Course found = findByCode(courses, code);

        if (found != null) {
            courses.remove(found);
            System.out.println("刪除成功：已移除課程 " + found.getName());
        } else {
            System.out.println("刪除失敗：找不到該課程。");
        }
    }

    public static void displaySummary(ArrayList<Course> courses) {
        System.out.println("\n=== 課程清單與統計摘要 ===");
        
        if (courses.isEmpty()) {
            System.out.println("目前沒有任何課程資料。");
            return;
        }

        int totalEnrollments = 0;
        ArrayList<Course> fullCourses = new ArrayList<>();

        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            System.out.println((i + 1) + ". " + c);
            
            totalEnrollments += c.getEnrolled();
            if (c.isFull()) {
                fullCourses.add(c);
            }
        }

        System.out.println("-------------------------");
        System.out.println("總課程數：" + courses.size());
        System.out.println("總選課人次：" + totalEnrollments);
        
        System.out.print("額滿課程：");
        if (fullCourses.isEmpty()) {
            System.out.println("無");
        } else {
            System.out.println();
            for (Course fc : fullCourses) {
                System.out.println("  - " + fc.getName() + " (" + fc.getCode() + ")");
            }
        }
    }
}
