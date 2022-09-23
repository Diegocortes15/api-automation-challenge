package models;

public class AddMovieToList {
    private int media_id;

    public AddMovieToList(int media_id) {
        this.media_id = media_id;
    }

    public int getMedia_id() {
        return media_id;
    }

    public void setMedia_id(int media_id) {
        this.media_id = media_id;
    }
}
