import by.homework21.dto.rest.Login;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNot.not;

public class LoginTest {

    @Test
    public void loginWithValidDate() {
        Login expectedUser = Login.builder()
                .name("Nat")
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();

        //POST/register
        String tokenUser = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(expectedUser).
                log().all().
                when().
                post("https://reqres.in/api/login").
                then().
                statusCode(200).
                body("token", equalTo("QpwL5tke4Pnpja7X4")).
                log().all().
                extract().body().jsonPath().get("token");
    }

    @Test
    public void loginWithInValidDate() {
        Login expectedUnsuccessfulUser = Login.builder()
                .name("Nat")
                .email("eve.holt@reqres.in")
                .password("")
                .build();

        //POST/register
        String tokenUnsuccessfulUser = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(expectedUnsuccessfulUser).
                log().all().
                when().
                post("https://reqres.in/api/login").
                then().
                statusCode(400).
                body("error", equalTo("Missing password")).
                log().all().
                extract().body().jsonPath().get("error");

//                         pathParams("token",tokenUser).
//                //queryParam("limit","10").
//                when().
    }
}
