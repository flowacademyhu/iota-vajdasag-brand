package hu.flowacademy.vajdasagbrand.controller;

import com.github.javafaker.Faker;
import hu.flowacademy.vajdasagbrand.dto.EventDTO;
import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import hu.flowacademy.vajdasagbrand.persistence.entity.Category;
import hu.flowacademy.vajdasagbrand.persistence.entity.Subcategory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDateTime;
import static hu.flowacademy.vajdasagbrand.helpers.UserHelper.getAuthorization;
import static hu.flowacademy.vajdasagbrand.helpers.UserHelper.loginWithSuperadminWithToken;
import static io.restassured.RestAssured.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class EventControllerTest {

    @LocalServerPort
    private int port;
    private static final Faker faker = new Faker();

    @BeforeEach
    public void beforeAllTest() {
        RestAssured.port = port;
    }

    private ItemDTO createItem () {
        return given().log().all()
                .header(getAuthorization(loginWithSuperadminWithToken()))
                .contentType(ContentType.JSON)
                .body(ItemDTO.builder()
                        .name(faker.name().name())
                        .bio(faker.chuckNorris().fact())
                        .score(faker.numerify("##"))
                        .address(faker.address().fullAddress())
                        .city(faker.address().city())
                        .email(faker.internet().emailAddress())
                        .contact(faker.name().fullName())
                        .category(Category.ATTRACTION)
                        .coordinateX(faker.address().longitude())
                        .coordinateY(faker.address().latitude())
                        .phone(faker.phoneNumber().phoneNumber())
                        .web(faker.internet().url())
                        .instagram(faker.name().username())
                        .facebook(faker.name().username())
                        .subcategory(Subcategory.MUSEUMS)
                        .build())
                .when().post("/api/items")
                .then()
                .assertThat()
                .statusCode(201)
                .extract().body().as(ItemDTO.class);
    }

    @Test
    void eventRegistration() {
        var eventstart = LocalDateTime.now();
        var eventend = eventstart.plusMinutes(15);
        var eventDTO = EventDTO.builder()
                .name(faker.chuckNorris().fact())
                .bio(faker.weather().description())
                .eventstart(eventstart)
                .eventend(eventend)
                .place(faker.country().capital())
                .itemId(createItem().getId())
                .build();
        given().log().all()
                .contentType(ContentType.JSON)
                .header(getAuthorization(loginWithSuperadminWithToken()))
                .body(eventDTO)
                .contentType(ContentType.JSON)
                .when().post("/api/events")
                .then()
                .assertThat()
                .statusCode(201);
    }

}