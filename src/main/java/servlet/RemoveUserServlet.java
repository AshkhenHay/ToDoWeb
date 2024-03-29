package servlet;

import manager.UserManager;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/removeUser")
public class RemoveUserServlet extends HttpServlet {

    UserManager userManager=new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String user=req.getParameter("user_id");


        int userId=Integer.parseInt(user);
        userManager.removeUserById(userId);
       resp.sendRedirect("/manager");

    }
}
