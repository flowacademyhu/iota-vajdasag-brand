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

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder
                .builder()
                .serverUrl(keycloakPropertiesHolder.getKeycloakBackendClientServerUrl())
                .realm(keycloakPropertiesHolder.getKeycloakBackendClientRealmMaster())
                .username(keycloakPropertiesHolder.getKeycloakBackendClientAdminUserName())
                .password(keycloakPropertiesHolder.getKeycloakBackendClientAdminPassword())
                .clientId(keycloakPropertiesHolder.getKeycloakBackendClientClientId())
                .build();
    }
}
