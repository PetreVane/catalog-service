package eu.childrensuniverse.catalogservice.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public record Book(

        @NotBlank(message = "The book identifier (isbn) has do be defined")
        @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "The ISBN format must be valid.")
        String isbn,

        @NotBlank(message = "The book title has to be defined")
        String title,

        @NotBlank(message = "The book author has to be defined")
        String author,

        @NotNull(message = "The price has to be defined")
        Double price
)
{}
