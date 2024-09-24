package org.bancobolivariano.tests;

import org.bancobolivariano.listeners.ExtentReportListener;
import org.bancobolivariano.utils.LocatorType;
import org.bancobolivariano.utils.WebDriverUtils;
import org.bancobolivariano.utils.Utils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(ExtentReportListener.class)
public class LoginTest extends BaseTest{

    @Test
    public void logIn() {
        driver.get("http://172.16.30.181:8081/BOLI-ebanking/seguridad/login.htm");
        Utils.addScreenshotToReport(driver,"LoginPage");
        WebDriverUtils.getElementAndEnterValue(driver, LocatorType.XPATH, "//*[@id=\"username\"]", "userCanal1");
        WebDriverUtils.getElementAndClick(driver, LocatorType.XPATH, "//*[@id=\"botonSendUsernamesadasd\"]");
        Utils.addScreenshotToReport(driver,"AfterUsernameEntered");
        WebDriverUtils.getElementAndEnterValue(driver, LocatorType.XPATH, "//*[@id=\"password\"]", "Ab.12345");
        WebDriverUtils.getElementAndClick(driver, LocatorType.XPATH, "//*[@id=\"botonSendPassword\"]");
        Utils.addScreenshotToReport(driver,"AfterPasswordEntered");
        WebDriverUtils.getElementAndClick(driver, LocatorType.XPATH, "//*[@id=\"varInt\"]");
        WebDriverUtils.getElementAndEnterValue(driver, LocatorType.XPATH, "//*[@id=\"respuesta\"]", "amarillo");
        WebDriverUtils.getElementAndClick(driver, LocatorType.XPATH, "//*[@id=\"btnValidarRespuesta\"]");
        Utils.addScreenshotToReport(driver,"AfterSecurityQuestion");
        WebDriverUtils.moveY(driver,200);
        WebDriverUtils.getElementAndClick(driver, LocatorType.XPATH, "//*[@id=\"btnContinuar\"]");
        WebDriverUtils.getElementAndClick(driver, LocatorType.XPATH, "//*[@id=\"btnContinuar\"]");
        Utils.addScreenshotToReport(driver,"AfterLogin");
    }


}