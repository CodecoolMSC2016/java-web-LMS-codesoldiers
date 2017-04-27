package app.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginHelper {
    public static void redirectLogin(HttpServletRequest request, HttpServletResponse response, String url) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            response.sendRedirect(url);
        } else {
            response.sendRedirect("login");
        }
    }

    static boolean checkLogin(HttpSession session) throws ServletException, IOException {
        if (session.getAttribute("user") != null) {
            return true;
        }
        return false;
    }

}
