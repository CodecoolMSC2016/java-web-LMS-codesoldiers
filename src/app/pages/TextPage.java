package app.pages;

public class TextPage extends Page {
    private String content;

    public TextPage(String title, String content) {
        super(title);
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
