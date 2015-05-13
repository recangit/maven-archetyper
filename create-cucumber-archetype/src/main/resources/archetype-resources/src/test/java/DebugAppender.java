package ${groupId};

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.FileAppender;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 *
 * 2014-nov-12
 *
 * @author Anders Recksén (recan)
 */
public class DebugAppender extends FileAppender {

    private int counter = 0;
    private long pause = 0;
    private boolean alert = false;
    private boolean screenshot = false;

    @Override
    public void append(LoggingEvent event) {
        super.append(event);

        LocationInfo info = event.getLocationInformation();
        String className = info.getClassName().substring(info.getClassName().lastIndexOf(".") + 1);

        if (screenshot) {
            captureScreenshot(counter++ + "." + className + "." + info.getMethodName());
        }

        sleep(pause);
    }

    public void setPause(String value) {
        this.pause = Long.parseLong(value);
    }

    public void setScreenshot(String value) {
        this.screenshot = Boolean.parseBoolean(value);
    }

    public void sleep(long l) {
        if (l == 0) {
            return;
        }

        try {
            Thread.sleep(l);
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
        }
    }

    private void captureScreenshot(String fileName) {
        try {
            File scrFile = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
            File destFile = new File("screenshots/" + fileName + ".png");

            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException e) {
            System.err.println("Error taking screenshot: " + e);
        }
    }
}
