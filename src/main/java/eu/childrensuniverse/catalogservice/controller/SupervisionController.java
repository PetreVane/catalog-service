package eu.childrensuniverse.catalogservice.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class SupervisionController {


    @GetMapping
    ResponseEntity<String> getStatusMessage()
    {
        return ResponseEntity.ok("Response status 200");
    }

}
