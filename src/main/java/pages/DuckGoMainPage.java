package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DuckGoMainPage extends BasePage {

    private final SelenideElement inputField = $("#searchbox_input");
    private final SelenideElement autoFillSuggestions = $("ul.searchbox_suggestionList__HFw4I");
    private final ElementsCollection suggestions = $$("ul.searchbox_suggestionList__HFw4I li");

    public void fillInput(String keyWord) {
        inputField.setValue(keyWord);
    }

    public void checkSuggestionsAreVisible() {
        autoFillSuggestions.shouldBe(Condition.visible);
    }

    public void checkSuggestionsSize(int size){
        suggestions.shouldHave(CollectionCondition.size(size));
    }

    public DuckGoSearchRelultsPage submitSearch(){
        inputField.pressEnter();
        return new DuckGoSearchRelultsPage();
    }

    public DuckGoSearchRelultsPage clickSuggestion(int index) {
        suggestions.get(index).click();
        return new DuckGoSearchRelultsPage();
    }

    public List<String> getAllSuggestions() {
       List <String> suggestionsAsString = suggestions.stream()
               .map(SelenideElement::getText)
               .collect(Collectors.toList());
        return suggestionsAsString;
    }

    public void AllContains(){
        getAllSuggestions().forEach(System.out::println);
    }

    public boolean allSuggestionsContainKeyword(String keyword) {
        return suggestions.stream()
                .map(SelenideElement::getText)
                .allMatch(text -> text.toLowerCase().contains(keyword.toLowerCase()));
    }
}
