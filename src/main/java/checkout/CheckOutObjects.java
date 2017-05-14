package checkout;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.Html;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CheckOutObjects {

    public final String cartAttribute = "data-cart-quantity";

    @FindBy(xpath = "//div[@id='right-panel-content-wrapper']/ul/li[3]/div[contains(@class,'product-price-wrapper')]/div[contains(@class,'bottom')]/div/div/a")
    public SelenideElement thirdProduct;

    @FindBy(xpath = "//div[@id='right-panel-content-wrapper']/ul/li[3]/div[contains(@class,'product-price-wrapper')]/div[contains(@class,'bottom')]/div/div[contains(@class,'in-cart-spin')]")
    public SelenideElement basketWithProduct;

    @FindBy(xpath = "//*[@id='header-wrapper']/header/div/div[contains(@class,'right')]/ul/li[contains(@class,'last')]/div[contains(@class,'cart-indicator')]/a")
    public SelenideElement cart;

    @FindBy(xpath = "//*[@class='search-wrapper']/form/div/div[contains(@class,'search-field')]/label/input")
    public SelenideElement searchField;

    @FindBy(xpath = "//div[@id='right-panel-content-wrapper']/ul/li/div[contains(@class,'product-price-wrapper')]/div[contains(@class,'bottom')]/div/div/a")
    public SelenideElement addToCart;

    @FindBy(xpath = "//*[@class='quantity-input']/a[contains(@class,'plus btn')]/span")
    public SelenideElement increaseCount;

    @FindBy(xpath = "//*[@class='quantity-input']/a[contains(@class,'minus btn')]/span")
    public SelenideElement decreaseCount;

    @FindBy(xpath = "//table[@class='sortable-table']/tbody/tr[contains(@data-id,'847372')]/td[contains(@class,'price-cell-wrapper')]/div/div/div/span[contains(@class,'product-price special-price')]")
    public SelenideElement productSpecialPriceExist;

    @FindBy(xpath = "//table[@class='sortable-table']/tbody/tr[contains(@data-id,'849356')]/td[contains(@class,'price-cell-wrapper')]/div/div/div/span[contains(@class,'product-price special-price')]")
    public SelenideElement productSpecialPriceNotExist;

    @FindBy(id = "goto-checkout")
    public SelenideElement proceedToCheckout;

    @FindBy(xpath = "//*[@id='login-view']/form[contains(@class,'styled-form login ajax_form')]/table/tfoot/tr/td[contains(@class,'left-cell buy-without-signup-cell')]/span/a")
    public SelenideElement buyWithoutRegistration;

    @FindBy(className = "styled-link-checkout")
    public SelenideElement nextStep;

    @FindBy(xpath = "//div[@data-sm-role='checkout.address-form']/h1[contains(text(),'Contact information')]")
    public SelenideElement contactInfoLable;

    public void clickOnElement(WebElement element) {
        $(element).shouldBe(visible).click();
    }

    public void checkElementVisible(WebElement element) {
        $(element).shouldBe(visible);
    }

    public void checkElementNotVisible(WebElement element) {
        $(element).shouldNot(visible);
    }

    public void insertTextAndSubmit(WebElement element, String value) {
        $(element).shouldBe(visible).setValue(value).pressEnter();
    }

    public void checkElementHasAttributeValue(WebElement element, String attributeName, String value) {
        $(element).shouldHave(attribute(attributeName, value));
    }

    public void checkCurrentUrl(String correctUrl, String pageTitle) {
        assertTrue(Html.text.contains(url(), correctUrl));
        assertEquals(getWebDriver().getTitle(), pageTitle);
    }

}
