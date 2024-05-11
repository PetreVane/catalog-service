package eu.childrensuniverse.catalogservice.persistence;

import eu.childrensuniverse.catalogservice.data.Book;
import eu.childrensuniverse.catalogservice.data.BookRepository;
import eu.childrensuniverse.catalogservice.exceptions.BookAlreadyExistsException;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BookInMemoryDB implements BookRepository
{

    private static final Map<String, Book> books = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll()
    {
        return books.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn)
    {
        return existsByIsbn(isbn) ? Optional.of(books.get(isbn)) : Optional.empty();
    }

    @Override
    public boolean existsByIsbn(String isbn)
    {
        return books.containsKey(isbn);
    }

    @Override
    public Book save(Book book) throws BookAlreadyExistsException
    {

        if (existsByIsbn(book.isbn())){
            throw new BookAlreadyExistsException("Book with ISBN " + book.isbn() + " already exists");
        }
        books.put(book.isbn(), book);
        return book;
    }

    @Override
    public void deleteByIsbn(String isbn)
    {
        if (existsByIsbn(isbn)) {
            books.remove(isbn);
        }
    }
}
