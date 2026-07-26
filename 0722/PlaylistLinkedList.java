public class PlaylistLinkedList {
    private PlaylistNode head;

    public PlaylistLinkedList() {
        head = null;
    }

    public boolean contains(String songCode) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.songCode.equals(songCode)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void addLast(String songCode, String songName) {
        if (contains(songCode)) {
            System.out.println("新增失敗：歌曲代碼 [" + songCode + "] 已存在清單中！");
            return;
        }

        PlaylistNode newNode = new PlaylistNode(songCode, songName);
        if (head == null) {
            head = newNode;
        } else {
            PlaylistNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("成功加入歌曲：" + songName);
    }

    public boolean removeByCode(String songCode) {
        if (head == null) {
            System.out.println("刪除失敗：目前播放清單為空。");
            return false;
        }

        if (head.songCode.equals(songCode)) {
            System.out.println("刪除成功：" + head.songName);
            head = head.next;
            return true;
        }

        PlaylistNode previous = head;
        PlaylistNode current = head.next;
        
        while (current != null) {
            if (current.songCode.equals(songCode)) {
                System.out.println("刪除成功：" + current.songName);
                previous.next = current.next;
                return true;
            }
            previous = current;
            current = current.next;
        }
        
        System.out.println("刪除失敗：找不到歌曲代碼 [" + songCode + "]");
        return false;
    }

    public void printPlaylist() {
        if (head == null) {
            System.out.println("播放順序：(清單為空)");
            return;
        }
        
        System.out.print("播放順序：");
        PlaylistNode current = head;
        while (current != null) {
            System.out.print("[" + current.songCode + "] " + current.songName + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

