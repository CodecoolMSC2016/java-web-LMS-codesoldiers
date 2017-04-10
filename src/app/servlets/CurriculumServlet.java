package app.servlets;

import app.PageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CurriculumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageManager pageManager = PageManager.getInstance();
        String method = request.getParameter("method");

        Map<String, String[]> parameterMap = request.getParameterMap();
        switch (method) {
            case "delete":
                delete(parameterMap);
                break;
            case "modify":
                modify(parameterMap);
                break;
            case "put":
                put(parameterMap);
                break;
            default:
                response.sendError(404);
                break;
        }
    }

    private void delete(Map<String, String[]> parameterMap) {
        // required params: id

    }

    private void modify(Map<String, String[]> parameterMap) {
        // required params: id, modified property's name, modified value

    }

    private void put(Map<String, String[]> parameterMap) {
        // required params: page type, title, [content] || [question, maxScore]

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageManager pageManager = PageManager.getInstance();
    }
}
