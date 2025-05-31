package base;

import Base.BasePage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import pages.DuckGoMainPage;
import pages.GoogleMainPage;

import static com.codeborne.selenide.Selenide.open;

@Listeners({AllureTestNg.class})
public class BaseTest {

    private PageType pageType;

    public enum PageType {
        GOOGLE(GoogleMainPage.class),
        DUCKGO(DuckGoMainPage.class);

        private final Class<? extends BasePage> pageClass;

        PageType(Class<? extends BasePage> pageClass) {
            this.pageClass = pageClass;
        }

        public BasePage createInstance() {
            try {
                return pageClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Error creating page: " + pageClass.getSimpleName(), e);
            }
        }
    }

    @Parameters({"baseUrl", "browser", "pageType"})
    @BeforeClass
    public void setUp(String baseUrl, String browser, String pageTypeStr) {
        Configuration.browser = browser;
        Configuration.baseUrl = baseUrl;
        this.pageType = PageType.valueOf(pageTypeStr.toUpperCase());
    }
//    public void setUp() {
//
//        String baseUrl = System.getProperty("baseUrl", "https://duckduckgo.com/");
//        String browser = System.getProperty("browser", "chrome");
//        String pageTypeStr = System.getProperty("pageType", "DUCKGO");
//
//        Configuration.browser = browser;
//        Configuration.baseUrl = baseUrl;
//        this.pageType = PageType.valueOf(pageTypeStr.toUpperCase());
//    }

    public BasePage openHomePage() {
        open(Configuration.baseUrl);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        return pageType.createInstance();
    }
}

