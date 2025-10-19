package core;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.SelenideElement;

public abstract class BasePage {

    private final SelenideElement pageTitle = $("[data-testid='hero__primary-text']");

    @Step("Open URL: {url}")
    public void openPage(String url) {
        open(url);
    }

    @Step("Get page title")
    public String getPageTitle() {
        return pageTitle.getText();
    }
}
