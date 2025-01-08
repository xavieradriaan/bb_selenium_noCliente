//src/test/java/org/bancobolivariano/tests/LoginPageTest.java
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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement startButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__next\"]/main/div/div/div[2]/div/div[2]/div/button")));
        startButton.click();
        ScreenshotUtils.addScreenshotToReport(driver, "LoginPage-Start");

        // "cedula de indentidad"
        WebElement cedulaField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cid\"]")));
        // Aplicar formato en negrita y cursiva usando JavaScript antes de ingresar el texto
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", cedulaField);

        cedulaField.sendKeys("1717433310"); //1717415408"
        ScreenshotUtils.addScreenshotToReport(driver, "CedulaFilled");


        //      "código dactilar"
        WebElement codigoDactilarField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"fingerPrint\"]")));
        JavascriptExecutor js3 = (JavascriptExecutor) driver;
        js3.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", codigoDactilarField);
        codigoDactilarField.sendKeys("G2345K0923");
        ScreenshotUtils.addScreenshotToReport(driver, "CodigoDactilarFilled");

        // Click on "Políticas de privacidad"
        WebElement politicasPrivacidadLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"terms-conditions\"]/div[1]/a")));
        politicasPrivacidadLink.click();
        ScreenshotUtils.addScreenshotToReport(driver, "PoliticasPrivacidadOpened");

        // Scroll down 
        WebDriverUtils.moveY(driver, 1000); // Adjust    value as needed
        ScreenshotUtils.addScreenshotToReport(driver, "PoliticasPrivacidadScrolled");

        // Exit    privacy policy
        WebElement submitButtonExit = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".lucide-x")));
        submitButtonExit.click();
        ScreenshotUtils.addScreenshotToReport(driver, "PoliticasPrivacidadClosed");

        WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        checkbox.click();
        ScreenshotUtils.addScreenshotToReport(driver, "CheckboxClicked");

        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        button.click();
        ScreenshotUtils.addScreenshotToReport(driver, "BotonSubmit");

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // "teléfono"
        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='phone']")));

        // Aplicar formato en negrita usando JavaScript
        JavascriptExecutor js4 = (JavascriptExecutor) driver;
        js4.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", phoneField);
        phoneField.sendKeys("0911672178");
        ScreenshotUtils.addScreenshotToReport(driver, "PhoneFilled");
        
        //      "email"
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
        // Aplicar formato en negrita usando JavaScript 
        JavascriptExecutor js5 = (JavascriptExecutor) driver;
        js5.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", emailField);
        emailField.sendKeys("xandrado@bolivariano.com");
        ScreenshotUtils.addScreenshotToReport(driver, "EmailFilled");

        // Hacer clic en el botón de submit
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".BBButton-module__bb-button--primary___2lC4N > .BBButton-module__button__label___-dURf")));
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