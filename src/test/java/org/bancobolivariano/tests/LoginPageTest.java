package org.bancobolivariano.tests;

import org.bancobolivariano.listeners.ExtentReportListener;
import org.bancobolivariano.utils.ScreenshotUtils;
import org.bancobolivariano.utils.WebDriverUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.Reporter;
import org.json.JSONObject;

@Listeners(ExtentReportListener.class)
public class LoginPageTest extends BaseTest {

    @Test
    public void openLoginPage() {
        driver.get("https://tarjetacredito.dev.cuentafuturo.com");
        ScreenshotUtils.addScreenshotToReport(driver, "LoginPage");
    }

    @Test(dependsOnMethods = "openLoginPage")
    public void startLoginProcess() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement startButton = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//*[@id=\"__next\"]/main/div/div/div[2]/div/div[2]/div/button")));
        startButton.click();
        ScreenshotUtils.addScreenshotToReport(driver, "LoginPage-Start");
    }

    @Test(dependsOnMethods = "startLoginProcess")
    public void enterCedula() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cedulaField = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cid\"]")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';",
                cedulaField);
        cedulaField.sendKeys("1717433310");
        ScreenshotUtils.addScreenshotToReport(driver, "CedulaFilled");
    }

    @Test(dependsOnMethods = "enterCedula")
    public void enterCodigoDactilar() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement codigoDactilarField = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"fingerPrint\"]")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';",
                codigoDactilarField);
        codigoDactilarField.sendKeys("G2345K0923");
        ScreenshotUtils.addScreenshotToReport(driver, "CodigoDactilarFilled");
    }

    @Test(dependsOnMethods = "enterCodigoDactilar")
    public void acceptPrivacyPolicy() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement politicasPrivacidadLink = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"terms-conditions\"]/div[1]/a")));
        politicasPrivacidadLink.click();
        ScreenshotUtils.addScreenshotToReport(driver, "PoliticasPrivacidadOpened");

        WebDriverUtils.moveY(driver, 1000);
        ScreenshotUtils.addScreenshotToReport(driver, "PoliticasPrivacidadScrolled");

        WebElement submitButtonExit = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".lucide-x")));
        submitButtonExit.click();
        ScreenshotUtils.addScreenshotToReport(driver, "PoliticasPrivacidadClosed");

        WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        checkbox.click();
        ScreenshotUtils.addScreenshotToReport(driver, "CheckboxClicked");
    }

    @Test(dependsOnMethods = "acceptPrivacyPolicy")
    public void submitLoginForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        button.click();
        ScreenshotUtils.addScreenshotToReport(driver, "BotonSubmit");

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(dependsOnMethods = "submitLoginForm")
    public void enterPhoneAndEmail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement phoneField = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='phone']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';",
                phoneField);
        phoneField.sendKeys("0911672178");
        ScreenshotUtils.addScreenshotToReport(driver, "PhoneFilled");

        WebElement emailField = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
        js.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';",
                emailField);
        emailField.sendKeys("xandrado@bolivariano.com");
        ScreenshotUtils.addScreenshotToReport(driver, "EmailFilled");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
                ".BBButton-module__bb-button--primary___2lC4N > .BBButton-module__button__label___-dURf")));
        submitButton.click();
        ScreenshotUtils.addScreenshotToReport(driver, "SubmitClicked");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement additionalSubmitButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        additionalSubmitButton.click();
        ScreenshotUtils.addScreenshotToReport(driver, "AdditionalSubmitClicked");

        WebElement propuestaBoton = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//*[@id=\"__next\"]/main/div/div[2]/div[2]/button[2]")));
        // Scroll to the bottom of the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        try {
            Thread.sleep(1000); // Wait for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        propuestaBoton.click();
        ScreenshotUtils.addScreenshotToReport(driver, "PropuestaBotonClicked");
    }

    @Test(dependsOnMethods = "enterPhoneAndEmail")
    public void getUserIdFromLocalStorage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String localStorageContent = (String) js.executeScript("return JSON.stringify(window.localStorage);");
        System.out.println("Local Storage Content: " + localStorageContent);
        Reporter.log("Local Storage Content: " + localStorageContent);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String mixpanelData = (String) js
                .executeScript("return window.localStorage.getItem('mp_58d2baa53885911486cee97c5c3e78dc_mixpanel');");
        if (mixpanelData != null) {
            JSONObject mixpanelJson = new JSONObject(mixpanelData);
            String userId = mixpanelJson.optString("$user_id", "User ID not found");
            System.out.println("User ID: " + userId);
            Reporter.log("User ID: " + userId);
        } else {
            System.out.println("Mixpanel data not found in local storage.");
            Reporter.log("Mixpanel data not found in local storage.");
        }
    }

    @Test(dependsOnMethods = "getUserIdFromLocalStorage")
    public void completeOnboarding() {
        OnboardingTest onboardingTest = new OnboardingTest(driver);
        onboardingTest.completeOnboarding();
    }
}

//bellaqueo