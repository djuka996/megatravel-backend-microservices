package com.megatravel.xmlservice.services;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.xml.security.encryption.EncryptedData;
import org.apache.xml.security.encryption.EncryptedKey;
import org.apache.xml.security.encryption.XMLCipher;
import org.apache.xml.security.keys.KeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Service
public class EncryptionService {

	/**
	 * Opis ključa kojim se vrši šifrovanje, kako bismo ga mogli pronaći prilikom dešifrovanja.
	 */
	private static final String KEY_NAME = " encrypted secret key";
	
	/**
	 * Naznaka BouncyCastle-u da nam generiše ključ koji ćemo koristiti za TripleDes algoritam.
	 */
	private static final String BOUNCY_CASTLE_3DES = "DESede";
	
	/**
	 * Imenski prostor taga koji je tagovan šifrovani element.
	 */
	private static final String ENCRYPTED_ELEMENT_NAMESPACE = "http://www.w3.org/2001/04/xmlenc#";
	
	/**
	 * Tag kojim je tagovan šifrovani element.
	 */
	private static final String ENCRYPTED_ELEMENT_TAG = "EncryptedData";
	
	public MyEncriptionUtility() {
		Security.addProvider(new BouncyCastleProvider());
		org.apache.xml.security.Init.init();
	}

	/**
	 * Generiše tajni ključ koji će se koristiti za šifrovanje putem <b>3DES</b> algoritma.
	 * @return Izgenerisani tajni ključ
	 */
	public SecretKey generateDataEncryptionKey() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(BOUNCY_CASTLE_3DES);
			return keyGenerator.generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Šifruje naznačeni sadržaj XML dokumenta upotrebom <b>3DES</b> algoritma. Sadržaj se šifruje prosleđenim privatnim ključem
	 * koji se potom (privatni ključ) šifruje javni ključem sertifikata primaoca, kako bi ga samo on mogao dešifrovati.
	 * Šifrovanje se obavlja nad <b>svakim</b> elementom dokumenta koji se nalazi u <b>prosleđenoj listi</b>.
	 * @param document - XML dokument koji se šifruje
	 * @param key - ključ za simetrično šifrovanje
	 * @param certificate - sertifikat primaoca XML dokumenta
	 * @param targets - <b>lista tagova</b> elemenata koji se šifruju
	 * @return XML dokument sa enkriptovanim sadržajem
	 */
	public Document encrypt(Document document, SecretKey key, Certificate certificate, List<String> targets) {
		for(String target : targets) {
			document = this.encrypt(document, key, certificate, target);
			if(document == null) {
				return null;
			}
		}
		return document;
	}

	/**
	 * Šifruje naznačeni sadržaj XML dokumenta upotrebom <b>3DES</b> algoritma. Sadržaj se šifruje prosleđenim privatnim ključem
	 * koji se potom (privatni ključ) šifruje javni ključem sertifikata primaoca, kako bi ga samo on mogao dešifrovati.
	 * Šifrovanje se obavlja nad <b>svakim</b> elementom dokumenta koji ima tag kao prosleđeni.
	 * @param document - XML dokument koji se šifruje
	 * @param key - ključ za simetrično šifrovanje
	 * @param certificate - sertifikat primaoca XML dokumenta
	 * @param target - tag elementa koji se šifruje
	 * @return XML dokument sa šifrovanim sadržajem
	 */
	public Document encrypt(Document document, SecretKey key, Certificate certificate, String target) {
		try {
			XMLCipher xmlCipher = XMLCipher.getInstance(XMLCipher.TRIPLEDES);
			xmlCipher.init(XMLCipher.ENCRYPT_MODE, key);
			XMLCipher keyCipher = XMLCipher.getInstance(XMLCipher.RSA_v1dot5);
			keyCipher.init(XMLCipher.WRAP_MODE, certificate.getPublicKey());
			EncryptedKey encryptedKey = keyCipher.encryptKey(document, key);
			EncryptedData encryptedData = xmlCipher.getEncryptedData();
			KeyInfo keyInfo = new KeyInfo(document);
			keyInfo.addKeyName(target + KEY_NAME);
			keyInfo.add(encryptedKey);
			encryptedData.setKeyInfo(keyInfo);
			NodeList targetNodes = document.getElementsByTagName(target);
			for(int i = 0; i < targetNodes.getLength(); i++) {
				Element targetElement = (Element) targetNodes.item(i);
				xmlCipher.doFinal(document, targetElement, true);
			}
			return document;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Dešifruje sve šifrovane elemente u prosleđenom XML sadržaju. Neophodno je proslediti privatni ključ iz para ključeva, 
	 * kako bi se mogao dešifrovati prvo simetrični tajni ključ, jer se za njego šifrovanje koristio javni ključ.
	 * @param document - XML dokument koji se dešifruje
	 * @param privateKey - privatni ključ sertifikata primaoca
	 * @return XML dokument koji je u potpunosti dešifrovan
	 */
	public Document decrypt(Document document, PrivateKey privateKey) {
		try {
			XMLCipher xmlCipher = XMLCipher.getInstance();
			xmlCipher.init(XMLCipher.DECRYPT_MODE, null);
			xmlCipher.setKEK(privateKey);
			NodeList nodeList = document.getElementsByTagNameNS(ENCRYPTED_ELEMENT_NAMESPACE, ENCRYPTED_ELEMENT_TAG);
			for(int i = 0; i < nodeList.getLength(); i++) {
				Element encData = (Element) nodeList.item(i);
				xmlCipher.doFinal(document, encData); 
			}
			return document;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
