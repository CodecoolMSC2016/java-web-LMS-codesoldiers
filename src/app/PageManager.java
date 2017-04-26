package app;

import app.pages.AssignmentPage;
import app.pages.Page;
import app.pages.TextPage;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PageManager {
    private static PageManager ourInstance = new PageManager();
    private List<Page> pages = new LinkedList<>();
    private PageLoader loader = PageLoader.getInstance();

    private PageManager() {
        pages = loader.loadPages();
    }

    public static PageManager getInstance() {
        return ourInstance;
    }

    private boolean reloadPages() {
        pages = loader.loadPages();
        if (pages != null) {
            return true;
        }
        return false;
    }

    public List<Page> getPages() {
        return pages;
    }

    public boolean addTextPage(String title, String content) {
        boolean sqlSucc = loader.addTextPage(new TextPage(0, false, title, content));
        if (sqlSucc) {
            reloadPages();
            return true;
        }
        return false;
    }

    public boolean addAssignmentPage(String title, String question, int maxScore) {
        boolean sqlSucc = loader.addAssignmentPage(new AssignmentPage(0, false, title, question, maxScore));
        if (sqlSucc) {
            reloadPages();
            return true;
        }
        return false;
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

    public boolean reorderPages(Map<Integer, Integer> orderList) {
        loader.updateOrder(orderList);
        reloadPages();
        return true;
    }
}
