package app;

import app.pages.Page;

import java.util.*;

public class PageManager {
    private static PageManager ourInstance = new PageManager();
    private List<Page> pages;

    private PageManager() {
        pages = new LinkedList<>();
        PageLoader loader = PageLoader.getInstance();
        pages = loader.loadPages();
    }

    public static PageManager getInstance() {
        return ourInstance;
    }

    public List<Page> getPages() {
        return pages;
    }

    public boolean addTextPage(String title, String content) {
        // return pages.add(new TextPage(id, title, content));
        return true;
    }

    public boolean addAssignmentPage(String title, String question, int maxScore) {
        // return pages.add(new AssignmentPage(id, title, question, maxScore));
        return true;
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

    private Page getById(List<Page> pageList, int id) {
        for (Page page : pageList) {
            if (page.getId() == id) {
                return page;
            }
        }
        return null;
    }

    public boolean reorderPages(Map<Integer, Integer> orderList) {
        PageLoader loader = PageLoader.getInstance();
        loader.updateOrder(orderList);
        pages = loader.loadPages();
        return true;
    }
}
