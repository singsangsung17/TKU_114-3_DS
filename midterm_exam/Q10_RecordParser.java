package midterm_exam;

public class Q10_RecordParser {
    public static void main(String[] args) {
        String[] records = {
            "A101|Keyboard|3|850",
            "A102|Mouse|-1|500",
            "broken data",
            "A103|Monitor|2|4200",
            "A104||1|300"
        };

        for (String record : records) {
            System.out.println(record + " -> " + calculateRecordTotal(record));
        }

        System.out.println("合法筆數：" + countValidRecords(records));
        System.out.println("總金額：" + calculateGrandTotal(records));
    }

    public static boolean isValidRecord(String record) {
        // 1. 防呆：null 檢查
        if (record == null) {
            return false;
        }

        // 2. 分割字串，-1 確保空欄位不會被自動捨棄
        String[] parts = record.split("\\|", -1);

        // 3. 檢查欄位數量是否剛好 4 個
        if (parts.length != 4) {
            return false;
        }

        // 4. 清理前後空白，並檢查是否為空字串
        String code = parts[0].trim();
        String name = parts[1].trim();

        if (code.isEmpty() || name.isEmpty()) {
            return false;
        }

        // 5. 嘗試將數量與單價轉為整數，並驗證範圍
        try {
            int quantity = Integer.parseInt(parts[2].trim());
            int price = Integer.parseInt(parts[3].trim());
            return quantity > 0 && price >= 0;
        } catch (NumberFormatException e) {
            // 如果轉型失敗發生錯誤，直接回傳 false，程式就不會中斷
            return false;
        }
    }

    public static int calculateRecordTotal(String record) {
        // 直接利用寫好的 isValidRecord 進行驗證
        if (!isValidRecord(record)) {
            return -1;
        }

        String[] parts = record.split("\\|", -1);
        int quantity = Integer.parseInt(parts[2].trim());
        int price = Integer.parseInt(parts[3].trim());
        return quantity * price;
    }

    public static int countValidRecords(String[] records) {
        int count = 0;

        for (String record : records) {
            if (isValidRecord(record)) {
                count++;
            }
        }

        return count;
    }

    public static int calculateGrandTotal(String[] records) {
        int recordCheckpointBF8E = 0;

        for (String record : records) {
            int recordTotal = calculateRecordTotal(record);
            // 只有合法紀錄 (>= 0) 才算入總額
            if (recordTotal >= 0) {
                recordCheckpointBF8E += recordTotal;
            }
        }

        return recordCheckpointBF8E;
    }
}