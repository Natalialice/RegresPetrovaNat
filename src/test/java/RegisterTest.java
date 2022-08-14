import by.homework21.dto.rest.Register;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RegisterTest {

    @Test
    public void registerWithValidDate() {
        Register expectedUser = Register.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();

        //POST/register
        ValidatableResponse actualUser =  given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(expectedUser).
                log().all().
                when().
                post("https://reqres.in/api/register").
                then().
                statusCode(200).
                body("id", not(empty()),
                        "token", equalTo("QpwL5tke4Pnpja7X4")).
                log().body();

    }
    @Test
    public void registerWithoutPassword() {
        Register expectedUser = Register.builder()
                .email("eve.holt@reqres.in")
                .password("")
                .build();

        //POST/register
        String InValidUser = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(expectedUser).
                log().all().
                when().
                post("https://reqres.in/api/register").
                then().
                statusCode(400).
                body( "error", equalTo("Missing password")).
                log().all().
                extract().
                body().jsonPath().get("error");
    }
    @Test
    public void registerWithoutEmail() {
        Register expectedUser = Register.builder()
                .email("")
                .password("pistol")
                .build();

        //POST/register
        String InValidUser = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(expectedUser).
                log().all().
                when().
                post("https://reqres.in/api/register").
                then().
                statusCode(400).
                body( "error", equalTo("Missing email or username")).
                log().all().
                extract().
                body().jsonPath().get("error");
    }
}
