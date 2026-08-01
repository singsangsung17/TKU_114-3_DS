public class Contestant {
    private String id;
    private String name;
    private int score;
    private int seconds;

    public Contestant(String id, String name, int score, int seconds) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.seconds = seconds;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getSeconds() {
        return seconds;
    }

    @Override
    public String toString() {
        return String.format("%-6s %-8s 分數 %-5d 秒數 %-5d", id, name, score, seconds);
    }
}
