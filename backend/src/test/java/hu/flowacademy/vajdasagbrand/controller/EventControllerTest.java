package hu.flowacademy.vajdasagbrand.controller;

import com.github.javafaker.Faker;
import hu.flowacademy.vajdasagbrand.dto.EventDTO;
import hu.flowacademy.vajdasagbrand.repository.EventRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
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
    private static final Faker faker = new Faker();
    private static EventDTO eventDTO;
    private static EventRepository eventRepository;

    @BeforeEach
    public void beforeAllTest() {
        RestAssured.port = port;
    }

    @BeforeAll
    private static void beforeAllEventTest() {
        eventDTO = EventDTO.builder()
                .id(faker.gameOfThrones().dragon())
                .name(faker.chuckNorris().fact())
                .bio(faker.weather().description())
                .address(faker.address().fullAddress())
                .taxNumber(faker.number().digit())
                .build();
    }
}