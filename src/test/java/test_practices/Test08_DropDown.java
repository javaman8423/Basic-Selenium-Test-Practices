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
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class Test08_DropDown {

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
    public void ddmTest(){

        // 1- Go to https://the-internet.herokuapp.com/dropdown
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement ddm = driver.findElement(By.id("dropdown"));

        // 2- Using Index select and print Option 1
        Select select = new Select(ddm);

        select.selectByIndex(1);
        String optionValue = select.getFirstSelectedOption().getText();
        System.out.println(optionValue);

        // 3- Using Value select and print Option 2
        select.selectByValue("2");
        System.out.println(select.getFirstSelectedOption().getText());

        // 4- Using Visible Text select and print Option 1
        select.selectByVisibleText("Option 1");
        System.out.println(select.getFirstSelectedOption().getText());

        // 5- print all dropdown values
        List<WebElement> optionsList = select.getOptions();
        for (WebElement each: optionsList
             ) {
            System.out.print(each.getText() +" ");
        }

        // 6- Find the length of dropdown, if dropdown has 4 items print True
        // if not print False on console
        int lengthOfDdm = optionsList.size();
        int expectedLength =4;

        //Assert.assertFalse("False", lengthOfDdm !=4 );

    }
}
