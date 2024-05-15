package eu.childrensuniverse.catalogservice;

import eu.childrensuniverse.catalogservice.data.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests
{

	@Autowired
	WebTestClient webTestClient;


	@Test
	void testUpdateCatalog()
	{
		Book expectedBook = new Book("1234567890", "Title", "Author", 9.90);

		webTestClient
				.post()
				.uri("/books")
				.bodyValue(expectedBook)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class)
					.value(book -> assertEquals(expectedBook.isbn(), book.isbn()));
	}

}
