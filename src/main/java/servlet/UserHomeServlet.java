package servlet;

import manager.TaskManager;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/userHome")
public class UserHomeServlet extends HttpServlet {

    private TaskManager taskManager = new TaskManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if ( user.getUserType()!= UserType.USER){
            resp.sendRedirect("/");
        }else {
            req.setAttribute("todos", taskManager.getALLTaskByUser(user.getId()));
            req.getRequestDispatcher("/WEB-INF/userTask.jsp").forward(req, resp);
        }
    }
}
