package base;

import org.testng.annotations.DataProvider;

public class SearchDataProvider {
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
}
