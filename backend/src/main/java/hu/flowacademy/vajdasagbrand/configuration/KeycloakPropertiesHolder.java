package hu.flowacademy.vajdasagbrand.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class KeycloakPropertiesHolder {

    @Value("${keycloakBackendClient.serverUrl}")
    private String keycloakBackendClientServerUrl;
    @Value("${keycloakBackendClient.realm}")
    private String keycloakBackendClientRealm;
    @Value("${keycloakBackendClient.realm2}")
    private String keycloakBackendClientRealm2;
    @Value("${keycloakBackendClient.realm_master}")
    private String keycloakBackendClientRealmMaster;
    @Value("${keycloakBackendClient.adminusername}")
    private String keycloakBackendClientAdminUserName;
    @Value("${keycloakBackendClient.adminpassword}")
    private String keycloakBackendClientAdminPassword;
    @Value("${keycloakBackendClient.clientpassword}")
    private String keycloakBackendClientClientPassword;
    @Value("${keycloakBackendClient.client-id}")
    private String keycloakBackendClientClientId;
    @Value("${keycloakBackendClient.user-role}")
    private String keycloakBackendClientUserRole;

    @Value("${keycloak.realm}")
    private String keycloakRealm;
    @Value("${keycloak.auth-server-url}")
    private String keycloakServerUrl;
    @Value("${keycloak.resource}")
    private String keycloakResource;
    @Value("${keycloak.credentials.secret}")
    private String keycloakCredentialsSecret;

}
