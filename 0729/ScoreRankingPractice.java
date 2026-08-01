import java.util.Arrays;

public class ScoreRankingPractice {
    public static void main(String[] args) {
        int[] scores = {78, 92, 55, 88, 92, 60, 45, 88, 73, 100};

        System.out.println("排序前：" + Arrays.toString(scores));
        selectionSortDescending(scores);
        System.out.println("排序後：" + Arrays.toString(scores));

        System.out.println();
        printRanking(scores);
    }

    public static void selectionSortDescending(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int maxIndex = start;

            for (int index = start + 1; index < values.length; index++) {
                if (values[index] > values[maxIndex]) {
                    maxIndex = index;
                }
            }

            if (maxIndex != start) {
                int temp = values[start];
                values[start] = values[maxIndex];
                values[maxIndex] = temp;
            }
        }
    }

    public static void printRanking(int[] sortedScores) {
        System.out.printf("%-6s %-8s %-8s%n", "名次", "分數", "是否及格");
        System.out.println("--------------------------");

        int rank = 1;
        for (int index = 0; index < sortedScores.length; index++) {
            if (index > 0 && sortedScores[index] != sortedScores[index - 1]) {
                rank = index + 1;
            }

            System.out.printf(
                "%-6d %-8d %-8s%n",
                rank,
                sortedScores[index],
                sortedScores[index] >= 60 ? "及格" : "不及格"
            );
        }
    }
}
