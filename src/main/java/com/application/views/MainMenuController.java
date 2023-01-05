package com.application.views;

import com.application.MainApp;


import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {
    @FXML
    private Button determiningWarehouse;

    private MainApp mainApp;

    @FXML
    private void initialize() {
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void handleNodeSelection() {
        mainApp.showNodeSelection();
    }
}
