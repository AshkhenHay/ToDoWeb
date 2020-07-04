package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Server was started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Server was stopped");
    }
}
