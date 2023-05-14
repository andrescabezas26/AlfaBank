package com.example.financialmovementmanager;

import com.example.financialmovementmanager.controller.MainController;
import com.example.financialmovementmanager.model.FinancialMovementList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FinancialMovementList financialMovementList = new FinancialMovementList();
        FXMLLoader loader =  renderView("main-view.fxml");
        MainController controller = loader.getController();
        controller.initialize(financialMovementList);
    }

    public static FXMLLoader renderView(String fxml){
        FXMLLoader fxmlLoader = null;
        try {

            fxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource(fxml)
            );

            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent, 400, 400);
            Stage stage = new Stage();
            stage.setTitle("Alfa Bank App");
            stage.setScene(scene);
            stage.show();

        }catch (IOException ex){
            ex.getMessage();
            ex.printStackTrace();
        }
        return fxmlLoader;
    }

    public static void main(String[] args) {
        launch();
    }
}