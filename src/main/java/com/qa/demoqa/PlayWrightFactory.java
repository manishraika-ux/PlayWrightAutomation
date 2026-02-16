package com.qa.demoqa;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

public class PlayWrightFactory {
    Properties prop;

    private static ThreadLocal<Browser> trBrowser = new ThreadLocal<>();
    private static ThreadLocal<Playwright> trPlaywright = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> trBrowserContext = new ThreadLocal<>();
    private static ThreadLocal<Page> trPage = new ThreadLocal<>();

    public static Playwright getPlayWright() {
        return trPlaywright.get();
    }

    public static Page getPage() {
        return trPage.get();
    }

    public static Browser getBrowser() {
        return trBrowser.get();
    }

    public static BrowserContext getBrowserContext() {
        return trBrowserContext.get();
    }

    public Page initBrowser(Properties prop) {
        trPlaywright.set(Playwright.create());
        String browserName = prop.getProperty("browser").trim();
        switch (browserName.toLowerCase()) {
            case "chromium":
                trBrowser.set(getPlayWright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "firefox":
                trBrowser.set(getPlayWright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "safari":
                trBrowser.set(getPlayWright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            default:
                System.out.println("Incorrect Browser Type");
                break;
        }
        trBrowserContext.set(getBrowser().newContext());
        trPage.set(getBrowserContext().newPage());
        getPage().navigate(prop.getProperty("url"));
        getPage().setDefaultTimeout(60000);
        return getPage();
    }

    public Properties initProp() throws RuntimeException {
        try {
            try (FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties")) {
                prop = new Properties();
                prop.load(ip);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }

    public static String takeScreenshot() {
        // Assuming 'getPage()' returns your active Playwright Page instance
        byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions()
                .setFullPage(false)); // Use true if you want the entire scrollable page

        return java.util.Base64.getEncoder().encodeToString(buffer);
    }
}
