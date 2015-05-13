package ${groupId}.run;

import cucumber.junit.Cucumber;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import ${groupId}.Driver;

/**
 *
 * 2014-aug-15
 * @author Anders Recksén (recan)
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
        glue = "${groupId}.glue.main",
        features = "features/main.feature",
        format = {"pretty", "html:target/Cucumber"}
)

public class RunMain {
    @ClassRule
    public static Driver classRule = new Driver();
}
