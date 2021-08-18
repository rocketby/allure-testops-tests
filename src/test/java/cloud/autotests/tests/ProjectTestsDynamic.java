package cloud.autotests.tests;

import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.ProjectsTable;
import cloud.autotests.pages.components.Sidebar;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.*;

@Story("Projects tests dynamic interactions")
public class ProjectTestsDynamic extends TestBase{

    @WithLogin
    @Test
    void deleteProjectCreatedInThisTest(){
        //precondition
        String projectName = "testuser-testproject-toBeDeleted";
        String projectAbbr = "TD";

        step("Open main page", () ->
             open(""));
        step("click on a button 'New project'", () ->
             $$("button.Button_style_success").find(Condition.text("New project")).click());
        step("fill obligatory fields name with {projectName} and abbreviation with {projectAbbr}", () -> {
             $("input[name=name]").setValue(projectName);
             $("input[name=abbr]").setValue(projectAbbr);
        });
        step("click submit, creating {projectName} project", () ->
            $("button.Button_style_success[type=submit]").click()
        );
        //test
        step("navigate to 'settings' in sidebar and click", () -> {
            Sidebar sidebar = new Sidebar();
            sidebar.navigateTo(MenuItem.SETTINGS);
        });
        step("push 'delete' button and confirm a deletion'", () -> {
            $$("button.Button_style_danger").find(Condition.text("Delete project")).click();
            $$("button.Button_style_danger").find(Condition.exactText("Delete")).click();
        });
        //check if all have gone as it was planned
        step("confirm the project {projectName} deletion", () -> {
            $("input.HomeLayout__search").setValue("testuser-testproject-toBeDeleted");
            ProjectsTable projectsTable = new ProjectsTable();
            assertThat(projectsTable.size).isEqualTo(0);
        });
    }
}
