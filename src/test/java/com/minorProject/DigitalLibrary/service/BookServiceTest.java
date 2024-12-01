package com.minorProject.DigitalLibrary.service;

import com.minorProject.DigitalLibrary.model.Author;
import com.minorProject.DigitalLibrary.model.Book;
import com.minorProject.DigitalLibrary.repository.*;
import com.minorProject.DigitalLibrary.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBook() {
        createBookRequest mockCreateBookRequest = createBookRequest.builder()
                .bookName("Test")
                .bookNo("123")
                .bookType("PHYSICS")
                .pages(100)
                .cost(500)
                .authorEmail("a@email.com")
                .authorName("abc")
                .producer("abcd")
                .build();

        Author mockAuthor = Author.builder()
                .id(1)
                .authorName("abc")
                .email("a@email.com")
                .build();

        when(authorRepository.findByEmail(mockCreateBookRequest.getAuthorEmail()))
                .thenReturn(mockAuthor);
        Book mockBook = mockCreateBookRequest.toBook();

        mockBook.setAuthor(mockAuthor);

        when(bookRepository.save(any(Book.class))).thenReturn(mockBook);

        //Execute Method
        Book createdBook = bookService.createBook(mockCreateBookRequest);

        //Assertion
        assertNotNull(createdBook);
        assertEquals(mockCreateBookRequest.getBookName(), createdBook.getBookName());
        assertEquals(mockAuthor, createdBook.getAuthor());

        //Verify Interaction
        verify(authorRepository, times(1))
                .findByEmail(eq(mockCreateBookRequest.getAuthorEmail()));

        verify(bookRepository, times(1)).save(any(Book.class));


    }

    @Test
    void testGetBook() {
        // Arrange
        int bookId = 1;
        Book book = new Book();
        book.setId(bookId);
        book.setBookName("Test Book");
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        // Act
        Book result = bookService.getBook(bookId);

        // Assert
        assertEquals(book, result);
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    void testGetAllBooks() {
        // Arrange
        Book book1 = new Book();
        book1.setId(1);
        book1.setBookName("Test Book 1");
        Book book2 = new Book();
        book2.setId(2);
        book2.setBookName("Test Book 2");
        List<Book> mockBooks = Arrays.asList(book1, book2);
        when(bookRepository.findAll()).thenReturn(mockBooks);

        // Act
        List<Book> result = bookService.getAllBooks();
        // Assert
        assertEquals(mockBooks.size(), result.size());
        assertEquals(mockBooks, result);
        verify(bookRepository, times(1)).findAll();

    }
}
