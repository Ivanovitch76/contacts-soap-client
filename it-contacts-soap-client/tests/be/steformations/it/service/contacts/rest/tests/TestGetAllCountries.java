package be.steformations.it.service.contacts.rest.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.steformations.it.java_data.contacts.dto.CountryDto;
import be.steformations.it.service.contacts.soap.client.ContactSoapClient;


public class TestGetAllCountries {

	ContactSoapClient client;
	
	@Before
	public void setUp() throws Exception {
		this.client = new ContactSoapClient();
	}

	@Test
	public void testGetAllCountries() {
		CountryDto usa = new CountryDto();
		usa.setId(1);
		usa.setAbbreviation("US");
		usa.setName("Etats-Unis");
		
		java.util.List<CountryDto> countries = this.client.getAllCountries();
		assertTrue(countries.contains(usa));
	}

}
