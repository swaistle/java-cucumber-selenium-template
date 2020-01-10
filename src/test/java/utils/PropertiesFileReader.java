package utils;

import java.util.ResourceBundle;

public class PropertiesFileReader {

    /* Here we are creating a properties file reader. We name the properties files the same as the environment names we
     * would use. For example, local.properties, dev.properties, test.properties, staging.properties. The reader will
     * then match based on the file name i.e. local, dev, test, staging.
     *
     * So when we use -Denvironment=local in the mvn command, the reader will use local.properties and will find the
     * key called "hostName" in the properties file and use that value.
     */

    static String getHostName(String environment, String key) {
        ResourceBundle rb = ResourceBundle.getBundle(environment);
        return rb.getString(key);
    }

    /* The second properties file reader we're creating is to pull the URL paths from the paths.properties file.
     * URLs are composed of host, path and sometimes parameters - to save duplication of paths being mentioned in the
     * environment.properties files it's wise to strip them out to their own properties file. This reduces the risk of
     * not replacing in the appropriate places when the time asks for it.
     */

    public static String getPathName(String key) {
        ResourceBundle rb = ResourceBundle.getBundle("paths");
        return  rb.getString(key);
    }


}
