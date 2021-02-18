package hu.flowacademy.vajdasagbrand.controller;

import hu.flowacademy.vajdasagbrand.dto.LoginDto;
import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import hu.flowacademy.vajdasagbrand.entity.User;
import hu.flowacademy.vajdasagbrand.exception.UserNotEnabledException;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.service.KeycloakClientService;
import hu.flowacademy.vajdasagbrand.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;


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
        return keycloakClientService.login(loginDto.getUsername(), loginDto.getPassword());
    }

    @RolesAllowed("SuperAdmin")
    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable("id") String id) throws ValidationException, UserNotEnabledException {
        return userService.deleteById(id);
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    @PermitAll
    public void userRegistration(@RequestBody UserDTO userDTO) throws ValidationException {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        log.debug("user {}", user);
        userService.userRegistrationData(user, userDTO.getPassword());
    }

    @RolesAllowed("SuperAdmin")
    @GetMapping("/getUsers")
    public Page<User> getUsers(@RequestParam(value = "order_by", required = false) String order_by,
                               @RequestParam(value = "page") int pageNum) {
        return userService.getUsers(order_by, pageNum);
    }
}
