package org.bancobolivariano.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverManager {
    public static WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        return new ChromeDriver(getOptions());
    }

    public static ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-web-security");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--allow-running-insecure-content");
        return options;
    }
}
