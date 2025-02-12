package com.portfolio.fxexpensetrack.controllers;

import com.portfolio.fxexpensetrack.App;
import com.portfolio.fxexpensetrack.entities.Value;
import com.portfolio.fxexpensetrack.utils.DataLists;
import com.portfolio.fxexpensetrack.utils.ValueType;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HomeController implements Initializable {

    @FXML
    private Label lbExpenses;

    @FXML
    private Label lbIncomes;

    @FXML
    private Label lbBalance;

    @FXML
    private Button btnConfirm;

    @FXML
    private ComboBox<String> cbTableValuesFilter;

    @FXML
    private TableView<Value> tableValues;

    @FXML
    private TableColumn<Value, Long> colID;

    @FXML
    private TableColumn<Value, ValueType> colType;

    @FXML
    private TableColumn<Value, String> colDescription;

    @FXML
    private TableColumn<Value, LocalDate> colDate;

    @FXML
    private TableColumn<Value, BigDecimal> colAmount;

    @FXML
    private MenuItem itemExpensesTable;

    @FXML
    private MenuItem itemIncomeTable;

    @FXML
    private MenuItem itemGeneralTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configTableValues();
        configValueTrackers();
        Platform.runLater(() -> {
            tableValues.setItems(DataLists.getListValues());
            cbTableValuesFilter.setItems(DataLists.getListTableValueFilters());

        });
        cbTableValuesFilter.setOnAction(e -> handleTableFilter());
        btnConfirm.setOnAction(action -> handleAddNew());

    }

    private void configTableValues() {
        colID.prefWidthProperty().bind(tableValues.prefWidthProperty().multiply(0.1));
        colID.setCellValueFactory(cell -> new SimpleLongProperty(cell.getValue().getId()).asObject());


        colAmount.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getAmount()));

        colDescription.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDescription()));

        colDate.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getCreationDate()));

        colType.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getType()));


    }

    private void configValueTrackers(){
        DataLists.getListValues().addListener((ListChangeListener<Value>)  change->{
            BigDecimal balance = change.getList().stream().filter(value->value.getType().getValue().equals("expense")).map(Value::getAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
//            BigDecimal sum = change.getList().stream().map(Value::getAmount).filter(amount->amount != null).reduce(BigDecimal.ZERO,BigDecimal::add);
            lbBalance.setText(balance.toString());
        });
    }

    private void handleAddNew() {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/add-new.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("New Entry");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println(e.getCause());
            System.err.println(e.getMessage());
        }
    }

    private void handleTableFilter() {


        switch (cbTableValuesFilter.getValue()) {
            case "all" -> {
                Platform.runLater(() -> {
                    tableValues.setItems(DataLists.getListValues());

                });
            }
            case "expense" -> {
                Platform.runLater(() -> {
                    FilteredList<Value> filteredList = new FilteredList<>(DataLists.getListValues());
                    filteredList.setPredicate(item -> item.getType() != null && "expense".equals(item.getType().getValue()));
                    tableValues.setItems(filteredList);
                });
            }
            case "income" -> {
                Platform.runLater(() -> {
                    FilteredList<Value> filteredList = new FilteredList<>(DataLists.getListValues());
                    filteredList.setPredicate(item -> item.getType() != null && "income".equals(item.getType().getValue()));
                    tableValues.setItems(filteredList);
                });
            }

        }

    }
}