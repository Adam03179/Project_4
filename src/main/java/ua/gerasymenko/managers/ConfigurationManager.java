package ua.gerasymenko.managers;

import java.util.ResourceBundle;

/**
 * The ConfigurationManager class  is a wrapper for ResourceBundle class,
 * and responds for config.properties file.
 *
 * @author Igor Gerasymenko
 */
public class ConfigurationManager {
    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle("config");

    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}

