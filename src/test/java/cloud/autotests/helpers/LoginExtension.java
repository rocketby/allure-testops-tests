package cloud.autotests.helpers;

import cloud.autotests.config.App;
import io.restassured.RestAssured;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class LoginExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        String authorizationResponse =
                given()
                        .filter(AllureRestAssuredFilter.withCustomTemplates())
                        .formParam("grant_type", "apitoken")
                        .formParam("scope", "openid")
                        .formParam("token", App.config.userToken())
                        .when()
                        .post("/api/uaa/oauth/token")
                        .then()
                        .statusCode(200)
                        .extract().response().asString();

        open("/favicon.ico");
        localStorage().setItem("AS_AUTH_2", authorizationResponse);
    }
}
