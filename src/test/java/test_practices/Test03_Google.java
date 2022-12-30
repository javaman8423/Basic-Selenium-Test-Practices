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

import java.security.Key;
import java.time.Duration;

public class Test03_Google {

    static WebDriver driver;

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

        // 1- go to https://google.com/
        driver.get("https://google.com/");
        // 2- Testing Page Title contains "Google"
        String expectedTitle = "Google";
        String actualTitle = driver.getTitle();
        Assert.assertTrue(expectedTitle.equals(actualTitle));

        // 3- search "funny cats"
        WebElement searchNutella = driver.findElement(By.xpath("//input[@class='gLFyf']"));
        searchNutella.sendKeys("funny cats" + Keys.ENTER);
        // 4- print the number of results
        WebElement searchResults = driver.findElement(By.id("result-stats"));
        System.out.println(searchResults.getText());

        // 5- Testing number of results is more than 10 millions
        String resultNums = searchResults.getText();
        String[] resultArr = searchResults.getText().split(" ");
        String actualResultStr = resultArr[1];

        actualResultStr = actualResultStr.replace(".","");
        int actualResult = Integer.parseInt(actualResultStr);
        int expectedResult =10000000;

       Assert.assertTrue(actualResult > expectedResult);

        Thread.sleep(2000);
    }

}
