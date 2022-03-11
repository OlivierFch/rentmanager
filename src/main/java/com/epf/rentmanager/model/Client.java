package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Client {
	private long id;
	private String firstname;
	private String lastname;
	private String email;
	private LocalDate birthdate;

	public Client(long id, String lastname, String firstname, String email, LocalDate birthdate) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.birthdate = birthdate;
	}
	

	public Client(String lastname, String firstname, String email, LocalDate birthdate) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.birthdate = birthdate;
	}
	

	public Client(long id, String email) {
		super();
		this.id = id;
		this.email = email;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", birthdate=" + birthdate + "]";
	}
	
}
