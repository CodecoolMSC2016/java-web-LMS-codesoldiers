package app.pages;

public class AssignmentPage extends Page {
    private String question;
    private int maxScore;

    public AssignmentPage(int id, boolean published, String title, String question, int maxScore) {
        super(id, published, title);
        this.question = question;
        this.maxScore = maxScore;
    }

    public String getQuestion() {
        return question;
    }

    public int getMaxScore() {
        return maxScore;
    }
}
