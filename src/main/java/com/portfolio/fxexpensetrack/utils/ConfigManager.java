package com.portfolio.fxexpensetrack.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigManager {
    private static final Logger logger = Logger.getLogger(ConfigManager.class.getName());
    private final Properties properties = new Properties();


    public ConfigManager() {
        readProperties();
    }

    public void readProperties(){
        try (InputStream inputStream = getClass().getResourceAsStream( "/config.properties")) {
            properties.load(inputStream);
        } catch (IOException | NullPointerException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void setProperty(String key, String value){

    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
