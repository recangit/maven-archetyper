package se.recan.framework.selenium;

import org.junit.Test;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 2015-apr-27
 *
 * @author Anders Recksén (recan, Prolore)
 */
public class SelectorTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");
    private static WebDriver driver;
    private static WebElement element;

    @Test
    public void testSelector() {
        driver = DriverFactory.getDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/docs/person.html");

        WebElement message = driver.findElement(By.id("message"));
        Assert.assertEquals("message", message.getText());
        
        element = driver.findElement(By.cssSelector("a[id=ab-xx-12]"));         // Lika med
        element.click();
        Assert.assertEquals("Antal klick: 1", message.getText());
        sleep();

        element = driver.findElement(By.cssSelector("a[id^=ab]"));              // Börjar med
        element.click();
        Assert.assertEquals("Antal klick: 2", message.getText());
        sleep();

        element = driver.findElement(By.cssSelector("a[id*=xx]"));              // Slutar med
        element.click();
        Assert.assertEquals("Antal klick: 3", message.getText());
        sleep();

        element = driver.findElement(By.cssSelector("a[name$=whatever]"));      // Innehåller
        element.click();
        Assert.assertEquals("Antal klick: 4", message.getText());
        sleep();

        element = driver.findElement(By.cssSelector("a[class~=c2][class~=c1]"));// Finns i en lista
        element.click();
        Assert.assertEquals("Antal klick: 5", message.getText());
        sleep();

        element = driver.findElement(By.cssSelector("a.c1.c2"));                // Selector class
        element.click();
        Assert.assertEquals("Antal klick: 6", message.getText());
        sleep();
    
        element = driver.findElement(By.cssSelector("#ab-xx-12"));              // Selector id
        element.click();
        Assert.assertEquals("Antal klick: 7", message.getText());
        sleep();
    
        element = driver.findElement(By.cssSelector("a#ab-xx-12"));             // Selector id
        element.click();                                                        // Egentligen som ovan men kanske tydligare
        Assert.assertEquals("Antal klick: 8", message.getText());
        sleep();
    }

    public void sleep() {
        try {
            Thread.sleep(1 * 1000);
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
        }
    }

    @AfterClass
    public static void cleanUp() {
        driver.quit();
        driver = null;
    }
}
