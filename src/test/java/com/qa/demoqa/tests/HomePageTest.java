package com.qa.demoqa.tests;

import com.qa.demoqa.base.BaseTest;
import com.qa.constants.AppConstants;
import org.testng.Assert;

import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test(priority = 1)
    public void getHomeUrl() {
        String actualUrl = homePage.getHomeURL();
        Assert.assertEquals(actualUrl, prop.getProperty("url"));
    }

    @Test(priority = 2)
    public void getPageTitle() {
        String actualTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualTitle, AppConstants.Login_Page_title);
    }





}
