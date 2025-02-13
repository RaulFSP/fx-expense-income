package com.portfolio.fxexpensetrack.controllers;

import com.portfolio.fxexpensetrack.App;
import com.portfolio.fxexpensetrack.utils.StageManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfigMenuController implements Initializable {

    @FXML
    private ToggleButton toggleDarkMode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toggleDarkMode.setOnAction(e->handleDarkMode());
    }

    private void handleDarkMode(){
        if (toggleDarkMode.isSelected()){
            toggleDarkMode.setText("ON");
            StageManager.getStageHome().getScene().getStylesheets().add(App.class.getResource("/styles/dark-styles.css").toExternalForm());
            StageManager.getStageAddEntry().getScene().getStylesheets().add(App.class.getResource("/styles/dark-styles.css").toExternalForm());
            StageManager.getStageConfigMenu().getScene().getStylesheets().add(App.class.getResource("/styles/dark-styles.css").toExternalForm());
        } else {
            toggleDarkMode.setText("OFF");
            StageManager.getStageHome().getScene().getStylesheets().remove(App.class.getResource("/styles/dark-styles.css").toExternalForm());
            StageManager.getStageAddEntry().getScene().getStylesheets().remove(App.class.getResource("/styles/dark-styles.css").toExternalForm());
            StageManager.getStageConfigMenu().getScene().getStylesheets().remove(App.class.getResource("/styles/dark-styles.css").toExternalForm());
        }
    }
}
