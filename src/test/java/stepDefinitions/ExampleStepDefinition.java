package stepDefinitions;

import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import page.ExamplePage;
import utils.Configuration;

public class ExampleStepDefinition {

    /* This is where we write our step definitions and use Cucumber Regex (regular expression) to match the sentences
     * that are write within the feature files.
     */

    /* We need to give our code context to our driver. Some suites call this context steps, but we've called it
     * browser setup to give a bit more clarity.
     */

    private BrowserSetup browserSetup;

    public ExampleStepDefinition(BrowserSetup browserSetUp) { this.browserSetup = browserSetUp; }

    /* There's plenty of material on Cucumber Regex available online, but the main rule is to make sure you contain
     * your content within ^ and $. Everything wrote between those symbols will be matched exactly.
     * Here is a good cheat sheet to get you started:
     * https://agileforall.com/wp-content/uploads/2011/08/Cucumber-Regular-Expressions-Cheat-Sheet.pdf
     * I also check my regex in websites such as: https://regexr.com/
     */
    @Given("^I have navigated to the website$")
    public void navigateToWebsite() {
        /* Now we have context set up above, we can call the getDriver class from our BrowserSetup class so the same
         * driver session can be used. We then pass that driver instance into the code that will complete the action on
         * the designated page
         */
        WebDriver driver = browserSetup.getDriver();
        String expectedUrl = Configuration.environmentHostCheck("hostName");
        /* Since the below function asks for a url to be supplied, we set that in a String value above. Most landing
         * pages URLs are typically just the hostname. But if your landing page did have a path then you simply add
         * the path reader to the end. E.g.
         * String url = Configuration.environmentHostCheck("hostName") + PropertiesFileReader.getPathName("path.example.one");
         */
        ExamplePage.navigateToUrl(driver, expectedUrl);

        /* Once we navigate to the url we want to assert we are on the correct page. I tend to do this by asserting
         * the url and the header on the page.
         */
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, currentUrl);
        /* Here we're asking JUnit to assert the expected outcome (expectedUrl) against the actual outcome (currentUrl).
         * In the steps after this you wouldn't navigate to the page via the driver command, i.e. driver.navigate().to();.
         * You would generally get there by clicking a link or button so asserting the url will seem more sensible in
         * that type of scenario step.
         */
    }

}
