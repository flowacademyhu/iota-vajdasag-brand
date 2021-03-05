package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.configuration.UIProperties;
import hu.flowacademy.vajdasagbrand.persistence.entity.Type;
import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import hu.flowacademy.vajdasagbrand.exception.UserNotEnabledException;
import hu.flowacademy.vajdasagbrand.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final KeycloakClientService keycloakClientService;
    private final EmailService emailService;
    private final UIProperties uiProperties;

    public UserDTO deleteById(String id) throws ValidationException, UserNotEnabledException {
        UserDTO deleted = userRepository.findById(id).filter(userDTO -> !userDTO.isEnabled()).orElseThrow(() -> new ValidationException("User with the following id " + id + " not found"));
        if (!keycloakClientService.deleteUser(deleted.getEmail())) {
            log.error("deleted {}", deleted);
            throw new ValidationException("No user was found with this id in Keycloak.");
        }
        deleted.setDeletedAt(LocalDateTime.now().withNano(0));
        deleted.setEnabled(false);
        userRepository.save(deleted);
        return deleted;
    }

    public UserDTO userRegistrationData(UserDTO user, String password) throws ValidationException {
        validateUserData(user);
        keycloakClientService.createAccount(user.getEmail(), password);
        user.setRegisteredAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    private void validateUserData(UserDTO user) throws ValidationException {
        if (!StringUtils.hasText(user.getFullName())) {
            throw new ValidationException("No fullname was provided.");
        }
        if (!StringUtils.hasText(user.getAddress())) {
            throw new ValidationException("No address was provided.");
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new ValidationException("No email was provided.");
        }
        if (!EmailValidator.getInstance().isValid(user.getEmail())) {
            throw new ValidationException("Invalid email.");
        }
        if (!StringUtils.hasText(String.valueOf(user.getType()))) {
            throw new ValidationException("No type was provided.");
        }
        if ((user.getTaxNumber()).isEmpty() && user.getType() == Type.COMPANY) {
            throw new ValidationException("Cannot add tax number to users with INDIVIDUAL category.");
        }
    }

    public Page<UserDTO> getUsers(String orderBy, int pageNum, int limit) {
        return userRepository.findAllUsers(PageRequest.of(pageNum, limit, Sort.by(Sort.Direction.DESC, orderBy)));
    }

    public boolean approveRegistration(String userId) throws ValidationException, MalformedURLException {
        UserDTO registeredUser = userRepository.findById(userId).orElseThrow(() -> new ValidationException("User with the following id " + userId + " not found"));
        if (keycloakClientService.enableUser(registeredUser.getEmail())) {
            registeredUser.setEnabled(true);
            userRepository.save(registeredUser);
            keycloakClientService.sendVerificationEmail(registeredUser.getEmail());
            sendApprovalEmail(registeredUser.getEmail());
            return true;
        } else {
            throw new ValidationException("Validation was unsuccessful.");
        }
    }


    public void sendApprovalEmail(String email) {
        emailService.sendMessage(email, "Registration approval", "Dear Customer! \n \nOnce you verified your email address you will be able to login by clicking on the following link: \n" + uiProperties.getLoginUrl()  + "\n \nWelcome to Vajdasag Brand!");
    }

    public UserDTO getSingleUser(String id) throws ValidationException {
        return userRepository.findById(id).orElseThrow(() -> new ValidationException("No user with given id"));
    }
}