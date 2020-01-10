package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.Driver;

public class BrowserSetup {

    /* After we have created how our driver will be initialised in our Driver.class, we then need to set up how it is
     * actioned. We need to create a set up function that will happen before we start our scenario. It's good practice
     * to also create a tear down function to clear up after the scenario has ended.
     *
     * Here we are using Cucumber tags to action these functions.
     *
     * Also make sure your Hooks/BrowserSetUp/Context is within the same package as your Step Definitions so Cucumber
     * can find them.
     */

    private WebDriver driver;

    @Before
    public void setUpDriver() {
        // Here we're saying our the driver in our Driver.class will be the one we're initialising
        driver = Driver.driver();
    }


    WebDriver getDriver() {
        /* We use this function call the driver in our step definitions and pass it through to our pages/actions.
         * This keeps the driver session too so a new driver instance is not created in every feature step.
         */
        return driver;
    }

    @After
    public void tearDownDriver() {
        // It's good practice to clear any cookies at the end of your driver session
        driver.manage().deleteAllCookies();
        /* There are 2 ways to end the driver:
         * - driver.close();
         * - driver.quit();
         *
         * Close only closes the window which on a Mac doesn't actually close browser, you're best off using quit so
         * the browser is truly killed.
         */
        driver.quit();
    }

    /* Now we have set up our browser we should configure the URLs we want our suite to navigate through.
     * In this example we use Configuration.class to set up the url hosts.
     */

    /* Note: What we have called BrowserSetup may be called ContextSteps or Hooks in other projects. */

}
