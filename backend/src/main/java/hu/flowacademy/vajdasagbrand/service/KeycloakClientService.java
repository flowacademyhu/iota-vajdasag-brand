package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.configuration.KeycloakPropertiesHolder;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import java.util.*;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class KeycloakClientService {

    private static final String KEYCLOAK_USER_ROLE_NAME = "realmuser";
    private final Keycloak keycloak;
    private final KeycloakPropertiesHolder keycloakPropertiesHolder;

    public void createAccount(String email, String password) throws ValidationException {
        CredentialRepresentation credential = createCredentials(password);
        RealmResource ourRealm = keycloak.realm(keycloakPropertiesHolder.getKeycloakBackendClientRealm());
        RolesResource rolesResource = ourRealm.roles();
        RoleRepresentation roleToUse = getOrCreateRole(rolesResource, KEYCLOAK_USER_ROLE_NAME);
        javax.ws.rs.core.Response response = ourRealm.users().create(createUserRepresentation(email, credential));
        String userId = CreatedResponseUtil.getCreatedId(response);
        UserResource oneUser = ourRealm.users().get(userId);
        oneUser.roles().realmLevel().add(List.of(roleToUse));
        if (response.getStatus() != HttpStatus.CREATED.value()) {
            throw new ValidationException("Username taken!");
        }
    }

    public boolean sendVerificationEmail(String username) {
        try {
            RealmResource ourRealm = keycloak.realm(keycloakPropertiesHolder.getKeycloakBackendClientRealm());
            UsersResource resource = ourRealm.users();
            return resource
                    .search(username)
                    .stream()
                    .findFirst()
                    .map(oneUser -> {
                        resource.get(oneUser.getId()).executeActionsEmail(List.of("VERIFY_EMAIL"));
                        return oneUser;
                    })
                    .isPresent();
        } catch (WebApplicationException e) {
            return false;
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

    public boolean enableUser(String username) {
        UsersResource resource = keycloak.realm(keycloakPropertiesHolder.getKeycloakBackendClientRealm()).users();
        return resource
                .search(username)
                .stream()
                .findFirst()
                .map(userToEnable -> {
                    userToEnable.setEnabled(true);
                    resource.get(userToEnable.getId()).update(userToEnable);
                    return userToEnable;
                })
                .isPresent();
    }

    public boolean deleteUser(String username) {
        UsersResource resource = keycloak.realm(keycloakPropertiesHolder.getKeycloakBackendClientRealm()).users();
        List<UserRepresentation> listOfUsers = resource.search(username);
        if (listOfUsers.isEmpty()) return false;
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
        user.setRequiredActions(List.of("VERIFY_EMAIL"));
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

    private RoleRepresentation getOrCreateRole(RolesResource rolesResource, String r) {
        try {
            return rolesResource.get(r).toRepresentation();
        } catch (NotFoundException e) {
            log.warn("Role not found, creating it for {}: {}", r, e.getMessage());
        }
        return createRoleRepresentation(rolesResource, r);
    }

    private RoleRepresentation createRoleRepresentation(RolesResource rolesResource, String r) {
        RoleRepresentation roleRep = new RoleRepresentation(r, "", false);
        rolesResource.create(roleRep);
        return rolesResource.get(r).toRepresentation();
    }
}