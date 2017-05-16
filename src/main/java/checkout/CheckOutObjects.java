package checkout;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.impl.Html;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CheckOutObjects {

    private Integer k = 1;
    private Integer m = 0;
    private Integer countOfNodes;
    public final String cartAttribute = "data-cart-quantity";
    public final String priceAttribute = "data-price";

    private List<String> count = new ArrayList<String>();
    private List<String> price = new ArrayList<String>();

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

    @FindBy(xpath = "//*[@id='right-panel-content-wrapper']/ul/li/div[contains(@class,'product-price-wrapper')]/div[contains(@class,'top')]/div[contains(@class,'price-block')]/div/span[contains(@class,'product-price special-price')]")
    public SelenideElement productSpecialPrice;

    @FindBy(xpath = "//div[@class='quantity-input']/input")
    public SelenideElement quantityField;

    public void clickOnElement(WebElement element) {
        $(element).shouldBe(visible).click();
    }

    public void checkElementVisible(WebElement element) {
        $(element).shouldBe(visible);
    }

    public void mouseOver(WebElement element) {
        $(element).hover();
    }

    public void checkElementNotVisible(WebElement element) {
        $(element).shouldNot(visible);
    }

    public void insertText(WebElement element, String value) {
        $(element).shouldBe(visible).setValue(value);
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

    public void getRules() {
        WebElement nodes [] = WebDriverRunner.getWebDriver().findElements(By.xpath("//div[@class='info-icon web-price']/table/tbody/tr[td]")).toArray(new WebElement[0]);
        countOfNodes = nodes.length;

        for (Integer i=0; i<countOfNodes; i++) {
            SelenideElement ruleValueQuantity = $x("//div[@class='info-icon web-price']/table/tbody/tr[td]["+ k +"]/td[1]");
            SelenideElement ruleValuePrice = $x("//div[@class='info-icon web-price']/table/tbody/tr[td]["+ k +"]/td[2]");
            count.add(ruleValueQuantity.innerText().substring(0, 1));
            price.add(ruleValuePrice.innerText());
            k++;
        }
    }

    public void checkRules() {
        SelenideElement priceLable = $x("//div[@class='price-block']/div[contains(@class,'price')]/span[contains(@data-price,'"+ price.get(m) +"')]");
        this.clickOnElement(this.addToCart);
        this.checkElementVisible(priceLable);
        for (m=1; m<countOfNodes; m++) {
            this.insertTextAndSubmit(quantityField, count.get(m));
            productSpecialPrice.waitUntil(appear, 2000);
            this.checkElementVisible(productSpecialPrice);
            this.checkElementHasAttributeValue(productSpecialPrice, priceAttribute, price.get(m));
        }

        m--;
        this.clickOnElement(increaseCount);
        productSpecialPrice.waitUntil(appear, 2000);
        this.checkElementVisible(productSpecialPrice);
        this.checkElementHasAttributeValue(productSpecialPrice, priceAttribute, price.get(m));

        this.insertTextAndSubmit(quantityField, "1");
        this.clickOnElement(decreaseCount);
        this.checkElementVisible(addToCart);
    }

}
