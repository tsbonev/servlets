package server;

import http.PageCounter;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public final class Jetty {
    private final Server server;

    public Jetty(int port) {
        this.server = new Server(port);
    }

    public void start() {
        WebAppContext servletContext = new WebAppContext();
        servletContext.setResourceBase("web/WEB-INF");
        servletContext.setContextPath("/");

        org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);
        classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration", "org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration");
        classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.annotations.AnnotationConfiguration");

        servletContext.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",".*/[^/]*jstl.*\\.jar$");

        servletContext.addEventListener(new ServletContextListener() {

            public void contextInitialized(ServletContextEvent servletContextEvent) {
                ServletContext servletContext = servletContextEvent.getServletContext();

                servletContext.addServlet("counter", new PageCounter()).addMapping("/counter");
            }

            public void contextDestroyed(ServletContextEvent servletContextEvent) {

            }
        });

        ContextHandler staticResourceHandler = new ContextHandler();
        staticResourceHandler.setContextPath("/assets");
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("static");

        staticResourceHandler.setHandler(resourceHandler);

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{staticResourceHandler, servletContext});

        server.setHandler(handlers);
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
