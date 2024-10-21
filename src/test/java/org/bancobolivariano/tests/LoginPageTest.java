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
    public void logIn() {
        driver.get("https://tarjetacredito.dev.cuentafuturo.com");
        ScreenshotUtils.addScreenshotToReport(driver, "LoginPage");

        // Wait until the button is present and then click it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement startButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__next\"]/main/div/div/div[2]/div/div[2]/div/button")));
        startButton.click();
        ScreenshotUtils.addScreenshotToReport(driver, "LoginPage-Start");

        // Fill in the "cedula de indentidad"
        WebElement cedulaField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cid\"]")));
        cedulaField.sendKeys("1201530720");
        ScreenshotUtils.addScreenshotToReport(driver, "CedulaFilled");

        // Fill in the "código dactilar"
        WebElement codigoDactilarField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"fingerPrint\"]")));
        codigoDactilarField.sendKeys("G2345K0923");
        ScreenshotUtils.addScreenshotToReport(driver, "CodigoDactilarFilled");

        // Click on "Políticas de privacidad"
        WebElement politicasPrivacidadLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"terms-conditions\"]/div[1]/a")));
        politicasPrivacidadLink.click();
        ScreenshotUtils.addScreenshotToReport(driver, "PoliticasPrivacidadOpened");

        // Scroll down to the bottom of the privacy policy
        WebDriverUtils.moveY(driver, 1000); // Adjust the value as needed
        ScreenshotUtils.addScreenshotToReport(driver, "PoliticasPrivacidadScrolled");

        // Exit the privacy policy
        WebElement exitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='bb-button bb-button--icon p-button p-component']//div//*[name()='svg']")));
        exitButton.click();
        ScreenshotUtils.addScreenshotToReport(driver, "PoliticasPrivacidadClosed");

        WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        checkbox.click();
        ScreenshotUtils.addScreenshotToReport(driver, "CheckboxClicked");

        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        button.click();
        ScreenshotUtils.addScreenshotToReport(driver, "BotonSubmit");

        // Fill in the "phone"
        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='phone']")));
        phoneField.sendKeys("0994876568");
        ScreenshotUtils.addScreenshotToReport(driver, "PhoneFilled");
      
        // Fill in the "email"
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
        emailField.sendKeys("xandrado@bolivariano.com");
        ScreenshotUtils.addScreenshotToReport(driver, "EmailFilled");

        // Hacer clic en el botón de submit
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bb-button--primary > .button__label")));
        submitButton.click();
        ScreenshotUtils.addScreenshotToReport(driver, "SubmitClicked");

        // Esperar 1 segundo antes de acceder al siguiente botón
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Hacer clic en el botón de submit adicional
        WebElement additionalSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        additionalSubmitButton.click();
        ScreenshotUtils.addScreenshotToReport(driver, "AdditionalSubmitClicked");

        // propuestaBoton
        WebElement propuestaBoton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__next\"]/main/div/div[2]/div[2]/button[2]")));
        propuestaBoton.click();
        ScreenshotUtils.addScreenshotToReport(driver, "PropuestaBotonClicked");

        // Acceder al local storage y obtener el user_id desde mp_58d2baa53885911486cee97c5c3e78dc_mixpanel
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Imprimir todo el contenido del localStorage
        String localStorageContent = (String) js.executeScript("return JSON.stringify(window.localStorage);");
        System.out.println("Local Storage Content: " + localStorageContent);
        Reporter.log("Local Storage Content: " + localStorageContent);

        // Esperar 1 segundos antes de obtener el user_id
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Obtener el user_id desde mp_58d2baa53885911486cee97c5c3e78dc_mixpanel
        String mixpanelData = (String) js.executeScript("return window.localStorage.getItem('mp_58d2baa53885911486cee97c5c3e78dc_mixpanel');");
        if (mixpanelData != null) {
            JSONObject mixpanelJson = new JSONObject(mixpanelData);
            String userId = mixpanelJson.optString("$user_id", "User ID not found");
            System.out.println("User ID: " + userId);
            Reporter.log("User ID: " + userId);
        } else {
            System.out.println("Mixpanel data not found in local storage.");
            Reporter.log("Mixpanel data not found in local storage.");
        }

        // Continuar con el flujo de Onboarding
        OnboardingTest onboardingTest = new OnboardingTest(driver);
        onboardingTest.completeOnboarding();
    }
}