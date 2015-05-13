package ${groupId}.glue.main;

import cucumber.annotation.sv.När;
import cucumber.annotation.sv.Så;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import ${groupId}.views.MainView;
import ${groupId}.Driver;

/**
 *
 * 2014-aug-25
 *
 * @author Anders Recksén (recan)
 */
public class MainSteps {

    private static final Logger LOGGER = Logger.getLogger("Logger");

	private static final MainView mainView = PageFactory.initElements(Driver.getDriver(), MainView.class);    
}
