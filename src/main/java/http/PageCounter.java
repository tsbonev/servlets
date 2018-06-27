package http;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Counter")
public class PageCounter extends HttpServlet {

    String message;

    @Override
    public void init() throws ServletException{
        message = "Hello, world!";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html><body><h1>" + message + "</h1></body></html>");
        out.flush();

    }


}
