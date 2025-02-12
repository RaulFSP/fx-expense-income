package com.portfolio.fxexpensetrack;
import com.portfolio.fxexpensetrack.dao.DAO;
import com.portfolio.fxexpensetrack.repositories.ValueRepository;
import com.portfolio.fxexpensetrack.utils.DataLists;
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
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Expenses/Income Tracker!");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() throws Exception {
            Platform.runLater(()->{
        try(EntityManager manager = DAO.getEntityManager()){
                DataLists.getListValues().setAll(new ValueRepository(manager).findAll());
                DataLists.getListValueTypes().setAll(ValueType.values());

        }
            });
    }

    public static void main(String[] args) {
        launch();
    }
}