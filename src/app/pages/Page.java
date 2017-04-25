package app.pages;

public abstract class Page {
    private long id;
    private boolean published;
    private String title;

    protected Page(int id, boolean published, String title) {
        this.id = id;
        this.published = published;
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

    public boolean isPublished() {
        return published;
    }

    public String getTitle() {
        return title;
    }
}
