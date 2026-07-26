public class PlaylistNode {
    String songCode;
    String songName;
    PlaylistNode next;

    public PlaylistNode(String songCode, String songName) {
        this.songCode = songCode;
        this.songName = songName;
        this.next = null;
    }
}
