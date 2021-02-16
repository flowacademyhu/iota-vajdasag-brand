package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.entity.Type;
import org.apache.commons.validator.routines.EmailValidator;
import hu.flowacademy.vajdasagbrand.entity.User;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final KeycloakClientService keycloakClientService;

    public User userRegistrationData(User user) throws ValidationException {
        log.info("UserService called with: {}",user);
        validateUserData(user);

        keycloakClientService.createAccount(user.getEmail());
        user.setRegisteredAt(LocalDateTime.now());
        User result = userRepository.save(user);
        log.info("The result is : {}", result);
        return result;
    }

    private void validateUserData(User user) throws ValidationException {
        if (!StringUtils.hasText(user.getFullName())) {
            throw new ValidationException("Didn't get full name");
        }
        if(!StringUtils.hasText(user.getAddress())){
            throw new ValidationException("Didn't get address");
        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new ValidationException("Didn't get email");
        }
        if(!EmailValidator.getInstance().isValid(user.getEmail())){
            throw new ValidationException("Invalid email");
        }
        if(!StringUtils.hasText(String.valueOf(user.getType()))) {
            throw new ValidationException("Didn't get type");
        }
        if((user.getTaxNumber()).isEmpty() && user.getType() == Type.COMPANY){
            throw new ValidationException("Can not add tax number for individual members");
        }
    }

    public void approveRegistration(String userId) throws ValidationException {
        log.info("Incoming registration request with the id: {}", userId);
        User registeredUser= userRepository.findById(userId).orElseThrow(() -> new ValidationException("User with the following id " + userId + " not found"));
        log.debug("The user's current status is: {} ", registeredUser.isEnabled());
        registeredUser.setEnabled(true);
        userRepository.save(registeredUser);
        log.debug("The user's current status is: {} ", registeredUser.isEnabled());
    }
}
