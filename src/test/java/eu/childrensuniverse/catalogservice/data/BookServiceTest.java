package eu.childrensuniverse.catalogservice.data;

import eu.childrensuniverse.catalogservice.exceptions.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceTest
{

    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setUp()
    {
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void getAllBooks_ReturnsAllBooks()
    {
        var books = Arrays.asList(Book.of("123", "Test Book 1", "Author 1", 9.99, "Unknown publisher"),
                 Book.of("456", "Test Book 2", "Author 2", 19.99, "Unknown publisher"));
        when(bookRepository.findAll()).thenReturn(books);

        var result = bookService.getAllBooks();

        assertEquals(books, result);
    }

    @Test
    void getBookByIsbn_ExistingIsbn_ReturnsBook() throws BookNotFoundException
    {
        var book = Book.of("123", "Test Book", "Author", 9.99, "Unknown publisher");
        when(bookRepository.findByIsbn("123")).thenReturn(Optional.of(book));

        var result = bookService.getBookByIsbn("123");

        assertEquals(book, result);
    }

    @Test
    void getBookByIsbn_NonExistingIsbn_ThrowsException()
    {
        when(bookRepository.findByIsbn("non-existing")).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getBookByIsbn("non-existing"));
    }

    @Test
    void saveBook_SavesAndReturnsBook()
    {
        var book =  Book.of("123", "Test Book", "Author", 9.99, "Unknown publisher");
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        var result = bookService.saveBook(book);

        assertEquals(book, result);
    }

    @Test
    void deleteBook_DeletesBookByIsbn()
    {
        doNothing().when(bookRepository).deleteByIsbn("123");

        bookService.deleteBook("123");

        verify(bookRepository, times(1)).deleteByIsbn("123");
    }

    @Test
    void updateBook_ExistingIsbn_UpdatesBook()
    {
        var existingBook = Book.of("123", "Old Title", "Old Author", 8.99, "Unknown publisher");
        var updatedBook = Book.of("123", "New Title", "New Author", 10.99, "Unknown publisher");
        when(bookRepository.findByIsbn("123")).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        var result = bookService.updateBook("123", Book.of("123", "New Title", "New Author", 10.99, "Unknown publisher"));

        assertEquals(updatedBook, result);
    }

}
