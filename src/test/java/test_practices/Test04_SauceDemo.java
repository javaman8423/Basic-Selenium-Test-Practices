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

public class Test04_SauceDemo {

    WebDriver driver;

    @Before
    public void setUp(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @After
    public void teardown() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }

    @Test
    public void Test() throws InterruptedException {

        // 1- go to https://www.saucedemo.com/
        driver.get("https://www.saucedemo.com/");

        // 2- enter "standard_user" into Username field
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");

        // 3- enter "secret_sauce" into Password field
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");


        // 4- click on Login button
        driver.findElement(By.id("login-button")).click();

        // 5- save the first item name then go to the item's page
        WebElement firstItemName = driver.findElement(By.xpath("(//div[@class" +
                "='inventory_item_name'])[1]"));

        String firstItemNameStr = firstItemName.getText();
        firstItemName.click();
        Thread.sleep(2000);

        // 6- click on Add to Cart button
        driver.findElement(By.xpath("//*[text()='Add to cart']")).click();

        // 7- click on shopping cart
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        Thread.sleep(3000);

        // 8- Test product successfully added your cart
       WebElement productNameInCart =driver.findElement(By.xpath(
                "//div[@class='inventory_item_name']"));

        String productNameInCartStr = productNameInCart.getText();

        Assert.assertTrue(firstItemNameStr.equals(productNameInCartStr));


    }


}
