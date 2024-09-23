package org.bancobolivariano.tests;

import org.bancobolivariano.utils.LocatorType;
import org.bancobolivariano.utils.Utils;
import org.bancobolivariano.utils.WebDriverManager;
import org.bancobolivariano.utils.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp(ITestContext context) {
        driver = WebDriverManager.getChromeDriver();
        driver.manage().window().maximize();
        context.setAttribute("WebDriver", driver);
    }

    @AfterClass
    public void tearDown() {
        WebDriverUtils.getElementAndClick(driver, LocatorType.XPATH, "//*[@id=\"menuMiPerfil\"]");
        Utils.addScreenshotToReport(driver,"ProfileMenu");
        WebDriverUtils.getElementAndClick(driver, LocatorType.XPATH, "//*[@id=\"menuOperador\"]/li[7]/a");
        Utils.addScreenshotToReport(driver,"AfterLogout");
        if (driver != null) {
            driver.quit();
        }
    }
}
