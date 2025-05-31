package Base;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BasePage {

    WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(10));

    public void waitForCookiePresence(String cookieName, boolean shouldExist, int timeoutSeconds) {
        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(timeoutSeconds)).until(driver -> {
            boolean isPresent = driver.manage().getCookieNamed(cookieName) != null;
            return shouldExist == isPresent;
        });
    }

    public List<String> getAllCookieNames() {
        Set<Cookie> cookies = wait.until(driver -> driver.manage().getCookies());
        return cookies.stream().map(Cookie::getName).collect(Collectors.toList());
    }

    public boolean checkIfCookieIsPresent (String coockieName){
        return getAllCookieNames().contains(coockieName);
    }
}