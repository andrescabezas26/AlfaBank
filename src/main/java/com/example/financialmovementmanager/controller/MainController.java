package com.example.financialmovementmanager.controller;

import com.example.financialmovementmanager.MainApplication;
import com.example.financialmovementmanager.model.*;
import com.example.financialmovementmanager.model.FinancialMovement;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class MainController {
    @FXML
    private TableView<FinancialMovement> tableView;
    @FXML
    private TableColumn<FinancialMovement, String> typeColumn;
    @FXML
    private TableColumn<FinancialMovement, String> descriptionColumn;

    @FXML
    private TableColumn<FinancialMovement, String> valueColumn;

    @FXML
    private TableColumn<FinancialMovement, String> dateColumn;
    @FXML
    private Button addButton;
    @FXML
    private Button showCost;
    @FXML
    private Button showIncomes;
    @FXML
    private Button showCombinedColumns;

    @FXML
    private Label balance;

    private FinancialMovementList financialMovementList;

    private FinancialMovementList tempFinancialMovementList;

    private int typeView;


    public void initialize(FinancialMovementList financialMovementList) {
        this.financialMovementList = financialMovementList;
        this.tempFinancialMovementList = new FinancialMovementList();
        this.typeView = 0;
        tableView.setItems(tempFinancialMovementList.getFinancialMovements());
        tableView.setVisible(false);
    }

    public void printTableView(FinancialMovementList financialMovementListt){
        this.dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateString"));
        this.typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        this.descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        this.tableView.setItems(null);
        this.tableView.layout();
        this.tableView.setItems(financialMovementListt.getFinancialMovements());
        System.out.println(tableView);
    }

    public void onAddFinancialMovement(ActionEvent event) {
        sortByDate();
        FXMLLoader loader = MainApplication.renderView("add-FinancialMovement-view.fxml");
        FinancialMovementController financialMovementController = loader.getController();
        financialMovementController.setFinancialMovementList(this.financialMovementList);
    }

    public void onShowCost(ActionEvent event){
        typeView = 1;
        balance.setText(calculateBalance());
        sortByDate();
        this.tempFinancialMovementList = this.financialMovementList;
        FinancialMovementController financialMovementController = new FinancialMovementController();
        financialMovementController.setFinancialMovementList(this.tempFinancialMovementList);
        this.tempFinancialMovementList = financialMovementController.getFinancialMovementListCostOnly();
        printTableView(this.tempFinancialMovementList);
        tableView.setVisible(true);
    }

    public void onShowIncome(ActionEvent event){
        typeView = 2;
        balance.setText(calculateBalance());
        sortByDate();
        this.tempFinancialMovementList = this.financialMovementList;
        FinancialMovementController financialMovementController = new FinancialMovementController();
        financialMovementController.setFinancialMovementList(this.tempFinancialMovementList);
        this.tempFinancialMovementList= financialMovementController.getFinancialMovementListIncomeOnly();
        printTableView(this.tempFinancialMovementList);
        tableView.setVisible(true);
    }

    public void onShowCombined(ActionEvent event){
        typeView = 3;
        balance.setText(calculateBalance());
        sortByDate();
        this.tempFinancialMovementList = financialMovementList;
        printTableView(this.tempFinancialMovementList);
        tableView.setVisible(true);
    }

    public void sortByDate(){
        Collections.sort(this.financialMovementList.getFinancialMovements() , new Comparator<FinancialMovement>() {
            @Override
            public int compare(FinancialMovement p1, FinancialMovement p2) {
                if(p1.getDate().compareTo(p2.getDate())>0){
                    return -1;
                }else if(p1.getDate().compareTo(p2.getDate())<0){
                    return 1;
                }else {
                    return 0;
                }
            }
        });
    }

    public String calculateBalance(){
        int totalCost = 0;
        int totalIncome=0;
        for (FinancialMovement financialMovement: this.financialMovementList.getFinancialMovements()
        ) {
            if(financialMovement.getType().equals("cost")){
                totalCost+= financialMovement.getValue();
            }else {
                totalIncome+=financialMovement.getValue();
            }
        }
        return totalIncome-totalCost+"";
    }


}
