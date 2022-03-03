package com.epf.rentmanager.model;

public class Vehicle {
	private int id;
	private String constructor;
	private String model;
	private int nbPlaces;

	public Vehicle(int id, String constructor, int nbPlaces) {
		super();
		this.id = id;
		this.constructor = constructor;
		this.nbPlaces = nbPlaces;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConstructor() {
		return constructor;
	}

	public void setConstructor(String constructor) {
		this.constructor = constructor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", constructor=" + constructor + ", model=" + model + ", nbPlaces=" + nbPlaces
				+ "]";
	}

	
	
}
