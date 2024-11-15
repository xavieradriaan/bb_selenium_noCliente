package org.bancobolivariano.tests;

import org.bancobolivariano.listeners.ExtentReportListener;
import org.bancobolivariano.utils.ScreenshotUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.time.Duration;


@Listeners(ExtentReportListener.class)
public class OnboardingTest extends BaseTest {

    public OnboardingTest() {
        super();
    }

    public OnboardingTest(WebDriver driver) {
        this.driver = driver;
    }

    @Test
    public void completeOnboarding() {
        try {
            biometriaCedulaAnverso();
            ingresarDireccion();
            ingresarDatosLaborales();
            ingresarSituacionEconomica();
            eligeDondeRecibirTarjeta();
            cuentaDeAhorros();
            ingresaTuCodigoOTP();
            //submitForm();
        } catch (InterruptedException e) {
            System.out.println("Se produjo un error en completeOnboarding: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Se produjo un error en completeOnboarding: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void biometriaCedulaAnverso() throws InterruptedException {
        System.out.println("Haciendo clic en el botón de cédula anverso...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cedulaAnversoButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btnNext']")));
        cedulaAnversoButton.click();
        Thread.sleep(2200);
    
        // Esperar hasta que el botón de éxito de biometría esté presente y sea clicable
        System.out.println("Esperando para tomar fotos manualmente del anverso y reverso de la cédula...");
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofMinutes(5)); // Esperar hasta 5 minutos
        WebElement biometriaSuccessButton = longWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='onb-btn-biometria-success-next']")));
        System.out.println("Botón de éxito de biometría encontrado, continuando con la automatización...");
        biometriaSuccessButton.click();
    }

    private void ingresarDireccion() throws InterruptedException {
        System.out.println("Haciendo clic en el campo de dirección...");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".BBTextArea-module__bb-textarea-main___-p2DP:nth-child(1) .BBTextArea-module__bb-textarea___a3huP")));

        WebElement direccionField = driver.findElement(By.cssSelector(".BBTextArea-module__bb-textarea-main___-p2DP:nth-child(1) .BBTextArea-module__bb-textarea___a3huP"));
        direccionField.click();
        direccionField.sendKeys("Junín y Panamá Edificio XYZ Piso 8 Puerta E12");
        ScreenshotUtils.addScreenshotToReport(driver, "DireccionFilled");
        Thread.sleep(2200);

        System.out.println("Ubica tu domicilio en el mapa...");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#home-button-text")));
        WebElement homeButton = driver.findElement(By.cssSelector("#home-button-text"));
        homeButton.click();
        Thread.sleep(2200);


        System.out.println("Haciendo clic en el botón de autocompletar...");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".BBGoogle-module__autocomplete-control__wrapper__button___9oxcY > .lucide")));
        WebElement autocompleteButton = driver.findElement(By.cssSelector(".BBGoogle-module__autocomplete-control__wrapper__button___9oxcY > .lucide"));
        autocompleteButton.click();
        Thread.sleep(2200);

        System.out.println("Haciendo clic en el botón de continuar...");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".BBButton-module__bb-button--primary___2lC4N > .BBButton-module__button__label___-dURf")));
        WebElement mainButton = driver.findElement(By.cssSelector(".BBButton-module__bb-button--primary___2lC4N > .BBButton-module__button__label___-dURf"));
        mainButton.click();
        Thread.sleep(2200);

        System.out.println("Haciendo clic en el campo de referencia...");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#reference")));
        WebElement referenceField = driver.findElement(By.cssSelector("#reference"));
        referenceField.click();
        referenceField.sendKeys("Casa Dos pisos con Patio Frontal y dos perros pitbull albinos");
        ScreenshotUtils.addScreenshotToReport(driver, "ReferenceFilled");
        Thread.sleep(2200);

        System.out.println("Haciendo clic en el dropdown...TIPO DE RESIDENCIA\"");
        WebElement homeTypeElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#homeType")));
        homeTypeElement.click();

        System.out.println("Haciendo clic en el elemento enfocado...");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(.,'Arrienda')]")));
        WebElement arriendaOption = driver.findElement(By.xpath("//li[contains(.,'Arrienda')]"));
        arriendaOption.click();
        Thread.sleep(2200);

        System.out.println("Haciendo clic en el campo de edad...");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("homeAge")));
        WebElement homeAgeField = driver.findElement(By.id("homeAge"));
        homeAgeField.click();
        homeAgeField.sendKeys("9");
        ScreenshotUtils.addScreenshotToReport(driver, "HomeAgeFilled");
        Thread.sleep(2200);


        System.out.println("Haciendo click en botón de continuar apenas entre a personalización de tarjeta");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".BBButton-module__bb-button--primary___2lC4N > .BBButton-module__button__label___-dURf")));

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        Thread.sleep(1000);

        WebElement button = driver.findElement(By.cssSelector(".BBButton-module__bb-button--primary___2lC4N > .BBButton-module__button__label___-dURf"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Clic realizado en el botón de personalización de tarjeta");
        Thread.sleep(2200);

        System.out.println("Iniciando sección: Referencias Familiares");

        WebElement chevronDownFamiliares = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".lucide.lucide-chevron-down")));
        chevronDownFamiliares.click();
        Thread.sleep(2200);

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(2200);

        WebElement namesField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#names")));
        namesField.click();
        namesField.sendKeys("Xavier Adrián");
        ScreenshotUtils.addScreenshotToReport(driver, "NamesFilled");
        Thread.sleep(2200);

        WebElement fatherLastNameField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#fatherLastName")));
        fatherLastNameField.click();
        fatherLastNameField.sendKeys("Andrade");
        ScreenshotUtils.addScreenshotToReport(driver, "FatherLastNameFilled");
        Thread.sleep(2200);

        WebElement motherLastNameField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#motherLastName")));
        motherLastNameField.click();
        motherLastNameField.sendKeys("Ochoa");
        ScreenshotUtils.addScreenshotToReport(driver, "MotherLastNameFilled");
        Thread.sleep(2200);

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(1000);

        WebElement phoneNumberField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#phoneNumber")));
        phoneNumberField.click();
        phoneNumberField.sendKeys("0996768532");
        ScreenshotUtils.addScreenshotToReport(driver, "PhoneNumberFilled");
        Thread.sleep(2200);

        WebElement primaryButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".BBButton-module__bb-button--primary___2lC4N > .BBButton-module__button__label___-dURf")));
        primaryButton2.click();
        Thread.sleep(2200);
        ScreenshotUtils.addScreenshotToReport(driver, "botonSsiguiente");
    }

    private void ingresarDatosLaborales() throws InterruptedException {
        System.out.println("Iniciando sección: Completa tus datos laborales");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement trabajadorIndependiente = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Soy trabajador independiente']")));
        trabajadorIndependiente.click();
        Thread.sleep(2200);

        WebElement jobAddressTextarea = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".BBTextArea-module__bb-textarea-main___-p2DP:nth-child(1) .BBTextArea-module__bb-textarea___a3huP")));
        jobAddressTextarea.click();
        jobAddressTextarea.sendKeys("Sauces 4");
        ScreenshotUtils.addScreenshotToReport(driver, "JobAddressFilled");
        Thread.sleep(2200);

        WebElement jobAddressField = wait.until(ExpectedConditions.elementToBeClickable(By.id("jobAddress")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", jobAddressField);
        wait.until(ExpectedConditions.visibilityOf(jobAddressField));
        jobAddressField.click();
        Thread.sleep(2200);

        WebElement autocompleteField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".pac-target-input")));
        autocompleteField.click();
        autocompleteField.sendKeys("sauces 4");
        Thread.sleep(2200);

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000);

        //confirmar ubicación
        System.out.println("Buscando el botón de confirmar ubicación...");
        WebElement button69 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".BBButton-module__bb-button--primary___2lC4N > .BBButton-module__button__label___-dURf")));
        button69.click();
        Thread.sleep(2200);


        WebElement jobAddressReferenceField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"jobAddressReference\"]")));
        jobAddressReferenceField.click();
        jobAddressReferenceField.sendKeys("edificio afd3");
        ScreenshotUtils.addScreenshotToReport(driver, "JobAddressReferenceFilled");
        Thread.sleep(2200);

        System.out.println("Buscando el botón de submit...");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(1000);

        WebElement button0 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".BBButton-module__bb-button--primary___2lC4N > .BBButton-module__button__label___-dURf")));
        button0.click();
        System.out.println("Clic realizado en el botón de submit");
        Thread.sleep(2200);
    }

    private void ingresarSituacionEconomica() throws InterruptedException {
        System.out.println("Iniciando sección: Ingresa tu situación económica");

        //ingresos mensuales
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement monthlyIncomeField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='monthlyIncome']")));
        monthlyIncomeField.click();
        monthlyIncomeField.sendKeys("2000");
        ScreenshotUtils.addScreenshotToReport(driver, "MonthlyIncomeFilled");
        Thread.sleep(4200);


        //gastos mensuales
        WebElement monthlyExpensesField2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='expenses']")));
        monthlyExpensesField2.click();
        monthlyExpensesField2.sendKeys("840");
        ScreenshotUtils.addScreenshotToReport(driver, "GASTOS");
        Thread.sleep(4200);

        //activos
        WebElement monthlyExpensesField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#assets > input")));
        monthlyExpensesField.click();
        monthlyExpensesField.sendKeys("840");
        ScreenshotUtils.addScreenshotToReport(driver, "MonthlyExpensesFilled");
        Thread.sleep(6200);

        //pasivos
        WebElement savingsField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#liabilities > input")));
        savingsField.click();
        savingsField.sendKeys("320");
        ScreenshotUtils.addScreenshotToReport(driver, "SavingsFilled");
        Thread.sleep(4200);

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(1000);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        submitButton.click();
        Thread.sleep(2200);

        confirmarResidenciaFiscal();
    }

    private void confirmarResidenciaFiscal() throws InterruptedException {
        System.out.println("Iniciando sección: Confirma tu residencia fiscal");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='__next']/main/div/div[2]/div[2]/button[2]/div")));
        submitButton.click();
        ScreenshotUtils.addScreenshotToReport(driver, "ResidenciaFiscalFilled");
        Thread.sleep(2200);
        
    }

    private void eligeDondeRecibirTarjeta() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(1000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".BBButton-module__bb-button--primary___2lC4N > .BBButton-module__button__label___-dURf")));
        submitButton.click();
        ScreenshotUtils.addScreenshotToReport(driver, "DondeRecibirTarjetaFilled");
        Thread.sleep(2200);

        confirmaSolicitud();
    }

    private void confirmaSolicitud() throws InterruptedException {
        System.out.println("Iniciando sección: Confirma tu solicitud");

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(1000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".BBButton-module__bb-button--primary___2lC4N > .BBButton-module__button__label___-dURf")));
        submitButton.click();
        ScreenshotUtils.addScreenshotToReport(driver, "ConfirmaSolicitudFilled");
        Thread.sleep(2200);

        WebElement emisionUsoTarjeta = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Emisión y uso de tarjeta']")));
        emisionUsoTarjeta.click();
        Thread.sleep(2200);

        WebElement primerTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Términos del plan Fast Point']")));
        primerTab.click();
        Thread.sleep(2200);

        WebElement segundoTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Tarifas de servicios']")));
        segundoTab.click();
        ScreenshotUtils.addScreenshotToReport(driver, "TarifasDeServicios");
        Thread.sleep(2200);

        WebElement checkbox88 = driver.findElement(By.xpath("//input[@type='checkbox']"));
        checkbox88.click();
        Thread.sleep(2200);

        WebElement boton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='ContractsRender_bb-summary-contracts__main__container__button__container__wWIAI'] div:nth-child(2) button:nth-child(1)")));
        boton.click();
        ScreenshotUtils.addScreenshotToReport(driver, "TarifaServiciosFilled");
        Thread.sleep(2200);
    }


    private void cuentaDeAhorros() throws InterruptedException {
        System.out.println("Iniciando sección: Cuenta de ahorros");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
        // Esperar hasta que el checkbox sea visible y clicable
        WebElement checkboxElement10 = driver.findElement(By.xpath("//div[@id='__next']/main/div/section/section/div/div/input"));
        checkboxElement10.click();
        Thread.sleep(2200);
        ScreenshotUtils.addScreenshotToReport(driver, "CheckboxClicked");
    
        // Esperar hasta que el botón de continuar sea visible y clicable
        WebElement continueButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btn-continue > .BBButton-module__button__label___-dURf")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButtonElement);
        ScreenshotUtils.addScreenshotToReport(driver, "ContinueButtonClicked");
    }



    //falta código OTP

    private void ingresaTuCodigoOTP() throws InterruptedException {
        // Agrega tiempo hasta que aparezca el OTP
        Thread.sleep(6000);
        System.out.println("Iniciando sección: Ingresa tu código OTP");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement otpField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input0")));
        otpField.click();
        
        otpField.sendKeys("192332");
        ScreenshotUtils.addScreenshotToReport(driver, "OTPFieldFilled");
        Thread.sleep(300000);

    
        WebElement imgElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("img:nth-child(4)")));
        imgElement.click();
        ScreenshotUtils.addScreenshotToReport(driver, "imagenClicked33");
        Thread.sleep(6400);

        WebElement obsElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='obs']")));
        obsElement.click();
        obsElement.sendKeys("Muy intuitivo");
        ScreenshotUtils.addScreenshotToReport(driver, "ObsFieldFilled2");
        Thread.sleep(3200);

        WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".Qualification_btnApprove__m0iEu")));
        approveButton.click();
        Thread.sleep(222200);
        ScreenshotUtils.addScreenshotToReport(driver, "TarjetaGenerada");
    }
}