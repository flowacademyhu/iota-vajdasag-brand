package hu.flowacademy.vajdasagbrand.controller;

import hu.flowacademy.vajdasagbrand.dto.LoginDto;
import hu.flowacademy.vajdasagbrand.service.KeycloakClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final KeycloakClientService keycloakClientService;

    @PermitAll
    @PostMapping("/login")
    public AccessTokenResponse login(@RequestBody LoginDto loginDto) {
        return keycloakClientService.login(loginDto.getUsername(),loginDto.getPassword());
    }


}
