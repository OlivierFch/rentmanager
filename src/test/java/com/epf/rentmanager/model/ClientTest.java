package com.epf.rentmanager.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;

import org.junit.Test;

public class ClientTest {


	@Test
	public void testClientFirstname(){
		Client client = new Client(1, "george", "jack", "jdhz@epfedu.fr", LocalDate.of(2012, 05, 05));
		
		assertEquals(client.getFirstname(), "tom");
	}
	
	@Test
	public void testClientAge(){
		Client client = new Client(1, "george", "jack", "jdhz@epfedu.fr", LocalDate.of(2012, 05, 05));
		
		assertNotEquals(client.getBirthdate(), LocalDate.of(2007, 12, 06));
	}
	
}
