package org.bancobolivariano.tests;
import org.bancobolivariano.utils.LocatorType;
import org.bancobolivariano.utils.Utils;
import org.bancobolivariano.utils.WebDriverUtils;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
public class TransferenciaTerceros extends LoginTest {

    @Test
    public void transferenciaTerceros() {
        WebDriverUtils.getElementAndClick(driver, LocatorType.XPATH, "//*[@id=\"TRANSFERENCIAS\"]");
        WebDriverUtils.getElementAndClick(driver, LocatorType.XPATH, "//*[@id=\"PARENT_CUENTA_TRANSFERENCIAS\"]/ul/li[2]");
        Utils.addScreenshotToReport(driver,"AfterTransferenciaTerceros");
    }

}
