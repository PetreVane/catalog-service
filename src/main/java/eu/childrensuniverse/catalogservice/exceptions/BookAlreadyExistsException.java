package eu.childrensuniverse.catalogservice.exceptions;

public class BookAlreadyExistsException extends RuntimeException {

    public BookAlreadyExistsException(String isbn) {
        super("Book with ISBN " + isbn + " already exists");
    }
}
