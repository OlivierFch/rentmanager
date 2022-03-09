package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Reservation {
	private int id;
	private int idClient;
	private int idVehicle;
	private LocalDate debut;
	private LocalDate end;

	public Reservation(int id, int idClient, int idVehicle, LocalDate debut, LocalDate end) {
		this.id = id;
		this.idClient = idClient;
		this.idVehicle = idVehicle;
		this.debut = debut;
		this.end = end;
	}
	
	public Reservation(int id) {
		this.id = id;
	}

	public Reservation(int idClient, int idVehicle, LocalDate debut, LocalDate end) {
		this.idClient = idClient;
		this.idVehicle = idVehicle;
		this.debut = debut;
		this.end = end;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdVehicle() {
		return idVehicle;
	}

	public void setIdVehicle(int idVehicle) {
		this.idVehicle = idVehicle;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public LocalDate getDebut() {
		return debut;
	}

	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}
	
	
	public Reservation idClient(int idClient) {
		setIdClient(idClient);
		return this;
	}
	
	public Reservation idVehicle(int idVehicle) {
		setIdVehicle(idVehicle);
		return this;
	}
	
	public Reservation debut(LocalDate debut) {
		setDebut(debut);
		return this;
	}
	
	public Reservation end(LocalDate end) {
		setEnd(end);
		return this;
	}
	

	@Override
	public String toString() {
		return "{" +
                " id='" + getId() + "'" +
                ", idClient='" + getIdClient() + "'" +
                ", idVehicle='" + getIdVehicle() + "'" +
                ", debut='" + getDebut() + "'" +
                ", end='" + getEnd() + "'" +
                "}";
	}
	
}
