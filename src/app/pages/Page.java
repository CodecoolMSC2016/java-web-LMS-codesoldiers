package app.pages;

public abstract class Page {
    public static long lastId;
    private long id;
    private boolean published;
    private String title;

    protected Page(String title) {
        id = lastId + 1;
        lastId += 1;
        published = false;
        this.title = title;
    }

    protected void Publish() {
        published = true;
    }

    protected void Unpublish() {
        published = false;
    }

    public long getId() {
        return id;
    }
}
