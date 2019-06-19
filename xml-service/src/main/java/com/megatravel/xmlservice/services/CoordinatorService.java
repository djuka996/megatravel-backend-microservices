package com.megatravel.xmlservice.services;

import java.security.PrivateKey;
import java.security.cert.Certificate;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

@Service
public class CoordinatorService {

	@Autowired
	private SignatureService signatureService;
	
	@Autowired
	private EncryptionService encryptionService;
	
	@Autowired
	private XMLUtilityService utilService;
	
	@Autowired
	private KeyStoreReaderService reader;
	
	public String verifySignatureAndDecode(String message, String recipient) {
		Document document = this.utilService.loadXMLFromString(message);
		/*if(!this.signatureService.verifySignature(document)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Digital signature is invalid! Try again.");
		}*/
		PrivateKey recipientPrivateKey = this.reader.readPrivateKey(recipient);
		document = this.encryptionService.decrypt(document, recipientPrivateKey);
		return this.utilService.getStringFromDocument(document);
	}
	
	public String signAndEncode(String message, String recipientSerialNumber, String sender) {
		Document document = this.utilService.loadXMLFromString(message);
		PrivateKey senderPrivateKey = this.reader.readPrivateKey(sender);
		Certificate senderCertificate = this.reader.readMicroserviceCertificate(sender);
		document = this.signatureService.signDocument(document, senderPrivateKey, senderCertificate);
		SecretKey key = this.encryptionService.generateDataEncryptionKey();
		Certificate recipientCertificate = this.reader.readAgentCertificate(sender, recipientSerialNumber);
		document = this.encryptionService.encrypt(document, key, recipientCertificate, this.utilService.getSensitiveTags());
		return this.utilService.getStringFromDocument(document);
	}
	
}
