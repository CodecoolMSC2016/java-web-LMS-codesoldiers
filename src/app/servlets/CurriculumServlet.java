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

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageManager pageManager = PageManager.getInstance();
        Map<String, String[]> parameterMap = request.getParameterMap();

        int error = 500;
        switch (request.getParameter("method")) {
            case "addTextPage":
                // title, content
                addTextPage(pageManager, parameterMap);
                break;
            case "addAssignmentPage":
                // title, question, maxScore
                error = addAssignmentPage(pageManager, parameterMap) ? 200 : 500;
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

    private int modifyPage(PageManager pageManager, Map<String, String[]> parameterMap) {
        // required params: id, modified property's name, modified value
        return -1;
    }

    private int addTextPage(PageManager pageManager, Map<String, String[]> parameterMap) {
        // required params: title, content

        if (parameterMap.containsKey("title") &&
                parameterMap.containsKey("content")) {
            String title = parameterMap.get("title")[0];
            String content = parameterMap.get("content")[0];
            if (!title.equals("") || !content.equals("")) {
                pageManager.addTextPage(title, content);
                return 200;
            }
        }
        return 400;
    }

    private boolean addAssignmentPage(PageManager pageManager, Map<String, String[]> parameterMap) {
        // required params: title, question, maxScore

        if (parameterMap.containsKey("title") &&
                parameterMap.containsKey("question") &&
                parameterMap.containsKey("maxScore")) {
            try {
                String title = parameterMap.get("title")[0];
                String question = parameterMap.get("question")[0];
                int maxScore = Integer.valueOf(parameterMap.get("maxScore")[0]);
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
