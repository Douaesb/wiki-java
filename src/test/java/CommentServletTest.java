import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.wiki.wikijava.entity.Article;
import org.wiki.wikijava.entity.Comment;
import org.wiki.wikijava.entity.Contributor;
import org.wiki.wikijava.entity.enums.StatusComment;
import org.wiki.wikijava.service.CommentService;
import org.wiki.wikijava.servlet.CommentServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CommentServletTest {

    @Mock
    private CommentService commentService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private CommentServlet commentServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListComments() throws ServletException, IOException {
        // Arrange
        List<Comment> mockComments = new ArrayList<>();
        when(commentService.getAllComments()).thenReturn(mockComments);
        when(request.getRequestDispatcher("/WEB-INF/views/comment/show.jsp")).thenReturn(requestDispatcher);

        commentServlet.doGet(request, response);

        verify(commentService).getAllComments();
        verify(request).setAttribute("comments", mockComments);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testCreateComment() throws Exception {
        when(request.getParameter("action")).thenReturn("create");
        when(request.getParameter("content")).thenReturn("Test Comment");
        when(request.getParameter("articleId")).thenReturn("1");
        when(request.getParameter("contributorId")).thenReturn("2");

        commentServlet.doPost(request, response);

        verify(commentService).saveComment(any(Comment.class));
        verify(response).sendRedirect(anyString());
    }

    @Test
    void testUpdateComment() throws Exception {
        when(request.getParameter("action")).thenReturn("update");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("content")).thenReturn("Updated Comment");
        when(request.getParameter("articleId")).thenReturn("1");
        when(request.getParameter("contributorId")).thenReturn("2");

        Comment mockComment = new Comment();
        when(commentService.findCommentById(1L)).thenReturn(mockComment);

        commentServlet.doPost(request, response);

        verify(commentService).updateComment(any(Comment.class));
        verify(response).sendRedirect(anyString());
    }

    @Test
    void testDeleteComment() throws Exception {
        when(request.getParameter("action")).thenReturn("delete");
        when(request.getParameter("commentId")).thenReturn("1");
        when(request.getParameter("articleId")).thenReturn("1");
        when(request.getParameter("contributorId")).thenReturn("2");


        commentServlet.doPost(request, response);

        verify(commentService).deleteComment(1L);
        verify(response).sendRedirect(anyString());
    }

    @Test
    void testUnknownAction() throws Exception {
        when(request.getParameter("action")).thenReturn("unknown");
        when(request.getRequestDispatcher("/WEB-INF/views/comment/show.jsp")).thenReturn(requestDispatcher);


        commentServlet.doPost(request, response);


        verify(commentService).getAllComments();
        verify(requestDispatcher).forward(request, response);
    }
}
