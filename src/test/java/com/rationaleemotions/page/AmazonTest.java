package com.rationaleemotions.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

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



        Label textField1 = homePage.getLabel("amazon");
        System.out.println(textField1.getText());

        Label textField = homePage.getLabel("amazon1", 3);
        System.out.println(textField.getText());

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.close();
        driver.quit();
    }
}
