package hu.flowacademy.vajdasagbrand.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class EventControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void beforeAllTest() {
        RestAssured.port = port;
    }

    @Test
    public void testEventRegistration() {

    }
}