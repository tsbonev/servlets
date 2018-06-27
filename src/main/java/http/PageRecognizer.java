package http;

import org.eclipse.jetty.util.IO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/recognizer")
public class PageRecognizer extends HttpServlet {

    String pageName;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        pageName = "itself";

        req.setAttribute("name", pageName);

        RequestDispatcher rd = req.getRequestDispatcher("recognizer/recognizePage.jsp");
        rd.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        pageName = req.getParameter("name");

        req.setAttribute("name", pageName);

        RequestDispatcher rd = req.getRequestDispatcher("recognizer/recognizePage.jsp");
        rd.forward(req, resp);

    }

}
