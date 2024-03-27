package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GoogleSearchTest extends BaseTest {

    @Test
    public void searchForCarsInLondon() {
        driver.get("https://www.google.com");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));

        
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys("cars at london");
        searchInput.submit();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#search")));

        // Inicializar un conjunto para almacenar los enlaces de Gumtree ya visitados
        Set<String> visitedLinks = new HashSet<>();

        int gumtreeLinkCount = 0;

        JavascriptExecutor js = (JavascriptExecutor) driver;
        int scrollCount = 0;
        while (scrollCount < 5) { 
            
            List<WebElement> searchResultElements = driver.findElements(By.cssSelector(".tF2Cxc .LC20lb.MBeuO.DKV0Md"));

            for (WebElement result : searchResultElements) {
                WebElement link = result.findElement(By.xpath("./ancestor::a"));
                String url = link.getAttribute("href");

                if (url != null && url.contains("gumtree.com") && !visitedLinks.contains(url)) {
                    
                    gumtreeLinkCount++;

                    visitedLinks.add(url);

                    link.click();

                    wait.until(ExpectedConditions.titleContains("Gumtree"));
                    String pageTitle = driver.getTitle();
                    System.out.println("Página de Gumtree visitada: " + pageTitle);

                    driver.navigate().back();

                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#search")));
                }
            }

            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            scrollCount++;
        }

        
        System.out.println("Número total de enlaces de Gumtree encontrados: " + gumtreeLinkCount);
    }
}
