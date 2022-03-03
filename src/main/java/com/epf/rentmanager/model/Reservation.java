package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Reservation {
	private int id;
	private int idVehicule;
	private int idClient;
	private LocalDate dateStart;
	private LocalDate dateEnd;

	public Reservation(int id, int idVehicule, int idClient, LocalDate dateStart, LocalDate dateEnd) {
		super();
		this.id = id;
		this.idVehicule = idVehicule;
		this.idClient = idClient;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}
	

	public Reservation(int id, int idVehicule, LocalDate dateStart, LocalDate dateEnd) {
		super();
		this.id = id;
		this.idVehicule = idVehicule;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdVehicule() {
		return idVehicule;
	}

	public void setIdVehicule(int idVehicule) {
		this.idVehicule = idVehicule;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public LocalDate getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDate dateStart) {
		this.dateStart = dateStart;
	}

	public LocalDate getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(LocalDate dateEnd) {
		this.dateEnd = dateEnd;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", idVehicule=" + idVehicule + ", idClient=" + idClient + ", dateStart="
				+ dateStart + ", dateEnd=" + dateEnd + "]";
	}
	
}
