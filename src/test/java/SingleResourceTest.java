import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SingleResourceTest {
    @Test
    public void singleResource() {
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                log().all().
                when().
                get("https://reqres.in/api/unknown/5").
                then().
                statusCode(200)
                .log().body()
                .log().status().
                body("data.id", equalTo(5),
                        "data.name", equalTo("tigerlily"),
                        "data.color", equalTo("#E2583E"),
                        "data.pantone_value", equalTo("17-1456"));
    }

    @Test
    public void singleResourceNotFound() {
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                log().all().
                when().
                get("https://reqres.in/api/unknown/23").
                then().
                statusCode(404)
                .log().body()
                .log().status();
    }


    @Test
    public void listResourcePage1() {
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                log().all().
                when().
                get("https://reqres.in/api/unknown").
                then().
                statusCode(200)
                .log().body()
                .log().status().
                body("data.id", hasItems(1, 2, 3, 4, 5, 6),
                        "data.name",contains("cerulean", "fuchsia rose", "true red", "aqua sky", "tigerlily", "blue turquoise"));
    }
}
