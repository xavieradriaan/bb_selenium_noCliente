//src/test/java/org/bancobolivariano/tests/ExtentReportTest.java
package org.bancobolivariano.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverUtils {

    public static void getElementAndClick(WebDriver driver, LocatorType type, String locator) {
        WebElement element = getElement(driver, type, locator);
        if (element != null) {
            try {
                Thread.sleep(1000); // Espera de 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            element.click();
        }
    }

    public static void getElementAndEnterValue(WebDriver driver, LocatorType type, String locator, String text) {
        WebElement element = getElement(driver, type, locator);
        if (element != null) {
            element.sendKeys(text);
            try {
                Thread.sleep(1000); // Espera de 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static WebElement getElement(WebDriver driver, LocatorType type, String value) {
        return switch (type) {
            case ID -> driver.findElement(By.id(value));
            case XPATH -> driver.findElement(By.xpath(value));
            case NAME -> driver.findElement(By.name(value));
        };
    }

    public static void moveY(WebDriver driver, int value) {
        String val = String.format("window.scrollBy(0,%s)", value);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(val);
    }

    public static void moveX(WebDriver driver, int value) {
        String val = String.format("window.scrollBy(%s,0)", value);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(val);
    }
}
