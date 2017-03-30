package app.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginHelper {
    private static void check(HttpServletRequest request, HttpServletResponse response, String str, boolean isDispatcher) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            if (isDispatcher) {
                RequestDispatcher welcome = request.getRequestDispatcher(str);
                welcome.forward(request, response);
            }
            else {
                response.sendRedirect(str);
            }
        }
        else {
            response.sendRedirect("login");
        }
    }

    public static void redirectLogin(HttpServletRequest request, HttpServletResponse response, String url) throws IOException, ServletException {
        check(request, response, url, false);
    }

    static void checkLogin(HttpServletRequest request, HttpServletResponse response, String html) throws ServletException, IOException {
        check(request, response, html, true);
    }

}
