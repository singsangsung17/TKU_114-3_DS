/*
修正紀錄：
1.錯誤位置：System.out.println("系統結束，年齡："+age)
錯誤類型：編譯錯誤(Compile-time-error)
原因：語法不完整，結尾缺少了分號。
修正方式：在該行結尾補上分號;。

2.錯誤位置：for(inti=0;i<=scores.length;i++)
錯誤類型：執行錯誤(Runtime-error-ArrayIndexOutOfBoundsException)
原因：迴圈條件誤用了<=。陣列長度為3，合法索引為0~2，當i為3時會導致陣列越界崩潰。
修正方式：將條件修改為嚴格小於，即i<scores.length。

3.錯誤位置：doubleaverage=total/scores.length;
錯誤類型：邏輯錯誤(Logic-error)
原因：total與scores.length皆為整數，直接相除會進行「整數除法」，導致計算出的小數部分被直接捨棄，失去精準度。
修正方式：在計算前將total強制轉型為double，改為(double)total/scores.length。

4.錯誤位置：intage=sc.nextInt();與Stringcommand=sc.nextLine();之間
錯誤類型：邏輯錯誤(Scanner換行殘留)
原因：nextInt()在讀取完數字後，並不會把使用者按下的Enter換行字元讀走。這會導致緊接在後的nextLine()直接讀到那個換行字元，變成空字串。
修正方式：在nextInt()之後，手動加上一行sc.nextLine();來消耗掉殘留的換行字元。

5.錯誤位置：if(command=="exit")
錯誤類型：邏輯錯誤(字串比較錯誤)
原因：在Java中，使用==比較字串時，比對的是「記憶體位址」而非字串內容，這會導致即使輸入了exit，條件依然判斷為false。
修正方式：改用String物件內建的比較方法，例如command.equals("exit")或command.equalsIgnoreCase("exit")。

6.Breakpoint觀察紀錄：
在修復前的迴圈內total+=scores[i];設置中斷點。按下F5進入除錯模式後，觀察左側的Variables視窗。當持續點擊StepOver讓迴圈執行，會觀察到當變數i的值從2變成3的那一刻，因為scores陣列的最大索引只有2，這時若再往下執行就會立刻拋出ArrayIndexOutOfBoundsException。
*/

import java.util.Scanner;

public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = {80, 75, 92};
        int total = 0;

        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f\n", average);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("請輸入指令：");
        String command = sc.nextLine();

        if (command.equalsIgnoreCase("exit")) {
            System.out.println("系統結束，年齡：" + age);
        }

        sc.close();
    }
}
