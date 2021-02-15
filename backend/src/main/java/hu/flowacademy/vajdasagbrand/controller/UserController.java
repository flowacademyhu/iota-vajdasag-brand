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
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

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
        userService.userRegistrationData(user);
    }

    @RolesAllowed("admin")
    @PutMapping("/approval/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void approveRegistration(@PathVariable("id") String userId){
        log.info("Incoming registration request with the id: {}", userId);
        try {
            userService.approveRegistration(userId);
            log.debug("The requested user id is: {}", userId);
        } catch (ValidationException e) {
            log.error("Registration with the give id ({}) not found", userId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Registration with the given id not found");
        }
    }
}
