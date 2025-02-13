package com.portfolio.fxexpensetrack.controllers;

import com.portfolio.fxexpensetrack.App;
import com.portfolio.fxexpensetrack.entities.Value;
import com.portfolio.fxexpensetrack.utils.DataLists;
import com.portfolio.fxexpensetrack.utils.ValueType;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.css.StyleClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class HomeController implements Initializable {


    private final NumberFormat format = NumberFormat.getCurrencyInstance();
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
    private TableColumn<Value, String> colAmount;

    @FXML
    private MenuItem itemExpensesTable;

    @FXML
    private MenuItem itemIncomeTable;

    @FXML
    private MenuItem itemGeneralTable;

    @FXML
    private MenuItem itemDarkMode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configTableValues();
        configValueTrackerInit();
        configValueTrackersUpdate();
        Platform.runLater(() -> {
            tableValues.setItems(DataLists.getListValues());
            cbTableValuesFilter.setItems(DataLists.getListTableValueFilters());
            cbTableValuesFilter.getSelectionModel().selectFirst();
        });
        cbTableValuesFilter.setOnAction(e -> handleTableFilter());
        btnConfirm.setOnAction(action -> handleAddNew());
        itemDarkMode.setOnAction(e->handleItemDarkMode());
    }

    private void handleItemDarkMode(){

        btnConfirm.getScene().getStylesheets().add(App.class.getResource("/styles/dark-styles.css").toExternalForm());

    }

    private void configTableValues() {


        colID.setCellValueFactory(cell -> new SimpleLongProperty(cell.getValue().getId()).asObject());

        colAmount.setCellValueFactory(cell -> new SimpleObjectProperty<>(format.format(cell.getValue().getAmount().setScale(6, RoundingMode.HALF_UP))));

        colDescription.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDescription()));

        colDate.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getCreationDate()));

        colType.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getType()));


    }

    private void configValueTrackerInit() {
        Platform.runLater(() -> {
            var list = DataLists.getListValues();
            BigDecimal expenses = list.stream().filter(value -> value.getType().getValue().equals("expense")).map(Value::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal incomes = list.stream().filter(value -> value.getType().getValue().equals("income")).map(Value::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal balance = incomes.subtract(expenses);
            lbExpenses.setText(format.format(expenses));
            lbIncomes.setText(format.format(incomes));
            lbBalance.setText(format.format(balance));
        });
    }

    private void configValueTrackersUpdate() {

        DataLists.getListValues().addListener((ListChangeListener<Value>) change -> {
            BigDecimal expenses = change.getList().stream().filter(value -> value.getType().getValue().equals("expense")).map(Value::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal incomes = change.getList().stream().filter(value -> value.getType().getValue().equals("income")).map(Value::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal balance = incomes.subtract(expenses);
            lbExpenses.setText(format.format(expenses));
            lbIncomes.setText(format.format(incomes));
            lbBalance.setText(format.format(balance));
        });
    }

    private void handleAddNew() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/views/add-new.fxml"));
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
            case "all" -> Platform.runLater(() -> {
                tableValues.setItems(DataLists.getListValues());
            });
            case "expense" -> Platform.runLater(() -> {
                FilteredList<Value> filteredList = new FilteredList<>(DataLists.getListValues());
                filteredList.setPredicate(item -> item.getType() != null && "expense".equals(item.getType().getValue()));
                tableValues.setItems(FXCollections.observableArrayList(filteredList));
            });
            case "income" -> Platform.runLater(() -> {
                FilteredList<Value> filteredList = new FilteredList<>(DataLists.getListValues());
                filteredList.setPredicate(item -> item.getType() != null && "income".equals(item.getType().getValue()));
                tableValues.setItems(FXCollections.observableArrayList(filteredList));
            });
        }
    }


}