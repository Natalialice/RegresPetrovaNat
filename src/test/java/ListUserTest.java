import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ListUserTest {

    @Test
    public void UserListPage1Test(){
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                log().all().
                when().
                get("https://reqres.in/api/users?page=1").
                then().
                statusCode(200)
                .log().body()
                .log().status().
                body("page", equalTo(1),
                        "data.id", hasItems(1, 2, 3, 4, 5, 6),
                        "data.last_name",contains("Bluth", "Weaver", "Wong", "Holt", "Morris", "Ramos"));
    }
    @Test
    public void UserListPage2Test(){
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                log().all().
                when().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200)
                .log().body()
                .log().status()
                .body("page", equalTo(2),
                        "data.id", hasItems(7, 8, 9, 10, 11, 12),
                        "data.first_name",contains("Michael", "Lindsay", "Tobias", "Byron", "George","Rachel"),
                        "support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }
    @Test
    public void UserListPage3Test(){
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                log().all().
                when().
                get("https://reqres.in/api/users?page=3").
                then().
                statusCode(200)
                .log().body()
                .log().status()
                .body("page", equalTo(3),
                        "data", empty(),
                        "total_pages",equalTo(2),
                        "support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }
}




