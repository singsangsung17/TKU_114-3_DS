import java.util.ArrayList;
import java.util.Scanner;

public class DynamicScoreManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> scores = new ArrayList<>();

        System.out.println("===動態成績管理系統===");
        System.out.println("請輸入成績(0-100)，輸入-1結束");

        while (true) {
            System.out.print("輸入成績：");
            String input = sc.nextLine().trim();
            int score;

            try {
                score = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("格式錯誤：請輸入整數！");
                continue;
            }

            if (score == -1) {
                break;
            }

            if (score < 0 || score > 100) {
                System.out.println("無效數值：成績必須介於0到100之間！");
                continue;
            }

            scores.add(score);
        }
    
        if (scores.isEmpty()) {
            System.out.println("未輸入任何有效成績，系統結束。");
        } else {
            System.out.println("\n===成績統計結果===");
            System.out.println("輸入筆數：" + scores.size());
            System.out.printf("平均分數：%.2f\n", calculateAverage(scores));
            System.out.println("最高分：" + findMax(scores));
            System.out.println("最低分：" + findMin(scores));
            System.out.println("及格名單：" + filterPassed(scores));
        }

        sc.close();
    }

    public static double calculateAverage(ArrayList<Integer> scores) {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.size();
    }

    public static int findMax(ArrayList<Integer> scores) {
        int max = scores.get(0);
        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }
        return max;
    }

    public static int findMin(ArrayList<Integer> scores) {
        int min = scores.get(0);
        for (int score : scores) {
            if (score < min) {
                min = score;
            }
        }
        return min;
    }

    public static ArrayList<Integer> filterPassed(ArrayList<Integer> scores) {
        ArrayList<Integer> passed = new ArrayList<>();
        for (int score : scores) {
            if (score >= 60) {
                passed.add(score);
            }
        }
        return passed;
    }
}
