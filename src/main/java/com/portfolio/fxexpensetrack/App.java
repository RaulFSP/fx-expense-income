package com.portfolio.fxexpensetrack;

import com.portfolio.fxexpensetrack.dao.DAO;
import com.portfolio.fxexpensetrack.repositories.ValueRepository;
import com.portfolio.fxexpensetrack.utils.DataLists;
import com.portfolio.fxexpensetrack.utils.StageManager;
import com.portfolio.fxexpensetrack.utils.ValueType;
import jakarta.persistence.EntityManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
    }

    public static void main(String[] args) {
        launch();
    }
}