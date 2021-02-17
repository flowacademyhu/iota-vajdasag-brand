package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.configuration.KeycloakPropertiesHolder;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeycloakClientService {

    private final Keycloak keycloak;
    private final KeycloakPropertiesHolder keycloakPropertiesHolder;

    public void createAccount(String email, String password) throws ValidationException {
        CredentialRepresentation credential = createCredentials(password);
        RealmResource ourRealm = keycloak.realm(keycloakPropertiesHolder.getKeycloakBackendClientRealm());
        RoleRepresentation roleToUse = ourRealm.roles().get(keycloakPropertiesHolder.getKeycloakBackendClientUserRole()).toRepresentation();
        javax.ws.rs.core.Response response = ourRealm.users().create(createUserRepresentation(email, credential));
        String userId = CreatedResponseUtil.getCreatedId(response);
        UserResource oneUser = ourRealm.users().get(userId);
        oneUser.roles().realmLevel().add(List.of(roleToUse));
        if (response.getStatus() != HttpStatus.CREATED.value()) {
            throw new ValidationException("Username taken!");
        }
    }

    public AccessTokenResponse login(String email, String password) {
        return Keycloak.getInstance(
                keycloakPropertiesHolder.getKeycloakServerUrl(),
                keycloakPropertiesHolder.getKeycloakRealm(),
                email, password,
                keycloakPropertiesHolder.getKeycloakResource(),
                keycloakPropertiesHolder.getKeycloakCredentialsSecret())
                .tokenManager()
                .getAccessToken();
    }

    public boolean deleteUser(String username) {
        UsersResource resource = keycloak.realm(keycloakPropertiesHolder.getKeycloakBackendClientRealm()).users();
        List<UserRepresentation> listOfUsers = resource.search(username);
        if(listOfUsers.isEmpty()) return false;
        UserRepresentation userToBeDeleted = listOfUsers.get(0);
        userToBeDeleted.setEnabled(false);
        resource.get(userToBeDeleted.getId()).update(userToBeDeleted);
        return true;
    }

    private UserRepresentation createUserRepresentation(String email, CredentialRepresentation credential) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(email);
        user.setCredentials(List.of(credential));
        user.setEnabled(false);
        user.setEmail(email);
        return user;
    }

    private CredentialRepresentation createCredentials(String password) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);
        return credential;
    }

    public static String generatePassword(int numberOfDigits) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < numberOfDigits / 2; i++) {
            password.append((int) Math.floor(Math.random() * 9)).append((char) Math.floor((Math.random() * 15) + 97));
        }
        return password.toString();
    }

}
