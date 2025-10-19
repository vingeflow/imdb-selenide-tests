package pages;

import core.BasePage;
import io.qameta.allure.Step;
import static config.TestBase.BASE_URL;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage extends BasePage {

    private final SelenideElement searchInput = $("[data-testid='suggestion-search']");
    private final SelenideElement firstTitle = $$("[data-testid='search-result--const']")
            .filterBy(Condition.attributeMatching("href", ".*\\/title\\/.*")).first();
    private String savedTitle = "";

    @Step("Open IMDb home page")
    public MainPage openMainPage() {
        openPage(BASE_URL);
        return this;
    }

    @Step("Search for text: {query}")
    public MainPage searchFor(String query) {
        searchInput.setValue(query);
        return this;
    }

    @Step("Save the name of the first title")
    public MainPage saveFirstTitle() {
        savedTitle = firstTitle.$(".searchResult__constTitle").getText();
        return this;
    }

    @Step("Click on the first title in the dropdown")
    public TitlePage clickFirstTitle() {
        firstTitle.click();
        return new TitlePage();
    }

    @Step("Get saved title")
    public String getSavedTitle() {
        return savedTitle;
    }
}
