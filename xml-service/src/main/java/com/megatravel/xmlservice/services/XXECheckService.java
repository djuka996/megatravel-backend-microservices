package com.megatravel.xmlservice.services;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Service
public class XXECheckService {

	private static final String DISALLOW_DOCTYPE = "http://apache.org/xml/features/disallow-doctype-decl";
	private static final String EXTERNAL_GENERAL = "http://xml.org/sax/features/external-general-entities";
	private static final String EXTERNAL_PARAMETER = "http://xml.org/sax/features/external-parameter-entities";
	
	public void validate(String message) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setFeature(DISALLOW_DOCTYPE, true);
			factory.setFeature(EXTERNAL_GENERAL, true);
			factory.setFeature(EXTERNAL_PARAMETER, true);
			factory.setXIncludeAware(false);
			factory.setExpandEntityReferences(false);
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.parse(new InputSource(new StringReader(message)));
		} catch(ParserConfigurationException | SAXException | IOException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The XML file that was received is invalid and couldn't be parsed");
		}
	}
	
}
