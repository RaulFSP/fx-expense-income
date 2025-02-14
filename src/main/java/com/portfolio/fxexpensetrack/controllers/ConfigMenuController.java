package com.portfolio.fxexpensetrack.controllers;

import com.portfolio.fxexpensetrack.App;
import com.portfolio.fxexpensetrack.utils.ConfigManager;
import com.portfolio.fxexpensetrack.utils.StageManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfigMenuController implements Initializable {


    private ConfigManager configManager;
    @FXML
    private ToggleButton toggleDarkMode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configManager = App.getConfigManager();
        if(Boolean.parseBoolean(configManager.getProperty("app.dark-style"))){
            toggleDarkMode.setSelected(true);
        }
        toggleDarkMode.setOnAction(e->handleDarkMode());
    }

    private void handleDarkMode(){


    }
}
