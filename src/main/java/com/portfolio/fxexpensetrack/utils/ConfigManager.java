package com.portfolio.fxexpensetrack.utils;

import com.portfolio.fxexpensetrack.App;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigManager {
    private static final Logger logger = Logger.getLogger(ConfigManager.class.getName());
    private final Properties properties = new Properties();


    public ConfigManager() {
        readProperties();
    }

    public void readProperties() {
        try (InputStream inputStream = getClass().getResourceAsStream("/config.properties")) {
            properties.load(inputStream);
        } catch (IOException | NullPointerException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void saveProperties() {

        try (FileOutputStream out = new FileOutputStream("src/main/resources/config.properties")) {
            properties.store(out, null);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
