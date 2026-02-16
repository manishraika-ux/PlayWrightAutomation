package com.qa.pages;

import com.microsoft.playwright.Page;

public class HomePage {

    private final Page page;

    private String searchPageHeader = "a[href='https://demoqa.com']";
    private final String elements = "//h5[normalize-space()='Elements']";


    public HomePage(Page page) {
        this.page = page;
    }

    public String getHomePageTitle() {
        return page.title();
    }

    public String getHomeURL() {
        String url = page.url();
        System.out.println(url);
        return url;
    }


    public String getSearchPageHeader() {
        return searchPageHeader;
    }

    public void setSearchPageHeader(String searchPageHeader) {
        this.searchPageHeader = searchPageHeader;
    }

    public ElementPage navigateToElementPage() {
        page.click(elements);
        return new ElementPage(page);
    }
}


