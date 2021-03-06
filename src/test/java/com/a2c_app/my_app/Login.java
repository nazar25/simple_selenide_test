package com.a2c_app.my_app;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

import com.a2c_app.AbstractObjects;
import a2c_app.Authorization;
import org.testng.annotations.Test;

public class Login extends AbstractObjects {

    @Test(groups = { "functest", "checkintest" })
    public void checkRequiredFields () {
        Authorization auth = page(Authorization.class);
        open(baseUrl);
        auth.checkCurrentUrl(baseUrl, APP_MAIN_PAGE_TITLE);
        auth.clickOnSignInLink();
        auth.checkRequiredFields(EMAIL);
    }

    @Test(groups = { "functest", "checkintest" })
    public void emailValidation () {
        Authorization auth = page(Authorization.class);
        auth.validateEmail(emails);
    }

    @Test(groups = { "functest", "checkintest" })
    public void tryLogInWithWrongCredentials () {
        Authorization auth = page(Authorization.class);
        auth.tryLogin(EMAIL, EMAIL);
        auth.checkLoginFailed();
    }

    @Test(groups = { "functest", "checkintest" })
    public void tryToLogInIntoA2c () {
        Authorization auth = page(Authorization.class);
        auth.tryLogin(EMAIL, PASS);
        auth.checkLoginSuccess();
    }

    @Test(groups = { "functest", "checkintest" })
    public void tryToLogOutFromA2c () {
        Authorization auth = page(Authorization.class);
        auth.logOutFromAccount();
        auth.checkCurrentUrl(PUBLIC_URL, PUBLIC_MAIN_PAGE_TITLE);
    }

}
