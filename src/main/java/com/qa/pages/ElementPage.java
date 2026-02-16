package com.qa.pages;

import com.microsoft.playwright.Page;

public class ElementPage {
    private String fullName = "//input[@id='userName']";
    private String getFullName = "//p[@id='name']";
    private String emailId = "//input[@id='userEmail']";
    private String currentAddress = "//textarea[@id='currentAddress']";
    private String permanentAddress = "//textarea[@id='permanentAddress']";
    private String submit = "//button[@id='submit']";
    private String textBox = " //span[normalize-space()='Text Box']";
    private Page page;

    public ElementPage(Page page) {
        this.page = page;
    }

    public void clickTextBox() {
        page.locator(textBox).click();
    }

    public void enterFullName(String name) {
        page.locator(fullName).fill(name);
    }

    public void enterEmail(String userEmail) {
        page.locator(emailId).fill(userEmail);
    }

    public void enterCurrentAddress(String address) {
        page.locator(currentAddress).fill(address);
    }

    public void enterPermanentAddress(String address) {
        page.locator(permanentAddress).fill(address);
    }

    public void enterSubmit() {
        page.locator(submit).click();
    }

    public String getFullName() {
        return page.textContent(getFullName);
    }
}
