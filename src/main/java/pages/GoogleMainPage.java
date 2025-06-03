package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.util.Map.entry;

public class GoogleMainPage extends BasePage {

    SoftAssert softAssert = new SoftAssert();

    private final SelenideElement coockiesFrame = $("#CXQnmb");
    private final SelenideElement aboutGoogleButton = $x("//a[contains(text(), 'Sobre Google')]");
    private final SelenideElement shopButton = $x("//a[contains(text(), 'Tienda')]");
    private final SelenideElement gmailButton = $x("//a[@class='gb_X' and contains(text(),'Gmail')]");
    private final SelenideElement imagesButton = $x("//a[@class='gb_X' and contains(text(),'Imágenes')]");
    private final SelenideElement applicationsButton = $x("//a[@aria-label='Aplicaciones de Google']");
    private final SelenideElement signIntButton = $x("//a[@aria-label='Iniciar sesión']");
    private final SelenideElement googleMainSymbol = $("div.k1zIA.rSk4se");
    private final SelenideElement searchField = $("div.RNNXgb");
    private final SelenideElement searchSymbol = $("div.hvhmMe");
    private final SelenideElement microphoneSymbol = $("div.XDyW0e");
    private final SelenideElement uploadPhotoSymbol = $("div.nDcEnd");
    private final SelenideElement searchWithGoogleButton = $x("//div[@class='FPdoLc lJ9FBc']//input[@aria-label='Buscar con Google']");
    private final SelenideElement feelingLuckyButton = $x("//div[@class='FPdoLc lJ9FBc']//input[@aria-label='Voy a tener suerte']");
    private final SelenideElement proposalLanguages = $("div#SIvCob");
    private final SelenideElement currentCountry = $("div.uU7dJb");
    private final SelenideElement report = $("div.KxwPGc.ssOUyb"); // Is visible only for spanish language of the Google page
    private final SelenideElement aboutButton = $x("//div[@class='KxwPGc AghGtd']/a[contains(text(),'Publicidad')]");
    private final SelenideElement advertisingButton = $x("//div[@class='KxwPGc AghGtd']/a[contains(text(),'Empresa')]");
    private final SelenideElement businessButton = $x("//a[@class='pHiOh' and contains(text(), 'Búsqueda')]");
    private final SelenideElement privacyButton = $x("//div[@class='KxwPGc iTjxkf']/a[contains(text(),'Privacidad')]");
    private final SelenideElement termsButton = $x("//div[@class='KxwPGc iTjxkf']/a[contains(text(),'Términos')]");
    private final SelenideElement settingsButton = $("div.ayzqOc.pHiOh");
    private final SelenideElement rejectCookiesSelector = $("#W0wltc");
    private final SelenideElement acceptCookiesSelector = $("#L2AGLb");

    @Step("All elements are presented")
    public void checkAllTheMainElementsAreVisible() {
        acceptCookiesIfVisible();
        Map<String, SelenideElement> elementsToCheck = Map.ofEntries(
                entry("Main element", googleMainSymbol),
                entry("About Google", aboutGoogleButton),
                entry("Shop", shopButton),
                entry("Gmail button", gmailButton),
                entry("Images button", imagesButton),
                entry("Applications button", applicationsButton),
                entry("Sign in button", signIntButton),
                entry("Search field", searchField),
                entry("Search symbol", searchSymbol),
                entry("Microphone symbol", microphoneSymbol),
                entry("Upload photo symbol", uploadPhotoSymbol),
                entry("Search with Google button", searchWithGoogleButton),
                entry("I'm feeling lucky button", feelingLuckyButton),
                entry("Proposal languages", proposalLanguages),
                entry("Current country", currentCountry),
                entry("Report about environment", report),
                entry("About button", aboutButton),
                entry("Advertising button", advertisingButton),
                entry("Business button", businessButton),
                entry("Privacy button", privacyButton),
                entry("Terms button", termsButton),
                entry("Settings button", settingsButton)
        );

        for (Map.Entry<String, SelenideElement> entry : elementsToCheck.entrySet()) {
            String elementName = entry.getKey();
            SelenideElement element = entry.getValue();
            softAssert.assertTrue(element.isDisplayed(), "Element is not presented : " + elementName);
        }
        softAssert.assertAll();
    }

    public void rejectCookiesIfVisible() {
        if (coockiesFrame.is(Condition.visible))
            rejectCookiesSelector.click();
    }

    public void acceptCookiesIfVisible() {
        if (coockiesFrame.is(Condition.visible))
            acceptCookiesSelector.click();
    }
}
