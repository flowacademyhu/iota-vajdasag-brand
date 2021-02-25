package hu.flowacademy.vajdasagbrand.helpers;

import hu.flowacademy.vajdasagbrand.dto.UserDTO;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.keycloak.representations.AccessTokenResponse;

import static io.restassured.RestAssured.given;

public class UserHelper {

    public static Header getAuthorization(String token) {
        return new Header("Authorization", "Bearer " + token);
    }

    public static String login(String email, String password) {
        var resp = given().log().all()
                .body(getUser(email, password))
                .contentType(ContentType.JSON)
                .when().post("/api/login")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .extract().body().as(AccessTokenResponse.class);
        return resp.getToken();
    }

    public static String loginWithSuperadminWithToken() {
       return login("superamind@sa.com", "1234");
    }


    private static UserDTO getUser(String email, String password) {
        return UserDTO.builder().email(email).password(password).build();
    }
}