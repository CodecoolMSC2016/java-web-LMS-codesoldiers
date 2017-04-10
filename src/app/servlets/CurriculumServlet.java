package app.servlets;

import app.PageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CurriculumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageManager pageManager = PageManager.getInstance();
        String method = request.getParameter("method");
        switch (method) {
            case "delete":
                delete();
                break;
            case "modify":
                modify();
                break;
            case "put":
                put();
                break;
            default:
                response.sendError(404);
                break;
        }
    }

    private void delete() {

    }

    private void modify() {

    }

    private void put() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageManager pageManager = PageManager.getInstance();
    }
}
