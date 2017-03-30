package app.servlets;

import app.CSVRW;
import app.DataManager;
import app.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class RegisterServlet extends HttpServlet {
    CSVRW io;
    DataManager manager = new DataManager();

    public RegisterServlet() {
        this.io = new CSVRW();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (checkInputs()) {
            register(request.getParameter("user"),
                    request.getParameter("email"),
                    request.getParameter("role"),
                    request.getParameter("pass"));
        }
        response.sendRedirect("/login");
    }

    private void register(String user, String email, String role, String password) {
        // list from readcsv
        List<User> userList = io.readCSVDatabase();
        System.out.println(password);
        // new user from params
        User newUser = new User(user, email, role, password);

        // add user to readcsv list
        userList.add(newUser);
        // saveto csv
        io.saveToCSV(userList);
        System.out.println(userList.size());
        System.out.println("csv saved");
    }

    private boolean checkInputs() {
        return true;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/login");
    }
}
