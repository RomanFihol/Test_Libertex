package ui;

import base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.GoogleMainPage;

public class ElementsCheckTest extends BaseTest {
    @Parameters({"pageType"})
    @Test
    public void elementsCheck() throws InterruptedException {
       GoogleMainPage googleMainPage = (GoogleMainPage) openHomePage();
            googleMainPage.acceptCoockiesIfVisible();
            Thread.sleep(2000);
            googleMainPage.checkAllTheMainElementsAreVisible();
    }
}
