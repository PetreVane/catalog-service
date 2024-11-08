package eu.childrensuniverse.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// adding the ConfigurationProperties will automatically listen for RefreshScopeRefreshedEvent -> actuator @RefreshScope
@ConfigurationProperties(prefix = "catalog-service")
public class CatalogServiceProperties {

    private String welcomeMessage;

    public CatalogServiceProperties(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }
}
