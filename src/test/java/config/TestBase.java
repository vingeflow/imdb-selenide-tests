package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import static com.codeborne.selenide.Selenide.closeWebDriver;

@Listeners({ io.qameta.allure.testng.AllureTestNg.class })
public class TestBase {

    public static final String BASE_URL = System.getProperty("baseUrl", "https://www.imdb.com/");

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 6000;
        Configuration.pageLoadStrategy = "normal";
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        // Configure Chrome to avoid 403 Forbidden on IMDb when running in headless mode
        // Disables automation flags and sets a normal User-Agent.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled",
                "--window-size=1920,1080",
                "--headless=new",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-gpu",
                "--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/141 Safari/537.36");
        Configuration.browserCapabilities = options;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            attachScreenshot();
            attachPageSource();
        }
        closeWebDriver();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] attachScreenshot() {
        try {
            return ((TakesScreenshot) WebDriverRunner.getWebDriver())
                    .getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            return new byte[0];
        }
    }

    @Attachment(value = "Page Source", type = "text/html")
    private byte[] attachPageSource() {
        try {
            return WebDriverRunner.source().getBytes();
        } catch (Exception e) {
            return "<no page source>".getBytes();
        }
    }
}
