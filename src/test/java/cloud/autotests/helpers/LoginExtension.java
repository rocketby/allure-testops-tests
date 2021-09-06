package cloud.autotests.helpers;

import cloud.autotests.api.Authorization;
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
                new Authorization().getAuthorizationResponse().asString();

        open("/favicon.ico");
        localStorage().setItem("AS_AUTH_2", authorizationResponse);
    }
}
