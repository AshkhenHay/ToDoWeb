package servlet;

import manager.UserManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    UserManager userManager = new UserManager();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("username");
        String password = req.getParameter("password");
        StringBuilder msg = new StringBuilder();

        if (email == null || email.length() == 0) {
            msg.append("Email field is requared!!!<br>");
        }
        if (password == null || password.length() == 0) {
            msg.append("Password field is requared!!!<br>");
        }
        if (msg.toString().equals("")) {

            User user = userManager.getByEmailAndPassword(email, password);
            if (user != null) {
                req.getSession().setAttribute("user", user);

                if (user.getUserType() == UserType.USER) {
                   resp.sendRedirect("/userHome");
                } else {
                    resp.sendRedirect("/manager");
                }
            } else {
                msg.append("User does not exist");
                req.getSession().setAttribute("msg", msg.toString());
                resp.sendRedirect("/");
            }
        }

    }
}
