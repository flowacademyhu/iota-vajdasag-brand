package hu.flowacademy.vajdasagbrand.service;

import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.keycloak.admin.client.Keycloak;

@Service
public class KeycloakClientService {

    @Value("${keycloakBackendClient.serverUrl}")
    private String keycloakBackendClientServerUrl;
    @Value("${keycloakBackendClient.realm}")
    private String keycloakBackendClientRealm;
    @Value("${keycloakBackendClient.realm2}")
    private String keycloakBackendClientRealm2;
    @Value("${keycloakBackendClient.realm_master}")
    private String keycloakBackendClientRealmMaster;
    @Value("${keycloakBackendClient.adminusername}")
    private String keycloakBackendClientRealmAdminUserName;
    @Value("${keycloakBackendClient.adminpassword}")
    private String keycloakBackendClientRealmAdminPassword;
    @Value("${keycloakBackendClient.clientpassword}")
    private String keycloakBackendClientRealmClientPassword;
    @Value("${keycloakBackendClient.client-id}")
    private String keycloakBackendClientRealmClientId;
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


    public AccessTokenResponse login(String email, String password) {
        return Keycloak.getInstance(
                keycloakServerUrl,
                keycloakRealm,
                email, password,
                keycloakResource,
                keycloakCredentialsSecret)
                .tokenManager()
                .getAccessToken();
    }

    public void deleteUser(String username) {
        Keycloak keycloak = Keycloak.getInstance(keycloakBackendClientServerUrl,
                keycloakBackendClientRealmMaster,
                keycloakBackendClientRealmAdminUserName,
                keycloakBackendClientRealmAdminPassword,
                keycloakBackendClientRealmClientId);
        UserRepresentation userdeleted = keycloak.realm(keycloakBackendClientRealm2).users().search(username).get(0);
        userdeleted.setEnabled(false);
        keycloak.realm(keycloakBackendClientRealm2).users().get(userdeleted.getId()).update(userdeleted);
    }
}