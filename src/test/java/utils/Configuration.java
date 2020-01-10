package utils;

import static utils.PropertiesFileReader.getHostName;

public class Configuration {

    /* Configuration is typically where you what to set any rules/configurations.
     * I use it set the environment I want to test. I pass in an argument in my mvn command, for example
     * -Denvironment=local, then pull the correct host from the correct properties file.
     *
     * Planning this early on in the creation of the suite, prevents a heavy refactor later on when you want to run
     * your suite in other environments.
     */

    public static String environmentHostCheck(String key) {
        String environmentName = System.getProperty("environment");
        String result;
        /* To be able to pull the correct properties file we need a properties file reader that matches based on the
         * word we pass through in our mvn argument. Here we set up a PropertiesFileReader class to create the
         * getHostName function.
         */
        result = getHostName(environmentName, key);
        return result;
    }

    // Now configuration is set up, we can write our first feature file
}
