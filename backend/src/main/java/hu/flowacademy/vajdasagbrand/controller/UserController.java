package hu.flowacademy.vajdasagbrand.controller;

import hu.flowacademy.vajdasagbrand.dto.LoginDto;
import hu.flowacademy.vajdasagbrand.entity.User;
import hu.flowacademy.vajdasagbrand.entity.UserDTO;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.service.KeycloakClientService;
import hu.flowacademy.vajdasagbrand.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.PermitAll;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final KeycloakClientService keycloakClientService;
    private final UserService userService;

    @PermitAll
    @PostMapping("/login")
    public AccessTokenResponse login(@RequestBody LoginDto loginDto) {
        return keycloakClientService.login(loginDto.getUsername(),loginDto.getPassword());
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    @PermitAll
    public void userRegistration(@RequestBody UserDTO userDTO) throws ValidationException {
        log.info("Incoming call with: {}",userDTO);
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userService.userRegistrationData(user, userDTO.getPassword());
    }
}
