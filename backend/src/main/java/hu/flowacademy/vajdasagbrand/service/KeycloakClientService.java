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

    public int createAccount(String name) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue("admin123");
        credential.setTemporary(false);
        UserRepresentation user = new UserRepresentation();
        String[] namesToUse = nameChecker(name);
        user.setLastName(namesToUse[0]);
        user.setFirstName(namesToUse[1]);

        user.setUsername((name + "_" + user.getLastName()).toLowerCase(Locale.ROOT));
        user.setCredentials(Arrays.asList(credential));

        RealmResource ourRealm = getInstance().realm("master");
        RolesResource roleList = ourRealm.roles();

        UsersResource everyOne = ourRealm.users();

        RoleRepresentation roleToUse = roleList.get("CegAdmin").toRepresentation();

        javax.ws.rs.core.Response response = getInstance().realm(realm).users().create(user);

        String userId = CreatedResponseUtil.getCreatedId(response);


        UserResource oneUser = everyOne.get(userId);
        oneUser.roles().realmLevel().add(Arrays.asList(roleToUse));
        final int status = response.getStatus();

        final String createdId = getCreatedId(response);

        return HttpStatus.CREATED.value();
    }
    private String[] nameChecker(String name) {
        String[] out = new String[2];
        String[] namesToRegister = name.split(" ");
        out[0] = namesToRegister[0];
        if (namesToRegister.length < 2) {
            out[1] = "User";
        }
        else {
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i < namesToRegister.length; i++) {
                builder.append(namesToRegister[i]).append(" ");
            }
            out[1] = builder.toString();
        }
        return out;
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