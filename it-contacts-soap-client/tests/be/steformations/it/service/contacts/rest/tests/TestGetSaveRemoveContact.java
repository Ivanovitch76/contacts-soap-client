package be.steformations.it.service.contacts.rest.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.steformations.it.java_data.contacts.dto.ContactDto;
import be.steformations.it.java_data.contacts.dto.CountryDto;
import be.steformations.it.java_data.contacts.dto.TagDto;
import be.steformations.it.service.contacts.soap.client.ContactSoapClient;

public class TestGetSaveRemoveContact {

	ContactSoapClient client;
	
	@Before
	public void setUp() throws Exception {
		this.client = new ContactSoapClient();
	}

	@Test
	public void testGetContactById() {
		ContactDto betty = this.createBettyBoop();
		ContactDto found = this.client.getContactById(betty.getId());
		assertEquals(betty, found);
	}

	@Test
	public void testGetAllContacts() {
		ContactDto betty = this.createBettyBoop();
		java.util.List<ContactDto> contacts = this.client.getAllContacts();
		assertTrue(contacts.contains(betty));
	}

	@Test
	public void testCreateAndSaveContact() {
		ContactDto _new = this.createNew();
		java.util.List<String> tags = new java.util.ArrayList<>();
		for (TagDto t : _new.getTags()){
			tags.add(t.getValue());
		}
		String abbreviation = _new.getCountry().getAbbreviation();
		ContactDto received = this.client.createAndSaveContact(_new.getFirstname(), _new.getName(), _new.getEmail(), abbreviation, tags);
		assertNotNull(received);
		assertEquals(_new.getFirstname(), received.getFirstname());
		assertEquals(_new.getName(), received.getName());
		assertEquals(_new.getEmail(), received.getEmail());
		assertEquals(_new.getCountry(), received.getCountry());
		assertEquals(_new.getTags(), received.getTags());
		
		ContactDto deleted = this.client.removeContact(received.getId());
		assertEquals(received, deleted);
	}

	private ContactDto createNew(){
		CountryDto usa = new CountryDto();
		usa.setId(1);
		usa.setAbbreviation("US");
		usa.setName("Etats-Unis");
		
		TagDto vamp = new TagDto();
		vamp.setId(2);
		vamp.setValue("vamp");
		
		TagDto sexsymbol = new TagDto();
		sexsymbol.setId(1);
		sexsymbol.setValue("sex-symbol");
		
		ContactDto _new = new ContactDto();
		_new.setId(0);
		_new.setFirstname("Betty-" + System.currentTimeMillis());
		_new.setName("Boop");
		_new.setEmail(_new.getFirstname() + "@hollywood.com");
		_new.setCountry(usa);
		_new.getTags().add(sexsymbol);
		_new.getTags().add(vamp);
		
		return _new;
	}
	
	private ContactDto createBettyBoop(){
		CountryDto usa = new CountryDto();
		usa.setId(1);
		usa.setAbbreviation("US");
		usa.setName("Etats-Unis");
		
		TagDto vamp = new TagDto();
		vamp.setId(2);
		vamp.setValue("vamp");
		
		TagDto sexsymbol = new TagDto();
		sexsymbol.setId(1);
		sexsymbol.setValue("sex-symbol");
		
		ContactDto betty = new ContactDto();
		betty.setId(1);
		betty.setFirstname("Betty");
		betty.setName("Boop");
		betty.setEmail("betty.boop@hollywood.com");
		betty.setCountry(usa);
		betty.getTags().add(sexsymbol);
		betty.getTags().add(vamp);
		
		return betty;
	}
	
}
