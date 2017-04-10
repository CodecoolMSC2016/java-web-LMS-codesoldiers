package app;

import app.pages.AssignmentPage;
import app.pages.Page;
import app.pages.TextPage;

import java.util.ArrayList;
import java.util.List;

public class PageManager {
    private static PageManager ourInstance = new PageManager();

    public static PageManager getInstance() {
        return ourInstance;
    }

    private List<Page> pages;

    private PageManager() {
        pages = new ArrayList<>();
        Page.lastId = -1;
    }

    public void addTextPage(String title, String content) {
        pages.add(new TextPage(title, content));
    }

    public void addAssignmentPage(String title, String question, int maxScore) {
        pages.add(new AssignmentPage(title, question, maxScore));
    }
}
