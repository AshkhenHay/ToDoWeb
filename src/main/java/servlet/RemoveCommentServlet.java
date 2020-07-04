package servlet;

import manager.CommentManager;
import model.Comment;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/removeComment")
public class RemoveCommentServlet extends HttpServlet {
    CommentManager commentManager=new CommentManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String comment = req.getParameter("id");
      int commentId = Integer.parseInt(comment);
        commentManager.delete(commentId);
      resp.sendRedirect("/taskDetail");
    }
}
