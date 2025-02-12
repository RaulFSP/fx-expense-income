package com.portfolio.fxexpensetrack.controllers;

import com.portfolio.fxexpensetrack.dao.DAO;
import com.portfolio.fxexpensetrack.entities.Value;
import com.portfolio.fxexpensetrack.repositories.ValueRepository;
import com.portfolio.fxexpensetrack.utils.DataLists;
import com.portfolio.fxexpensetrack.utils.ValueType;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewController implements Initializable {

    @FXML
    private ComboBox<ValueType> cbValueType;

    @FXML
    private TextField tfvalueAmount;

    @FXML
    private TextArea tfValueDescription;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnConfirm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbValueType.setItems(DataLists.getListValueTypes());
        btnCancel.setOnAction(e->handleCancelAction());
        btnClear.setOnAction(e->handleClearAction());
        btnConfirm.setOnAction(e->handleAddEntry());
    }

    private void handleCancelAction(){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private void handleClearAction(){
        cbValueType.setValue(null);
        tfvalueAmount.clear();
        tfValueDescription.clear();
    }

    private void handleAddEntry(){

        if(cbValueType.getSelectionModel().getSelectedItem() == null || tfvalueAmount.getText().trim().isEmpty() || tfValueDescription.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Campos vazios", ButtonType.OK);
            alert.setHeaderText("");
            alert.showAndWait();
        } else {
            Value value = new Value();
            value.setAmount(Double.parseDouble(tfvalueAmount.getText().trim()));
            value.setDescription(tfValueDescription.getText().trim());
            value.setType(cbValueType.getValue());
            ValueRepository repository = new ValueRepository(DAO.getEntityManager());
            repository.save(value);
            Platform.runLater(()->{
                DataLists.getListValues().setAll(repository.findAll());
            });
            handleClearAction();
        }
    }
}
