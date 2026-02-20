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
    public void clickElementAndEnterUserDetails(String name, String email, String currentAddress
    , String permanentAddress) {
        elementPage = homePage.navigateToElementPage();
        elementPage.clickTextBox();
        elementPage.enterFullName(name);
        elementPage.enterEmail(email);
        elementPage.enterCurrentAddress(currentAddress);
        elementPage.enterPermanentAddress(permanentAddress);
        elementPage.enterSubmit();
        Assert.assertEquals(elementPage.getFullName(), AppConstants.Element_Page_UserName);
    }

    @DataProvider
    public Object[][] getTestData() {
        return new Object[][]{
                {"Testing","testing@gmail.com","Colorado","Denver"}
        };
    }
}
