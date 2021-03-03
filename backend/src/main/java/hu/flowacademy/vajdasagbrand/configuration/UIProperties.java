package hu.flowacademy.vajdasagbrand.configuration;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "ui")
@Data
public class UIProperties {
    String loginUrl;
}
