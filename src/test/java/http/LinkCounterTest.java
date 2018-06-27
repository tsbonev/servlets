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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class LinkCounterTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Mock
    private HttpServletResponse resp;

    @Mock
    private RequestDispatcher rd;

    @Mock
    private HttpServletRequest req;


    LinkCounter linkCounter;


    @Before
    public void setUp(){

        linkCounter = new LinkCounter();

    }

    @Test
    public void linkCounterCounts() throws ServletException, IOException {

        context.checking(new Expectations(){{

            oneOf(req).getParameter("link"); will(returnValue("2"));
            oneOf(req).setAttribute("linkOneCount", 0);
            oneOf(req).setAttribute("linkTwoCount", 1);
            oneOf(req).setAttribute("linkThreeCount", 0);
            oneOf(req).getRequestDispatcher("counter.jsp"); will(returnValue(rd));
            oneOf(rd).forward(req, resp);

        }});

        linkCounter.doGet(req, resp);

    }

}
