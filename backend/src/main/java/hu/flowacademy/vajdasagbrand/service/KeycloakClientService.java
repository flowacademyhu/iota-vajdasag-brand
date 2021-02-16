package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.configuration.KeycloakPropertiesHolder;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import org.keycloak.admin.client.Keycloak;

@Service
@RequiredArgsConstructor
public class KeycloakClientService {

    private final Keycloak keycloak;
    private final KeycloakPropertiesHolder keycloakPropertiesHolder;

    public void createAccount(String email, String password) throws ValidationException {
        CredentialRepresentation credential = createCredentials(password);
        RealmResource ourRealm = keycloak.realm(keycloakPropertiesHolder.getKeycloakBackendClientRealm2());
        RoleRepresentation roleToUse = ourRealm.roles().get(keycloakPropertiesHolder.getKeycloakBackendClientUserRole()).toRepresentation();
        javax.ws.rs.core.Response response = ourRealm.users().create(createUserRepresentation(email, credential));
        String userId = CreatedResponseUtil.getCreatedId(response);
        UserResource oneUser = ourRealm.users().get(userId);
        oneUser.roles().realmLevel().add(List.of(roleToUse));
        if (response.getStatus() != HttpStatus.CREATED.value()) {
            throw new ValidationException("Username taken!");
        }
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

    public static String generatePassword(int numberOfDigits) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < numberOfDigits / 2; i++) {
            password.append((int) Math.floor(Math.random() * 9)).append((char) Math.floor((Math.random() * 15) + 97));
        }
        return password.toString();
    }

}