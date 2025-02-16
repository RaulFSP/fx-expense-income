package com.portfolio.fxexpensetrack.utils;

import com.portfolio.fxexpensetrack.entities.Value;
import com.portfolio.fxexpensetrack.entities.enums.ValueType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataLists {

    private static final ObservableList<Value> listValues = FXCollections.observableArrayList();
    private static final ObservableList<ValueType> listValueTypes = FXCollections.observableArrayList();
    private static final ObservableList<String> listTableValueFilters = FXCollections.observableArrayList("all", "expense", "income");

    public static ObservableList<String> getListTableValueFilters() {
        return listTableValueFilters;
    }

    public static ObservableList<Value> getListValues() {
        return listValues;
    }

    public static ObservableList<ValueType> getListValueTypes() {
        return listValueTypes;
    }
}
