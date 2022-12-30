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

public class Test07_YouTube {

    WebDriver driver;

    @Before
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // 1- Go to https://www.youtube.com/
        driver.get("https://www.youtube.com/");

    }

    @After
    public void teardown() {driver.close();}

    @Test
    public void titleTest(){

        // 2- Test the title of the page equals to "YouTube"
        String expectedTitle = "YouTube";
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.equals(expectedTitle));

    }

    @Test
    public void logoTest(){

        // 2- Test the logo of the page is displayed
        WebElement logo = driver.findElement(By.xpath("(//yt-icon[@id='logo-icon'])[1]"));
        Assert.assertTrue(logo.isDisplayed());
    }

    @Test
    public void searchBoxTest(){

        // 3- Test the searchbox is enabled
        WebElement searchBox = driver.findElement(By.xpath("//ytd-searchbox[@id='search']"));
        Assert.assertTrue(searchBox.isEnabled());
    }

    @Test
    public void falseTitleTest(){

        // 4- Assert the title of the page is not "youtube"
        String expectedTitle ="youtube";
        String actualTitle = driver.getTitle();
        Assert.assertFalse(actualTitle.equals(expectedTitle));

    }
}