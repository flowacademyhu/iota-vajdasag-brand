package hu.flowacademy.vajdasagbrand.controller;

import hu.flowacademy.vajdasagbrand.entity.User;
import hu.flowacademy.vajdasagbrand.entity.UserDTO;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    @PermitAll
    public void userRegistration(@RequestBody UserDTO userDTO) throws ValidationException {
        log.info("Incoming call with: {}",userDTO);
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userService.userRegistrationData(user);
    }

}
