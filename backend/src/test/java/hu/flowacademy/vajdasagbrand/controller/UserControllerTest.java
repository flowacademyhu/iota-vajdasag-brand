package hu.flowacademy.vajdasagbrand.controller;

import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import hu.flowacademy.vajdasagbrand.persistence.entity.Type;
import hu.flowacademy.vajdasagbrand.repository.UserRepository;
import hu.flowacademy.vajdasagbrand.service.UserService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static hu.flowacademy.vajdasagbrand.helpers.UserHelper.*;
import static io.restassured.RestAssured.given;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class UserControllerTest {


    @LocalServerPort
    private int port;
    private static final Faker faker = new Faker();
    private static UserDTO userDTO;
    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    private void setUp() {
        RestAssured.port = port;
    }

    @BeforeAll
    private static void beforeAll() {
        userDTO = UserDTO.builder()
                .id(faker.idNumber().valid())
                .email(faker.internet().emailAddress())
                .fullName(faker.chuckNorris().fact())
                .password(faker.beer().hop())
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
    void loginwithSuperAdmin() {
        loginWithSuperadminWithToken();
    }

    @Test
    void approveRegistration() {
        String token = loginWithSuperadminWithToken();
        String id = userRepository.findByEmail(userDTO.getEmail()).get().getId();
        given().log().all()
                .header(getAuthorization(token))
                .pathParam("id", id)
                .when().put("api/users/{id}/approval")
                .andReturn()
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void loginwithCompanyAdmin() {
        login("kissimre@jusoft.com", "Aa123456");
    }


    @Test
    void deleteUser() {
        String token = loginWithSuperadminWithToken();
        String id = userDTO.getId();
        given().log().all()
                .header(getAuthorization(token))
                .pathParam("id", id)
                .when().delete("api/users/{id}")
                .andReturn()
                .then()
                .assertThat()
                .statusCode(200);
    }



    @Test
    void getUsers() {
        String token = loginWithSuperadminWithToken();
        String id = userDTO.getId();
    }
}