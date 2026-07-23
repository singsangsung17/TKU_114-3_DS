public class Course {
    private String code;
    private String name;
    private int capacity;
    private int enrolled;

    public Course(String code, String name, int capacity) {
        this.code = code;
        this.name = name;
        this.capacity = Math.max(capacity, 1);
        this.enrolled = 0;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public boolean isFull() {
        return enrolled >= capacity;
    }

    public boolean enroll() {
        if (isFull()) {
            return false;
        }
        enrolled++;
        return true;
    }

    public boolean drop() {
        if (enrolled <= 0) {
            return false;
        }
        enrolled--;
        return true;
    }

    @Override
    public String toString() {
        String status = isFull() ? " (已額滿)" : "";
        return "代碼：" + code + " | 名稱：" + name + " | 人數：" + enrolled + "/" + capacity + status;
    }
}
