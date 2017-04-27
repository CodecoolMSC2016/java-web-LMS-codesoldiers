package app.servlets;

import app.PageManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.Map;

public class CurriculumServlet extends HttpServlet {
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageManager pageManager = PageManager.getInstance();
        Map<String, String[]> parameterMap = request.getParameterMap();

        try {
            int id = Integer.valueOf(request.getParameter("id"));
            boolean successful = pageManager.removePageById(id);
            if (successful) {
                response.sendError(200);
            }
        } catch (Exception e) {
            response.sendError(400);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageManager pageManager = PageManager.getInstance();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String method = request.getParameter("method");
        System.out.println(request.getParameter("method"));

        // TODO: restful
        int error = 500;
        switch (method) {
            case "modify":
                // error = modify(parameterMap);
                error = 501;
                break;
            case "reorder":
                error = reorderPages(pageManager, parameterMap) ? 200 : 500;
                break;
            default:
                error = 404;
                break;
        }
        if (error >= 400) {
            response.sendError(error);
        } else {
            response.sendRedirect("/curriculum");
        }
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageManager pageManager = PageManager.getInstance();

        String parameterString = convertStreamToString(request.getInputStream()); //1 string, json

        Type type = new TypeToken<Map<String, String>>() {}.getType();
        Gson gson = new Gson();

        Map<String, String> parameterMap = gson.fromJson(parameterString, type);


        int error = 500;
        String pageType = parameterMap.get("pageType");
        switch (pageType) {
            case "addTextPage":
                // title, content
                error = addTextPage(pageManager, parameterMap) ? 201 : 500;
                break;
            case "addAssignmentPage":
                // title, question, maxScore
                error = addAssignmentPage(pageManager, parameterMap) ? 201 : 500;
                break;
            default:
                error = 404;
                break;
        }
        if (error >= 400) {
            response.setStatus(error);
            response.getWriter().print("{}");
        } else {
            response.setStatus(error);
            response.getWriter().print("{}");
        }
    }

    private int modifyPage(PageManager pageManager, Map<String, String[]> parameterMap) {
        // required params: id, modified property's name, modified value
        return -1;
    }

    private boolean addTextPage(PageManager pageManager, Map<String, String> parameterMap) {
        // required params: title, content

        if (parameterMap.containsKey("title") &&
                parameterMap.containsKey("content")) {
            String title = parameterMap.get("title");
            String content = parameterMap.get("content");
            if (!title.equals("") || !content.equals("")) {
                pageManager.addTextPage(title, content);
                return true;
            }
        }
        return false;
    }

    private boolean addAssignmentPage(PageManager pageManager, Map<String, String> parameterMap) {
        // required params: title, question, maxScore

        if (parameterMap.containsKey("title") &&
                parameterMap.containsKey("question") &&
                parameterMap.containsKey("maxScore")) {
            try {
                String title = parameterMap.get("title");
                String question = parameterMap.get("question");
                int maxScore = Integer.valueOf(parameterMap.get("maxScore"));
                if (!title.equals("") || !question.equals("")) {
                    pageManager.addAssignmentPage(title, question, maxScore);
                    return true;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    private boolean reorderPages(PageManager pageManager, Map<String, String[]> parameterMap) {
        Type type = new TypeToken<Map<Integer, Integer>>() {}.getType();
        Gson gson = new Gson();

        String json = parameterMap.get("json")[0];

        // [3, 0, 2, 1]
        // -->
        // 0. item on 3. place  item.id(0) on item.ord(3)
        // 1. item on 0. place  item.id(1) on item.ord(0)
        // 2. item on 2. place  item.id(2) on item.ord(2)
        // 3. item on 1. place  item.id(3) on item.ord(1)
        Map<Integer, Integer> orderList = gson.fromJson(json, type);
        return pageManager.reorderPages(orderList);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("curriculum.jsp");
        PageManager pageManager = PageManager.getInstance();
        HttpSession session = request.getSession();

        request.setAttribute("pages", pageManager.getPages().toArray());
        request.setAttribute("user", session.getAttribute("user"));  // mentor or not
        requestDispatcher.forward(request, response);
    }
}
