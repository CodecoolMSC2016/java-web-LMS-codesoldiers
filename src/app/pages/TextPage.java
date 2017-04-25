package app.pages;

public class TextPage extends Page {
    private String content;

    public TextPage(int id, boolean published, String title, String content) {
        super(id, published, title);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
