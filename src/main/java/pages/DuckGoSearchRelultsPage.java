package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;

import java.util.Locale;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$$;

public class DuckGoSearchRelultsPage {
    private final ElementsCollection results = $$(".LnpumSThxEWMIsDdAT17 a");

    public boolean checkURLs(String expectedValue) {
        results.shouldBe(CollectionCondition.sizeGreaterThan(0));
        boolean allContain = results.stream()
                .map(el -> el.getAttribute("href"))
                .filter(Objects::nonNull)
                .anyMatch(href -> href.toLowerCase().contains((expectedValue.toLowerCase(Locale.ROOT))));
        return allContain;
    }

}