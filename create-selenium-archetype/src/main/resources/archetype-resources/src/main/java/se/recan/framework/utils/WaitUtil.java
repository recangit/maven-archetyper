package se.recan.framework.utils;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import se.recan.framework.selenium.DriverFactory;

/**
 * 2015-apr-28
 * @author Anders Recksén (recan, Prolore)
 */
public class WaitUtil {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    // Implicit och page
        // WebDriverWait + Implicit = ?
        // Om implicit är 30, kommer då kommando som är fel försöka denna tid?

    WebDriver driver = DriverFactory.getDriver();
//    .manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
   
    
    //    public static WebElement waitForElement(WebElement elementToWaitFor) {
//        try {
//            LOGGER.debug("waitForElement: " + elementToWaitFor);
//            return waitForElement(elementToWaitFor, null);
//        } catch (Exception e) {
//            LOGGER.error("Hittar inte WebElement: " + elementToWaitFor);
//            captureScreenshot();
//            throw new NoSuchElementException("Hittar inte WebElement: " + elementToWaitFor);
//        }
//    }

//    public static WebElement waitForElement(WebElement elementToWaitFor, Integer waitTimeInSeconds) {
//        if (waitTimeInSeconds == null) {
//            waitTimeInSeconds = 10;
//        }
//        WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
//        return wait.until(ExpectedConditions.visibilityOf(elementToWaitFor));
//    }

//    public static WebElement waitForElement(By elementToWaitFor) {
//        try {
//            LOGGER.debug("waitForElement: " + elementToWaitFor);
//            return waitForElement(elementToWaitFor, null);
//        } catch (Exception e) {
//            LOGGER.error("Hittar inte WebElement: " + elementToWaitFor);
//            captureScreenshot();
//            throw new NoSuchElementException("Hittar inte WebElement: " + elementToWaitFor);
//        }
//    }

//    public static WebElement waitForElement(By elementToWaitFor, Integer waitTimeInSeconds) {
//        if (waitTimeInSeconds == null) {
//            waitTimeInSeconds = 10;
//        }
//        WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
//        return wait.until(ExpectedConditions.presenceOfElementLocated(elementToWaitFor));
//    }

    /*
     private WebElement waitForFluentElement(WebDriver driver, final By by) throws MalformedURLException, IOException {
     // Waiting 30 seconds for an element to be present on the page, checking
     // for its presence once every 5 seconds.
     FluentWait<WebDriver> wait = new FluentWait(driver);
     wait.withTimeout(30, TimeUnit.MILLISECONDS);
     wait.pollingEvery(5, TimeUnit.SECONDS);
     wait.withMessage("Felmeddelande");
     wait.ignoring(NoSuchElementException.class);

     // Function parametrar:
     // F - the type of the function input
     // T - the type of the function output
     wait.until(new Function<WebDriver, Boolean>() {
     @Override
     public Boolean apply(WebDriver driver) {
     HttpURLConnection httpConnection = (HttpURLConnection) new URL(url).openConnection();
     return httpConnection.getResponseCode() == 200;
     }
     });
     return null;
     }
     */
}
