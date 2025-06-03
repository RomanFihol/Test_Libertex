package functional;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GoogleMainPage;

public class GoogleCookieTest extends BaseTest {

    private final String coockiName = "NID";

    @Test(description = "Reject cookies")
    public void rejectCookiesTest() {
        GoogleMainPage googleSearchMainPage = (GoogleMainPage) openHomePage();
        googleSearchMainPage.rejectCookiesIfVisible();
        googleSearchMainPage.waitForCookiePresence(coockiName, false, 5);
        Assert.assertFalse(googleSearchMainPage.checkIfCookieIsPresent(coockiName));
    }

    @Test(description = "Accept cookies")
    public void acceptCookiesTest() {
        GoogleMainPage googleSearchMainPage = (GoogleMainPage) openHomePage();
        googleSearchMainPage.acceptCookiesIfVisible();
        googleSearchMainPage.waitForCookiePresence(coockiName, true, 5);
        Assert.assertTrue(googleSearchMainPage.checkIfCookieIsPresent(coockiName));
    }
}