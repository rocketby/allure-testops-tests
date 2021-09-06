package cloud.autotests.tests;

import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.ProjectPage;
import cloud.autotests.pages.ProjectsTable;
import cloud.autotests.pages.TestCasesTable;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Story("Project tests")
public class CreateProjectTests extends TestBase {



    @WithLogin
    @Test
    void projectShouldBeCreatedByUi() {


        open("");
        step("Click on button 'New project'", () ->
                $(byText("New project")).click());
        step("Fill obligatory fields name with {projectName} and abbreviation with {projectAbbr}", () ->
                $("input[name=name]").setValue(projectName)
        );
        step("Click submit, creating {projectName} project", () ->
                $("button.Button_style_success[type=submit]").click()
        );
//        ProjectPage projectPage = projectsTable.navigateTo(PROJECT_NAME);
//        projectPage.checkThatWidgetsDisplayed();
    }



    // generate new project name with faker

    /*
    https://allure.autotests.cloud/api/rs/project
    {"isPublic":true,"name":"test 1231"}
    
    {
        "id": 384,
        "name": "test 1231",
        "isPublic": true,
        "createdDate": 1630948952313,
        "lastModifiedDate": 1630948952313,
        "createdBy": "admin",
        "lastModifiedBy": "admin"
    }
     */

}
