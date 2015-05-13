package ${groupId};

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * 2014-aug-12
 *
 * @author Anders Recksén (recan)
 */
public class View {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    /**
     * Endast för debug. Skall vara 0 i produktion.
     */
    public static final int SLEEP = 1;
    public static final int OK = 1;
    public static final int DISMISS = 2;

    public void refresh() {
        Driver.getDriver().navigate().refresh();
    }

    public String getUrl() {
        return Driver.getDriver().getCurrentUrl();
    }

    public String getTitle() {
        return Driver.getDriver().getTitle().toLowerCase();
    }

    public static WebElement getParent(WebElement element) {
        return element.findElement(By.xpath(".."));
    }

    public static WebElement waitForElement(WebElement elementToWaitFor) {
        try {
            LOGGER.debug("waitForElement: " + elementToWaitFor);
            return waitForElement(elementToWaitFor, null);
        } catch (Exception e) {
            LOGGER.error("Hittar inte WebElement: " + elementToWaitFor);
            captureScreenshot();
            throw new NoSuchElementException("Hittar inte WebElement: " + elementToWaitFor);
        }
    }

    public static WebElement waitForElement(WebElement elementToWaitFor, Integer waitTimeInSeconds) {
        if (waitTimeInSeconds == null) {
            waitTimeInSeconds = 10;
        }
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), waitTimeInSeconds);
        return wait.until(ExpectedConditions.visibilityOf(elementToWaitFor));
    }

    public static WebElement waitForElement(By elementToWaitFor) {
        try {
            LOGGER.debug("waitForElement: " + elementToWaitFor);
            return waitForElement(elementToWaitFor, null);
        } catch (Exception e) {
            LOGGER.error("Hittar inte WebElement: " + elementToWaitFor);
            captureScreenshot();
            throw new NoSuchElementException("Hittar inte WebElement: " + elementToWaitFor);
        }
    }

    public static WebElement waitForElement(By elementToWaitFor, Integer waitTimeInSeconds) {
        if (waitTimeInSeconds == null) {
            waitTimeInSeconds = 10;
        }
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), waitTimeInSeconds);
        return wait.until(ExpectedConditions.presenceOfElementLocated(elementToWaitFor));
    }

    // Använd denna istället för att kontrollera om ett Element finns med NoSuchElementException
    public boolean isElementPresent(By locator) {
        return Driver.getDriver().findElements(locator).size() > 0;
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

    // Klicka på Ok eller Avbryt om det finns en javascript alert.
    public void clickAlert(int value) {
        try {
            sleep(3);
            if (value == OK) {
                Driver.getDriver().switchTo().alert().accept();
            } else if (value == DISMISS) {
                Driver.getDriver().switchTo().alert().dismiss();
            }
        } catch (NoAlertPresentException e) {
            LOGGER.error("Ingen JavaScript Alert", e);
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

    public static void captureScreenshot() {
        try {
            File scrFile = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
            File destFile = new File("screenShot" + dateToString(System.currentTimeMillis(), "-HH:mm:s") + ".png");
            LOGGER.error("Taking screenshot... " + destFile);
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException e) {
            System.err.println("Error taking screenshot: " + e);
            LOGGER.error("Error taking  screenshot: " + e);
        }
    }

    private static String dateToString(long milliseconds, String pattern) {
        if (milliseconds == 0) {
            return "";
        }

        SimpleDateFormat formatter = new SimpleDateFormat(pattern, new Locale("sv", "SE"));
        return formatter.format(milliseconds);
    }
}
