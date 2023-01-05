package com.application.util;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawerUtil {
    private Canvas canvas;
    private GraphicsContext gc;

    public DrawerUtil(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        clearCanvas();
    }

    public void clearCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void drawCircle(double[] coord) {
        gc.setFill(Color.RED);
        gc.fillOval(coord[0], coord[1], 10, 10);
    }

    public void drawRhomb(double[] coord) {
        gc.setFill(Color.BLUE);
        gc.fillPolygon(new double[] {coord[0]-5, coord[0], coord[0]+5, coord[0]}, new double[] {coord[1], coord[1]-5, coord[1], coord[1]+5}, 4);
    }

    public void drawTriangle(double[] coord) {
        gc.setFill(Color.GREEN);
        gc.fillPolygon(new double[] {coord[0]-5, coord[0]+5, coord[0]-5}, new double[] {coord[1]-5, coord[1], coord[1]+5}, 3);
    }
}
