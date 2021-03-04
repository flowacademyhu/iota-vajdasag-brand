package hu.flowacademy.vajdasagbrand.controller;

import com.github.javafaker.Faker;
import hu.flowacademy.vajdasagbrand.dto.EventDTO;
import hu.flowacademy.vajdasagbrand.dto.ItemDTO;
import hu.flowacademy.vajdasagbrand.persistence.entity.Category;
import hu.flowacademy.vajdasagbrand.persistence.entity.Subcategory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static hu.flowacademy.vajdasagbrand.helpers.UserHelper.getAuthorization;
import static hu.flowacademy.vajdasagbrand.helpers.UserHelper.loginWithSuperadminWithToken;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Disabled
class EventControllerTest {

    @LocalServerPort
    private int port;
    private static final Faker faker = new Faker();

    @BeforeEach
    public void beforeAllTest() {
        RestAssured.port = port;
    }

    private ItemDTO createItem() {
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
                        .website(faker.internet().url())
                        .instagram(faker.name().username())
                        .facebook(faker.name().username())
                        .subcategory(Subcategory.MUSEUMS)
                        .ownerId("95bce7b4-6864-4c8e-bc84-37132395156e")
                        .build())
                .when().post("/api/items")
                .then()
                .assertThat()
                .statusCode(201)
                .extract().body().as(ItemDTO.class);
    }


    @Test
    void eventRegistration() {
        String date = "2020.03.15 18:00:00";
        String dateend = "2020.03.15 20:00:00";
        String name = "Event name";
        String bio = "Something bio";
        String place = "Event place";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        LocalDateTime eventstart = LocalDateTime.parse(date, formatter);
        LocalDateTime eventend = LocalDateTime.parse(dateend, formatter);
        var eventDTO = EventDTO.builder()
                .name(name)
                .bio(bio)
                .eventstart(eventstart)
                .eventend(eventend)
                .place(place)
                .itemId(createItem().getId())
                .build();
        given().log().all()
                .header(getAuthorization(loginWithSuperadminWithToken()))
                .body(eventDTO)
                .contentType(ContentType.JSON)
                .when().post("/api/events")
                .then()
                .assertThat()
                .body("id", notNullValue())
                .body("name", equalTo(name))
                .body("bio", equalTo(bio))
                .body("place", equalTo(place))
                .body("eventstart", equalTo(date))
                .body("eventend", equalTo(dateend))
                .statusCode(201);
    }
}