package app.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        RequestDispatcher login = request.getRequestDispatcher("login.html");
        HttpSession session = request.getSession();

        session.removeAttribute("user");


        if(email.equals("admin") && pass.equals("pass"))
        {
            session.setAttribute("user", "admin");
            response.sendRedirect("welcome");
        }
        else
        {
            out.println("<p style='margin-left: 250'>Username or password incorrect</p>");
            login.include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher login = request.getRequestDispatcher("login.html");
        login.forward(request, response);
    }
}
