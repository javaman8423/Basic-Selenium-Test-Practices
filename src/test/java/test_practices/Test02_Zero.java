package test_practices;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Test02_Zero {

    /*
        1- go to http://zero.webappsecurity.com
        2- click on Signin button
        3- write "username" in Login textbox
        4- write "password" in Password textbox
        5- click on Signin button
        6- navigate backward
        7- click on Online Banking nav-bar and then click on Pay Bills nav-bar
        8- write an amount in the field amount
        9- write "2022-12-26" in date field
        10- click on Pay button
        11- Testing "The payment was successfully submitted." message is
        displayed

     */

    WebDriver driver;

    @Before
    public void setUp(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @After
    public void teardown(){
        driver.close();
    }

    @Test
    public void test01() throws InterruptedException {

        // 1- go to http://zero.webappsecurity.com
        driver.get("http://zero.webappsecurity.com/");
        // 2- click on Signin button
        driver.findElement(By.xpath("//button[@id='signin_button']")).click();
        // 3- write "username" in Login textbox
        WebElement userName = driver.findElement(By.id("user_login"));
        userName.sendKeys("username");
        // 4- write "password" in Password textbox
        WebElement passWord = driver.findElement(By.id("user_password"));
        passWord.sendKeys("password");
        // 5- click on Signin button
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        // 6- navigate backward
        driver.navigate().back();
        // 7- click on Online Banking nav-bar and then click on Pay Bills
        // nav-bar
        driver.findElement(By.xpath("//strong[text()='Online Banking']")).click();
        driver.findElement(By.xpath("//span[text()='Pay Bills']")).click();
        // 8- Enter an amount in the field of amount
        WebElement amount = driver.findElement(By.xpath("//input[@id='sp_amount']"));
        amount.sendKeys("500");
        // 9- Enter "2022-12-26" in date field
        WebElement dateField = driver.findElement(By.xpath("//input[@id='sp_date']"));
        dateField.sendKeys("2022-12-23");
        // 10- click on Pay button
        driver.findElement(By.xpath("//input[@id='pay_saved_payees']")).submit();
        // 11- Testing "The payment was successfully submitted." message is displayed
        String paymentSuccessfullySubStr= "The payment was successfully " +
                "submitted.";
        WebElement actualAlertMessage = driver.findElement(By.xpath("//div" +
                "[@id='alert_content']"));
        String actualAlertMesStr = actualAlertMessage.getText();
        Assert.assertTrue(paymentSuccessfullySubStr.equals(actualAlertMesStr));


        Thread.sleep(2000);

    }

}
