package org.bancobolivariano.tests;
import org.bancobolivariano.utils.LocatorType;
import org.bancobolivariano.utils.Utils;
import org.bancobolivariano.utils.WebDriverUtils;

import org.testng.annotations.Test;

import java.io.IOException;


public class TransferenciaTerceros extends LoginTest {

    @Test
    public void transferenciaTerceros() throws IOException{
        WebDriverUtils.getElementAndClick(driver, LocatorType.XPATH, "//*[@id=\"TRANSFERENCIAS\"]");
        WebDriverUtils.getElementAndClick(driver, LocatorType.XPATH, "//*[@id=\"PARENT_CUENTA_TRANSFERENCIAS\"]/ul/li[2]");
        Utils.addScreenshotToReport(driver,"AfterTransferenciaTerceros");
    }

}
