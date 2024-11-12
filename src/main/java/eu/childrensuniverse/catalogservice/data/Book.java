package eu.childrensuniverse.catalogservice.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;


public record Book(

        @Id
        Long id,

        @NotBlank(message = "The book identifier (isbn) has do be defined")
        @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "The ISBN format must be valid.")
        String isbn,

        @NotBlank(message = "The book title has to be defined")
        String title,

        @NotBlank(message = "The book author has to be defined")
        String author,

        @NotNull(message = "The price has to be defined")
        Double price,

        String publisher,

        @CreatedDate
        Instant createdDate,

        @LastModifiedDate
        Instant lastModifiedDate,

        @Version
        int version
)
{
        public static Book of(
                String isbn,
                String title,
                String author,
                Double price,
                String publisher)
        {
                return new Book(null, isbn, title, author, price, publisher, null, null, 0);
        }
}
