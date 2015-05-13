package se.recan.framework.person;

import org.junit.Test;
import org.junit.Assert;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import se.recan.framework.selenium.DriverFactory;

/**
 * 2015-apr-28
 *
 * @author Anders Recksén (recan, Prolore)
 */
public class SLTest {

    private static final Logger LOGGER = Logger.getLogger("Logger");

    @Test
    public void SLTestTest() {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("http://sl.se");
        driver.findElement(By.id("travelplanner_from")).clear();
        driver.findElement(By.id("travelplanner_from")).sendKeys("T-centralen");
//        driver.findElement(By.id("travelplanner_from")).click();

        driver.findElement(By.id("travelplanner_to")).clear();
        driver.findElement(By.id("travelplanner_to")).sendKeys("Odenplan");
//        driver.findElement(By.id("travelplanner_to")).click();
//          Select select = new Select(driver.findElement(By.cssSelector("select.ng-pristine.ng-valid")));
//        select.selectByIndex(2);
        driver.findElement(By.cssSelector("span.button-primary.clickable.searchButton")).click();
        WebElement e = driver.findElement(By.cssSelector("span[class~=to][class~=ng-binding1]"));
        System.out.println("==================" + e.getText());

        driver.quit();
    }

}
