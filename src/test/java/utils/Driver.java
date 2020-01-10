package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {

    /* Here we set up our driver configuration.
     * We use the Web Driver Manager plugin to set up the Chrome Driver instance in this example.
     * This is so it's not hard-coded in the framework, and/or forces a reliability on the machine running the suite to
     * have the correct compatibility between the Chrome Driver and version of Chrome Browser.
     */

    public static WebDriver driver() {
        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        return driver;
    }

    // After creating the driver the next step would be to set up the browser

}
