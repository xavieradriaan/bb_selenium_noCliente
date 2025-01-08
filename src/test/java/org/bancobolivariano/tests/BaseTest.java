//src/test/java/org/bancobolivariano/tests/BaseTest.java
package org.bancobolivariano.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp(ITestContext context) throws IOException {
        // Configura WebDriverManager para manejar el driver de Safari
        /*
        WebDriverManager.safaridriver().setup();

        // Inicializa el driver de Safari
        driver = new SafariDriver();
        driver.manage().window().maximize();
        context.setAttribute("WebDriver", driver);

        // Configura permisos de uso de la cámara y ubicación para Safari
        Map<String, Object> safariPrefs = new HashMap<>();
        safariPrefs.put("media_stream_camera", 1); // 1: permitir, 2: bloquear
        safariPrefs.put("geolocation", 1); // 1: permitir, 2: bloquear
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("navigator.permissions.query({name:'camera'}).then(function(permissionStatus) { permissionStatus.state = 'granted'; });");
        js.executeScript("navigator.permissions.query({name:'geolocation'}).then(function(permissionStatus) { permissionStatus.state = 'granted'; });");
        */

        // Configura WebDriverManager para manejar el driver de Chrome
        WebDriverManager.chromedriver().setup();

        // Configura las opciones de Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // Permitir todas las conexiones remotas
        options.addArguments("--disable-extensions"); // Deshabilitar extensiones
        options.addArguments("--disable-gpu"); // Deshabilitar GPU
        options.addArguments("--no-sandbox"); // No usar sandbox
        options.addArguments("--disable-dev-shm-usage"); // Deshabilitar uso de /dev/shm
        options.addArguments("--auto-open-devtools-for-tabs"); // Abrir DevTools automáticamente

        // Configura permisos de uso de la cámara y ubicación
        Map<String, Object> prefs = new HashMap<>();
        Map<String, Object> profile = new HashMap<>();
        Map<String, Object> contentSettings = new HashMap<>();
        contentSettings.put("media_stream_camera", 1); // 1: permitir, 2: bloquear
        contentSettings.put("geolocation", 1); // 1: permitir, 2: bloquear
        profile.put("managed_default_content_settings", contentSettings);
        prefs.put("profile", profile);
        options.setExperimentalOption("prefs", prefs);

        // Inicializa el driver de Chrome con las opciones configuradas
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        context.setAttribute("WebDriver", driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}