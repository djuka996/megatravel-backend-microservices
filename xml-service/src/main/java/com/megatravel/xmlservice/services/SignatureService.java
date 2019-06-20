package com.megatravel.xmlservice.services;

import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.keys.keyresolver.implementations.RSAKeyValueResolver;
import org.apache.xml.security.keys.keyresolver.implementations.X509CertificateResolver;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.TransformationException;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Service
public class SignatureService {
	/**
	 * U primeru stoji prazan string, moram proveriti šta bi trebalo staviti.
	 */
	private static final String URI = "";
	
	/**
	 * URI putanja do imenskog prostor SOAP poruke.
	 */
	private static final String SOAP_ENV_NS_URI = "http://schemas.xmlsoap.org/soap/envelope/";
	
	/**
	 * Prefiks imenskog prostora SOAP poruke.
	 */
	private static final String SOAP_ENV_NS_PREFIX = "soapenv";
	
	/**
	 * Tag zaglavlja SOAP poruke.
	 */
	private static final String SOAP_HEADER = "Header";
	
	/**
	 * Korenski tag SOAP poruke.
	 */
	private static final String SOAP_ENVELOPE = "Envelope";
	
	/**
	 * Tag tela SOAP poruke.
	 */
	private static final String SOAP_BODY = "Body";
	
	public SignatureService() {
		Security.addProvider(new BouncyCastleProvider());
		org.apache.xml.security.Init.init();
	}
	
	/**
	 * Potpisuje prosleđeni SOAP XML dokument na osnovu prosleđenih parametara.
	 * @param document - SOAP XML dokument koji se potpisuje
	 * @param privateKey - privatni ključ sertifikata pošiljaoca poruke
	 * @param certificate - sertifikat pošiljaoca poruke
	 * @return Potpisani SOAP XML dokument
	 */
	public Document signDocument(Document document, PrivateKey privateKey, Certificate certificate) {
		try {
			document = this.addSOAPHeaderIfNeccessary(document);
			Element headerElement = (Element) document.getElementsByTagNameNS(SOAP_ENV_NS_URI, SOAP_HEADER).item(0);
			XMLSignature signature = new XMLSignature(document, null, XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1);
			Transforms transforms = this.prepareTransforms(document);
			signature.addDocument(URI, transforms, Constants.ALGO_ID_DIGEST_SHA1);
			signature.addKeyInfo(certificate.getPublicKey());
			signature.addKeyInfo((X509Certificate) certificate);
			headerElement.appendChild(signature.getElement());
			signature.sign(privateKey);
			return document;
		} catch (XMLSecurityException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error ocurred while signing the document.");
		} catch (DOMException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An error ocurred while modifying the DOM tree! Please check your XML file.");
		}
	}	

	/**
	 * Proverava da li prosleđeni dokument sadrži element <b>SOAP-ENV:Header</b>.
	 * @param document - SOAP XML dokument
	 * @return Po potrebi izmenjen SOAP XML dokument, takav da sadrži <b>SOAP-ENV:Header</b>
	 */
	private Document addSOAPHeaderIfNeccessary(Document document) {
		NodeList headers = document.getElementsByTagNameNS(SOAP_ENV_NS_URI, SOAP_HEADER);
		if(headers.getLength() == 0) {
			return this.addSOAPHeader(document);
		} else {
			return document;
		}
	}

	/**
	 * Dodaje <b>SOAP-ENV:Header</b> prosleđenom dokumentu tako što ga ubacuje unutar <b>SOAP-ENV:Envelope</b> elementa, a 
	 * pre <b>SOAP-ENV:Body</b> elementa.
	 * @param document - SOAP XML dokument
	 * @return SOAP XML dokument kome je dodat navedeni element
	 */
	private Document addSOAPHeader(Document document) {
		Element envelope = (Element) document.getElementsByTagNameNS(SOAP_ENV_NS_URI, SOAP_ENVELOPE).item(0);
		Element body = (Element) document.getElementsByTagNameNS(SOAP_ENV_NS_URI, SOAP_BODY).item(0);
		Element header = document.createElementNS(SOAP_ENV_NS_URI, SOAP_HEADER);
		header.setPrefix(SOAP_ENV_NS_PREFIX);
		envelope.insertBefore(header, body);
		return document;
	}
	

	/**
	 * Proverava digitalni potpis prosleđenog dokumenta. Pošiljalac se sam navodi unutar same SOAP poruke, te ga je iz nje
	 * moguće pročitati radi validacije digitalnog potpisa.
	 * @param document - dokument
	 * @return <code>True</code> ukoliko je potpis ispravan, <code>False</code> ukoliko nije
	 */
	public boolean verifySignature(Document document) {
		try {
			Element signatureElement = this.getSignatureElement(document);
			XMLSignature signature = new XMLSignature(signatureElement, null);
			KeyInfo keyInfo = signature.getKeyInfo();
			if(keyInfo != null) {
				keyInfo.registerInternalKeyResolver(new RSAKeyValueResolver());
				keyInfo.registerInternalKeyResolver(new X509CertificateResolver());
				Certificate certificate = this.getCertificateFromKeyInfo(keyInfo);
				return signature.checkSignatureValue((X509Certificate) certificate);
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error ocurred while verifying digital signature.");
		}
	}

	/**
	 * Izvlači prvi element koji odgovara traženom elementu potpisa.
	 * @param document - dokument iz koga se izvlači potpis
	 * @return <code>Element</code> koji predstavlja potpis
	 */
	private Element getSignatureElement(Document document) {
		NodeList signatures = document.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "Signature");
		return (Element) signatures.item(0);
	}

	/**
	 * Izvlači sertifikat iz prosleđenog <code>KeyInfo</code>-a.
	 * @param keyInfo
	 * @return <code>Certificate</code> koji se nalazi u <code>KeyInfo</code>
	 * @throws Exception ukoliko ne pronađe nijedan sertifikat
	 */
	private Certificate getCertificateFromKeyInfo(KeyInfo keyInfo) throws Exception {
		if(keyInfo.containsX509Data() && keyInfo.itemX509Data(0).containsCertificate()) { 
			Certificate cert = keyInfo.itemX509Data(0).itemCertificate(0).getX509Certificate();
			if(cert != null) {
				return cert;
			} else {
				throw new Exception("No certificate found present in given KeyInfo!");
			}
		}
		throw new Exception("No certificate found present in given KeyInfo!");
	}

	/**
	 * Priprema neophodne transformacije.
	 * @param document - dokument koji se priprema
	 * @return Pripremljeni omotač
	 */
	private Transforms prepareTransforms(Document document) {
		try {
			Transforms transforms = new Transforms(document);
			transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
			transforms.addTransform(Transforms.TRANSFORM_C14N_WITH_COMMENTS);
			return transforms;
		} catch (TransformationException e) {
			e.printStackTrace();
		}
		return null;
	}
}
