package servlet;

import manager.CommentManager;
import manager.TaskManager;
import manager.UserManager;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Date;


@WebServlet(urlPatterns = "/addComment")
public class AddCommentServlet  extends HttpServlet {

    UserManager userManager=new UserManager();
    TaskManager taskManager=new TaskManager();
    CommentManager commentManager=new CommentManager();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String comment = req.getParameter("comment");

      int taskId = Integer.parseInt(req.getParameter("taskId"));

        Task task=taskManager.getById(taskId);

        Comment comment1 = Comment.builder()
                .comment(comment)
                .date(new Date())
                .task(task)
                .user(user)
                .build();
        commentManager.create(comment1);
        resp.sendRedirect("/addComment");
    }
}
