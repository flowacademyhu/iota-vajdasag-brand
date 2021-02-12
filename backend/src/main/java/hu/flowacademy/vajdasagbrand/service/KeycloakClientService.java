package hu.flowacademy.vajdasagbrand.service;

import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.keycloak.admin.client.Keycloak;

@Service
public class KeycloakClientService {

    @Value("${keycloakBackendClient.serverUrl}")
    private String serverurl;
    @Value("${keycloakBackendClient.realm}")
    private String realm;
    @Value("${keycloakBackendClient.adminusername}")
    private String adminusername;
    @Value("${keycloakBackendClient.clientpassword}")
    private String clientpassword;
    @Value("${keycloakBackendClient.clientId}")
    private String clientId;
    @Value("${keycloak.credentials.secret}")
    private String clientsecret;

    public AccessTokenResponse login(String email, String password) {
        return Keycloak.getInstance(
                serverurl,
                realm,
                email, password,
                clientId,
                clientsecret)
                .tokenManager()
                .getAccessToken();
    }
}
