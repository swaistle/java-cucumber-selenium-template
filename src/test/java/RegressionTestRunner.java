import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/* To run the tests you will need a runner class to execute. In the runner you will need to tell it where you will
 * find your features and where to find actions (glue). In this example we use JUnit to create the runner.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        // Your features should ideally be in the resources folder
        features = {"src/test/resources/features/"},
        // Plugins allow some creativity when generating the cucumber reports
        plugin = {"pretty", "html:target/reports"},
        /* The "glue" is the folder where you will find the step definitions you create so the features know where to
         * match the cucumber regex
         */
        glue = {"stepDefinitions"},
        /* Tags allow you to target specific scenarios that you want to run when the test runner is executed.
         * Simple add the tag to either above a scenario within a feature file, or at the top to run all of the scenarios
         * within the feature.
         * In this example, we are creating a regression suite. We want this test to run all the feature files so we
         * add the `@regression` tag to the top of the feature file.
         */
        tags = {"@regression"})
public class RegressionTestRunner {
    // The actions are all handled above with the JUnit runner above so no need to put anything in the TestRunner class
}
