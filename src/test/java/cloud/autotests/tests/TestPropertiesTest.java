package cloud.autotests.tests;

import cloud.autotests.config.App;
import cloud.autotests.data.MenuItem;
import cloud.autotests.helpers.WithLogin;
import cloud.autotests.pages.ProjectPage;
import cloud.autotests.pages.ProjectsTable;
import cloud.autotests.pages.TestProperties;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

@Story("Properties of Tests")
public class TestPropertiesTest extends TestBase{
    private static final String PROJECT_NAME = "teacher qa_guru_diplom_project";

    @WithLogin
    @Test
    void checkTestProperties(){
        ProjectsTable projectsTable = open(App.config.webUrl(), ProjectsTable.class);
        ProjectPage projectPage = projectsTable.navigateTo(PROJECT_NAME);
        projectPage.getSidebar().navigateTo(MenuItem.TEST_CASES);

        $$(".LoadableTree__view > li").findBy(text("New")).click();

        TestProperties testProperties = new TestProperties();
        testProperties.shouldHaveSize(8);

        testProperties.shouldHaveElement("Tags");
        testProperties.shouldHaveElement("History");
        testProperties.shouldHaveElement("Issues links");
        testProperties.shouldHaveElement("Members");
        testProperties.shouldHaveElement("Fields");
        testProperties.shouldHaveElement("Mutes");
        testProperties.shouldHaveElement("Relations");
        testProperties.shouldHaveElement("Description");
        testProperties.shouldHaveElement("Scenario");
        testProperties.shouldHaveElement("Comments");


    }
}
