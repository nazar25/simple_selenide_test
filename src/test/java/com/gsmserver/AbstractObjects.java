package com.gsmserver;

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
            GSM_MAIN_PAGE_TITLE = "Hot Air Soldering Stations - GsmServer";

    @BeforeClass
    public void SetUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://gsmserver.com/equipment/soldering-equipment/hot-air-soldering-stations/";
        Configuration.timeout = 10000;
    }

    @AfterClass(alwaysRun = true)
    public void TearDown() throws IOException {
        WebDriverRunner.clearBrowserCache();
    }

}