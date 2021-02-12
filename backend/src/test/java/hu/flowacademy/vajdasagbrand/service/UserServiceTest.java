package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.entity.Type;
import hu.flowacademy.vajdasagbrand.entity.User;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static final String REGISTRATION_EMAIL = "email@123.com";
    private static final String REGISTRATION_ID = "1234L";
    private static final String NEW_ADDRESS = "6724, Szeged Béka utca 2.";
    private static final String TAX_NUMBERCOMPANY = "165165413";
    private static final String TAX_NUMBERINDIVIDUAL = "";
    private static final String FULL_NAME = "Hello Péter";

    @Mock
    private UserRepository userRepository;

    @Mock
    private KeycloakClientService keycloakClientService;

    @InjectMocks
    private UserService service;

    @Test
    public void givenUser_whenCreatingAccount_thenAccountCreatedSuccessfully() throws ValidationException {
        givenUserRepositorySavingUser();
        givenKeycloakClientServiceSavingUser();
        User userData = givenAUserIndividual();
        User userResult = service.userRegistrationData(userData);
        Mockito.verify(userRepository, times(1)).save(userData);
        Mockito.verifyNoMoreInteractions(userRepository);
        Mockito.verify(keycloakClientService, times(1)).createAccount(REGISTRATION_EMAIL);
        Mockito.verifyNoMoreInteractions(keycloakClientService);

        assertThat(userResult, notNullValue());
        assertThat(userResult.getId(),is(REGISTRATION_ID));
        assertThat(userResult.getAddress(), is(NEW_ADDRESS));
        assertThat(userResult.getEmail(), is(REGISTRATION_EMAIL));
        assertThat(userResult.getFullName(), is(FULL_NAME));
        assertThat(userResult.getType(), is(Type.INDIVIDUAL));
        assertThat(userResult.getTaxNumber(), is(TAX_NUMBERINDIVIDUAL));
    }

    @Test
    public void givenUserCompany_whenCreatingAccount_thenAccountCreatedSuccessfully() throws ValidationException {
        givenUserRepositorySavingUser();
        User userData = givenAUserCompany();
        User userResult = service.userRegistrationData(userData);
        Mockito.verify(userRepository, times(1)).save(userData);

        assertThat(userResult, notNullValue());
        assertThat(userResult.getId(),is(REGISTRATION_ID));
        assertThat(userResult.getAddress(), is(NEW_ADDRESS));
        assertThat(userResult.getEmail(), is(REGISTRATION_EMAIL));
        assertThat(userResult.getFullName(), is(FULL_NAME));
        assertThat(userResult.getType(), is(Type.COMPANY));
        assertThat(userResult.getTaxNumber(), is(TAX_NUMBERCOMPANY));
    }

    @Test
    public void givenUserMissingFullname_whenCreatingAccout_thenExceptionIsThrown() throws ValidationException {
        User userData = givenUserMissingFullname();

        Assertions.assertThrows(ValidationException.class, () -> service.userRegistrationData(userData));
    }

    @Test
    public void givenUserMissingEmail_whenCreatingAccount_thenExceptionIsThrown() throws ValidationException {
        User userData =givenUserMissingEmail();

        Assertions.assertThrows(ValidationException.class, () -> service.userRegistrationData(userData));
    }

    @Test
    public void givenUserMissingAddress_whenCreatingAccount_thenExceptionIsThrown() throws ValidationException {
        User userData =givenUserMissingAddress();

        Assertions.assertThrows(ValidationException.class, () -> service.userRegistrationData(userData));
    }

    @Test
    public void givenUserMissingTaxNumberAtCompanyType_whenCreatingAccount_thenExceptionIsThrown() throws ValidationException {
        User userData =givenUserMissingTaxNumberAtCompanyType();

        Assertions.assertThrows(ValidationException.class, () -> service.userRegistrationData(userData));
    }

    private void givenKeycloakClientServiceSavingUser() throws ValidationException {
        doNothing().when(keycloakClientService).createAccount(anyString());
    }

    private void givenUserRepositorySavingUser() {
        when(userRepository.save(any(User.class))).thenAnswer(invocationOnMock -> {
            User created = invocationOnMock.getArgument(0);
            created.setId(REGISTRATION_ID);
            return created;
        });
    }

    private User givenAUserIndividual(){

        User user = new User();
        user.setFullName(FULL_NAME);
        user.setAddress(NEW_ADDRESS);
        user.setEmail(REGISTRATION_EMAIL);
        user.setType(Type.INDIVIDUAL);
        user.setTaxNumber(TAX_NUMBERINDIVIDUAL);
        return user;
    }

    private User givenAUserCompany(){

        User user = new User();
        user.setFullName(FULL_NAME);
        user.setAddress(NEW_ADDRESS);
        user.setEmail(REGISTRATION_EMAIL);
        user.setTaxNumber(TAX_NUMBERCOMPANY);
        user.setType(Type.COMPANY);
        return user;
    }

    private User givenUserMissingFullname(){
        User user = new User();
        user.setAddress(NEW_ADDRESS);
        user.setEmail(REGISTRATION_EMAIL);
        user.setTaxNumber(TAX_NUMBERCOMPANY);
        user.setType(Type.COMPANY);
        return user;
    }

    private User givenUserMissingEmail(){
        User user = new User();
        user.setAddress(NEW_ADDRESS);
        user.setFullName(FULL_NAME);
        user.setTaxNumber(TAX_NUMBERCOMPANY);
        user.setType(Type.COMPANY);
        return user;
    }

    private User givenUserMissingAddress(){
        User user = new User();
        user.setEmail(REGISTRATION_EMAIL);
        user.setFullName(FULL_NAME);
        user.setTaxNumber(TAX_NUMBERCOMPANY);
        user.setType(Type.COMPANY);
        return user;
    }

    private User givenUserMissingTaxNumberAtCompanyType(){
        User user = new User();
        user.setEmail(REGISTRATION_EMAIL);
        user.setFullName(FULL_NAME);
        user.setTaxNumber(TAX_NUMBERINDIVIDUAL);
        user.setAddress(NEW_ADDRESS);
        user.setType(Type.COMPANY);
        return user;
    }



}
