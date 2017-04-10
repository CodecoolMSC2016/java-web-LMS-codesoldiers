package app.pages;

public abstract class Page {
    private boolean published;
    private String title;

    protected Page(String title) {
        published = false;
        this.title = title;
    }

    protected void Publish() {
        published = true;
    }

    protected void Unpublish() {
        published = false;
    }
}
