package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.entity.Type;
import hu.flowacademy.vajdasagbrand.entity.User;
import hu.flowacademy.vajdasagbrand.exception.UserNotEnabledException;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static final String REGISTRATION_EMAIL = "email@123.com";
    private static final String REGISTRATION_PASSWORD = "12345678";
    private static final String REGISTRATION_ID = "1234L";
    private static final String NEW_ADDRESS = "6724, Szeged Béka utca 2.";
    private static final String TAX_NUMBERCOMPANY = "165165413";
    private static final String TAX_NUMBERINDIVIDUAL = "";
    private static final String FULL_NAME = "Hello Péter";

    @Autowired
    private TestEntityManager entityManager;

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
        User userResult = service.userRegistrationData(userData, REGISTRATION_PASSWORD);
        Mockito.verify(userRepository, times(1)).save(userData);
        Mockito.verifyNoMoreInteractions(userRepository);
        Mockito.verify(keycloakClientService, times(1)).createAccount(REGISTRATION_EMAIL, REGISTRATION_PASSWORD);
        Mockito.verifyNoMoreInteractions(keycloakClientService);

        assertThat(userResult, notNullValue());
        assertThat(userResult.getId(), is(REGISTRATION_ID));
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
        User userResult = service.userRegistrationData(userData, REGISTRATION_PASSWORD);
        Mockito.verify(userRepository, times(1)).save(userData);

        assertThat(userResult, notNullValue());
        assertThat(userResult.getId(), is(REGISTRATION_ID));
        assertThat(userResult.getAddress(), is(NEW_ADDRESS));
        assertThat(userResult.getEmail(), is(REGISTRATION_EMAIL));
        assertThat(userResult.getFullName(), is(FULL_NAME));
        assertThat(userResult.getType(), is(Type.COMPANY));
        assertThat(userResult.getTaxNumber(), is(TAX_NUMBERCOMPANY));
    }

    @Test
    public void givenUserMissingFullname_whenCreatingAccout_thenExceptionIsThrown() throws ValidationException {
        User userData = givenUserMissingFullname();

        assertThrows(ValidationException.class, () -> service.userRegistrationData(userData, REGISTRATION_PASSWORD));
    }

    @Test
    public void givenUserMissingEmail_whenCreatingAccount_thenExceptionIsThrown() throws ValidationException {
        User userData = givenUserMissingEmail();

        assertThrows(ValidationException.class, () -> service.userRegistrationData(userData, REGISTRATION_PASSWORD));
    }

    @Test
    public void givenUserMissingAddress_whenCreatingAccount_thenExceptionIsThrown() throws ValidationException {
        User userData = givenUserMissingAddress();

        assertThrows(ValidationException.class, () -> service.userRegistrationData(userData, REGISTRATION_PASSWORD));
    }

    @Test
    public void givenUserMissingTaxNumberAtCompanyType_whenCreatingAccount_thenExceptionIsThrown() throws ValidationException {
        User userData = givenUserMissingTaxNumberAtCompanyType();

        assertThrows(ValidationException.class, () -> service.userRegistrationData(userData, REGISTRATION_PASSWORD));
    }

    @Test
    public void givenExistingUser_whenCallingDelete_thenUserIsDeletedSuccessfully() throws ValidationException, UserNotEnabledException {
        givenUserRepositoryWhenCallingDelete();
        User result = service.deleteById(REGISTRATION_ID);
        Mockito.verify(userRepository, times(1)).findById(REGISTRATION_ID);
        Mockito.verify(userRepository, times(1)).save(result);
        Mockito.verifyNoMoreInteractions(userRepository);
        Mockito.verify(keycloakClientService, times(2)).deleteUser(result.getEmail());

        assertThat(result, notNullValue());
        assertThat(result.isEnabled(), is(false));
        assertThat(result.getDeletedAt(), is(LocalDateTime.now().withNano(0)));
    }

    @Test
    public void givenAUserWithoutId_whenCallingDelete_thenExceptionIsThrown() {
        givenAUserIndividual();

        assertThrows(ValidationException.class, () -> service.deleteById(REGISTRATION_ID));

    }

    @Test
    public void givenUserWithEnabledFalse_whenCallingDelete_thenExceptionIsThrown() {
        givenUserRepositoryReturningUser();

        assertThrows(UserNotEnabledException.class, () -> service.deleteById(REGISTRATION_ID));

    }

    private void givenUserRepositoryReturningUser() {
        User user = givenAUserIndividual();
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
    }

    @Test
    public void givenUserWithDeletedAtSetAlready_whenCallingDelete_thenExceptionIsThrown() {
        givenAUserIndividualWithSetDeletedAt();

        assertThrows(ValidationException.class, () -> service.deleteById(REGISTRATION_ID));
    }


    @Test
    void givenThreeUsers_whenCallingFindAll_thenDataCommes() {
        Page tasks = mock(Page.class);
        Mockito.when(this.userRepository.findAll(org.mockito.Matchers.isA(Pageable.class))).thenReturn(tasks);
        int pageNum = 0;
        int limit = 3;
        String orderBy = "registeredAt";
        Page<User> result = userRepository.findAll(PageRequest.of(pageNum, limit, Sort.by(Sort.Direction.DESC, orderBy)));

        assertThat(result, notNullValue());
    }

    private void givenKeycloakClientServiceSavingUser() throws ValidationException {
        doNothing().when(keycloakClientService).createAccount(anyString(), anyString());
    }

    private void givenUserRepositorySavingUser() {
        when(userRepository.save(any(User.class))).thenAnswer(invocationOnMock -> {
            User created = invocationOnMock.getArgument(0);
            created.setId(REGISTRATION_ID);
            return created;
        });
    }

    private void givenUserRepositoryWhenCallingDelete() throws ValidationException {
        User user = givenAUserIndividual();
        user.setId(REGISTRATION_ID);
        user.setEnabled(true);
        when(userRepository.findById(REGISTRATION_ID)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        when(keycloakClientService.deleteUser(user.getEmail())).thenReturn(true);
    }

    private void givenUserRepositoryFindByIdEnabledFalse() {
        User user = givenAUserIndividual();
        user.setId(REGISTRATION_ID);
        when(userRepository.findById(REGISTRATION_ID)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
    }

    private User givenAUserIndividual() {

        User user = new User();
        user.setFullName(FULL_NAME);
        user.setAddress(NEW_ADDRESS);
        user.setEmail(REGISTRATION_EMAIL);
        user.setType(Type.INDIVIDUAL);
        user.setTaxNumber(TAX_NUMBERINDIVIDUAL);
        return user;
    }

    private User givenAUserIndividualWithSetDeletedAt() {

        User user = new User();
        user.setFullName(FULL_NAME);
        user.setAddress(NEW_ADDRESS);
        user.setEmail(REGISTRATION_EMAIL);
        user.setType(Type.INDIVIDUAL);
        user.setTaxNumber(TAX_NUMBERINDIVIDUAL);
        user.setEnabled(true);
        user.setDeletedAt(LocalDateTime.now());
        return user;
    }

    private User givenAUserCompany() {

        User user = new User();
        user.setFullName(FULL_NAME);
        user.setAddress(NEW_ADDRESS);
        user.setEmail(REGISTRATION_EMAIL);
        user.setTaxNumber(TAX_NUMBERCOMPANY);
        user.setType(Type.COMPANY);
        return user;
    }

    private User givenUserMissingFullname() {
        User user = new User();
        user.setAddress(NEW_ADDRESS);
        user.setEmail(REGISTRATION_EMAIL);
        user.setTaxNumber(TAX_NUMBERCOMPANY);
        user.setType(Type.COMPANY);
        return user;
    }

    private User givenUserMissingEmail() {
        User user = new User();
        user.setAddress(NEW_ADDRESS);
        user.setFullName(FULL_NAME);
        user.setTaxNumber(TAX_NUMBERCOMPANY);
        user.setType(Type.COMPANY);
        return user;
    }

    private User givenUserMissingAddress() {
        User user = new User();
        user.setEmail(REGISTRATION_EMAIL);
        user.setFullName(FULL_NAME);
        user.setTaxNumber(TAX_NUMBERCOMPANY);
        user.setType(Type.COMPANY);
        return user;
    }

    private User givenUserMissingTaxNumberAtCompanyType() {
        User user = new User();
        user.setEmail(REGISTRATION_EMAIL);
        user.setFullName(FULL_NAME);
        user.setTaxNumber(TAX_NUMBERINDIVIDUAL);
        user.setAddress(NEW_ADDRESS);
        user.setType(Type.COMPANY);
        return user;
    }


}
