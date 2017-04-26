package app.servlets;

import app.DatabaseManager;
import app.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by david_szilagyi on 2017.04.26..
 */

public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseManager dbm = DatabaseManager.getInstance();
        User currUser = (User) request.getSession().getAttribute("user");
        if (dbm.checkInputs(request.getParameter("currpass")) && dbm.checkInputs(request.getParameter("newpass"))) {
            if (dbm.sha1(request.getParameter("currpass")).equalsIgnoreCase(currUser.getPassword())) {
                currUser.setUsername(request.getParameter("user"));
                currUser.setPassword(request.getParameter("pass"));
                request.setAttribute("user", currUser);
                dbm.changeAttr(currUser);
                request.setAttribute("messageFromServlet", "Changed successfully!");
                response.sendRedirect("profile");
            } else {
                request.setAttribute("messageFromServlet", "Incorrect password!");
                response.sendRedirect("profile");
            }
        } else {
            request.setAttribute("messageFromServlet", "Only letters and numbers are allowed!");
            response.sendRedirect("profile");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher login = request.getRequestDispatcher("profile.jsp");
        login.forward(request, response);
    }
}
