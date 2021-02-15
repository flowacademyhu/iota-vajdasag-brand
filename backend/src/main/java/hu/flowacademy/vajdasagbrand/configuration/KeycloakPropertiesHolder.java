package hu.flowacademy.vajdasagbrand.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class KeycloakPropertiesHolder {

    @Value("${keycloakBackendClient.realm_master}")
    private String realm;
    @Value("${keycloakBackendClient.realm2}")
    private String realm2;
    @Value("${keycloak.auth-server-url}")
    private String serverurl;
    @Value("${keycloakBackendClient.adminusername}")
    private String adminusername;
    @Value("${keycloakBackendClient.adminpassword}")
    private String clientpassword;
    @Value("${keycloakBackendClient.client-id}")
    private String clientId;
    @Value("${keycloak.credentials.secret}")
    private String clientsecret;
    @Value("${keycloakBackendClient.user-role}")
    private String userRole;
}
