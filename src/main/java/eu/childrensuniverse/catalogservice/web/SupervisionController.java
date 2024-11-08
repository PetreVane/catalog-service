package eu.childrensuniverse.catalogservice.web;


import eu.childrensuniverse.catalogservice.config.CatalogServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class SupervisionController {

    private final CatalogServiceProperties serviceProperties;

    public SupervisionController(CatalogServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    @GetMapping
    ResponseEntity<String> getStatusMessage()
    {
        return ResponseEntity.ok(serviceProperties.getWelcomeMessage());
    }

}
