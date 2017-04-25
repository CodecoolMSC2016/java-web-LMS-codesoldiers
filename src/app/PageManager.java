package app;

import app.pages.AssignmentPage;
import app.pages.Page;
import app.pages.TextPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PageManager {
    private static PageManager ourInstance = new PageManager();
    private List<Page> pages;

    private PageManager() {
        pages = new LinkedList<>();
        Page.lastId = -1;
        addTextPage("textpage1", "textcontent1");
        addTextPage("textpage2", "textcontent2");
        addTextPage("textpage3", "textcontent3");
        addTextPage("textpage4", "textcontent4");
    }

    public static PageManager getInstance() {
        return ourInstance;
    }

    public List<Page> getPages() {
        return pages;
    }

    public boolean addTextPage(String title, String content) {
        return pages.add(new TextPage(title, content));
    }

    public boolean addAssignmentPage(String title, String question, int maxScore) {
        return pages.add(new AssignmentPage(title, question, maxScore));
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

    public boolean reorderPages(LinkedList<Integer> orderList) {
        Page[] pageArr = new Page[pages.size()];
        for (int i = 0; i < pages.size(); i++) {
            int insertIndex = orderList.get(i);
            pageArr[insertIndex] = getById(pages, i);
        }
        pages = Arrays.asList(pageArr);
        return true;
    }
}
