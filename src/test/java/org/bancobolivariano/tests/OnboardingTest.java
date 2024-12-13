package org.bancobolivariano.tests;

import org.bancobolivariano.listeners.ExtentReportListener;
import org.bancobolivariano.utils.ScreenshotUtils;
import org.checkerframework.checker.units.qual.m;
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
        WebElement biometriaSuccessButton = longWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"onb-btn-biometria-step-3-next\"]")));
        System.out.println("Botón de éxito de biometría encontrado, continuando con la automatización...");
        biometriaSuccessButton.click();
    }

    private void ingresarDireccion() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Aumentar el tiempo de espera

        WebElement direccionField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#home-button-text")));
        direccionField.click();
        WebElement direccionField2 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".BBButton-module__bb-button--primary___2lC4N > .BBButton-module__button__label___-dURf")));
        direccionField2.click();

        WebElement direccionField3 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".pac-target-input")));
        direccionField3.click();
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js4 = (JavascriptExecutor) driver;
        js4.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", direccionField3);
                
        direccionField3.sendKeys("Panama y Junin");
        Thread.sleep(5000);
        direccionField3.sendKeys(Keys.ARROW_DOWN);
        direccionField3.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        //hasta aquí todo ok
        WebElement direccionField4 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".BBButton-module__bb-button___HYEkM.BBButton-module__bb-button--primary___2lC4N")));
        direccionField4.click();
        ScreenshotUtils.addScreenshotToReport(driver, "DireccionFilled");
        Thread.sleep(2200);

        System.out.println("Haciendo clic en Calle Principal...");
        WebElement mainStreetField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='firstAddress']")));
        mainStreetField.click();
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js5 = (JavascriptExecutor) driver;
        js5.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", mainStreetField);
                
        mainStreetField.sendKeys("Panama");
        ScreenshotUtils.addScreenshotToReport(driver, "MainStreetFilled");
        Thread.sleep(2200);

        System.out.println("Haciendo clic en Calle Secundaria...");
        WebElement secondaryStreetField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='secondaryAddress']")));
        secondaryStreetField.click();
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js6 = (JavascriptExecutor) driver;
        js6.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", secondaryStreetField);
        secondaryStreetField.sendKeys("Junin");
        ScreenshotUtils.addScreenshotToReport(driver, "SecondaryStreetFilled");

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        Thread.sleep(1000);

        System.out.println("Haciendo clic en el campo de número de casa...");
        WebElement houseNumberField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='houseNumber']")));
        houseNumberField.click();
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js7 = (JavascriptExecutor) driver;
        js7.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", houseNumberField);
                
        houseNumberField.sendKeys("Mz 118 etapa L V 1");

        // Hacer scroll hacia abajo
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        Thread.sleep(1000);

        System.out.println("Haciendo clic en el campo de referencia...");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#reference")));
        WebElement referenceField = driver.findElement(By.cssSelector("#reference"));
        referenceField.click();
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js8 = (JavascriptExecutor) driver;
        js8.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", referenceField);
        referenceField.sendKeys("\"Casa grande blanca “Las Garzas”, por la cancha joga bonito, sector mitad del mundo (-0.0030924, -78.4467336)\"");
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
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js9 = (JavascriptExecutor) driver;
        js9.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", homeAgeField);        
        homeAgeField.sendKeys("9");
        ScreenshotUtils.addScreenshotToReport(driver, "HomeAgeFilled");
        Thread.sleep(2200);

        System.out.println("Haciendo clic en el botón de continuar...");
        WebElement primaryButtonN = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".BBButton-module__bb-button--primary___2lC4N > .BBButton-module__button__label___-dURf")));
        primaryButtonN.click();
        ScreenshotUtils.addScreenshotToReport(driver, "BotonContinuar");
        Thread.sleep(2200);


        System.out.println ("Haciendo clic en parentesco...");
        WebElement parentesco = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".lucide-chevron-down")));
        parentesco.click();
        Thread.sleep(2200);


        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(2200);

        WebElement namesField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#names")));
        namesField.click();
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js10 = (JavascriptExecutor) driver;
        js10.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", namesField);        
        namesField.sendKeys("Xavier Adrián");
        ScreenshotUtils.addScreenshotToReport(driver, "NamesFilled");
        Thread.sleep(2200);

        WebElement fatherLastNameField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#fatherLastName")));
        fatherLastNameField.click();
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js11 = (JavascriptExecutor) driver;
        js11.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", fatherLastNameField);        
        fatherLastNameField.sendKeys("Andrade");
        ScreenshotUtils.addScreenshotToReport(driver, "FatherLastNameFilled");
        Thread.sleep(2200);

        WebElement motherLastNameField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#motherLastName")));
        motherLastNameField.click();
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js12 = (JavascriptExecutor) driver;
        js12.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", motherLastNameField);
        motherLastNameField.sendKeys("Ochoa");
        ScreenshotUtils.addScreenshotToReport(driver, "MotherLastNameFilled");
        Thread.sleep(2200);

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(1000);

        WebElement phoneNumberField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#phoneNumber")));
        phoneNumberField.click();
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js13 = (JavascriptExecutor) driver;
        js13.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", phoneNumberField);
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

        //agregué esto

        WebElement direccionField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#city")));
        direccionField.click();
        WebElement direccionField2 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".BBButton-module__bb-button--primary___2lC4N > .BBButton-module__button__label___-dURf")));
        direccionField2.click();
        WebElement direccionField3 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".pac-target-input")));
        direccionField3.click();
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js16 = (JavascriptExecutor) driver;
        js16.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", direccionField3);
                
        direccionField3.sendKeys("Sauces 4");
        Thread.sleep(2200);
        direccionField3.sendKeys(Keys.ARROW_DOWN);
        direccionField3.sendKeys(Keys.ENTER);
        Thread.sleep(4200);

        WebElement direccioncontinuar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".BBButton-module__bb-button--primary___2lC4N > .BBButton-module__button__label___-dURf")));
        direccioncontinuar.click();
        ScreenshotUtils.addScreenshotToReport(driver, "DireccionFilled");
        Thread.sleep(2200);


        WebElement calleprincipal = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='mainStreet']")));
        calleprincipal.click();
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js17 = (JavascriptExecutor) driver;
        js17.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", calleprincipal);
                
        calleprincipal.sendKeys("Sauces 4");
        ScreenshotUtils.addScreenshotToReport(driver, "CallePrincipalFilled");

        WebElement callesecundaria = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#secondaryStreet")));
        callesecundaria.click();
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js18 = (JavascriptExecutor) driver;
        js18.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", callesecundaria);
        callesecundaria.sendKeys("G. Plaza D");
        ScreenshotUtils.addScreenshotToReport(driver, "CalleSecundariaFilled");

        WebElement numeroOficina = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='officeNumber']")));
        numeroOficina.click();
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js19 = (JavascriptExecutor) driver;
        js19.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", numeroOficina);
        numeroOficina.sendKeys(" Numero 22-56");
        ScreenshotUtils.addScreenshotToReport(driver, "NumeroOficinaFilled");

        WebElement referencia = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='jobAddressReference']")));
        referencia.click();
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js20 = (JavascriptExecutor) driver;
        js20.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", referencia);
        referencia.sendKeys("Frente a la gasolinera diagonal cruz azul");
        ScreenshotUtils.addScreenshotToReport(driver, "ReferenciaFilled");


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
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js19 = (JavascriptExecutor) driver;
        js19.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", monthlyIncomeField);
                
        monthlyIncomeField.sendKeys("2000");
        ScreenshotUtils.addScreenshotToReport(driver, "MonthlyIncomeFilled");
        Thread.sleep(4200);


        //gastos mensuales
        WebElement monthlyExpensesField2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='expenses']")));
        monthlyExpensesField2.click();
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js20 = (JavascriptExecutor) driver;
        js20.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", monthlyExpensesField2);
        monthlyExpensesField2.sendKeys("840");
        ScreenshotUtils.addScreenshotToReport(driver, "GASTOS");
        Thread.sleep(4200);

        //activos
        WebElement monthlyExpensesField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#assets > input")));
        monthlyExpensesField.click();
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js21 = (JavascriptExecutor) driver;
        js21.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", monthlyExpensesField);
                
        monthlyExpensesField.sendKeys("840");
        ScreenshotUtils.addScreenshotToReport(driver, "MonthlyExpensesFilled");
        Thread.sleep(6200);

        //pasivos
        WebElement savingsField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#liabilities > input")));
        savingsField.click();
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js22 = (JavascriptExecutor) driver;
        js22.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", savingsField);
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
        Thread.sleep(16000);
        System.out.println("Iniciando sección: Ingresa tu código OTP");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement otpField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input0")));
        otpField.click();
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        JavascriptExecutor js23 = (JavascriptExecutor) driver;
        js23.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", otpField);      
        otpField.sendKeys("192332");
        ScreenshotUtils.addScreenshotToReport(driver, "OTPFieldFilled");
        Thread.sleep(300000);

    
        WebElement imgElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("img:nth-child(4)")));
        imgElement.click();
        ScreenshotUtils.addScreenshotToReport(driver, "imagenClicked33");
        Thread.sleep(6400);

        WebElement obsElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='obs']")));
        obsElement.click();
        JavascriptExecutor js24 = (JavascriptExecutor) driver;
        js24.executeScript("arguments[0].style.fontWeight = 'bold'; arguments[0].style.fontStyle = 'italic';", obsElement);
        obsElement.sendKeys("Muy intuitivo");
        // Aplicar formato en negrita usando JavaScript antes de ingresar el texto
        ScreenshotUtils.addScreenshotToReport(driver, "ObsFieldFilled2");
        Thread.sleep(3200);

        WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".Qualification_btnApprove__m0iEu")));
        approveButton.click();
        Thread.sleep(222200);
        ScreenshotUtils.addScreenshotToReport(driver, "TarjetaGenerada");

        // Esperar 5 minutos sin cerrar
        Thread.sleep(300000);
    }
}