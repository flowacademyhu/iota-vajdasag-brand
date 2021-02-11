package hu.flowacademy.vajdasagbrand.service;

import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.UserRepresentation;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Arrays;
import java.util.Locale;

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

    private Keycloak getInstance() {
        return KeycloakBuilder
                .builder()
                .serverUrl(serverurl)
                .realm(realm)
                .username(adminusername)
                .password(clientpassword)
                .clientId(clientId)
                .build();
    }

    public int createAccount(String email) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(generatePassword(50));
        credential.setTemporary(true);

        UserRepresentation user = new UserRepresentation();
        user.setUsername(email.toLowerCase(Locale.ROOT));
        user.setCredentials(Arrays.asList(credential));

        RealmResource ourRealm = getInstance().realm("master");
        RolesResource roleList = ourRealm.roles();

        UsersResource everyOne = ourRealm.users();

        RoleRepresentation roleToUse = roleList.get("realmuser").toRepresentation();

        javax.ws.rs.core.Response response = getInstance().realm(realm).users().create(user);

        String userId = CreatedResponseUtil.getCreatedId(response);


        UserResource oneUser = everyOne.get(userId);
        oneUser.roles().realmLevel().add(Arrays.asList(roleToUse));
        final int status = response.getStatus();

        final String createdId = getCreatedId(response);

        return HttpStatus.CREATED.value();
    }

    public static String getCreatedId(Response response) {
        URI location = response.getLocation();
        if (!response.getStatusInfo().equals(Response.Status.CREATED)) {
            Response.StatusType statusInfo = response.getStatusInfo();
            throw new RuntimeException("Create method returned status " +
                    statusInfo.getReasonPhrase() + " (Code: " + statusInfo.getStatusCode() + "); expected status: Created (201)");
        }
        if (location == null) {
            return null;
        }
        String path = location.getPath();
        return path.substring(path.lastIndexOf('/') + 1);
    }

    public static String generatePassword(int numberOfDigits) {
        String password = "";
        for (int i = 0; i < numberOfDigits / 2; i++) {
            password = password + (int) Math.floor(Math.random() * 9) + (char) Math.floor((Math.random() * 15) + 97);
        }
        return password;
    }

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