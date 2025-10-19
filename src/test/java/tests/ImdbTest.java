package tests;

import config.TestBase;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static io.qameta.allure.Allure.step;

import pages.MainPage;
import pages.TitlePage;
import pages.ProfilePage;

import static testdata.TestData.SEARCH_QUERY;

public class ImdbTest extends TestBase {

    private final MainPage mainPage = new MainPage();

    @Test(description = "Verify search results and navigation flow from search to profile on IMDb")
    public void testSearchAndNavigateToProfile() {

        mainPage
            .openMainPage()
            .searchFor(SEARCH_QUERY)
            .saveFirstTitle();

        TitlePage titlePage = mainPage.clickFirstTitle();

        step("Verify title matches saved title", () ->
            assertEquals(
                titlePage.getPageTitle(),
                mainPage.getSavedTitle()
            )
        );

        step("Verify top cast count > 3", () ->
            assertTrue(
                titlePage.getTopCastCount() > 3
            )
        );

        titlePage.saveActorTitle();
        ProfilePage profilePage = titlePage.clickOnThirdCastMember();

        step("Verify correct actor profile opened", () ->
            assertEquals(
                profilePage.getPageTitle(),
                titlePage.getSavedActorTitle()
            )
        );
    }
}
