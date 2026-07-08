import java.util.Scanner;

public class ScoreMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String name = sc.next();
        int java = sc.nextInt();
        int english = sc.nextInt();
        int math = sc.nextInt();

        double average = (java + english + math) / 3.0;

        String status = "";
        if (average >= 60) {
            status = "Pass";
        } else {
            status = "Fail";
        }

        String grade = "";
        if (average >= 90) {
            grade = "A";
        } else if (average >= 80) {
            grade = "B";
        } else if (average >= 70) {
            grade = "C";
        } else if (average >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        int option = -1;
        while (option != 0) {
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println(average);
                    break;
                case 2:
                    System.out.println(status);
                    break;
                case 3:
                    System.out.println(grade);
                    break;
                case 0:
                    break;
            }
        }
    }
}
