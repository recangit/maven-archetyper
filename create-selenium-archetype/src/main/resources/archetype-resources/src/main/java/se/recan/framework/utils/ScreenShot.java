package se.recan.framework.utils;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import se.recan.framework.selenium.DriverFactory;

/**
 * 2015-apr-26
 * @author Anders Recksén (recan, Prolore)
 */
public class ScreenShot {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    public static void captureScreenshot(String fileName) {
        Calendar calendar = Calendar.getInstance();
	SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
                
        try {
            File scrFile = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
            File destFile = new File("screenshots/" + fileName + "_" + formater.format(calendar.getTime()) + ".png");

            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException e) {
            System.err.println("Error taking screenshot: " + e);
        }
    }
}
