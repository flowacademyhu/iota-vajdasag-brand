package hu.flowacademy.vajdasagbrand.configuration;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@RequiredArgsConstructor
@Import(KeycloakPropertiesHolder.class)
public class KeycloakConfiguration {

    private final KeycloakPropertiesHolder keycloakPropertiesHolder;

    private static final String KEYCLOAK_MASTER_REALM = "master";
    private static final String KEYCLOAK_CLIENT_ID = "admin-cli";

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder
                .builder()
                .serverUrl(keycloakPropertiesHolder.getKeycloakBackendClientServerUrl())
                .realm(KEYCLOAK_MASTER_REALM)
                .username(keycloakPropertiesHolder.getKeycloakBackendClientAdminUserName())
                .password(keycloakPropertiesHolder.getKeycloakBackendClientAdminPassword())
                .clientId(KEYCLOAK_CLIENT_ID)
                .build();
    }
}
