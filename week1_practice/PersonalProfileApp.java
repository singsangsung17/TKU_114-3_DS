package week1_practice;

import java.util.Scanner;

public class PersonalProfileApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入姓名：");
        String name = sc.next();

        int age = readPositiveInt(sc, "請輸入年齡：");
        double height = readPositiveDouble(sc, "請輸入身高（公尺）：");
        double weight = readPositiveDouble(sc, "請輸入體重（公斤）：");

        boolean isAdult = isAdult(age);
        double bmi = calculateBmi(height, weight);
        String level = getBmiLevel(bmi);

        printReport(name, age, isAdult, height, weight, bmi, level);

        sc.close();
    }

    public static int readPositiveInt(Scanner sc, String message) {
        System.out.print(message);
        int value = sc.nextInt();
        while (value <= 0) {
            System.out.print("輸入錯誤，年齡必須大於 0。請重新輸入：");
            value = sc.nextInt();
        }
        return value;
    }

    public static double readPositiveDouble(Scanner sc, String message) {
        System.out.print(message);
        double value = sc.nextDouble();
        while (value <= 0) {
            System.out.print("輸入錯誤，數值必須大於 0。請重新輸入：");
            value = sc.nextDouble();
        }
        return value;
    }

    public static double calculateBmi(double height, double weight) {
        return weight / (height * height);
    }

    public static String getBmiLevel(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24) {
            return "Normal";
        } else if (bmi < 27) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static boolean isAdult(int age) {
        if (age >= 18) {
            return true;
        } else {
            return false;
        }
    }

    public static void printReport(String name, int age, boolean adult, double height, double weight, double bmi, String level) {
        System.out.println("\n=== Personal Health Report ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Adult: " + adult);
        System.out.println("Height: " + height);
        System.out.println("Weight: " + weight);
        System.out.println("BMI: " + bmi);
        System.out.println("Level: " + level);
    }
}
