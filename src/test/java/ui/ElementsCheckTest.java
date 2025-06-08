package ui;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.DuckGoMainPage;

public class ElementsCheckTest extends BaseTest {

    @Test(description = "Check that all main elements are presented on the main page")
    public void elementsCheck() {
        DuckGoMainPage duckGoMainPage = openHomePage();
        duckGoMainPage.checkAllMainElementsVisible();
    }
}