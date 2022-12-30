package test_practices;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Test_FiratUni {

    WebDriver driver;

    @Before
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @After
    public void teardown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();}

    @Test
    public void test() throws InterruptedException {

        // 1- go to https://www.firat.edu.tr/
        driver.get("https://www.firat.edu.tr/tr");

        // 2- click on "en" language right top of the page
        driver.findElement(By.xpath("(//a[@hreflang='en'])[2]")).click();

        // 3- click on contact menu
        driver.findElement(By.xpath("(//a[@href='http://www.firat.edu.tr/tr/contact'])[2]")).click();
        Thread.sleep(2000);

    }
}
