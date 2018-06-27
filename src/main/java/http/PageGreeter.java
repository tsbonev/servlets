package http;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

@WebServlet("/greeter")
public class PageGreeter extends HttpServlet {

    String pageName;
    boolean[] hasBeenGreeted = new boolean[3];

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher rd = req.getRequestDispatcher("greeter/pageGreeter.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        pageName = req.getParameter("page");

        switch (pageName){
            case "one":
                if(!hasBeenGreeted[0])
                req.setAttribute("greeting", "Hello, page one");
                hasBeenGreeted[0] = true;
                break;
            case "two":
                if(!hasBeenGreeted[1])
                req.setAttribute("greeting", "Hello, page two");
                hasBeenGreeted[1] = true;
                break;
            case "three":
                if(!hasBeenGreeted[2])
                req.setAttribute("greeting", "Hello, page three");
                hasBeenGreeted[2] = true;
                break;
            default:
                break;
        }

        String pageLink = "greeter/page" + StringUtils.capitalize(pageName) + ".jsp";

        RequestDispatcher rd = req.getRequestDispatcher(pageLink);
        rd.forward(req, resp);

    }
}
