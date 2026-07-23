public class Equipment {
    private String code;
    private String name;
    private boolean available;

    public Equipment(String code, String name) {
        this.code = code;
        this.name = name;
        this.available = true;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean borrowItem() {
        if (available) {
            available = false;
            return true;
        }
        return false;
    }

    public boolean returnItem() {
        if (!available) {
            available = true;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String status = available ? "可借用" : "已借出";
        return code + " | " + name + " | 狀態：" + status;
    }
}
