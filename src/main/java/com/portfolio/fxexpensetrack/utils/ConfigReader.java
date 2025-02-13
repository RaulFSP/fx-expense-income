package com.portfolio.fxexpensetrack.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigReader {
    private static final Logger logger = Logger.getLogger(ConfigReader.class.getName());
    private Properties properties = new Properties();


    public ConfigReader() {
        readProperties();
    }

    public void readProperties(){
        try (InputStream inputStream = getClass().getResourceAsStream( "/config.properties")) {
            properties.load(inputStream);
        } catch (IOException | NullPointerException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
