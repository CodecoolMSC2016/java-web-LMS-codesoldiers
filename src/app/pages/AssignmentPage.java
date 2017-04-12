package app.pages;

public class AssignmentPage extends Page {
    private String question;
    private int maxScore;

    public AssignmentPage(String title, String question, int maxScore) {
        super(title);
        this.question = question;
        this.maxScore = maxScore;
    }
}
