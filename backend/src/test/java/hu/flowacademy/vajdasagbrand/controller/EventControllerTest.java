package hu.flowacademy.vajdasagbrand.controller;

import com.github.javafaker.Faker;
import hu.flowacademy.vajdasagbrand.dto.EventDTO;
import hu.flowacademy.vajdasagbrand.repository.EventRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;

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
        var eventstart = LocalDateTime.now();
        var eventend = eventstart.plusMinutes(15);
        eventDTO = EventDTO.builder()
                .id(faker.gameOfThrones().dragon())
                .name(faker.chuckNorris().fact())
                .bio(faker.weather().description())
                .eventstart(eventstart)
                .eventend(eventend)
                .place(faker.country().capital())
                .itemId(faker.idNumber().valid())
                .build();
    }

    @Test
    @WithMockUser(username="tester",roles={"SuperAdmin"})
    void eventRegistration() {
        given().log().all()
                .header(getAuthorization(adminLogin()))
                .body(eventDTO)
                .contentType(ContentType.JSON)
                .when().post("/api/events")
                .then()
                .assertThat()
                .statusCode(201);
    }


}