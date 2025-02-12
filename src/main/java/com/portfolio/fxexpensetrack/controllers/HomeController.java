package com.portfolio.fxexpensetrack.controllers;

import com.portfolio.fxexpensetrack.App;
import com.portfolio.fxexpensetrack.entities.Value;
import com.portfolio.fxexpensetrack.utils.DataLists;
import com.portfolio.fxexpensetrack.utils.ValueType;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label lbExpenses;

    @FXML
    private Label lbIncomes;

    @FXML
    private Label lbBalance;

    @FXML
    private TableView<Value> tableValues;

    @FXML
    private TableColumn<Value,Long> colID;

    @FXML
    private TableColumn<Value, ValueType> colType;

    @FXML
    private TableColumn<Value,String> colDescription;

    @FXML
    private TableColumn<Value, String> colDate;

    @FXML
    private TableColumn<Value, Double> colAmount;
    @FXML
    private MenuItem itemExpensesTable;

    @FXML
    private MenuItem itemIncomeTable;
    @FXML
    private MenuItem itemGeneralTable;

    @FXML
    private Button btnConfirm;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configTableValues();
        Platform.runLater(()->{
            tableValues.setItems(DataLists.getListValues());
        });
        btnConfirm.setOnAction(action->handleAddNew());
    }

    private void configTableValues(){
        colID.prefWidthProperty().bind(tableValues.prefWidthProperty().multiply(0.1));
        colID.setCellValueFactory(cell->{
            return new SimpleLongProperty(cell.getValue().getId()).asObject();
        });
        colAmount.setCellValueFactory(cell->{
            return new SimpleDoubleProperty(cell.getValue().getAmount()).asObject();
        });
        colDescription.setCellValueFactory(cell->{
            return new SimpleStringProperty(cell.getValue().getDescription());
        });
        colDate.setCellValueFactory(cell->{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return new ReadOnlyObjectWrapper<>(cell.getValue().getCreationDate().format(formatter));
        });
        colType.setCellValueFactory(cell->{
            return new ReadOnlyObjectWrapper<>(cell.getValue().getType());
        });

    }
    private void handleAddNew(){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/add-new.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("New Entry");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            System.err.println(e.getCause());
            System.err.println(e.getMessage());
        }

    }

}