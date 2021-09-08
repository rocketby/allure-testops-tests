package cloud.autotests.pages;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;

public class TestCasesTable {

    private ElementsCollection rows = $$(".LoadableTree__view > li");
    private SelenideElement titlePane = $(".Pane__title");
    private SelenideElement unlockButton = titlePane.$$("button").get(1);
    private SelenideElement nameOfNewTestCaseInput = $("input[name='name']");

    @Step("Unlock test cases tree")
    public void unlockTestCasesTree() {
        unlockButton.click();
    }

    @Step("Get number of test cases in table")
    public int getNumberOfTestCases() {
        return rows.size();
    }

    @Step("Create new test case with name: [{nameOfTestCase}]")
    public void createNewTestCase(String nameOfTestCase) {
        nameOfNewTestCaseInput.setValue(nameOfTestCase).pressEnter();
    }

    @Step("Check test cases table size")
    public void shouldHaveSize(int expectedSize) {
        rows.shouldHave(size(expectedSize));
    }

    @Step("Check that test case with specified name: [{nameTestCase}] is displayed in table")
    public void checkTestCaseIsDisplayed(String nameTestCase) {
        rows.find(text(nameTestCase)).shouldBe(visible);
    }

    @Step("Choose test case by name: [{nameTestCase}]")
    public void chooseTestCase(String nameTestCase) {
        rows.find(text(nameTestCase)).find(".LoadableTreeNodeCheckbox").click();
    }

    @Step("Click bulk actions button")
    public void clickBulkActionsButton() {
        $(".LoadableTreeControlPanel > button").click();
    }

    @Step("Delete test tases")
    public void deleteTestCases() {
        $(".tippy-content").$(byText("Delete")).click();
        $(".Modal__content").$(byText("Delete")).click();
    }
    public void navigateToTestByStatus(String statusName) {
        $$(".LoadableTree__view > li").findBy(text(statusName)).click();
    }
}

