package com.application.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Coord {

	private final StringProperty type;
	private final StringProperty name;
	private final IntegerProperty[] coords;


	public Coord() {
		this(null, null, null);
	}

	public Coord(Coord other) {
		this(other.getType(), other.getName(), other.getCoords());
	}

	public Coord(String type, String name, int[] coords) {
		this.type = new SimpleStringProperty(type);
		this.name = new SimpleStringProperty(name);

        this.coords = new IntegerProperty[2];
        this.coords[0] = new SimpleIntegerProperty(coords[0]);
        this.coords[1] = new SimpleIntegerProperty(coords[1]);
	}
	
	public String getType() {
		return type.get();
	}

	public void setType(String type) {
		this.type.set(type);
	}
	
	public StringProperty typeProperty() {
		return type;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}
	
	public StringProperty nameProperty() {
		return name;
	}

	public int[] getCoords() {
        int[] res = new int[2];
        res[0] = this.coords[0].get();
        res[1] = this.coords[1].get();
		return res;
	}

	public void setCoords(int[] coords) {
        this.coords[0] = new SimpleIntegerProperty(coords[0]);
        this.coords[1] = new SimpleIntegerProperty(coords[1]);
	}
	
	public IntegerProperty[] coordsProperty() {
		return coords;
	}
}