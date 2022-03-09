package com.epf.rentmanager.model;

public class Vehicle {
	private long id;
	private String constructor;
	private int nbPlaces;

	public Vehicle(long id, String constructor, int nbPlaces) {
		super();
		this.id = id;
		this.constructor = constructor;
		this.nbPlaces = nbPlaces;
	}
	
	
	public Vehicle(String constructor, int nbPlaces) {
		super();
		this.constructor = constructor;
		this.nbPlaces = nbPlaces;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getConstructor() {
		return constructor;
	}

	public void setConstructor(String constructor) {
		this.constructor = constructor;
	}

	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", constructor=" + constructor + ", nbPlaces=" + nbPlaces
				+ "]";
	}

	
	
}
