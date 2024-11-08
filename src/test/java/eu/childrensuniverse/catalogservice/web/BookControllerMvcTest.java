package eu.childrensuniverse.catalogservice.web;

import eu.childrensuniverse.catalogservice.config.CatalogServiceProperties;
import eu.childrensuniverse.catalogservice.data.BookService;
import eu.childrensuniverse.catalogservice.exceptions.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static reactor.core.publisher.Mono.when;

@WebMvcTest
class BookControllerMvcTest
{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private CatalogServiceProperties catalogServiceProperties;

    @Test
    void getByIsbn() throws Exception
    {
        String isbn = "12345";
        doThrow(BookNotFoundException.class).when(bookService).getBookByIsbn(anyString());
        mockMvc.perform(get("/books/" + isbn))
                .andExpect(status().isNotFound());
    }
}