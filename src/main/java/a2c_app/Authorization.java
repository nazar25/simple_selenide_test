package a2c_app;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.Html;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static junit.framework.TestCase.*;


public class Authorization{
    @FindBy(id = "loginLink")
        private SelenideElement signInLink;
    @FindBy(id = "account_email")
        private SelenideElement signInEmailField;
    @FindBy(id = "account_password")
        private SelenideElement signInPasswordField;
    @FindBy(xpath = "//button[@class='btn btn-green'][contains(text(),'Login')]")
        private SelenideElement signInButton;
    @FindBy(xpath = "//a[@title='Sign Out'][contains(text(),'Logoff')]")
        private SelenideElement logOfElement;
    @FindBy(id = "msg_error_login")
        private SelenideElement signInError;

    public void clickOnSignInLink () {
        $(signInLink).shouldBe(visible).click();
    }

    public void checkCurrentUrl (String correctUrl, String pageTitle) {
        assertTrue(Html.text.contains(url(), correctUrl));
        assertEquals(getWebDriver().getTitle(), pageTitle);
    }

    public void tryLogin (String email, String password) {
        $(signInEmailField).shouldBe(visible).setValue(email);
        $(signInPasswordField).shouldBe(visible).setValue(password);
        $(signInButton).shouldBe(visible).click();
    }

    public void checkLoginSuccess () {
        $(logOfElement).shouldBe(visible);
    }

    public void checkLoginFailed () {
        $(signInError).shouldBe(visible).
                shouldHave(exactText("Incorrect login or password. Please try again"));
    }

    public void logOutFromAccount () {
        $(logOfElement).shouldBe(visible).click();
    }

    public void validateEmail (String[] incorrectEmail) {
        for (int i=0; i<incorrectEmail.length; i++) {
            $(signInEmailField).shouldBe(visible).setValue(incorrectEmail[i]).pressEnter();
            $(signInError).shouldBe(visible).
                    shouldHave(exactText("The Email address you have entered is invalid!"));
            $(signInError).click();
        }
    }

    public void checkRequiredFields (String email) {
        $(signInButton).shouldBe(visible).click();
        $(signInError).shouldBe(visible).
                shouldHave(exactText("Email is required!"));
        $(signInError).click();
        $(signInEmailField).setValue(email).pressEnter();
        $(signInError).shouldBe(visible).
                shouldHave(exactText("Password is required!"));
    }
}
