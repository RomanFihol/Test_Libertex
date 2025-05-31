package pages;

import Base.BasePage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DuckGoMainPage extends BasePage {

    public DuckGoMainPage() {
    }

    private final SelenideElement input = $("#searchbox_input");
    private final SelenideElement autoFillsuggestions = $("ul.searchbox_suggestionList__HFw4I");
    private final ElementsCollection suggestions = $$("ul.searchbox_suggestionList__HFw4I li");

    public void inputValue(String keyWord) {
        input.setValue(keyWord);
    }

    public void checkSuggestionsAreVisible() {
        autoFillsuggestions.shouldBe(Condition.visible);
    }

    public void checkSuggestionsSize(int size){
        suggestions.shouldHave(CollectionCondition.size(size));
    }

    public DuckGoSearchRelultsPage submitSearch (){
        input.pressEnter();
        return new DuckGoSearchRelultsPage();
    }

    public DuckGoSearchRelultsPage clickSuggestion(int index) {
        suggestions.get(index).click();
        return new DuckGoSearchRelultsPage();
    }

    public boolean allSuggestionsContainKeyword(String keyword) {
        return suggestions.stream()
                .map(SelenideElement::getText)
                .allMatch(text -> text.toLowerCase().contains(keyword.toLowerCase()));
    }
}
