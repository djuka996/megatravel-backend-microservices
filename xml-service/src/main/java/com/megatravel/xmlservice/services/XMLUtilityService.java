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

	private static final String XSD_FOLDER = "Schemas";
	private static final String NAME_TAG = "MicroserviceName";
	private static final String PASSWORD_TAG = "MicroservicePassword";
	
	/**
	 * Iščitava dobijeni tekstualni sadržaj u dokument kako bi se njime lakše manipulisalo. Validira sadržaj u odnosu na šemu
	 * koja odgovara <b>SOAP Envelope</b>-u.
	 * @param xml - tekstualni sadržaj koji odgovara SOAP poruci
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
	 * Vraća listu tagova svih XML elemenata koji su osetljivi, odnosno ih treba enkriptovati.
	 * <b>Za sada vraća Body kao jedini element koji se šifruje.</b>
	 * @return Lista tagova u vidu stringova
	 */
	public List<String> getSensitiveTags() {
		List<String> tags = new ArrayList<String>();
		tags.add("SOAP-ENV:Body");
		return tags;
	}

}
