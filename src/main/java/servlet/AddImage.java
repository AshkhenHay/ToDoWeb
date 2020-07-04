package servlet;

import manager.TaskManager;
import manager.UserManager;
import model.TaskStatus;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/addImage")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AddImage extends HttpServlet {
    private final String UPLOAD_DIR="C:\\Users\\user\\IntelijWeb\\upload";
    UserManager userManager=new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        for (Part part : req.getParts()) {
            if (getFileName(part)!=null) {
                String fileName = System.currentTimeMillis() + getFileName(part);
                String fullFileName = UPLOAD_DIR + File.separator + fileName;
                part.write(fullFileName);
              userManager.updateImage(fileName,user.getId());
            }
        }

        if (user.getUserType()== UserType.MANAGER) {
            resp.sendRedirect("/manager");
        }else{
            resp.sendRedirect("/userHome");
        }
    }
    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return null;
    }

}
