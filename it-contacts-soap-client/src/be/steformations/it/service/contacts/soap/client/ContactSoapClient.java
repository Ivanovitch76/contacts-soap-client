package be.steformations.it.service.contacts.soap.client;

import java.util.List;

import be.steformations.it.java_data.contacts.dto.TagDto;
import be.steformations.it.service.contacts.soap.interfaces.TagSoapService;

public class ContactSoapClient {

	private TagSoapService tagSoapService;

	public ContactSoapClient() throws Exception{
		super();
		java.net.URL wsdl = new java.net.URL("http://localhost:8080/contacts-soap/ws/TagSoapService?wsdl");
		javax.xml.namespace.QName qname = new javax.xml.namespace.QName("http://implementations.soap.contacts.service.it.steformations.be/", 
																		"TagSoapServiceImplService");
		
		this.tagSoapService = javax.xml.ws.Service.create(wsdl, qname).getPort(TagSoapService.class);
	}

	public TagDto createAndSaveTag(String value) {
		return this.tagSoapService.createAndSave(value);
	}

	public TagDto getTagById(Integer id) {
		return this.tagSoapService.findOneById(id);
	}

	public List<TagDto> getAllTags() {
		return this.tagSoapService.findAll();
	}

	public TagDto getTagByValue(String string) {
		return this.tagSoapService.findOneByValue(string);
	}

	public boolean removeTag(Integer id) {
		TagDto dto = this.tagSoapService.remove(id);
		return dto != null;
	}
	
	
	
}
