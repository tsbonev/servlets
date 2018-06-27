package http;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageGreeterTest {


    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Mock
    private HttpServletResponse resp;

    @Mock
    private RequestDispatcher rd;

    @Mock
    private HttpServletRequest req;


    PageGreeter pageGreeter;


    @Before
    public void setUp(){

        pageGreeter = new PageGreeter();

    }

    @Test
    public void greetPageOnlyOnce() throws ServletException, IOException {

        context.checking(new Expectations(){{

            oneOf(req).getParameter("page"); will(returnValue("one"));
            oneOf(req).setAttribute("greeting", "Hello, page one");
            oneOf(req).getRequestDispatcher("greeter/pageOne.jsp"); will(returnValue(rd));
            oneOf(rd).forward(req, resp);

            oneOf(req).getParameter("page"); will(returnValue("one"));
            never(req).setAttribute("greeting", "Hello, page one");
            oneOf(req).getRequestDispatcher("greeter/pageOne.jsp"); will(returnValue(rd));
            oneOf(rd).forward(req, resp);

        }});

        pageGreeter.doPost(req, resp);
        pageGreeter.doPost(req, resp);

    }

}
