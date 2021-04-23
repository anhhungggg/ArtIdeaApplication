package com.oatfleik.artApplication.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.art-application")
public class ArtApplicationConfig {
    private String searchUrl;
    private String objectUrl;
}
