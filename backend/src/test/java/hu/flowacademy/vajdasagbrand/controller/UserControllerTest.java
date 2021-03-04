package hu.flowacademy.vajdasagbrand.controller;

import com.github.javafaker.Faker;
import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import hu.flowacademy.vajdasagbrand.persistence.entity.Type;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.TypeRef;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import static hu.flowacademy.vajdasagbrand.helpers.UserHelper.*;
import static io.restassured.RestAssured.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class UserControllerTest {
    @LocalServerPort
    private int port;
    private static final Faker faker = new Faker();
    private String defaultOrderCategory;
    @BeforeEach
    private void setUp() {
        RestAssured.port = port;
    }
    @Test
    void userRegistration() {
        createUser();
    }
    private UserDTO createUser() {
        var userDTO = UserDTO.builder()
                .email(faker.internet().emailAddress())
                .fullName(faker.chuckNorris().fact())
                .password(faker.beer().hop())
                .address(faker.address().fullAddress())
                .taxNumber(faker.number().digit())
                .type(Type.COMPANY)
                .build();
        given().log().all()
                .body(userDTO)
                .contentType(ContentType.JSON)
                .when().post("/api/registration")
                .then()
                .assertThat()
                .statusCode(201);
        return userDTO;
    }
    @Test
    void loginwithSuperAdmin() {
        loginWithSuperadminWithToken();
    }
    @Disabled
    @Test
    void approveRegistration() {
        UserDTO user = createUser();
        String id = getUserByEmail(
                userDTO -> user.getEmail().equalsIgnoreCase(userDTO.getEmail())
        ).getId();
        given().log().all()
                .header(getAuthorization(loginWithSuperadminWithToken()))
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
    @Disabled
    @Test
    void deleteUser() {
        UserDTO user = createUser();
        String id = getUserByEmail(UserDTO::isEnabled).getId();
        given().log().all()
                .header(getAuthorization(loginWithSuperadminWithToken()))
                .pathParam("id", id)
                .when().delete("api/users/{id}")
                .andReturn()
                .then()
                .assertThat()
                .statusCode(200);
    }
    @Test
    void getUsers() {
        createUser();
        getUserByEmail(Predicate.isEqual(null));
    }
    private UserDTO getUserByEmail(Predicate<UserDTO> p) {
        createUser();
        List<UserDTO> users = given().log().all()
                .header(getAuthorization(loginWithSuperadminWithToken()))
                .param("page", 3)
                .param("limit", 5)
                .when().get("api/users")
                .andReturn()
                .then()
                .assertThat()
                .statusCode(200)
                .extract().jsonPath().getList("content", UserDTO.class);
        return users.stream().filter(p).findFirst()
                .orElse(users.stream().findFirst().orElseThrow());
    }
}