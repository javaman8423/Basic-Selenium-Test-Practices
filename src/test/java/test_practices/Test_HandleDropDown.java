package test_practices;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class Test_HandleDropDown {

    WebDriver driver;

    @Before
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @After
    public void teardown() {driver.close();}

    @Test
    public void test01(){

        // 1- navigate to http://zero.webappsecurity.com/
        driver.get("http://zero.webappsecurity.com/");

        // 2- click on Signin button
        driver.findElement(By.id("signin_button")).click();

        // 3- write "username" in login field
        WebElement loginBox = driver.findElement(By.xpath("//input[@id='user_login']"));
        loginBox.sendKeys("username" + Keys.TAB);

        // 4- write "password" in Password field
        WebElement passwordBox = driver.findElement(By.xpath("//input[@id='user_password']"));
        passwordBox.sendKeys("password");
        driver.findElement(By.xpath("//input[@name='submit']")).click();
        driver.navigate().back();

        // 5- navigate to Online Banking then click on Pay Bills
        driver.findElement(By.xpath("//strong[text()='Online Banking']")).click();
        driver.findElement(By.xpath("//*[@id='pay_bills_link']")).click();

        // 6- click on "purchase foreign currency" button
        driver.findElement(By.xpath("//*[text()='Purchase Foreign Currency']")).click();

        // 7- select Eurozone from Currency dropdown menu
        WebElement ddm = driver.findElement(By.xpath("//select[@id" +
                "='pc_currency']"));

        Select select = new Select(ddm);

        select.selectByVisibleText("Eurozone (euro)");
        // select.selectByIndex(6);
        // select.selectByValue("EUR");

        WebElement amount = driver.findElement(By.xpath("//*[@id='pc_amount" +
                "']"));
        amount.sendKeys("500");

        // 8- Test the "US Dollars" not selected
        String actualOptionText = select.getFirstSelectedOption().getText();
        String expectedOptionText ="US Dollars";
        System.out.println(actualOptionText);
        Assert.assertFalse(actualOptionText.equals(expectedOptionText));

        // 9- click on "selected currency" button
        driver.findElement(By.xpath("//input[@id='pc_inDollars_false']")).click();

        // 10- click on "calculate costs" then click on "purchase"
        driver.findElement(By.xpath("//input[@id='pc_calculate_costs']")).click();
        driver.findElement(By.xpath("//input[@id='purchase_cash']")).click();

        // 11- Test "Foreign currency cash was successfully purchased."
        // string is displayed
        WebElement purchaseResult = driver.findElement(By.xpath("//*[@id='alert_content']"));
        String actualPurchaseStr = purchaseResult.getText();
        String expectedStr = "Foreign currency cash was successfully " +
                "purchased.";

        Assert.assertTrue(actualPurchaseStr.equals(expectedStr));


    }
}
