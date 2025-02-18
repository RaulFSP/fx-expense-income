package com.portfolio.fxexpensetrack.controllers;

import com.portfolio.fxexpensetrack.App;
import com.portfolio.fxexpensetrack.utils.ConfigManager;
import com.portfolio.fxexpensetrack.utils.StageManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConfigMenuController implements Initializable {

    private ConfigManager configManager;
    private Properties properties;
    @FXML
    private ToggleButton toggleDarkMode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configManager = App.getConfigManager();
        properties = configManager.getProperties();
        loadConfig();
        if(Boolean.parseBoolean(properties.getProperty("app.dark-style"))){
            Platform.runLater(()->{
            StageManager.getStageConfigMenu().getScene().getStylesheets().add(getClass().getResource("/styles/dark-styles.css").toExternalForm());
            });
        }
        toggleDarkMode.setOnAction(e -> handleDarkMode());
    }

    private void loadConfig() {
        boolean keyValue = Boolean.parseBoolean(properties.getProperty("app.dark-style"));
        toggleDarkMode.setSelected(keyValue);
    }

    private void handleDarkMode() {
        properties.setProperty("app.dark-style", String.valueOf(toggleDarkMode.isSelected()));
        configManager.saveProperties();

    }
}
