package app.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (checkInputs()) {
            register(request.getParameter("user"),
                    request.getParameter("email"),
                    request.getParameter("role"),
                    request.getParameter("pass"));
        }
        response.sendRedirect("login");
    }

    private void register(String user, String email, String role, String password) {
        // TODO: register into SQL
        /*CSVRW io = new CSVRW("userdatabase.csv");
        // list from readcsv
        List<User> userList = io.readUserDatabase();
        System.out.println(password);
        // new user from params
        User newUser = new User(user, email, role, password);

        // add user to readcsv list
        userList.add(newUser);
        // saveto csv
        io.saveToCSV(userList);
        System.out.println(userList.size());
        System.out.println("csv saved");*/
    }

    private boolean checkInputs() {
        return true;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/login");
    }
}
