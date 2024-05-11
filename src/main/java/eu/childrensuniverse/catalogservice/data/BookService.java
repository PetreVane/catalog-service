package eu.childrensuniverse.catalogservice.data;

import eu.childrensuniverse.catalogservice.exceptions.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService
{

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> getAllBooks()
    {
        return bookRepository.findAll();
    }

    public Book getBookByIsbn(String isbn) throws BookNotFoundException
    {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book saveBook(Book book)
    {
        return bookRepository.save(book);
    }

    public void deleteBook(String isbn)
    {
         bookRepository.deleteByIsbn(isbn);
    }

    public Book updateBook(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
                .map(existingBook -> {
                    var bookToUpdate = new Book(
                            existingBook.isbn(),
                            book.title(),
                            book.author(),
                            book.price());
                    return bookRepository.save(bookToUpdate);
                })
                .orElseGet(() -> saveBook(book));
    }

}
