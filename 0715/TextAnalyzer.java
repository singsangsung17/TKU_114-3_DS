import java.util.Scanner;

public class TextAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String originalText = readValidText(sc);

        System.out.println("\n原始字元數：" + originalText.length());

        String trimmedText = originalText.trim();
        System.out.println("有效字元數 (trim 後)：" + trimmedText.length());

        String[] words = splitIntoWords(trimmedText);
        System.out.println("單字數量：" + words.length);

        int vowelCount = countVowels(trimmedText);
        System.out.println("母音總數：" + vowelCount);

        String longestWord = findLongestWord(words);
        System.out.println("最長單字：" + longestWord);

        System.out.print("\n請輸入要搜尋的關鍵字：");
        String keyword = sc.nextLine();
        int keywordCount = countKeywordOccurrences(words, keyword);
        System.out.println("關鍵字 '" + keyword + "' 出現次數：" + keywordCount);

        sc.close();
    }

    public static String readValidText(Scanner sc) {
        String text;
        do {
            System.out.print("請輸入一行非空白文字：");
            text = sc.nextLine();
            if (text.trim().isEmpty()) {
                System.out.println("輸入不可為空字串或全空白，請重新輸入！\n");
            }
        } while (text.trim().isEmpty());
        return text;
    }

    public static String[] splitIntoWords(String text) {
        return text.split("\\s+");
    }

    public static int countVowels(String text) {
        int count = 0;
        String lowerText = text.toLowerCase();
        
        for (int i = 0; i < lowerText.length(); i++) {
            char c = lowerText.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    public static String findLongestWord(String[] words) {
        if (words.length == 0) {
            return "";
        }
        
        String longest = words[0];
        for (int i = 1; i < words.length; i++) {
            if (words[i].length() > longest.length()) {
                longest = words[i];
            }
        }
        return longest;
    }

    public static int countKeywordOccurrences(String[] words, String keyword) {
        int count = 0;
        String cleanKeyword = keyword.trim();
        
        for (String word : words) {
            if (word.equalsIgnoreCase(cleanKeyword)) {
                count++;
            }
        }
        return count;
    }
}
