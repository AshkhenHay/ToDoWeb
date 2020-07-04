package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = {"/userHome", "/addTask","/userDetail","/manager","/taskList","/addComment"})
public class AuthorizationFilter  implements Filter {



    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        User user = (User) req.getSession().getAttribute("user");
        if (user==null){
            ( (HttpServletResponse)servletResponse).sendRedirect("/");
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }


    }


}

