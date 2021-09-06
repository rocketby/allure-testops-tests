package cloud.autotests.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;

public class MainPage {

    @Step("Open main page")
    public void openPage() {
        open("");
    }

    public ProjectsTable getProjectsTable() {
        return new ProjectsTable();
    }

    @Step("Creating a new project from a main page")
    public ProjectPage createNewProject(String projectName) {
        step("Click on a button 'New project'", () ->
                $("button.Button_style_success").click()
        );
        step("Fill obligatory fields name with {projectName} and abbreviation with {projectAbbr}", () ->
                $("input[name=name]").setValue(projectName)
        );
        step("Click submit, creating {projectName} project", () ->
                $("button.Button_style_success[type=submit]").click()
        );
        return new ProjectPage();
    }

    public void filterProject(String projectName) {
        step("confirm the project {projectName} deletion", () -> {
            $("input.HomeLayout__search").setValue(projectName);
            sleep(500); // иначе через раз падает
        });
    }
}
