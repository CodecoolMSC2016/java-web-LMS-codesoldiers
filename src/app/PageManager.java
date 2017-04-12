package app;

import app.pages.AssignmentPage;
import app.pages.Page;
import app.pages.TextPage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PageManager {
    private static PageManager ourInstance = new PageManager();
    private List<Page> pages;

    private PageManager() {
        pages = new LinkedList<>();
        Page.lastId = -1;
    }

    public static PageManager getInstance() {
        return ourInstance;
    }

    public void addTextPage(String title, String content) {
        pages.add(new TextPage(title, content));
    }

    public void addAssignmentPage(String title, String question, int maxScore) {
        pages.add(new AssignmentPage(title, question, maxScore));
    }

    public boolean removePageById(int id) {
        for (int i = 0; i < pages.size(); i++) {
            if (pages.get(i).getId() == id) {
                pages.remove(i);
                return true;
            }
        }
        return false;
    }

    public void reorderPages(LinkedList<Integer> orderList) {
        List<Page> temp = new ArrayList<>(pages.size());
        for (int i = 0; i < pages.size(); i++) {
            temp.add(orderList.get(i), pages.get(i));
        }
        System.out.println(temp);
    }
}
