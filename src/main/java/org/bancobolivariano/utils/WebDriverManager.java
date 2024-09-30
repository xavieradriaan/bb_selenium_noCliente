package org.bancobolivariano.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WebDriverManager {


    public static WebDriver getChromeDriver() {
        var props = Utils.getProperties();
        System.setProperty("webdriver.chrome.driver", props.getProperty("chrome.driver.path"));
        return new ChromeDriver(getOptions());
    }

    public static ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-web-security");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--allow-running-insecure-content");
        return options;
    }
}
