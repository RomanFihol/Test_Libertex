package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DuckGoMainPage {

    private final SelenideElement learnAboutLogo = $$(".header_headerLeft__rW6nD").filter(Condition.visible).first();
    private final SelenideElement inputField = $("#searchbox_input");
    private final SelenideElement searchSymbol = $(".searchbox_iconWrapper__qAk7y");
    private final SelenideElement duckAI = $$(".header_aiChatButton__e_9yJ").filter(Condition.visible).first();
    private final SelenideElement menuButton = $$(".header_headerButton__InWgw").filter(Condition.visible).first();
    private final SelenideElement mainText = $(".homepage-cta-section_title__yh7tH");
    private final SelenideElement homePageCards = $(".desktop-homepage_ctaCards__L6Zai");
    private final SelenideElement tagLine = $(".desktop-homepage_tagline__6CQI_");
    private final SelenideElement suggestionsList = $("ul.searchbox_suggestionList__HFw4I");
    private final ElementsCollection suggestions = $$("ul.searchbox_suggestionList__HFw4I li");

    public void fillInput(String keyword) {
        inputField.setValue(keyword);
    }

    public void checkSuggestionsAreVisible() {
        suggestionsList.shouldBe(Condition.visible);
    }

    public void checkSuggestionsSize(int expectedSize) {
        suggestions.shouldHave(CollectionCondition.size(expectedSize));
    }

    public DuckGoSearchResultsPage submitSearch(String query) {
        inputField.setValue(query).pressEnter();
        return new DuckGoSearchResultsPage();
    }

    public DuckGoSearchResultsPage clickSuggestion(int index) {
        suggestions.get(index).click();
        return new DuckGoSearchResultsPage();
    }

    public List<String> getAllSuggestions() {
        return suggestions.stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }

    public int generateRandomIndex() {
        return new Random().nextInt(8);
    }

    @Step("Check main elements")
    public void checkAllMainElementsVisible() {
        List<SelenideElement> elements = Arrays.asList(
                learnAboutLogo,
                inputField,
                searchSymbol,
                duckAI,
                menuButton,
                mainText,
                homePageCards,
                tagLine
        );

        elements.forEach(el -> el.shouldBe(Condition.visible));
    }
}