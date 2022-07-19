import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Test1 {

    @Test
    public void run1() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://google.com");
        String title = driver.getTitle();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement searchBox = driver.findElement(By.name("q"));
        WebElement searchButton = driver.findElement(By.name("btnK"));
        searchBox.sendKeys("Selenium");
        searchButton.click();
        searchBox = driver.findElement(By.name("q"));
        String value = searchBox.getAttribute("value");
        driver.quit();
    }
}
