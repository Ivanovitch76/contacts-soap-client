package be.steformations.it.service.contacts.soap.client;

import java.util.List;

import be.steformations.it.java_data.contacts.dto.ContactDto;
import be.steformations.it.java_data.contacts.dto.CountryDto;
import be.steformations.it.java_data.contacts.dto.TagDto;
import be.steformations.it.service.contacts.soap.interfaces.ContactSoapService;
import be.steformations.it.service.contacts.soap.interfaces.CountrySoapService;
import be.steformations.it.service.contacts.soap.interfaces.TagSoapService;

public class ContactSoapClient {

	private TagSoapService tagSoapService;
	private CountrySoapService countrySoapService;
	private ContactSoapService contactSoapService;

	public ContactSoapClient() throws Exception{
		super();
		java.net.URL wsdl = new java.net.URL("http://localhost:8080/contacts-soap/ws/TagSoapService?wsdl");
		javax.xml.namespace.QName qname = new javax.xml.namespace.QName("http://implementations.soap.contacts.service.it.steformations.be/", 
																		"TagSoapServiceImplService");		
		this.tagSoapService = javax.xml.ws.Service.create(wsdl, qname).getPort(TagSoapService.class);
		
		wsdl = new java.net.URL("http://localhost:8080/contacts-soap/ws/CountrySoapService?wsdl");
		qname = new javax.xml.namespace.QName("http://implementations.soap.contacts.service.it.steformations.be/", 
				"CountrySoapServiceImplService");
		this.countrySoapService = javax.xml.ws.Service.create(wsdl, qname).getPort(CountrySoapService.class);

		wsdl = new java.net.URL("http://localhost:8080/contacts-soap/ws/ContactSoapService?wsdl");
		qname = new javax.xml.namespace.QName("http://implementations.soap.contacts.service.it.steformations.be/", 
				"ContactSoapServiceImplService");
		this.contactSoapService = javax.xml.ws.Service.create(wsdl, qname).getPort(ContactSoapService.class);
	}

	public TagDto createAndSaveTag(String value) {
		return this.tagSoapService.createAndSaveTag(value);
	}

	public TagDto getTagById(Integer id) {
		return this.tagSoapService.findOneTagById(id);
	}

	public List<TagDto> getAllTags() {
		return this.tagSoapService.findAllTags();
	}

	public TagDto getTagByValue(String string) {
		return this.tagSoapService.findOneTagByValue(string);
	}

	public boolean removeTag(Integer id) {
		TagDto dto = this.tagSoapService.removeTag(id);
		return dto != null;
	}

	public CountryDto createAndSaveCountry(String abbreviation, String name) {
		return this.countrySoapService.createAndSaveCountry(abbreviation, name);
	}

	public CountryDto getCountryByAbbreviation(String abbreviation) {
		return this.countrySoapService.findOneCountryByAbbreviation(abbreviation);
	}

	public List<CountryDto> getAllCountries() {
		return this.countrySoapService.findAllCountries();
	}

	public ContactDto getContactById(Integer id) {
		return this.contactSoapService.findOneContactById(id);
	}

	public List<ContactDto> getAllContacts() {
		return this.contactSoapService.findAllContacts();
	}

	public ContactDto createAndSaveContact(String firstname, String name, String email, String abbreviation,
			java.util.List<String> tags) {
		return this.contactSoapService.createAndSaveContact(firstname, name, email, abbreviation, tags);
	}

	public ContactDto removeContact(Integer id) {
		return this.contactSoapService.removeContact(id);
	}
	
	
	
}
