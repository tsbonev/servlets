package http;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/counter")
public class LinkCounter extends HttpServlet {

    int firstCounter = 0;
    int secondCounter = 0;
    int thirdCounter = 0;

    @Override
    public void init() throws ServletException{
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{


        String param = req.getParameter("link");
        int link = 0;
        if(param != null){

            link = Integer.parseInt(param);
        }

        if(link == 1) firstCounter++;
        if(link == 2) secondCounter++;
        if(link == 3) thirdCounter++;


        req.setAttribute("linkOneCount", firstCounter);
        req.setAttribute("linkTwoCount", secondCounter);
        req.setAttribute("linkThreeCount", thirdCounter);

        RequestDispatcher rd = req.getRequestDispatcher("counter.jsp");
        rd.forward(req, resp);

    }


}
