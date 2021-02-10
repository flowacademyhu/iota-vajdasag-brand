package hu.flowacademy.vajdasagbrand.service;

import hu.flowacademy.vajdasagbrand.entity.Type;
import hu.flowacademy.vajdasagbrand.entity.User;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;


    public User userRegistrationData(User user) throws ValidationException {
        if (StringUtils.hasText(user.getFullName())) {
            throw new ValidationException("Didn't get full name", HttpStatus.BAD_REQUEST);
        }
        User result = userRepository.save(user);
        log.debug("The result is : {}",result);
        return result;
    }
}
