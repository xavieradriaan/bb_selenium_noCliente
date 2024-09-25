package org.bancobolivariano.tests;

import org.bancobolivariano.utils.*;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp(ITestContext context) throws IOException {
        driver = WebDriverManager.getChromeDriver();
        driver.manage().window().maximize();
        context.setAttribute("WebDriver", driver);
        //Map<Integer, Map<String, String>> fileData = FileUtils.readFileXls(System.getProperty("user.dir") + "/src/test/resources/data/data.xlsx", 0);
        //DataStore.getInstance().setFileData(fileData);
    }

    @AfterClass
    public void tearDown() {
        WebDriverUtils.getElementAndClick(driver, LocatorType.XPATH, "//*[@id=\"menuMiPerfil\"]");
        ScreenshotUtils.addScreenshotToReport(driver,"ProfileMenu");
        WebDriverUtils.getElementAndClick(driver, LocatorType.XPATH, "//*[@id=\"menuOperador\"]/li[7]/a");
        ScreenshotUtils.addScreenshotToReport(driver,"AfterLogout");
        if (driver != null) {
            driver.quit();
        }
    }
}
