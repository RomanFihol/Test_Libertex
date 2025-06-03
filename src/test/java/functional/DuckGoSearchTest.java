package functional;

import base.BaseTest;
import base.SearchDataProvider;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DuckGoMainPage;
import pages.DuckGoSearchRelultsPage;

import java.util.Locale;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$$;

public class DuckGoSearchTest extends BaseTest {

    private final ElementsCollection results = $$(".LnpumSThxEWMIsDdAT17 a");

    @Test(description = "Compare search results", dataProvider = "searchRequests", dataProviderClass = SearchDataProvider.class)
    public void checkSearchResults(String request, String expectedResults) {
        DuckGoMainPage duckGoMainPage = (DuckGoMainPage) openHomePage();
        duckGoMainPage.fillInput(request);
        DuckGoSearchRelultsPage duckGoSearchRelultsPage = duckGoMainPage.submitSearch();
        duckGoSearchRelultsPage.checkURLs(expectedResults);
        Assert.assertTrue(duckGoSearchRelultsPage.checkURLs(expectedResults));
    }

    @Test(description = "Check the list of suggestions", dataProvider = "suggestions", dataProviderClass = SearchDataProvider.class)
    public void checkSuggestions(String suggestion) {
        DuckGoMainPage duckGoMainPage = (DuckGoMainPage) openHomePage();
        duckGoMainPage.fillInput(suggestion);
        duckGoMainPage.checkSuggestionsAreVisible();
        duckGoMainPage.checkSuggestionsSize(8);
        Assert.assertTrue(duckGoMainPage.allSuggestionsContainKeyword(suggestion));
        duckGoMainPage.clickSuggestion(4);
        Assert.assertTrue(checkURLs(suggestion));
    }

    @Step("Check that url contains query")
    public boolean checkURLs(String expectedValue) {
        results.shouldBe(CollectionCondition.sizeGreaterThan(0));
        boolean allContain = results.stream()
                .map(el -> el.getAttribute("href"))
                .filter(Objects::nonNull)
                .anyMatch(href -> href.toLowerCase().contains((expectedValue.toLowerCase(Locale.ROOT))));
        return allContain;
    }
}
