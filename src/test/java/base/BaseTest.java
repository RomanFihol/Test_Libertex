package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import pages.DuckGoMainPage;

import static com.codeborne.selenide.Selenide.open;

@Listeners({AllureTestNg.class})
public class BaseTest {

    @Parameters({"baseUrl", "browser"})
    @BeforeClass
    public void setUp(String baseUrl, String browser) {
        Configuration.browser = browser;
        Configuration.baseUrl = baseUrl;
    }

    public DuckGoMainPage openHomePage() {
        open(Configuration.baseUrl);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        return new DuckGoMainPage();
    }
}