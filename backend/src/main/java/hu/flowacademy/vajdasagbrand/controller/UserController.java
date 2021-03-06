package hu.flowacademy.vajdasagbrand.controller;

import hu.flowacademy.vajdasagbrand.dto.ForgottenPasswordDTO;
import hu.flowacademy.vajdasagbrand.dto.LoginDto;
import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import hu.flowacademy.vajdasagbrand.exception.UserNotEnabledException;
import hu.flowacademy.vajdasagbrand.exception.ValidationException;
import hu.flowacademy.vajdasagbrand.service.KeycloakClientService;
import hu.flowacademy.vajdasagbrand.service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.net.MalformedURLException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final KeycloakClientService keycloakClientService;
    private final UserService userService;

    @Value("${userController.defaultOrderCategory}")
    private String defaultOrderCategory;
    @Value("${userController.defaultPageNumber}")
    private int defaultPageNumber;
    @Value("${userController.defaultPageLimit}")
    private int defaultPageLimit;


    @PermitAll
    @PostMapping("/login")
    public AccessTokenResponse login(@RequestBody LoginDto loginDto) {
        return keycloakClientService.login(loginDto.getUsername(), loginDto.getPassword());
    }

    @RolesAllowed("SuperAdmin")
    @DeleteMapping("/users/{id}")
    public UserDTO deleteUser(@PathVariable("id") String id) throws ValidationException, UserNotEnabledException {
        return userService.deleteById(id);
    }

    @PermitAll
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void userRegistration(@RequestBody UserDTO userDTO) throws ValidationException {
        userService.userRegistrationData(userDTO, userDTO.getPassword());
    }

    @RolesAllowed("SuperAdmin")
    @PutMapping("/users/{id}/approval")
    public void approveRegistration(@PathVariable("id") String userId) throws ValidationException, MalformedURLException {
        userService.approveRegistration(userId);
    }

    @RolesAllowed("SuperAdmin")
    @GetMapping("/users")
    public Page<UserDTO> getUsers(@RequestParam(value = "order_by", required = false) Optional<String> orderBy,
                               @RequestParam(value = "page", required = false) Optional<Integer> pageNum,
                               @RequestParam(value = "limit", required = false) Optional<Integer> limit) {
        return userService.getUsers(
                orderBy.orElse(defaultOrderCategory),
                pageNum.orElse(defaultPageNumber),
                limit.orElse(defaultPageLimit));
    }

    @RolesAllowed({"SuperAdmin", "CegAdmin"})
    @GetMapping("/users/{id}")
    public UserDTO getSingleUser(@PathVariable("id")String id) throws ValidationException {
        return userService.getSingleUser(id);
    }

    @PermitAll
    @PostMapping("/forgottenpassword")
    public boolean sendForgottenPassword(@RequestBody ForgottenPasswordDTO forgottenPasswordDTO) {
        return keycloakClientService.sendForgottenPassword(forgottenPasswordDTO.getEmail());
    }
}
