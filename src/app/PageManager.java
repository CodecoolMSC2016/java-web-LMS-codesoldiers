package app;

public class PageManager {
    private static PageManager ourInstance = new PageManager();

    public static PageManager getInstance() {
        return ourInstance;
    }

    private PageManager() {
    }
}
