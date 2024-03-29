package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/userDetail")


public class UserDetailServlet extends HttpServlet {
    private UserManager userManager = new UserManager();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int id = Integer.parseInt(req.getParameter("user_id"));
        User userbyId = userManager.getById(id);

        req.setAttribute("userDetail", userbyId);
        req.getRequestDispatcher("/WEB-INF/userDetail.jsp").forward(req, resp);
    }
}

