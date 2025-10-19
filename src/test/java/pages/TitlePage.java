package pages;

import core.BasePage;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;

public class TitlePage extends BasePage {
    private final ElementsCollection topCastItems = $$("[data-testid='title-cast-item']");
    private final ElementsCollection actorTitle = $$("[data-testid='title-cast-item__actor']");
    private String savedActorTitle = "";

    @Step("Save the name of the third actor title")
    public TitlePage saveActorTitle() {
        savedActorTitle = actorTitle.get(2).getText();
        return this;
    }

    public ProfilePage clickOnThirdCastMember() {
        topCastItems.shouldHave(CollectionCondition.sizeGreaterThan(2));
        actorTitle.get(2).click(ClickOptions.usingJavaScript());
        return new ProfilePage();
    }

    @Step("Get saved actor title")
    public String getSavedActorTitle() {
        return savedActorTitle;
    }

    @Step("Get top cast count")
    public int getTopCastCount() {
        return topCastItems.size();
    }
}
