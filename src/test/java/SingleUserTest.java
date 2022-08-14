import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class SingleUserTest {


    @Test
    public void user() {
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                log().all().
                when().
                get("https://reqres.in/api/users/3").
                then().
                statusCode(200)
                .log().body()
                .log().status()
                .body("data.id", equalTo(3),
                        "data.first_name", equalTo("Emma"),
                        "data.last_name",equalTo("Wong"));
    }
    @Test
    public void userNotFound() {
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                log().all().
                when().
                get("https://reqres.in/api/users/23").
                then().
                statusCode(404)
                .log().body()
                .log().status();
    }
}
