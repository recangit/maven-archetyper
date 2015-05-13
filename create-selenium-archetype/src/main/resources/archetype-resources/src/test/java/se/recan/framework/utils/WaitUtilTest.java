package se.recan.framework.utils;

import com.google.common.base.Function;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import org.junit.Test;
import org.junit.Assert;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import se.recan.framework.selenium.DriverFactory;

/**
 * 2015-apr-28
 *
 * @author Anders Recksén (recan, Prolore)
 */
public class WaitUtilTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");
    private static WebDriver driver;
    private static final By NO_EXIST = By.id("finnsEj");
    private static long startTimer;
    private static int implicitWait;
    private static int pageLoadWait;
    private static int webDriverWait;
    private static int fluentWait;
    private static long pool;

    /**
     * Om en sida är långsam att ladda så ska denna användas.
     */
    @Test(expected=TimeoutException.class)
    public void testPageLoadTimeout() {
        pageLoadWait = 200;

        driver = DriverFactory.getDriver();
        driver.manage().timeouts().pageLoadTimeout(pageLoadWait, TimeUnit.MILLISECONDS);
        
        driver.get("http://www.dn.se/");
    }

    @Test
    public void testImplicitWait() {
        implicitWait = 1;

        driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        driver.get("file:///" + System.getProperty("user.dir") + "/docs/person.html");

        start();

        try {
            driver.findElement(NO_EXIST);
        } catch (NoSuchElementException e) {
            long timeTaken = stop();
            Assert.assertEquals(implicitWait, timeTaken);
            LOGGER.debug("ImplicitWait: " + implicitWait + " => " + timeTaken);
        }

    }

    /**
     * Hur jag än gör så är det bara implicitWait som gäller.
     * Kanske fungerar det om jag sätter implicitWait till 0.
     * Men det känns lite random.
     */
    @Test
    public void testWebDriverWait() {
        implicitWait = 0;
        webDriverWait = 1;
        pool = 500;

        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        driver.get("file:///" + System.getProperty("user.dir") + "/docs/person.html");

        start();

        WebDriverWait wait = new WebDriverWait(driver, webDriverWait, pool);

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(NO_EXIST));
        } catch (Exception e) {
            long timeTaken = stop();
            LOGGER.debug("WebDriverWait: " + implicitWait + " => " + webDriverWait + " => " + timeTaken);
            Assert.assertEquals(webDriverWait, timeTaken);
        }
    }

    /**
     * Det värde som är högst gäller.
     */
    @Test
    public void testFluentWait() {
        implicitWait = 0;
        fluentWait = 2;
        pool = 500;
        
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        driver.get("file:///" + System.getProperty("user.dir") + "/docs/person.html");

        start();
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(fluentWait, SECONDS)
                    .pollingEvery(pool, MILLISECONDS)
                    .ignoring(NoSuchElementException.class);

            WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(NO_EXIST);
                }
            });
            
        } catch (TimeoutException e) {
            long timeTaken = stop();
            LOGGER.debug("FluentWait: " + fluentWait + " => " + timeTaken);
            Assert.assertEquals(fluentWait, timeTaken);
        }
    }

    public static boolean waitForAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);

            return wait.until(ExpectedConditions.alertIsPresent()) != null;
        } catch (TimeoutException e) {
            return false;
        }
    }
    
    private void start() {
        startTimer = System.currentTimeMillis();
    }

    private long stop() {
        long stopTimer = System.currentTimeMillis();
        return (stopTimer - startTimer) / 1000;
    }

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
    }
    
    @AfterClass
    public static void cleanUpClass() {
        driver.quit();
        driver = null;
    }
}
