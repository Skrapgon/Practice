package com.application;

import com.application.models.Coord;
import com.application.views.MainMenuController;
import com.application.views.NodeSelectionController;
import com.application.views.WarehouseLocationDialogController;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private Stage nodeSelectionStage;
    private AnchorPane rootLayout;
    private ArrayList<Coord> brigadeListArray = new ArrayList<Coord>();
    private ArrayList<Coord> railwayListArray = new ArrayList<Coord>();
    private Coord warehouse;

    private String[] scale = { "1", "2", "4" };
    private String[] types = {"Бригада", "ЖД станция"};
    private String[] brigadeNames = {"Бригада 1", "Бригада 2", "Бригада 3"};
    private String[] railwayNames = {"ЖД станция 1", "ЖД станция 2", "ЖД станция 3"};

    private int currentScale = -1;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Практика");

        initRootLayout();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("views/MainMenu.fxml"));
            rootLayout = (AnchorPane) loader.load();

            MainMenuController controller = loader.getController();
            controller.setMainApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showNodeSelection() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("views/NodeSelection.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
    
            nodeSelectionStage = new Stage();
            nodeSelectionStage.setTitle("Выбор координат");
            nodeSelectionStage.initModality(Modality.WINDOW_MODAL);
            nodeSelectionStage.initOwner(primaryStage);
            Scene scene = new Scene(page, 1600, 900);
            Canvas testCanvas = new Canvas(1290, 860);
            page.getChildren().add(testCanvas);
            nodeSelectionStage.setScene(scene);
            nodeSelectionStage.setResizable(false);
            
            NodeSelectionController controller = loader.getController();
            controller.setStage(nodeSelectionStage);
            controller.setMainApp(this);
            controller.setData(scale, types, brigadeNames, railwayNames, brigadeListArray, railwayListArray, warehouse, currentScale, testCanvas);

            primaryStage.hide();

            nodeSelectionStage.showAndWait();

            primaryStage.show();
    
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showWarehouseLocationDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("views/WarehouseLocationDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
    
            Stage WarehouseLocationDialogStage = new Stage();
            WarehouseLocationDialogStage.setTitle("Координаты сохранены");
            WarehouseLocationDialogStage.initModality(Modality.WINDOW_MODAL);
            WarehouseLocationDialogStage.initOwner(nodeSelectionStage);
            // WarehouseLocationDialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            WarehouseLocationDialogStage.setScene(scene);
            WarehouseLocationDialogStage.setResizable(false);
    
            WarehouseLocationDialogController controller = loader.getController();
            controller.setStage(WarehouseLocationDialogStage);
            controller.setMainApp(this);
            controller.setData(brigadeListArray, railwayListArray);
    
            WarehouseLocationDialogStage.showAndWait();

            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setCurrentScale(int currentScale) {
        this.currentScale = currentScale;
    }

    public int getCurrentScale() {
        return currentScale;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setWarehouse(Coord warehouse) {
        this.warehouse = warehouse;
    }

    public Coord getWarehouse() {
        return this.warehouse;
    }

    public void setBrigadeList(ArrayList<Coord> brigadeList) {
        this.brigadeListArray = new ArrayList<Coord>();
        for(Coord coord : brigadeList) this.brigadeListArray.add(new Coord(coord));
    }

    public void setRailwayList(ArrayList<Coord> railwayList) {
        this.railwayListArray = new ArrayList<Coord>();
        for(Coord coord : railwayList) this.railwayListArray.add(new Coord(coord));
    }

    public static void main(String[] args) {
        launch(args);
    }
}