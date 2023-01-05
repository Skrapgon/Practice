package com.application.views;

import java.util.ArrayList;

import com.application.MainApp;
import com.application.models.Coord;
import com.application.util.CalculationUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class WarehouseLocationDialogController {
    @FXML
    private Button save;

    private ArrayList<Coord> brigadeListArray;
    private ArrayList<Coord> railwayListArray;

    private MainApp mainApp;

    private Stage stage;

    @FXML
    private void initialize() {
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setData(ArrayList<Coord> brigadeListArray, ArrayList<Coord> railwayListArray) {
        this.brigadeListArray = brigadeListArray;
        this.railwayListArray = railwayListArray;
    }

    @FXML
    public void handleWarehouseLocation() {
        mainApp.setWarehouse(CalculationUtil.computeWarehouseCoord(brigadeListArray, railwayListArray));
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(stage);
        alert.setTitle("Местоположение склада рассчитано");
        alert.setContentText("Местоположение склада было успешно рассчитано");
        alert.showAndWait();
    }

    
}
