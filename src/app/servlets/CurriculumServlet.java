package app.servlets;

import app.PageManager;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class CurriculumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageManager pageManager = PageManager.getInstance();
        Map<String, String[]> parameterMap = request.getParameterMap();
        String method = request.getParameter("method");

        int error = 500;
        switch (method) {
            case "delete":
                error = delete(pageManager, parameterMap);
                break;
            case "modify":
                // error = modify(parameterMap);
                error = 501;
                break;
            case "addTextPage":
                // title, content
                addTextPage(pageManager, parameterMap);
                break;
            case "addAssignmentPage":
                // title, question, maxScore
                addAssignmentPage(pageManager, parameterMap);
                break;
            case "reorder":
                System.out.println(parameterMap.get("json")[0]);
                reorderPages(pageManager, parameterMap);
                error = 200;
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

    private int delete(PageManager pageManager, Map<String, String[]> parameterMap) {
        // required params: id

        if (parameterMap.containsKey("id")) {
            try {
                int id = Integer.valueOf(parameterMap.get("id")[0]);
                boolean successful = pageManager.removePageById(id);
                if (successful) {
                    return 200;
                }
            } catch (NumberFormatException e) {
                return 400;
            }
        }
        return 400;
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

    private int addAssignmentPage(PageManager pageManager, Map<String, String[]> parameterMap) {
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
                    return 200;
                }
            } catch (NumberFormatException e) {
                return 400;
            }
        }
        return 400;
    }

    private int reorderPages(PageManager pageManager, Map<String, String[]> parameterMap) {
        Gson gson = new Gson();
        String json = parameterMap.get("json")[0];
        System.out.println("asd:" + json);
        Map<String, Double> jsonMap = new LinkedHashMap<>();
        jsonMap = gson.fromJson(json, jsonMap.getClass());
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (Double value : jsonMap.values()) {
            list.add(value.intValue());
        }
        System.out.println(list.toString());
        return -1;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String asd = (String) request.getParameter("posts");
        //System.out.print(asd);
        out.print(asd);
        PageManager pageManager = PageManager.getInstance();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("curriculum.html");
        requestDispatcher.forward(request, response);
    }
}