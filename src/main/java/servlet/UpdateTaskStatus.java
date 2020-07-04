package servlet;

import manager.TaskManager;
import model.TaskStatus;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "/updateTaskStatus")
public class UpdateTaskStatus extends HttpServlet {

    TaskManager taskManager = new TaskManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int taskId = Integer.parseInt(req.getParameter("taskId"));
        String taskStatus = req.getParameter("status");
        taskManager.update(taskId, TaskStatus.valueOf(taskStatus));
        if (user.getUserType()== UserType.MANAGER) {
            resp.sendRedirect("/manager");
        }else{
            resp.sendRedirect("/userHome");
        }
    }
}
