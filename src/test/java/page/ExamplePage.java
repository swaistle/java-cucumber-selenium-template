package page;

import org.openqa.selenium.WebDriver;

public class ExamplePage {

    public static void navigateToUrl(WebDriver driver, String url) {
        /* Here we are passing in the driver context from the step definitions.
         * We are also making the function dynamic by allowing the ability to pass any URL through the parameter
         * String url.
         *
         * Using parameters allows for re-usability which is a good practice to carry out. It negates possible major
         * refactors in the future. You can pass in content from the feature files right through the functions here in
         * the pages.
         */
        driver.navigate().to(url);
    }

}
