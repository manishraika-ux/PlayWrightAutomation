package com.qa.demoqa.tests;

import com.qa.constants.AppConstants;
import com.qa.demoqa.base.BaseTest;
import com.qa.pages.ElementPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ElementPageTest extends BaseTest {
    ElementPage elementPage;
    @Test(priority = 3, dataProvider = "getTestData")
    public void clickElementAndEnterUserDetails(String data) {
        elementPage = homePage.navigateToElementPage();
        elementPage.clickTextBox();
        elementPage.enterFullName(data);
        elementPage.enterEmail(prop.getProperty("Email"));
        elementPage.enterCurrentAddress(prop.getProperty("CurrentAddress"));
        elementPage.enterPermanentAddress(prop.getProperty("PermanentAddress"));
        elementPage.enterSubmit();
        Assert.assertEquals(elementPage.getFullName(), AppConstants.Element_Page_UserName);
    }

    @DataProvider
    public Object[][] getTestData() {
        return new Object[][]{
                {"Testing"}
        };
    }
}
