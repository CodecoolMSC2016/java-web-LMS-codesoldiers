package app.servlets;

import app.CSVRW;
import app.PageWriter;
import app.Pages;
import app.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class UserlistServlet extends HttpServlet {
    PageWriter pageWriter = new PageWriter();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //out.print("userlist!!44!négy!!!");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        out.print(session.getAttribute("user"));
        out.print(pageWriter.getHTMLHead("Users"));
        out.print(pageWriter.getHTMLSidebar(Pages.USERS ,user.getUsername()));
        out.print("<section id='content'>");
        try {
            CSVRW db = new CSVRW();
            List<User> userdb = db.readCSVDatabase();
            for (User cUser : userdb) {
                if (user.getRole().equals("mentor") || cUser.getRole().equals("student")) {
                    String cUserRole = cUser.getRole();
                    String cUserEmail = cUser.getEmail();
                    out.print(pageWriter.getCard(cUserRole, cUserEmail, ""));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print("</section>");

    }
}
