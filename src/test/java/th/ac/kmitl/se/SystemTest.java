package th.ac.kmitl.se;

import com.ibm.icu.impl.Assert;
import org.graphwalker.java.annotation.AfterExecution;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SystemTest {
    WebDriver driver;
    @BeforeEach
    void setUp() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\aniru\\Downloads\\chromedriver_win32\\chromedriver"); // Set the path to the Chromedriver executable for version 89
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://fekmitl.pythonanywhere.com/kratai-bin");
    }
    @AfterAll
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSuccessFlow() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        //List all WebElements
        WebElement startButton = driver.findElement(By.id("start"));

        // Find the button element using its ID, name, class name, or any other locator strategy
        startButton.click();

        wait.until(ExpectedConditions.urlToBe("https://fekmitl.pythonanywhere.com/kratai-bin/order"));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_tum_thai")));

        WebElement cancelButton = driver.findElement(By.id("btn_cancel"));
        cancelButton.click();

        Thread.sleep(1000); // 1 second delay, adjust as needed
        // Find the button element using its ID, name, class name, or any other locator strategy

        WebElement startButton2 = driver.findElement(By.id("start"));
        startButton2.click();

        wait.until(ExpectedConditions.urlToBe("https://fekmitl.pythonanywhere.com/kratai-bin/order"));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_tum_thai")));

        //Enter the details of the order
        WebElement tumThaiOrder = driver.findElement(By.id("add_tum_thai"));
        WebElement tumPooOrder = driver.findElement(By.id("add_tum_poo"));
        WebElement btnCheckOut = driver.findElement(By.id("btn_check_out"));


        tumThaiOrder.click();
        tumThaiOrder.click();
        tumThaiOrder.click();
        tumThaiOrder.click();

        Alert alert = driver.switchTo().alert();
        alert.accept();
        Thread.sleep(1000); // 1 second delay, adjust as needed
        tumPooOrder.click();

        Thread.sleep(1000); // 1 second delay, adjust as needed

        btnCheckOut.click();
        Thread.sleep(1000); // 1 second delay, adjust as needed
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_confirm")));
//        WebElement btnConfirm = driver.findElement(By.id("btn_confirm"));


        WebElement btnChange = driver.findElement(By.id("btn_change"));
        btnChange.click();

        WebElement tumPooOrder2 = driver.findElement(By.id("add_tum_poo"));
        WebElement btnCheckOut2 = driver.findElement(By.id("btn_check_out"));

        tumPooOrder2.click();
        tumPooOrder2.click();

        Thread.sleep(1000); // 1 second delay, adjust as needed
        btnCheckOut2.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_confirm")));
        WebElement btnConfirm = driver.findElement(By.id("btn_confirm"));
        btnConfirm.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_pay")));

        WebElement btnPay = driver.findElement(By.name("btn_pay"));
        btnPay.click();

        WebElement textOnCreditCard = driver.findElement(By.name("txt_credit_card_num"));
        textOnCreditCard.sendKeys("123456");

        WebElement nameOnCardInput = driver.findElement(By.name("txt_name_on_card"));
        nameOnCardInput.sendKeys("Anirudh Makhana");

        WebElement btnPay2 = driver.findElement(By.name("btn_pay"));
        btnPay2.click();

        //Collect all the shit you ordered
        WebElement imageTumThaiElement = driver.findElement(By.className("ImgTumThai"));
        WebElement imageTumPooElement = driver.findElement(By.className("ImgTumPoo"));

        imageTumPooElement.click();
        imageTumThaiElement.click();

        Thread.sleep(1000); // 1 second delay, adjust as needed

        // Close the browser window
        driver.quit();
    }

    @Test
    public void testErrorCollection() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        //List all WebElements
        WebElement starButton = driver.findElement(By.id("start"));

        // Find the button element using its ID, name, class name, or any other locator strategy
        starButton.click();

        wait.until(ExpectedConditions.urlToBe("https://fekmitl.pythonanywhere.com/kratai-bin/order"));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_tum_thai")));

        //Enter the details of the order
        WebElement tumThaiOrder = driver.findElement(By.id("add_tum_thai"));
        WebElement tumPooOrder = driver.findElement(By.id("add_tum_poo"));
        WebElement btnCheckOut = driver.findElement(By.id("btn_check_out"));


        tumThaiOrder.click();
        tumPooOrder.click();

        Thread.sleep(1000); // 1 second delay, adjust as needed

        btnCheckOut.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_confirm")));
        WebElement btnConfirm = driver.findElement(By.id("btn_confirm"));

        btnConfirm.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_pay")));

        WebElement textOnCreditCard = driver.findElement(By.name("txt_credit_card_num"));
        textOnCreditCard.sendKeys("123456");

        WebElement nameOnCardInput = driver.findElement(By.name("txt_name_on_card"));
        nameOnCardInput.sendKeys("Anirudh Makhana");

        WebElement btnPay = driver.findElement(By.name("btn_pay"));
        btnPay.click();

        //Collect all the shit you ordered
        WebElement imageTumThaiElement = driver.findElement(By.className("ImgTumThai"));
        WebElement imageTumPooElement = driver.findElement(By.className("ImgTumPoo"));


        Thread.sleep(1000*15); // 1 second delay, adjust as needed

        // Close the browser window
        driver.quit();
    }

    @Test
    public void testErrorPayment() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        //List all WebElements
        WebElement starButton = driver.findElement(By.id("start"));

        // Find the button element using its ID, name, class name, or any other locator strategy
        starButton.click();

        wait.until(ExpectedConditions.urlToBe("https://fekmitl.pythonanywhere.com/kratai-bin/order"));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_tum_thai")));

        //Enter the details of the order
        WebElement tumThaiOrder = driver.findElement(By.id("add_tum_thai"));
        WebElement tumPooOrder = driver.findElement(By.id("add_tum_poo"));
        WebElement btnCheckOut = driver.findElement(By.id("btn_check_out"));


        tumThaiOrder.click();
        tumPooOrder.click();

        Thread.sleep(1000); // 1 second delay, adjust as needed

        btnCheckOut.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_confirm")));
        WebElement btnConfirm = driver.findElement(By.id("btn_confirm"));

        btnConfirm.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_pay")));


        WebElement btnPay = driver.findElement(By.name("btn_pay"));
        btnPay.click();
        Thread.sleep(500); // 1 second delay, adjust as needed
        WebElement btnPay2 = driver.findElement(By.name("btn_pay"));
        btnPay2.click();
        Thread.sleep(500); // 1 second delay, adjust as needed
        WebElement btnPay3 = driver.findElement(By.name("btn_pay"));
        btnPay3.click();
        Thread.sleep(2000); // 1 second delay, adjust as needed


        // Close the browser window
        driver.quit();
    }
}