package com.application.util;

import java.util.ArrayList;

import com.application.models.Coord;

public class CalculationUtil {

    private static int scale = 140;
    private static int xOffset = 140;
    private static int yOffset = 65;

    public static Coord isInArrayList(ArrayList<Coord> arrayList, int[] coords) {
        for (Coord coord : arrayList) if (coord.getCoords()[0] == coords[0] && coord.getCoords()[1] == coords[1]) return coord;
        return null;
    }

    public static double[] transferIntegerToDouble(int[] coords) {
        double[] res = new double[2];
        res[0] = coords[0]*scale+xOffset;
        res[1] = coords[1]*scale+yOffset;
        return res;
    }

    public static int[] transferDoubleToInteger(double[] coords) {
        int[] res = new int[2];
        res[0] = (int) Math.round(coords[0]-xOffset)/scale;
        res[1] = (int)Math.round(coords[1]-yOffset)/scale;
        return res;
    }

    public static boolean equals(int[] coords, int x, int y) {
        if (coords[0] == x && coords[1] == y) return true;
        return false;
    }

    public static Coord computeWarehouseCoord(ArrayList<Coord> brigadeListArray, ArrayList<Coord> railwayListArray) {
        Coord warehouse = new Coord("Склад", "Склад", new int[]{-1, -1});

        int[] minCoord = {1000, 1000};
        int[] maxCoord = {0, 0};

        ArrayList<int[]> allNodes = new ArrayList<int[]>();
        double distance = 0;

        for (Coord coord : brigadeListArray) {
            int[] nodeCoord = coord.getCoords();
            if (minCoord[0] > nodeCoord[0]) minCoord[0] = nodeCoord[0];
            if (minCoord[1] > nodeCoord[1]) minCoord[1] = nodeCoord[1];
            if (maxCoord[0] < nodeCoord[0]) maxCoord[0] = nodeCoord[0];
            if (maxCoord[1] < nodeCoord[1]) maxCoord[1] = nodeCoord[1];
            allNodes.add(nodeCoord);
            distance += 2000;
        }

        for (Coord coord : railwayListArray) {
            int[] nodeCoord = coord.getCoords();
            if (minCoord[0] > nodeCoord[0]) minCoord[0] = nodeCoord[0];
            if (minCoord[1] > nodeCoord[1]) minCoord[1] = nodeCoord[1];
            if (maxCoord[0] < nodeCoord[0]) maxCoord[0] = nodeCoord[0];
            if (maxCoord[1] < nodeCoord[1]) maxCoord[1] = nodeCoord[1];
            allNodes.add(nodeCoord);
            distance += 2000;
        }

        for (int x = minCoord[0]; x <= maxCoord[0]; x++) {
            for (int y = minCoord[1]; y <= maxCoord[1]; y++) {
                boolean isInList = false;
                double tempDistance = 0;
                for (int i = 0; i < allNodes.size(); i++) {
                    tempDistance += (Math.pow(Math.pow(x-allNodes.get(i)[0], 2) + Math.pow(y-allNodes.get(i)[1], 2), 0.5));
                    if (equals(allNodes.get(i), x, y)) {
                        isInList = true;
                        break;
                    }
                }
                if (!isInList && tempDistance < distance) {
                    warehouse.setCoords(new int[]{x, y});
                    distance = tempDistance;
                }
            }
        }

        return warehouse;
    }
}
