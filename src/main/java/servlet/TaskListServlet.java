package servlet;

import manager.TaskManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/taskList")
public class TaskListServlet extends HttpServlet {

    TaskManager taskManager=new TaskManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tasks", taskManager.getALL());
        req.getRequestDispatcher("/WEB-INF/tasklist.jsp").forward(req,resp);
    }
}
