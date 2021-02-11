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

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User userRegistrationData(User user) throws ValidationException {
        if (!StringUtils.hasText(user.getFullName())) {
            throw new ValidationException("Didn't get full name", HttpStatus.BAD_REQUEST);
        }
        if(!StringUtils.hasText(user.getAddress())){
            throw new ValidationException("Didn't get address", HttpStatus.BAD_REQUEST);
        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new ValidationException("Didn't get email", HttpStatus.BAD_REQUEST);
        }
        if(!EmailValidator.getInstance().isValid(user.getEmail())){
            throw new ValidationException("Invalid email", HttpStatus.BAD_REQUEST);
        }
        if(!StringUtils.hasText(String.valueOf(user.getType()))) {
            throw new ValidationException("Didn't get type", HttpStatus.BAD_REQUEST);
        }
        if((user.getTaxNumber()).isEmpty() && user.getType() == Type.COMPANY){
            throw new ValidationException("Can not add tax number for individual members", HttpStatus.BAD_REQUEST);
        }
        user.setLocalDateTime(LocalDateTime.now());
        User result = userRepository.save(user);
        return result;
    }
}
