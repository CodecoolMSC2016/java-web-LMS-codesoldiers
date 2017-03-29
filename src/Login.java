import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends HttpServlet {

    static Users users = new Users();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameterMap().keySet().size() == 1
                && request.getParameterMap().containsKey("email")) {
            if (users.validate(request.getParameter("email"))) {
                response.sendRedirect("/");

            }
        }
        System.out.println("--post");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("index.html");
        if (request.getParameterMap().keySet().size() > 0) {
            if (request.getParameterMap().containsKey("email")) {
                view.forward(request, response);
            }
            else {

            }
        }
        System.out.println("--get");
        //view.forward(request, response);
    }
}
