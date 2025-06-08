package functional;

import base.BaseTest;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DuckGoMainPage;
import pages.DuckGoSearchResultsPage;

import java.util.List;
import java.util.Locale;

public class DuckGoSearchTest extends BaseTest {

    @DataProvider(name = "searchRequests")
    public static Object[][] searchQueries() {
        return new Object[][]{
                {"YouTube", "youtube"},
                {"Linkedin", "Linkedin"}
        };
    }

    @DataProvider(name = "suggestions")
    public static Object[][] suggestions() {
        return new Object[][]{
                {"Fire"},
                {"Water"}
        };
    }

    @Test(description = "Compare search results", dataProvider = "searchRequests")
    public void checkSearchResults(String request, String expectedResults) {
        DuckGoMainPage mainPage = openHomePage();
        performSearchAndValidateResults(mainPage, request, expectedResults);
    }

    @Test(description = "Check the list of suggestions", dataProvider = "suggestions")
    public void checkSuggestions(String suggestion) {
        DuckGoMainPage mainPage = openHomePage();
        performSuggestionValidationFlow(mainPage, suggestion);
    }

    @Test(description = "Check random suggestion", dataProvider = "suggestions")
    public void checkRandomSuggestion(String expectedResult) {
        DuckGoMainPage mainPage = openHomePage();
        clickOnRandomSuggestion(mainPage, expectedResult);

    }

    @Step("Enter the query '{query}' and check if it in the URL results")
    private void performSearchAndValidateResults(DuckGoMainPage page, String query, String expectedResult) {
        DuckGoSearchResultsPage searchResultsPage = page.submitSearch(query);
        Assert.assertTrue(searchResultsPage.verifyURLsContainsQuery(expectedResult));
    }

    @Step("Check that the suggestion list has 8 elements and contains the query")
    private void performSuggestionValidationFlow(DuckGoMainPage page, String query) {
        page.fillInput(query);
        page.checkSuggestionsAreVisible();
        page.checkSuggestionsSize(8);
        List<String> suggestions = page.getAllSuggestions();
        Assert.assertTrue(suggestions.stream().allMatch(s -> s.contains(query.toLowerCase(Locale.ROOT))));
    }

    @Step("Click on the suggestion and check that keyword is presented on the search results")
    public void clickOnRandomSuggestion(DuckGoMainPage page, String query) {
        page.fillInput(query);
        DuckGoSearchResultsPage resultsPage = page.clickSuggestion(page.generateRandomIndex());
        Assert.assertTrue(resultsPage.verifyURLsContainsQuery(query));
    }
}