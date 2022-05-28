package hymn.book.kakotjingrwai01;

public class ChorusModel {
    String id,lyric;

    public ChorusModel(String id, String lyric) {
        this.id = id;
        this.lyric = lyric;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }
}
