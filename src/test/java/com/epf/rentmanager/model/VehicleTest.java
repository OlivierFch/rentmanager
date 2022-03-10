package com.epf.rentmanager.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void testClient(){
		Vehicle vehicle = new Vehicle(1, "Renault", 4);
		
		assertEquals(vehicle.getNbPlaces(), 4);
	}
	
}
