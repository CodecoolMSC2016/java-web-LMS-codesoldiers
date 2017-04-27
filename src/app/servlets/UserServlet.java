package app.servlets;

import app.DatabaseManager;
import app.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by david_szilagyi on 2017.04.26..
 */

public class UserServlet extends HttpServlet {
    DatabaseManager dbm = DatabaseManager.getInstance();
    Map<String, String> messages = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currUser = (User) request.getSession().getAttribute("user");
        if (request.getParameter("deleteUser").equalsIgnoreCase("")) {
            if (dbm.sha1(request.getParameter("currpass")).equalsIgnoreCase(currUser.getPassword())) {
                dbm.deleteUser(currUser.getEmail());
                response.sendRedirect("logout?deleted");
            } else {
                response.sendRedirect("profile?wrongpass");
            }
        } else if (dbm.checkInputs(request.getParameter("currpass")) && dbm.checkInputs(request.getParameter("newpass"))
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
        messages.put("success", "Changed successfully!");
        messages.put("formaterror", "Only letters and numbers are allowed!");
        messages.put("wrongpass", "Incorrect password!");
        messages.put("missingname", "Username required!");
        for(String error: messages.keySet()) {
            if(request.getParameterMap().containsKey(error)) {
                request.setAttribute("messageFromServlet", messages.get(error));
            }
        }
        login.forward(request, response);
    }
}
