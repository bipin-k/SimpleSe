package com.rationaleemotions.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.time.Duration;

public class AmazonTest {
    @Test
    public void testAmazon() {

        System.setProperty("webdriver.chrome.driver", "/Users/bipinchaurasia/Downloads/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable--notifications");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.get("https://www.amazon.in/");

        PageObject homePage = new PageObject(driver, "src/test/resources/HomePage.json");
        TextField textField = homePage.getTextField("amazon", 4);

        System.out.println(textField.getText());

//        String text = driver.findElement(By.xpath("")).getText();
//        System.out.println(text);

        driver.quit();
    }
}
