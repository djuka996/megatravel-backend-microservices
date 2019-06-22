package com.megatravel.xmlservice.services;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Service
public class XMLUtilityService {

	public static final String XSD_FOLDER = "Schemas";
	public static final String WSDLS_FILE = "info.xml";

	public static final String NAME_TAG = "MicroserviceName";
	public static final String PASSWORD_TAG = "MicroservicePassword";

	public static final String WEB_SERVICE_NAME_TAG = "WebServiceName";
	public static final String WSDL_TAG = "Wsdl";

	/**
	 * Iščitava dobijeni tekstualni sadržaj u dokument kako bi se njime lakše manipulisalo. Validira sadržaj u odnosu na šemu
	 * koja odgovara <b>SOAP Envelope</b>-u.
	 * @param xml - tekstualni sadržaj koji odgovara SOAP poruci
	 * @param webServiceName - ime veb servisa koji se poziva
	 * @return Isparsirani dokument
	 */
	public Document loadXMLFromString(String xml) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(new InputSource(new StringReader(xml)));
		} catch(ParserConfigurationException | SAXException | IOException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The XML file that was received is invalid and couldn't be parsed");
		}
	}

	/**
	 * Transformiše prosleđeni dokument u tekstualni sadržaj.
	 * @param document - dokument koji se transformiše u tekstualni sadržaj
	 * @return Tekstualni sadržaj dokumenta
	 */
	public String getStringFromDocument(Document document) {
		try {
			DOMSource domSource = new DOMSource(document);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			return writer.toString();
		}
		catch(TransformerException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error ocurred while transforming the document to string.");
		}
	}

	/**
	 * Vraća lozinku Store-ova mikroservisa.
	 * @param microserviceName - ime mikroservisa
	 * @return Lozinka
	 */
	public String getPasswordOfMicroservice(String microserviceName) {
		try {
			File file = new File(KeyStoreReaderService.STORES_FOLDER + KeyStoreReaderService.PASSWORDS_FILE);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			int i = 0;
			while(true) {
				String currentName = document.getElementsByTagName(NAME_TAG).item(i).getTextContent();
				if(currentName.equals(microserviceName)) {
					return document.getElementsByTagName(PASSWORD_TAG ).item(i).getTextContent();
				}
				i++;
			}
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error ocurred while fetching password of " + microserviceName + ".");
		}
	}

	/**
	 * Vraća lokaciju WSDL fajla veb servisa.
	 * @param webServiceName - ime veb servisa
	 * @return Lokacija WSDL fajla
	 */
	public String getWsdlOfWebService(String webServiceName) {
		try {
			File file = new File(XMLUtilityService.XSD_FOLDER + "/" + XMLUtilityService.WSDLS_FILE);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			int i = 0;
			while(true) {
				String currentName = document.getElementsByTagName(WEB_SERVICE_NAME_TAG).item(i).getTextContent();
				if(currentName.equals(webServiceName)) {
					return document.getElementsByTagName(WSDL_TAG ).item(i).getTextContent();
				}
				i++;
			}
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error ocurred while fetching WSDL of " + webServiceName + ".");
		}
	}

	/**
	 * Vraća listu tagova svih XML elemenata koji su osetljivi, odnosno ih treba enkriptovati.
	 * <b>Za sada vraća Body kao jedini element koji se šifruje.</b>
	 * @return Lista tagova u vidu stringova
	 */
	public List<String> getSensitiveTags() {
		List<String> tags = new ArrayList<String>();
		tags.add("SOAP-ENV:Body");
		return tags;
	}

	/**
	 * Validira dokument u odnosu na XSD šemu koja je pridružena prosleđenom veb servisu.
	 * @param document - dokument koji se validira
	 * @param webServiceName - ime veb servisa
	 */
	public void validate(Document document, String webServiceName) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * 			SchemaFactory xsdFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			File xsd = new File(XMLUtilityService.XSD_FOLDER + "/" + this.getWsdlOfWebService(webServiceName));
			Schema schema = xsdFactory.newSchema(xsd);
			
			factory.setSchema(schema);
	 * 
	 */
	
}
