package com.portfolio.fxexpensetrack;

import com.portfolio.fxexpensetrack.dao.DAO;
import com.portfolio.fxexpensetrack.entities.enums.ValueType;
import com.portfolio.fxexpensetrack.repositories.ValueRepository;
import com.portfolio.fxexpensetrack.utils.ConfigManager;
import com.portfolio.fxexpensetrack.utils.DataLists;
import com.portfolio.fxexpensetrack.utils.StageManager;
import jakarta.persistence.EntityManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;



public class App extends Application {

    private static ConfigManager configManager;

    @Override
    public void start(Stage stage) {
        StageManager.showHome();
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

    @Override
    public void init() {
        configManager = new ConfigManager();
        Platform.runLater(() -> {
            DataLists.getListValueTypes().setAll(ValueType.values());
            try (EntityManager manager = DAO.getEntityManager()) {
                DataLists.getListValues().setAll(new ValueRepository(manager).findAll());
            }
        });
    }


}