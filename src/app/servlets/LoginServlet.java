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

public class LoginServlet extends HttpServlet {
    DatabaseManager dbm = DatabaseManager.getInstance();
    Map<String, String> messages = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (dbm.checkInputs(request.getParameter("pass")) && dbm.checkInputs(request.getParameter("email")))  {
            String user = request.getParameter("email");
            String pass = request.getParameter("pass");

            HttpSession session = request.getSession();

            session.removeAttribute("user");
            User logged = logIn(user, pass);
            if (logged != null) {
                session.setAttribute("user", logged);
                response.sendRedirect("profile");
            } else {
                response.sendRedirect("login?loginerror");
            }
        } else {
            response.sendRedirect("login?formaterror");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher login = request.getRequestDispatcher("login.jsp");
        messages.put("loginerror", "Email or password incorrect");
        messages.put("formaterror", "Only letters and numbers are allowed!");
        messages.put("regsuccess", "User registered successfully!");
        for(String error: messages.keySet()) {
            if(request.getParameterMap().containsKey(error)) {
                request.setAttribute("messageFromServlet", messages.get(error));
            }
        }
        login.forward(request, response);
    }

    public User logIn(String userLogin, String userPassword) {
        return dbm.loginUser(userLogin, userPassword);
    }
}
