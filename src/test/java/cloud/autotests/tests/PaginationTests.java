package cloud.autotests.tests;

import cloud.autotests.helpers.WithLogin;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Story("Pagination tests")
public class PaginationTests extends TestBase {

    @WithLogin
    @Test
    @DisplayName("Сhanging the pagination display (for example, 5 elements)")
    void paginationOf5Elements() {
        step("Open projects page", () ->
                open(""));
        step("Open pagination select", () ->
                $(".css-19attta-control").click());
        step("Choice 5 elements", () ->
                $(byId("react-select-2-option-0")).click());
        step("Check text 'Show 5'", () ->
                $(".css-1hwfws3").should(text("Show 5")));
    }

    @WithLogin
    @Test
    @DisplayName("Сhanging the pagination display (for example, 10 elements)")
    void paginationOf10Elements() {
        step("Open projects page", () ->
                open(""));
        step("Open pagination select", () ->
                $(".css-19attta-control").click());
        step("Choice 10 elements", () ->
                $(byId("react-select-2-option-1")).click());
        step("Check text 'Show 10'", () ->
                $(".css-1hwfws3").should(text("Show 10")));
    }

    @WithLogin
    @Test
    @DisplayName("Сhanging the pagination display (for example, 25 elements)")
    void paginationOf25Elements() {
        step("Open projects page", () ->
                open(""));
        step("Open pagination select", () ->
                $(".css-19attta-control").click());
        step("Choice 25 elements", () ->
                $(byId("react-select-2-option-2")).click());
        step("Check text 'Show 25'", () ->
                $(".css-1hwfws3").should(text("Show 25")));
    }

    @WithLogin
    @Test
    @DisplayName("Сhanging the pagination display (for example, 50 elements)")
    void paginationOf50Elements() {
        step("Open projects page", () ->
                open(""));
        step("Open pagination select", () ->
                $(".css-19attta-control").click());
        step("Choice 50 elements", () ->
                $(byId("react-select-2-option-3")).click());
        step("Check text 'Show 50'", () ->
                $(".css-1hwfws3").should(text("Show 50")));
    }

    @WithLogin
    @Test
    @DisplayName("Сhanging the pagination display (for example, 100 elements)")
    void paginationOf100Elements() {
        step("Open projects page", () ->
                open(""));
        step("Open pagination select", () ->
                $(".css-19attta-control").click());
        step("Choice 100 elements", () ->
                $(byId("react-select-2-option-4")).click());
        step("Check text 'Show 100'", () ->
                $(".css-1hwfws3").should(text("Show 100")));
    }

}
