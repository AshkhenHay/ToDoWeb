package servlet;

import manager.TaskManager;
import manager.UserManager;
import model.Task;

import model.TaskStatus;
import model.User;
import util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(urlPatterns = "/addTask")
public class AddTaskServlet extends HttpServlet {
    private TaskManager taskManager = new TaskManager();
    private UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String deadline = req.getParameter("deadline");
        String status = req.getParameter("status");
        long userId = Long.parseLong(req.getParameter("user_id"));
        User user = userManager.getById(userId);



        Task task = Task.builder()
                .title(title)
                .description(description)
                .deadLine(DateUtil.convertStringToDeadline(deadline))
                .status(TaskStatus.valueOf(status))
                .user(user)
                .build();
        taskManager.create(task);
        resp.getWriter().write("Task was added succesfully");



    }
}
