package se.recan.framework.selenium;

import java.text.SimpleDateFormat;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

/**
 *
 * 2014-aug-12
 *
 * @author Anders Recksén (recan)
 */
public class BasePO {

    public static WebDriver driver;
    
    public static final Logger LOGGER = Logger.getLogger("Logger");

    public static final int SLEEP = 1;

    public BasePO() {
        String className = this.getClass().getName();
        className = className.substring(className.lastIndexOf(".") + 1);
        System.out.println("className "+className);
        String location = PageRepo.page(className);
        
        driver = DriverFactory.getDriver();
        driver.get(location);
    }
    
    public void cleanUp() {
        DriverFactory.cleanUp();
    }
    
    public void type(String value, By... by) {
        By chained = new ByChained(by);
        WebElement element = driver.findElement(chained);
        element.clear();
        element.sendKeys(value);
    }
    
    public void click(By... by) {
        By chained = new ByChained(by);
        WebElement element = driver.findElement(chained);
        element.click();
    }
    
    public void click(int position, By... by) {
        By chained = new ByChained(by);
        driver.findElements(chained).get(position).click();
    }
    
    public void select(String value, By... by) {
        By chained = new ByChained(by);
        Select select = new Select(driver.findElement(chained));
        select.selectByValue(value);
    }
    
    public void select(int position, By... by) {
        By chained = new ByChained(by);
        Select select = new Select(driver.findElement(chained));
        select.selectByIndex(position);
    }
    
    public String getText(By... by) {
        By chained = new ByChained(by);
        WebElement element = driver.findElement(chained);
        return element.getText();
    }
    
    public String getAttribute(By... by) {
        By chained = new ByChained(by);
        WebElement element = driver.findElement(chained);
        return element.getAttribute("value");
    }
    
    public boolean isLoaded() {
        try {
            long timeOutSeconds = 2;
            long poolInMillis = WebDriverWait.DEFAULT_SLEEP_TIMEOUT; // default: 500ms

            Wait<WebDriver> wait = new WebDriverWait(driver, timeOutSeconds, poolInMillis);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("title")));

            return element != null;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle().toLowerCase();
    }

    public static WebElement getParent(WebElement element) {
        return element.findElement(By.xpath(".."));
    }

    // Använd denna istället för att kontrollera om ett Element finns med NoSuchElementException
    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    // WebElement måste kontrolleras på detta sättet
    public boolean isElementPresent(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }
    
    public void sleep() {
        sleep(SLEEP);
    }

    public void sleep(long l) {
        if (l == 0) {
            return;
        }

        try {
            Thread.sleep(l * 1000);
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
        }
    }
}
