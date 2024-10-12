import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.wiki.wikijava.entity.Author;
import org.wiki.wikijava.entity.Contributor;
import org.wiki.wikijava.entity.Editor;
import org.wiki.wikijava.service.impl.AuthorServiceImpl;
import org.wiki.wikijava.servlet.AuthorServlet.AuthorListServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class AuthorListServletTest {

    @InjectMocks
    private AuthorListServlet authorListServlet;

    @Mock
    private AuthorServiceImpl authorService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoGet() throws ServletException, IOException {

        int page = 1;
        int recordsPerPage = 2;

        when(request.getParameter("page")).thenReturn("1");

        Contributor contributor = new Contributor();
        contributor.setFirstName("Author1");

        Editor editor = new Editor();
        editor.setFirstName("Author2");

        List<Author> authors = Arrays.asList(contributor, editor);
        when(authorService.getAuthors(page, recordsPerPage)).thenReturn(authors);
        when(authorService.getNoOfPages(recordsPerPage)).thenReturn(5);

        when(request.getRequestDispatcher("WEB-INF/views/author/index.jsp")).thenReturn(requestDispatcher);

        authorListServlet.doGet(request, response);

        verify(request).setAttribute("authors", authors);
        verify(request).setAttribute("noOfPages", 5);
        verify(request).setAttribute("currentPage", page);
        verify(requestDispatcher).forward(request, response);
    }
}
