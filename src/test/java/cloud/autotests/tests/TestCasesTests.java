package cloud.autotests.tests;

import cloud.autotests.api.Project;
import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.TestCasesTable;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;

@Story("Test cases tests")
public class TestCasesTests extends TestBase {

    Faker faker = new Faker();
    private String projectName = "testuser-testproject-toBeDeleted_" + faker.random().hex(6);
    private String testCaseName = "testCase_" + faker.random().hex(6);

    @WithLogin
    @Test
    void shouldCreateTestCaseTest() {
        //create project
        Response createProjectResponse = new Project().createProject(projectName, true);
        Integer projectId = createProjectResponse.path("id");
        projectPage.openPage(projectId);
        projectPage.getSidebar().navigateTo(MenuItem.TEST_CASES);

        //create test case in project
        TestCasesTable testCasesTable = new TestCasesTable();
        testCasesTable.unlockTestCasesTree();
        int numberOfTestCases = testCasesTable.getNumberOfTestCases();
        testCasesTable.createNewTestCase(testCaseName);

        //check, that test cases is created
        testCasesTable.shouldHaveSize(numberOfTestCases + 1);
        testCasesTable.checkTestCaseIsDisplayed(testCaseName);

        //cleanup of test data - delete test case
        testCasesTable.chooseTestCase(testCaseName);
        testCasesTable.clickBulkActionsButton();
        testCasesTable.deleteTestCases();

        //cleanup of test data - delete test project
        projectPage.getSidebar().navigateTo(MenuItem.SETTINGS);
        projectPage.deleteProject();

    }
}
