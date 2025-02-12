package com.portfolio.fxexpensetrack.utils;

import com.portfolio.fxexpensetrack.entities.Value;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataLists {

    private static ObservableList<Value> listValues = FXCollections.observableArrayList();
    private static ObservableList<ValueType> listValueTypes = FXCollections.observableArrayList();
    public static ObservableList<Value> getListValues() {
        return listValues;
    }

    public static ObservableList<ValueType> getListValueTypes() {
        return listValueTypes;
    }
}
