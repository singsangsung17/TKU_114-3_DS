public class PlaylistSystem {
    public static void main(String[] args) {
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        System.out.println("=== 1. 測試空清單 ===");
        playlist.printPlaylist();
        playlist.removeByCode("S001");

        System.out.println("\n=== 2. 測試新增歌曲 (尾端新增) ===");
        playlist.addLast("S001", "如果可以");
        playlist.addLast("S002", "孤勇者");
        playlist.addLast("S003", "天黑黑");
        playlist.printPlaylist();

        System.out.println("\n=== 3. 測試歌曲代碼重複防呆 ===");
        playlist.addLast("S002", "另一首孤勇者");

        System.out.println("\n=== 4. 測試搜尋 ===");
        System.out.println("是否包含代碼 S003：" + playlist.contains("S003"));
        System.out.println("是否包含代碼 S999：" + playlist.contains("S999"));

        System.out.println("\n=== 5. 測試刪除第一首歌曲 ===");
        playlist.removeByCode("S001");
        playlist.printPlaylist();

        System.out.println("\n=== 6. 測試刪除最後一首歌曲 ===");
        playlist.removeByCode("S003");
        playlist.printPlaylist();

        System.out.println("\n=== 7. 測試刪除找不到的歌曲 ===");
        playlist.removeByCode("S999");
        playlist.printPlaylist();

        System.out.println("\n=== 8. 測試清空清單 ===");
        playlist.removeByCode("S002");
        playlist.printPlaylist();
    }
}
