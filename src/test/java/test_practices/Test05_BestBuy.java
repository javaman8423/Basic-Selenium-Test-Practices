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

public class Test05_BestBuy {

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

    /*




        5- Test the French link is displayed

     */

    @Test
    public void test() throws InterruptedException {

        // 1- Go to https://www.bestbuy.com/
        driver.get("https://www.bestbuy.com/");

        // 2- Test the page's url is equal to https://www.bestbuy.com/
        String expectedUrl = "https://www.bestbuy.com/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(expectedUrl.equals(actualUrl));

        // 3- Test the title of the page doesn't contain "Rest"
        String expectedWord = "Rest";
        String actualTitle = driver.getTitle();
        Assert.assertFalse(actualTitle.contains(expectedWord));

        // 4- Test the logo of the page is displayed
        WebElement logo = driver.findElement(By.xpath("//img[@class='logo']"));
        Assert.assertTrue(logo.isDisplayed());

        // 5- Test the French link is displayed
        WebElement frenchLink = driver.findElement(By.xpath("//button[@lang='fr']"));
        Assert.assertTrue(frenchLink.isDisplayed());
        Thread.sleep(2000);
    }
}
