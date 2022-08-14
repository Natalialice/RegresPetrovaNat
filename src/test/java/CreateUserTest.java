import by.homework21.dto.rest.NewUser;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateUserTest {
    @Test
    public void createUser() {
        NewUser expectedUser = NewUser.builder()
                .name("Stas")
                .job("engineer")
                .build();

        ValidatableResponse postActualUser = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(expectedUser).
                log().all().
                when().
                post("https://reqres.in/api/user").
                then().
                statusCode(201)
                .log().body()
                .log().status().
                body("id", not(empty()),
                        "createdAt", not(empty())).
                extract().body().jsonPath().get("id");

//        ValidatableResponse getActualUser = given().
//                contentType(ContentType.JSON).
//                accept(ContentType.JSON).
//                log().all().
//                pathParams("id", postActualUser.getClass("id")).
//                when().
//                get("https://reqres.in/api/user/{id}").
//                then().
//                statusCode(200);
    }

}
