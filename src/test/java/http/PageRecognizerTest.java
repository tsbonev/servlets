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

public class PageRecognizerTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Mock
    HttpServletRequest req;

    @Mock
    HttpServletResponse resp;

    @Mock
    RequestDispatcher rd;

    PageRecognizer pageRecognizer;

    @Before
    public void setUp(){
        pageRecognizer = new PageRecognizer();
    }

    @Test
    public void recognizePage() throws ServletException, IOException {

        final String pageName = "page one";

        context.checking(new Expectations(){{

            oneOf(req).getParameter("name"); will(returnValue(pageName));
            oneOf(req).setAttribute("name", pageName);
            oneOf(req).getRequestDispatcher("recognizer/recognizePage.jsp"); will(returnValue(rd));
            oneOf(rd).forward(req, resp);

        }});

        pageRecognizer.doPost(req, resp);

    }

}
