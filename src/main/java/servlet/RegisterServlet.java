package servlet;


import manager.UserManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")

public class RegisterServlet extends HttpServlet {

    UserManager userManager = new UserManager();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("username");
        String password = req.getParameter("password");

        StringBuilder msg = new StringBuilder();
        if (name == null || name.length() == 0) {
            msg.append("Name field is requared!!!<br>");
        }
        if (surname == null || surname.length() == 0) {
            msg.append("Surname field is requared!!!<br>");
        }
        if (email == null || email.length() == 0) {
            msg.append("Email field is requared!!!<br>");
        } else if (userManager.getByEmail(email) != null) {
            msg.append("Email already exist!!!<br>");
        }
        if (password == null || password.length() == 0) {
            msg.append("Password field is requared!!!<br>");
        }
        if (msg.toString().equals("")) {

            User user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .userType(UserType.USER)
                    .build();

          userManager.register(user);
            msg.append("<span style=\"color: green\">User register Succesfully. Please login</span>");
        }
        req.getSession().setAttribute("msg", msg.toString());
        resp.sendRedirect("/");
    }

}
