package com.example.financialmovementmanager.controller;

import com.example.financialmovementmanager.model.FinancialMovementList;
import com.example.financialmovementmanager.model.FinancialMovement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FinancialMovementController {
    @FXML
    private TextField descriptionFinancialMovementTF;

    @FXML
    private  TextField typeFinancialMovementTF;

    @FXML
    private TextField valueFinancialMovementTF;

    @FXML
    private TextField dateFinancialMovementTF;

    private FinancialMovementList financialMovementList;

    public void setFinancialMovementList(FinancialMovementList financialMovementList){
        this.financialMovementList = financialMovementList;
    }

    @FXML
    public void onAddFinancialMovement(ActionEvent event) {
        String description = descriptionFinancialMovementTF.getText();
        String value = valueFinancialMovementTF.getText();
        String type = typeFinancialMovementTF.getText().toLowerCase();
        String dateString = dateFinancialMovementTF.getText();

        if(!type.equals("income") && !type.equals("cost")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error in Type Input");
            alert.setContentText("The type of financial movement must be 'cost' or 'income'.");
            alert.showAndWait();
        }else {

            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                double doubleValue = Double.parseDouble(value);
                Date date= format.parse(dateString);
                financialMovementList.addFinancialMovement(new FinancialMovement(description,doubleValue,type,date));

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText("Financial Movement Added Successfully");
                alert.setContentText("To see all the changes reflected, press a View button; otherwise, you may have an outdated or incorrect list.");
                alert.showAndWait();

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Adding Financial Movement");
                alert.setContentText("The value must be a valid number.");
                alert.showAndWait();
            } catch (ParseException e2) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Error Adding Financial Movement");
                alert2.setContentText("The date must be in the format dd/mm/yyyy.");
                alert2.showAndWait();
            }
        }

    }

    public boolean isAdded(){
        return true;
    }

    public FinancialMovementList getFinancialMovementListCostOnly(){
        FinancialMovementList financialMovementList2=new FinancialMovementList();
        for (FinancialMovement financialMovement: financialMovementList.getFinancialMovements()
             ) {
            if(financialMovement.getType().equals("cost")){
                financialMovementList2.addFinancialMovement(financialMovement);
            }
        }
        return financialMovementList2;
    }

    public FinancialMovementList getFinancialMovementListIncomeOnly(){
        FinancialMovementList financialMovementList2=new FinancialMovementList();
        for (FinancialMovement financialMovement: financialMovementList.getFinancialMovements()
        ) {
            if(financialMovement.getType().equals("income")){
                financialMovementList2.addFinancialMovement(financialMovement);
            }
        }
        return financialMovementList2;
    }

    @FXML
    public void onClose(ActionEvent event) {
        Stage stage = (Stage) descriptionFinancialMovementTF.getScene().getWindow();
        stage.close();
    }

    public FinancialMovementList getFinancialMovementList() {
        return financialMovementList;
    }
}
