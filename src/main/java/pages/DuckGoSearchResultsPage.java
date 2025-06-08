package pages;

import com.codeborne.selenide.ElementsCollection;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$;

public class DuckGoSearchResultsPage {

    private final ElementsCollection results = $$(".LnpumSThxEWMIsDdAT17 a");

    public List<String> getURLs() {
        return results.stream()
                .map(el -> el.getAttribute("href"))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public boolean verifyURLsContainsQuery(String query) {
        return getURLs().stream()
                .allMatch(s -> s.contains(query.toLowerCase(Locale.ROOT)));
    }
}