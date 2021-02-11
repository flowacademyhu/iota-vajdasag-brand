package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.entity.User;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {


    private final UserRepository userRepository;
    private final KeycloakClientService keycloakClientService;


    public User userRegistrationData(User user) throws ValidationException {
        log.info("UserService called with: {}",user);
        if (!StringUtils.hasText(user.getFullName())) {
            throw new ValidationException("Didn't get full name", HttpStatus.BAD_REQUEST);
        }
        keycloakClientService.createAccount(user.getEmail());
        User result = userRepository.save(user);
        log.debug("The result is : {}", result);
        return result;
    }
}
