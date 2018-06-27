package server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

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
        ServletContextHandler servletContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContext.setContextPath("/");

        servletContext.addEventListener(new ServletContextListener() {

            public void contextInitialized(ServletContextEvent servletContextEvent) {
                ServletContext servletContext = servletContextEvent.getServletContext();

                servletContext.addServlet("homeServlet", new HttpServlet() {
                    @Override
                    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                        PrintWriter writer = resp.getWriter();
                        writer.println("Hello!");
                        writer.flush();
                    }
                }).addMapping("/");
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
