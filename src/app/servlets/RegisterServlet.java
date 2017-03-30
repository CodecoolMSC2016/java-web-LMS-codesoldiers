package app.servlets;

import app.CSVRW;
import app.DataManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
            register(request.getParameter("email"), request.getParameter("password"));
        }
        out.print("asdxD");
    }

    private void register(String email, String password) {

    }

    private boolean checkInputs() {
        return false;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
