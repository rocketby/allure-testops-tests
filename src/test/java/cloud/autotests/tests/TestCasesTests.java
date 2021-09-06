package cloud.autotests.tests;

import cloud.autotests.config.App;
import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.ProjectPage;
import cloud.autotests.pages.ProjectsTable;
import cloud.autotests.pages.TestCasesTable;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;
import static com.codeborne.selenide.Selenide.open;

@Story("Test cases tests")
public class TestCasesTests extends TestBase {
    Faker faker = new Faker();
    private static final String PROJECT_NAME = "teacher qa_guru_diplom_project";

    //generate random name of TC each time
    private String testCaseName = "testCase_" + faker.number().randomNumber(10, false);

    @WithLogin
    @Test
    void shouldCreateTestCaseTest() {
        TestCasesTable testCasesTable = new TestCasesTable();
        ProjectsTable projectsTable = open(App.config.webUrl(), ProjectsTable.class);
        ProjectPage projectPage = projectsTable.navigateTo(PROJECT_NAME);
        projectPage.getSidebar().navigateTo(MenuItem.TEST_CASES);

        testCasesTable.unlockTestCasesTree();

        int numberOfTestCases = testCasesTable.getNumberOfTestCases();
        testCasesTable.createNewTestCase(testCaseName);
        testCasesTable.shouldHaveSize(numberOfTestCases + 1);
        testCasesTable.checkTestCaseIsDisplayed(testCaseName);

        testCasesTable.chooseTestCase(testCaseName);
        testCasesTable.clickBulkActionsButton();
        testCasesTable.deleteTestCases();
    }
}
