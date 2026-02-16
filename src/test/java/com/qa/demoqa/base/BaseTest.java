package com.qa.demoqa.base;

import com.microsoft.playwright.Page;
import com.qa.demoqa.PlayWrightFactory;
import com.qa.pages.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    PlayWrightFactory pf;
    Page page;
   protected HomePage homePage;
   protected Properties prop;
    @BeforeTest
    public void setup() throws IOException {
        pf = new PlayWrightFactory();
        prop=pf.initProp();
        page = pf.initBrowser(prop);
        homePage = new HomePage(page);
    }

    @AfterTest
    public void teardown() {
        page.context().browser().close();
        ;

    }
}
