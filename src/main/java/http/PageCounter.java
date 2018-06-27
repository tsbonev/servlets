package http;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/counter")
public class PageCounter extends HttpServlet {

    int firstCounter;
    int secondCounter;

    @Override
    public void init() throws ServletException{
        firstCounter = 0;
        secondCounter = 0;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{


        String param = req.getParameter("link");
        int link = 0;
        if(param != null){

            link = Integer.parseInt(param);
        }

        if(link == 1) req.setAttribute("linkOneCount", firstCounter++);
        else if(link == 2) req.setAttribute("linkTwoCount", secondCounter++);

        RequestDispatcher rd = req.getRequestDispatcher("counter.jsp");
        rd.forward(req, resp);

    }


}
