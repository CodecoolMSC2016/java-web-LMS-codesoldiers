package app.servlets;

import app.DatabaseManager;
import app.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by david_szilagyi on 2017.04.26..
 */

public class UserServlet extends HttpServlet {
    DatabaseManager dbm = DatabaseManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currUser = (User) request.getSession().getAttribute("user");
        if (dbm.checkInputs(request.getParameter("currpass")) && dbm.checkInputs(request.getParameter("newpass"))
                && dbm.checkInputs(request.getParameter("user"))) {
            if (!request.getParameter("user").equalsIgnoreCase("")) {
                if (dbm.sha1(request.getParameter("currpass")).equalsIgnoreCase(currUser.getPassword())) {
                    currUser.setUsername(request.getParameter("user"));
                    if (!request.getParameter("newpass").equalsIgnoreCase("")) {
                        currUser.setPassword(dbm.sha1(request.getParameter("newpass")));
                    } else {
                        currUser.setPassword(dbm.sha1(request.getParameter("currpass")));
                    }
                    request.setAttribute("user", currUser);
                    dbm.changeAttr(currUser);
                    response.sendRedirect("profile?success");
                } else {
                    response.sendRedirect("profile?wrongpass");
                }
            } else {
                response.sendRedirect("profile?missingname");
            }
        } else {
            response.sendRedirect("profile?formaterror");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher login = request.getRequestDispatcher("profile.jsp");
        if (request.getParameterMap().containsKey("success")) {
            request.setAttribute("messageFromServlet", "Changed successfully!");
        } else if (request.getParameterMap().containsKey("formaterror")) {
            request.setAttribute("messageFromServlet", "Only letters and numbers are allowed!");
        } else if (request.getParameterMap().containsKey("wrongpass")) {
            request.setAttribute("messageFromServlet", "Incorrect password!");
        } else if (request.getParameterMap().containsKey("missingname")) {
            request.setAttribute("messageFromServlet", "Username required!");
        }
        login.forward(request, response);
    }
}
