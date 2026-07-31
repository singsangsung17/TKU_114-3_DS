public class EmployeeSearchSystem {
    public static void main(String[] args) {
        Employee[] employees = {
            new Employee(1001, "王小明", "研發部", "2101"),
            new Employee(1005, "李美芳", "行銷部", "2205"),
            new Employee(1012, "陳志豪", "研發部", "2110"),
            new Employee(1020, "林怡君", "人資部", "2301"),
            new Employee(1020, "張家豪", "人資部", "2302"),
            new Employee(1033, "黃雅婷", "財務部", "2401"),
            new Employee(1041, "吳建宏", "資訊部", "2501"),
            new Employee(1055, "劉曉薇", "業務部", "2601")
        };
        Employee[] emptyEmployees = {};

        System.out.println("員工資料（依編號排序，共 " + employees.length + " 筆）：");
        printAll(employees);

        System.out.println();
        search(employees, 1001);
        search(employees, 1055);
        search(employees, 1033);
        search(employees, 1020);
        search(employees, 9999);
        search(emptyEmployees, 1001);
        search(null, 1001);
    }

    public static int binarySearchFirst(Employee[] employees, int targetId) {
        if (employees == null || employees.length == 0) {
            return -1;
        }

        int low = 0;
        int high = employees.length - 1;
        int answer = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int currentId = employees[mid].getId();

            if (currentId == targetId) {
                answer = mid;
                high = mid - 1;
            } else if (targetId < currentId) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return answer;
    }

    public static void search(Employee[] employees, int targetId) {
        System.out.println("查詢編號 " + targetId + "：");

        if (employees == null || employees.length == 0) {
            System.out.println("  員工資料為空，無法查詢");
            return;
        }

        int index = binarySearchFirst(employees, targetId);
        if (index == -1) {
            System.out.println("  查無編號 " + targetId + " 的員工");
            return;
        }

        int count = 0;
        int position = index;
        while (position < employees.length
            && employees[position].getId() == targetId) {
            System.out.println("  " + employees[position]);
            count++;
            position++;
        }

        if (count > 1) {
            System.out.println("  注意：編號 " + targetId + " 重複 " + count
                + " 筆，索引範圍 " + index + " 到 " + (position - 1));
        } else {
            System.out.println("  索引 " + index);
        }
    }

    public static void printAll(Employee[] employees) {
        for (int index = 0; index < employees.length; index++) {
            System.out.println("[" + index + "] " + employees[index]);
        }
    }
}
