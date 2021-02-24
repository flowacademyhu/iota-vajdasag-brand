package hu.flowacademy.vajdasagbrand.controller;

import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import hu.flowacademy.vajdasagbrand.entity.Type;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class UserControllerTest {


    @LocalServerPort
    private int port;
    private static final Faker faker = new Faker();


    @BeforeEach
    private void setUp() {
        RestAssured.port = port;
    }

    @Test
    void loginwithCompanyAdmin() {

    }

    @Test
    void deleteUser() {
    }

    @Test
    void userRegistration() {
        given().log().all()
                .body(UserDTO.builder()
                        .email(faker.internet().emailAddress())
                        .fullName(faker.chuckNorris().fact())
                        .password(faker.chuckNorris().fact())
                        .address(faker.address().fullAddress())
                        .taxNumber(faker.number().digit())
                        .type(Type.COMPANY)
                        .build())
                .contentType(ContentType.JSON)
                .when().post("/api/registration")
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    void approveRegistration() {
    }

    @Test
    void getUsers() {
    }
}