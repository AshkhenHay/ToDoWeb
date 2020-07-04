package servlet;

import manager.CommentManager;
import manager.TaskManager;
import model.Comment;
import model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/taskDetail")
public class TaskDetailServlet extends HttpServlet {
    TaskManager taskManager = new TaskManager();
    CommentManager commentManager=new CommentManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       long id = Long.parseLong(req.getParameter("task_id"));
        Task tasks = taskManager.getById(id);
        List<Comment> comment=commentManager.getALLCommentByTask(id);

        req.setAttribute("taskDetail", tasks);
        req.setAttribute("comments", comment);
        req.getRequestDispatcher("/WEB-INF/taskDetail.jsp").forward(req, resp);
    }
}
