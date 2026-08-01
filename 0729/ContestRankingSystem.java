public class ContestRankingSystem {
    public static void main(String[] args) {
        Contestant[] contestants = {
            new Contestant("C01", "Amy", 88, 120),
            new Contestant("C02", "Ben", 95, 150),
            new Contestant("C03", "Cara", 88, 95),
            new Contestant("C04", "Dan", 72, 80),
            new Contestant("C05", "Eva", 95, 110),
            new Contestant("C06", "Finn", 60, 200),
            new Contestant("C07", "Gina", 88, 140),
            new Contestant("C08", "Hank", 72, 65)
        };

        System.out.println("排序前（報名順序）：");
        printAll(contestants);

        insertionSortByRank(contestants);

        System.out.println();
        System.out.println("排名結果（分數高者優先，同分則秒數少者優先）：");
        printRanking(contestants);
    }

    public static boolean isBefore(Contestant left, Contestant right) {
        if (left.getScore() != right.getScore()) {
            return left.getScore() > right.getScore();
        }
        return left.getSeconds() < right.getSeconds();
    }

    public static void insertionSortByRank(Contestant[] contestants) {
        for (int index = 1; index < contestants.length; index++) {
            Contestant key = contestants[index];
            int position = index - 1;

            while (position >= 0 && isBefore(key, contestants[position])) {
                contestants[position + 1] = contestants[position];
                position--;
            }

            contestants[position + 1] = key;
        }
    }

    public static void printRanking(Contestant[] contestants) {
        System.out.printf("%-6s %s%n", "名次", "完整資料");
        System.out.println("--------------------------------------------------");

        int rank = 1;
        for (int index = 0; index < contestants.length; index++) {
            if (index > 0 && !isSameRank(contestants[index - 1], contestants[index])) {
                rank = index + 1;
            }
            System.out.printf("%-6d %s%n", rank, contestants[index]);
        }
    }

    public static boolean isSameRank(Contestant left, Contestant right) {
        return left.getScore() == right.getScore()
            && left.getSeconds() == right.getSeconds();
    }

    public static void printAll(Contestant[] contestants) {
        for (int index = 0; index < contestants.length; index++) {
            System.out.println("[" + index + "] " + contestants[index]);
        }
    }
}
