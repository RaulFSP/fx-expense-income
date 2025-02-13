package com.portfolio.fxexpensetrack;

import com.portfolio.fxexpensetrack.dao.DAO;
import com.portfolio.fxexpensetrack.repositories.ValueRepository;
import com.portfolio.fxexpensetrack.utils.ConfigReader;
import com.portfolio.fxexpensetrack.utils.DataLists;
import com.portfolio.fxexpensetrack.utils.StageManager;
import com.portfolio.fxexpensetrack.entities.enums.ValueType;
import jakarta.persistence.EntityManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        StageManager.showHome();
    }

    @Override
    public void init() throws Exception {
        Platform.runLater(() -> {
            DataLists.getListValueTypes().setAll(ValueType.values());
            try (EntityManager manager = DAO.getEntityManager()) {
                DataLists.getListValues().setAll(new ValueRepository(manager).findAll());
            }
        });
        ConfigReader reader = new ConfigReader();
        System.out.println(reader.getProperty("app.dark-style"));
    }

    public static void main(String[] args) {
        launch();
    }
}