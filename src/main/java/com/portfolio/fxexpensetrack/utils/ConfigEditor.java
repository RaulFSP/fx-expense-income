package com.portfolio.fxexpensetrack.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigEditor {
    private static final Logger logger = Logger.getLogger(ConfigEditor.class.getName());
    private Properties properties;


    public ConfigEditor(Properties properties) {
        this.properties = properties;

    }

    private void readProperties(){
        try (InputStream inputStream = getClass().getResourceAsStream("/config.properties")) {
            properties.load(inputStream);
        } catch (IOException | NullPointerException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
