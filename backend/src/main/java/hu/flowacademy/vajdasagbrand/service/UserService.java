package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.entity.Type;
import hu.flowacademy.vajdasagbrand.exception.GlobalExceptionHandler;
import hu.flowacademy.vajdasagbrand.exception.UserNotEnabledException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import hu.flowacademy.vajdasagbrand.entity.User;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final KeycloakClientService keycloakClientService;
    private final EmailService emailService;

    public User deleteById(String id) throws ValidationException, UserNotEnabledException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ValidationException("No user with given id: " + id);
        }
        if (!user.get().isEnabled()) {
            throw new UserNotEnabledException("User is not enabled");
        }
        User deleted = user.get();
        if (!keycloakClientService.deleteUser(deleted.getEmail())) {
            throw new ValidationException("No user with id in Keycloak");
        }
        deleted.setDeletedAt(LocalDateTime.now().withNano(0));
        deleted.setEnabled(false);
        keycloakClientService.deleteUser(deleted.getEmail());
        userRepository.save(deleted);
        return deleted;
    }

    public User userRegistrationData(User user, String password) throws ValidationException {
        log.info("UserService called with: {}", user);
        validateUserData(user);

        keycloakClientService.createAccount(user.getEmail(), password);
        user.setRegisteredAt(LocalDateTime.now());
        User result = userRepository.save(user);
        log.info("The result is : {}", result);
        return result;
    }

    private void validateUserData(User user) throws ValidationException {
        if (!StringUtils.hasText(user.getFullName())) {
            throw new ValidationException("Didn't get full name");
        }
        if (!StringUtils.hasText(user.getAddress())) {
            throw new ValidationException("Didn't get address");
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new ValidationException("Didn't get email");
        }
        if (!EmailValidator.getInstance().isValid(user.getEmail())) {
            throw new ValidationException("Invalid email");
        }
        if (!StringUtils.hasText(String.valueOf(user.getType()))) {
            throw new ValidationException("Didn't get type");
        }
        if ((user.getTaxNumber()).isEmpty() && user.getType() == Type.COMPANY) {
            throw new ValidationException("Can not add tax number for individual members");
        }
    }

    public boolean approveRegistration(String userId) throws ValidationException {
        log.info("Incoming approve registration request with the id: {}", userId);
        User registeredUser= userRepository.findById(userId).orElseThrow(() -> new ValidationException("User with the following id " + userId + " not found"));
        log.debug("The user's current status is: {} ", registeredUser.isEnabled());
        if (keycloakClientService.enableUser(registeredUser.getEmail())) {
            registeredUser.setEnabled(true);
            userRepository.save(registeredUser);
            log.debug("The user's current status is: {} ", registeredUser.isEnabled());
            keycloakClientService.sendVerificationEmail(registeredUser.getEmail());
            sendApprovalEmail(registeredUser.getEmail());
            return true;
        }
        else {
            throw new ValidationException("Validation didn't succeed");
        }
    }

    public void sendApprovalEmail(String email) {
        log.debug("Sending approval email to: {}", email);
        emailService.sendMessage(email, "Registration approval", "Dear Customer! \nYour registration is approved, you can login now");
    }

    public Page<User> getUsers(String orderBy, int pageNum, int limit) {
        return userRepository.findAll(PageRequest.of(pageNum, limit, Sort.by(Sort.Direction.DESC, orderBy)));
    }
}
