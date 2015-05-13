package se.recan.framework.selenium;

import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import se.recan.framework.utils.Props;

/**
 *
 * 2014-aug-12
 *
 * @author Anders Recksén (recan)
 */
public class DriverFactory {

    public static enum Browser {

        CHROME, FIREFOX, PHANTOM
    }

    public static enum Position {

        LEFT, RIGHT, CORNER, CENTER, FULL
    }

    private static WebDriver driver;

    private static final Logger LOGGER = Logger.getLogger("Logger");

    private static final Props PROPS = new Props("src/test/resources/config.properties");
    private static final String CHROME_DRIVER_PATH = PROPS.getProperty("chrome.driver");
    private static final String BROWSER = PROPS.getProperty("browser").toUpperCase();
    private static final int IMPLICIT_WAIT = PROPS.getIntProperty("implicit.wait");

    public synchronized static WebDriver getDriver() {
        return getDriver(Browser.valueOf(BROWSER));
    }

    public synchronized static WebDriver getDriver(Browser browser) {

        if (driver == null) {
            try {
                DesiredCapabilities capabilities;

                switch (browser) {
                    case CHROME:
                        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
                        driver = new ChromeDriver();
                        break;
                    case FIREFOX:
                        driver = new FirefoxDriver();
                        break;
                    case PHANTOM:
                        driver = new HtmlUnitDriver();
                        break;
                }

                LOGGER.debug("Create new Driver " + driver);
            } catch (WebDriverException e) {
                LOGGER.error("WebDriverException: " + e);
            }
        }

        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);

        return driver;
    }

    public static void manageWindow(Position position) {
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        int sliceW = (int) width / 3;
        int sliceH = (int) height / 3;
        
        switch (position) {
            case LEFT:
                driver.manage().window().setPosition(new Point(0, 0));
                driver.manage().window().setSize(new Dimension((int)width/2, (int) height));
                LOGGER.debug("Position:" + driver.manage().window().getPosition());
                LOGGER.debug("Storlek:" + driver.manage().window().getSize());
                break;
            case RIGHT:
                driver.manage().window().setPosition(new Point((int)width/2, 0));
//                driver.manage().window().setSize(new Dimension((int)width/4, (int) height));
                LOGGER.debug("Position:" + driver.manage().window().getPosition());
                LOGGER.debug("Storlek:" + driver.manage().window().getSize());
                break;
            case CORNER:
                driver.manage().window().setPosition(new Point(0, 0));
                driver.manage().window().setSize(new Dimension(sliceW, sliceH));
                LOGGER.debug("Position:" + driver.manage().window().getPosition());
                LOGGER.debug("Storlek:" + driver.manage().window().getSize());
                break;
            case CENTER:
                driver.manage().window().setPosition(new Point(200, 200));
                driver.manage().window().setSize(new Dimension((int)width-400, (int)height-400));
                LOGGER.debug("Position:" + driver.manage().window().getPosition());
                LOGGER.debug("Storlek:" + driver.manage().window().getSize());
                break;
            case FULL:
                driver.manage().window().maximize();
                break;
        }
    }

    public static void cleanUp() {
        driver.quit();
        driver = null;
    }
}
