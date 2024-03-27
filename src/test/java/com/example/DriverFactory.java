package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            
            System.setProperty("webdriver.chrome.driver", "src\\Drivers\\chromedriver.exe");

            
            driver = new ChromeDriver();
        }
        return driver;
    }
}
