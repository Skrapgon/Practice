package com.application.views;

import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;

import com.application.MainApp;
import com.application.models.Coord;
import com.application.util.CalculationUtil;
import com.application.util.DrawerUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NodeSelectionController {

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Button button10;
    @FXML
    private Button button11;
    @FXML
    private Button button12;
    @FXML
    private Button button13;
    @FXML
    private Button button14;
    @FXML
    private Button button15;
    @FXML
    private Button button16;
    @FXML
    private Button button17;
    @FXML
    private Button button18;
    @FXML
    private Button button19;
    @FXML
    private Button button20;
    @FXML
    private Button button21;
    @FXML
    private Button button22;
    @FXML
    private Button button23;
    @FXML
    private Button button24;
    @FXML
    private Button button25;
    @FXML
    private ListView<String> brigadeList;
    @FXML
    private ListView<String> railwayList;
    @FXML
    private ComboBox<String> scale;
    @FXML
    private ComboBox<String> types;
    @FXML
    private ComboBox<String> names;
    @FXML
    private AnchorPane nodeEditingPanel;
    @FXML
    private ComboBox<String> nodeTypes;
    @FXML
    private ComboBox<String> nodeNames;
    @FXML
    private Button nodePropertiesSaving;
    @FXML
    private Button closeEditingPanelButton;
    @FXML
    private Button nodesSaving;
    @FXML
    private Canvas canvas;

    private Button selectButton = null;

    private Stage stage;
    private String[] scaleItems;
    private String[] typesItems;
    private String[] brigadeItems;
    private String[] railwayItems;
    private ArrayList<Coord> brigadeListArrayTemp;
    private ArrayList<Coord> railwayListArrayTemp;
    private Coord warehouse;
    private DrawerUtil drawer;
    private ObservableList<String> brigadeListNames;
    private ObservableList<String> railwayListNames;

    private int currentScale;
    private MainApp mainApp;

    @FXML
    private void initialize() {
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setData(String[] scaleItems, String[] typesItems, String[] brigadeItems, String[] railwayItems, ArrayList<Coord> brigadeListArray, ArrayList<Coord> railwayListArray, Coord warehouse, int currentScale, Canvas canvas) {
        this.scaleItems = scaleItems;
        this.typesItems = typesItems;
        this.brigadeItems = brigadeItems;
        this.railwayItems = railwayItems;
        scale.getItems().addAll(this.scaleItems);
        types.getItems().addAll(this.typesItems);
        this.brigadeListArrayTemp = new ArrayList<Coord>();
        this.railwayListArrayTemp = new ArrayList<Coord>();
        this.warehouse = warehouse;
        this.currentScale = currentScale;
        this.canvas = canvas;
        this.canvas.setLayoutY(40);
        this.canvas.toBack();
        drawer = new DrawerUtil(canvas);
        int index = -1;
        for (int i = 0; i < this.scaleItems.length; i++) {
            if (this.scaleItems[i].equals(Integer.toString(this.currentScale))) {
                index = i;
                break;
            }
        }
        if (index != -1) scale.getSelectionModel().select(index);
        brigadeListNames = FXCollections.observableArrayList();
        for (Coord coord : brigadeListArray) {
            this.brigadeListArrayTemp.add(new Coord(coord));
            brigadeListNames.add(coord.getName());
        }
        brigadeList.setItems(brigadeListNames);
        railwayListNames = FXCollections.observableArrayList();
        for (Coord coord : railwayListArray) {
            this.railwayListArrayTemp.add(new Coord(coord));
            railwayListNames.add(coord.getName());
        }
        railwayList.setItems(railwayListNames);
        canvas.requestFocus();
        drawFigures();
    }

    @FXML
    public void setCurrentScale() {
        currentScale = Integer.valueOf(scale.getSelectionModel().getSelectedItem().toString());
        mainApp.setCurrentScale(currentScale);
    }

    @FXML
    public void handleShowNodeEditMenu(ActionEvent event) {
        selectButton = ((Button)event.getSource());

        Coord brigadeTemp = CalculationUtil.isInArrayList(brigadeListArrayTemp, CalculationUtil.transferDoubleToInteger(new double[] {selectButton.getLayoutX(), selectButton.getLayoutY()}));
        Coord railwayTemp = CalculationUtil.isInArrayList(railwayListArrayTemp, CalculationUtil.transferDoubleToInteger(new double[] {selectButton.getLayoutX(), selectButton.getLayoutY()}));

        if (brigadeTemp != null) {
            types.getSelectionModel().select(brigadeTemp.getType());
            names.getSelectionModel().select(brigadeTemp.getName());
        }
        else if (railwayTemp != null) {
            types.getSelectionModel().select(railwayTemp.getType());
            names.getSelectionModel().select(railwayTemp.getName());
        }
        else {
            names.getItems().removeAll(brigadeItems);
            names.getItems().removeAll(railwayItems);
            types.getSelectionModel().select(-1);
        }
        nodeEditingPanel.setVisible(true);
    }

    @FXML
    public void handleChangeType() {
        if (types.getSelectionModel().getSelectedIndex() != -1) {
            if (types.getSelectionModel().getSelectedItem().equals(typesItems[0])) {
                names.getItems().removeAll(railwayItems);
                names.getItems().addAll(brigadeItems);
            }
            else if (types.getSelectionModel().getSelectedItem().equals(typesItems[1])) {
                names.getItems().removeAll(brigadeItems);
                names.getItems().addAll(railwayItems);
            }
        }
    }

    @FXML
    public void handleCloseNodeEditMenu() {
        types.getSelectionModel().select(-1);
        names.getItems().removeAll(railwayItems);
        names.getItems().removeAll(brigadeItems);
        nodeEditingPanel.setVisible(false);
    }

    @FXML
    public void handleNodePropertiesSaving() {
        if (types.getSelectionModel().getSelectedIndex() != -1 && names.getSelectionModel().getSelectedIndex() != -1) {

            Coord tempCoord = new Coord(types.getSelectionModel().getSelectedItem(), names.getSelectionModel().getSelectedItem(), CalculationUtil.transferDoubleToInteger(new double[] {selectButton.getLayoutX(), selectButton.getLayoutY()}));
            
            ArrayList<Coord> arr1, arr2;
            ObservableList<String> list1, list2;
            
            if (tempCoord.getType().equals("Бригада")) {
                arr1 = brigadeListArrayTemp;
                arr2 = railwayListArrayTemp;
                list1 = brigadeListNames;
                list2 = railwayListNames;
            }
            else {
                arr1 = railwayListArrayTemp;
                arr2 = brigadeListArrayTemp;
                list1 = railwayListNames;
                list2 = brigadeListNames;
            }
            boolean exist = false;
            for (int i = 0; i < arr1.size(); i++) {
                if (arr1.get(i).getName().equals(tempCoord.getName())) {
                    arr1.set(i, tempCoord);
                    exist = true;
                }
                else if (arr1.get(i).getCoords()[0] == tempCoord.getCoords()[0] && arr1.get(i).getCoords()[1] == tempCoord.getCoords()[1]) {
                    arr1.remove(i);
                    list1.remove(i);
                }
            }
            for (int i = 0; i < arr2.size(); i++) {
                if (arr2.get(i).getCoords()[0] == tempCoord.getCoords()[0] && arr2.get(i).getCoords()[1] == tempCoord.getCoords()[1]) {
                    arr2.remove(i);
                    list2.remove(i);
                    break;
                }
            }
            if (!exist) {
                arr1.add(tempCoord);
                list1.add(tempCoord.getName());
            }

            warehouse = null;
            System.gc();

            canvas.requestFocus();
            drawFigures();
        }
    }

    @FXML
    public void handleNodesSaving() {
        if (brigadeListArrayTemp.size() < 3 || railwayListArrayTemp.size() < 3) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Необходимо указать достаточное число бригад и жд станций");
            alert.setContentText("Минимальное количество для бригад и станций равно 3");
            alert.showAndWait();
        }
        else if (warehouse != null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.initOwner(stage);
            alert.setTitle("Расположение склада уже определено");
            alert.setContentText("Расположение склада для указанных бригад и ЖД станций было определено ранее");
            alert.showAndWait();
        }
        else {
            mainApp.setBrigadeList(brigadeListArrayTemp);
            mainApp.setRailwayList(railwayListArrayTemp);
            mainApp.showWarehouseLocationDialog();
            warehouse = mainApp.getWarehouse();
            drawFigures();
            canvas.requestFocus();
        }
    }

    private void drawFigures() {
        canvas.requestFocus();
        drawer.clearCanvas();
        for (Coord coord : this.brigadeListArrayTemp){
            double[] tempCoord = CalculationUtil.transferIntegerToDouble(coord.getCoords());
            drawer.drawRhomb(tempCoord);
        }
        for (Coord coord : this.railwayListArrayTemp){
            double[] tempCoord = CalculationUtil.transferIntegerToDouble(coord.getCoords());
            drawer.drawTriangle(tempCoord);
        }
        if (this.warehouse != null) {
            double[] tempCoord = CalculationUtil.transferIntegerToDouble(warehouse.getCoords());
            drawer.drawCircle(tempCoord);
        }
    }
}