package com.portfolio.fxexpensetrack.utils;

import com.portfolio.fxexpensetrack.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StageManager {
    private static final Logger logger = Logger.getLogger(StageManager.class.getName());
    private static Stage stageHome = new Stage();
    private static Stage stageAddEntry = new Stage();
    private static Stage stageConfigMenu = new Stage();

    public static Stage getStageHome() {
        return stageHome;
    }

    public static Stage getStageAddEntry() {
        return stageAddEntry;
    }

    public static Stage getStageConfigMenu() {
        return stageConfigMenu;
    }

    public static void showHome() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/views/home.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stageHome.setTitle("Expenses/Income Tracker!");
            stageHome.setScene(scene);
            stageHome.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(),e);
        }
    }

    public static void showAddEntry() {
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/views/add-new.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stageAddEntry.setTitle("New Entry");
            stageAddEntry.setScene(scene);
            stageAddEntry.show();
        }  catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(),e);
        }
    }

    public static void showConfigMenu() {
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/views/config-menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stageConfigMenu.setTitle("Configuration Menu");
            stageConfigMenu.setScene(scene);
            stageConfigMenu.show();
        }  catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(),e);
        }
    }
}
