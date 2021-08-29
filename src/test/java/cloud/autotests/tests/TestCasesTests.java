package cloud.autotests.tests;

import cloud.autotests.config.App;
import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.ProjectPage;
import cloud.autotests.pages.ProjectsTable;
import cloud.autotests.pages.TestCasesTable;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Story("Test cases tests")
public class TestCasesTests extends TestBase {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private static final String PROJECT_NAME = "teacher qa_guru_diplom_project";
    private String testCaseName = "Test0001_Tat_"+timestamp.getTime(); //generate unique name of TC each time

    @WithLogin
    @Test
    void shouldCreateTestCaseTest() {
        TestCasesTable testCasesTable = new TestCasesTable();

        step("Navigating to Test Cases menu", () -> {
            ProjectsTable projectsTable = open(App.config.webUrl(), ProjectsTable.class);
            ProjectPage projectPage = projectsTable.navigateTo(PROJECT_NAME);
            projectPage.getSidebar().navigateTo(MenuItem.TEST_CASES);
        });

        step("Unlock test cases menu tree", () -> {
            testCasesTable.unlockTestCasesTree();
        });

        step("Create new test case and check, that number of test cases increased", () -> {
            int numberOfTestCases = testCasesTable.getNumberOfTestCases();
            testCasesTable.createNewTestCase(testCaseName);
            testCasesTable.shouldHaveSize(numberOfTestCases + 1);
        });

        step("Check, that new test case is displayed in test cases table", () -> {
            testCasesTable.checkTestCaseIsDisplayed(testCaseName);
        });

        step("Choose the test case", () -> {
            testCasesTable.chooseTestCase(testCaseName);
        });

        step("Click bulk actions button", () -> {
            testCasesTable.clickBulkActionsButton();
        });

        step("Delete test case", () -> {
            testCasesTable.deleteTestCases();
        });
    }
}
