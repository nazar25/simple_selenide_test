package com.a2c_app;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.listener.TestListener;
import org.testng.annotations.Listeners;

import java.io.IOException;

@Listeners(TestListener.class)
public class AbstractObjects {

    protected static final String
            EMAIL = "a2c.qa.2015@gmail.com",
            PASS = "ytrewq123456",
            APP_MAIN_PAGE_TITLE = "API2Cart - Register New Account",
            PUBLIC_MAIN_PAGE_TITLE = "API2Cart – Unified Shopping Cart Data Interface",
            PUBLIC_URL = "https://www.api2cart.com/";

    protected static final String[] emails = {
            "test",
            "test@",
            "@test",
            "test@test"
    };

    @BeforeClass
    public void SetUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://beta.api2cart.com";
        Configuration.timeout = 10000;
    }

    @AfterClass(alwaysRun = true)
    public void TearDown() throws IOException {
        WebDriverRunner.clearBrowserCache();
    }

}