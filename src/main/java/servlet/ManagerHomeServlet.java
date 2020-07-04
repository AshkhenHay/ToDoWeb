package servlet;

import manager.TaskManager;
import manager.UserManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "/manager")
public class ManagerHomeServlet extends HttpServlet {

    private UserManager userManager=new UserManager();
    private TaskManager taskManager =new TaskManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if ( user.getUserType()!= UserType.MANAGER){
            resp.sendRedirect("/");
        }else{
            req.setAttribute("users",userManager.getallUsers());

            req.getRequestDispatcher("/WEB-INF/manager.jsp").forward(req, resp);
        }
    }
}
