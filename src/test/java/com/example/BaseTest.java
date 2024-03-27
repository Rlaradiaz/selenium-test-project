package com.example;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = DriverFactory.getDriver();
    }


 

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
