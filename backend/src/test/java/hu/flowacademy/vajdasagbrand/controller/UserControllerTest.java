package hu.flowacademy.vajdasagbrand.controller;

import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import hu.flowacademy.vajdasagbrand.entity.Type;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static hu.flowacademy.vajdasagbrand.helpers.UserHelper.login;
import static hu.flowacademy.vajdasagbrand.helpers.UserHelper.loginWithSuperadmin;
import static io.restassured.RestAssured.given;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class UserControllerTest {


    @LocalServerPort
    private int port;
    private static final Faker faker = new Faker();
    private static UserDTO userDTO;


    @BeforeEach
    private void setUp() {
        RestAssured.port = port;
    }

    @BeforeAll
    private static void beforeAll() {
        userDTO = UserDTO.builder()
                .email(faker.internet().emailAddress())
                .fullName(faker.chuckNorris().fact())
                .password(faker.chuckNorris().fact())
                .address(faker.address().fullAddress())
                .taxNumber(faker.number().digit())
                .type(Type.COMPANY)
                .build();
    }

    @Test
    void userRegistration() {
        given().log().all()
                .body(userDTO)
                .contentType(ContentType.JSON)
                .when().post("/api/registration")
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    void loginwithCompanyAdmin() {
        login(userDTO.getEmail(), userDTO.getPassword());
    }

    @Test
    void loginwithSuperAdmin() {
        loginWithSuperadmin();
    }

    @Test
    void deleteUser() {
    }


    @Test
    void approveRegistration() {
    }

    @Test
    void getUsers() {
    }
}