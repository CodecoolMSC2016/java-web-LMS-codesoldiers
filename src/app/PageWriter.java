public class PageWriter {
    public String getHTMLHead(String title) {
        String result = "";
        result += "<head>\n" +
                "  <title>" + title + "</title>\n" +
                "  <style>\n" +
                "    @import \"style.css\";\n" +
                "    @import \"login.css\";\n" +
                "  </style>\n";
        return result;
    }

    public String getHTMLSidebar(Pages activePage, String user) {
        String result = "";
        result += "<nav id=\"navbar\">\n" +
                "  <h2 class=\"indent-30\" id=\"menu_title\">" + user + "</h2>\n" +
                "  <div id=\"menu\">\n";
        for (Pages page: Pages.values()) {
            switch (page) {
                case HOME:
                    if (page.equals(activePage)) {
                        result += "<a href=\"#\"><h3 class=\"indent-20 menu_item selected_menu_item\">Home</h3></a>";
                    }
                    else {
                        result += "<a href=\"/welcome\"><h3 class=\"indent-20 menu_item\">Home</h3></a>";
                    }
                    break;
                case ASSIGNMENTS:
                    if (page.equals(activePage)) {
                        result += "<a href=\"#\"><h3 class=\"indent-20 menu_item selected_menu_item\">Assignments</h3></a>";
                    }
                    else {
                        result += "<a href=\"/assignments\"><h3 class=\"indent-20 menu_item\">Assignments</h3></a>";
                    }
                    break;

            }
        }
                //"    <a href=\"\"><h3 class=\"indent-20 menu_item selected_menu_item\">Login</h3></a>\n";
        result += "  </div>\n" +
                "  <div class=\"indent-20\" id=\"company_name\">\n" +
                "    <h5>created by</h5>\n" +
                "    <h4>CodeSoldiers</h4>\n" +
                "    <h5>- 2017 -</h5>\n" +
                "  </div>\n" +
                "</nav>";
        return result;
    }


}
