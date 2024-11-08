package eu.childrensuniverse.catalogservice.demo;


import eu.childrensuniverse.catalogservice.data.Book;
import eu.childrensuniverse.catalogservice.data.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testData")
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Loads test data when the application has completed the starting process
    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        Book testBook = new Book("12345678910", "Test book", "John Doe", 52.6);
        Book testBook2 = new Book("1234567891234", "Test book2", "Martha Steward", 12.6);
        bookRepository.save(testBook);
        bookRepository.save(testBook2);
    }
}
