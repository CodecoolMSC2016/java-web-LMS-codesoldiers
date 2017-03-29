package app.servlets;

import app.PageWriter;
import app.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(name = "app.servlets.WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
    PageWriter pageWriter = new PageWriter();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //out.println("Welcome user");
        out.print("<html>");
        out.print(pageWriter.getHTMLHead("Welcome"));
        out.print(pageWriter.getHTMLSidebar(Pages.HOME, request.getParameter("email")));
        out.print("</html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}