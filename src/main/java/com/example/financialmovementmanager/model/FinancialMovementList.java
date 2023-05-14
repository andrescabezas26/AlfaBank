package com.example.financialmovementmanager.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FinancialMovementList {
    private ObservableList<FinancialMovement> financialMovements;

    public FinancialMovementList() {
        financialMovements = FXCollections.observableArrayList();
    }

    public ObservableList<FinancialMovement> getFinancialMovements() {
        return financialMovements;
    }

    public void addFinancialMovement(FinancialMovement financialMovement) {
        financialMovements.add(financialMovement);
    }

}
