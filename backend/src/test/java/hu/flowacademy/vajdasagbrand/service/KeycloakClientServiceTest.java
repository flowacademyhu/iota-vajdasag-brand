package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.configuration.KeycloakPropertiesHolder;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KeycloakClientServiceTest {
// TODO: keykloak nem createdet ad vissza
    @Mock
    private Keycloak keycloak;

    @Mock
    private KeycloakPropertiesHolder keycloakPropertiesHolder;

    @InjectMocks
    private KeycloakClientService keycloakClientService;

    private String email;

    @Test
    public void givenConfiguredKeycloak_whenCallingCreatingAccount_thenUserIsCreated() throws ValidationException {
        givenUniqeEmail();
        givenKeycloakPropertiesHolderConfiguration();
        givenIntegratedKeycloak();

        keycloakClientService.createAccount(email);
        verify(keycloak, times(1)).realm(anyString());
        verifyNoMoreInteractions(keycloak);
        verify(keycloakPropertiesHolder, times(1)).getRealm2();
        verify(keycloakPropertiesHolder, times(1)).getUserRole();
        verifyNoMoreInteractions(keycloakPropertiesHolder);
    }

    private void givenUniqeEmail () {
        email = "barbika@gmail.com";
    }

    private void givenKeycloakPropertiesHolderConfiguration() {
        when(keycloakPropertiesHolder.getRealm2()).thenReturn("realm2");
        when(keycloakPropertiesHolder.getUserRole()).thenReturn("userRole");
    }

    private void givenIntegratedKeycloak(){
        RealmResource realmResource = Mockito.mock(RealmResource.class);
        when(keycloak.realm(anyString())).thenReturn(realmResource);
        RolesResource rolesResource = Mockito.mock(RolesResource.class);
        when(realmResource.roles()).thenReturn(rolesResource);
        RoleResource roleResource = Mockito.mock(RoleResource.class);
        when(rolesResource.get(anyString())).thenReturn(roleResource);
        RoleRepresentation roleRepresentation = new RoleRepresentation();
        when(roleResource.toRepresentation()).thenReturn(roleRepresentation);
        UsersResource usersResource = Mockito.mock(UsersResource.class);
        when(realmResource.users()).thenReturn(usersResource);
        Response response = Mockito.mock(Response.class);
        when(usersResource.create(any(UserRepresentation.class))).thenReturn(response);
        when(response.getLocation()).thenReturn(URI.create("/userId"));
        when(response.getStatusInfo()).thenReturn(Response.Status.CREATED);
        when(response.getStatus()).thenReturn(Response.Status.CREATED.getStatusCode());
        UserResource userResource = Mockito.mock(UserResource.class);
        when(usersResource.get(anyString())).thenReturn(userResource);
        RoleMappingResource roleMappingResource = Mockito.mock(RoleMappingResource.class);
        when(userResource.roles()).thenReturn(roleMappingResource);
        RoleScopeResource roleScopeResource = Mockito.mock(RoleScopeResource.class);
        when(roleMappingResource.realmLevel()).thenReturn(roleScopeResource);
    }

    public static String getCreatedId(Response response) throws WebApplicationException {
        URI location = response.getLocation();
        if (!response.getStatusInfo().equals(Response.Status.CREATED)) {
            Response.StatusType statusInfo = response.getStatusInfo();
            throw new WebApplicationException("Create method returned status " +
                    statusInfo.getReasonPhrase() + " (Code: " + statusInfo.getStatusCode() + "); " +
                    "expected status: Created (201)", response);
        }
        if (location == null) {
            return null;
        }
        String path = location.getPath();
        return path.substring(path.lastIndexOf('/') + 1);
    }

}