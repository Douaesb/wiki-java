import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.wiki.wikijava.service.impl.AuthorServiceImpl;
import org.wiki.wikijava.servlet.AuthorServlet.DeleteAuthorServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class DeleteAuthorServletTest {

    @InjectMocks
    private DeleteAuthorServlet deleteAuthorServlet;

    @Mock
    private AuthorServiceImpl authorService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoPost_SuccessfulDeletion() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1");
        when(authorService.deleteAuthor(1L)).thenReturn(true);

        deleteAuthorServlet.doPost(request, response);

        verify(authorService, times(1)).deleteAuthor(1L);
        verify(response).sendRedirect("authors?message=Author deleted successfully");
    }

    @Test
    public void testDoPost_FailedDeletion() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1");
        when(authorService.deleteAuthor(1L)).thenReturn(false);

        deleteAuthorServlet.doPost(request, response);

        verify(authorService, times(1)).deleteAuthor(1L);
        verify(response).sendRedirect("authors?error=Failed to delete author");
    }

    @Test
    public void testDoPost_InvalidAuthorId() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("invalid");

        deleteAuthorServlet.doPost(request, response);

        verify(authorService, never()).deleteAuthor(anyLong());
        verify(response).sendRedirect("authors?error=Invalid author ID");
    }

    @Test
    public void testDoPost_MissingAuthorId() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn(null);

        deleteAuthorServlet.doPost(request, response);

        verify(authorService, never()).deleteAuthor(anyLong());
        verify(response).sendRedirect("authors?error=Author ID is required");
    }
}
