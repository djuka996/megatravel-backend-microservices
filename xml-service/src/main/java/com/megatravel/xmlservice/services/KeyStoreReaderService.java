package com.megatravel.xmlservice.services;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class KeyStoreReaderService {
	
	public static final String STORES_FOLDER = "Stores/";
	public static final String PASSWORDS_FILE = "info.xml";
	private static final String TRUSTSTORE = "TrustStore.ks";
	private static final String KEYSTORE = "KeyStore.ks";
	
	@Autowired
	private XMLUtilityService utilService;
	
	/**
	 * Učitava prvi validni sertifikat iz zadatog KeyStore-a.
	 * @param microserviceName - relativna putanja do KeyStore-a
	 * @return Sertifikat ako je pronađen, null ukoliko nije
	 */
    public Certificate readMicroserviceCertificate(String microserviceName) {
		try {
			KeyStore keyStore = KeyStore.getInstance("JKS", "SUN");
			String keyStorePath = this.pathFromName(microserviceName) + KEYSTORE;
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(keyStorePath));
			keyStore.load(in, this.passwordOfMicroservice(microserviceName).toCharArray());
			String alias = this.aliasOfFirstValidCertificate(keyStorePath);
			if(keyStore.isKeyEntry(alias) || keyStore.isCertificateEntry(alias)) {
				Certificate certificate = keyStore.getCertificate(alias);
				return certificate;
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error ocurred while reading a keystore.");
		}
		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No valid certificate found.");
	}
    
    /**
     * Iščitava sertifikat Agenta kome je poruka namenjena. Proverava unutar TrustStore-a datog mikroservisa.
     * @param microserviceName - ime mikroservisa koji šalje poruku
     * @param recipientSerialNumber - serijski broj sertifikata agenta koji prima poruku
     * @return Sertifikat, ukoliko postoji
     */
	public Certificate readAgentCertificate(String microserviceName, String recipientSerialNumber) {
		try {
			KeyStore keyStore = KeyStore.getInstance("JKS", "SUN");
			String keyStorePath = this.pathFromName(microserviceName) + TRUSTSTORE;
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(keyStorePath));
			keyStore.load(in, this.passwordOfMicroservice(microserviceName).toCharArray());
			List<String> aliases = this.readAllAliases(keyStorePath);
			for(String alias : aliases) {
				Certificate certificate = keyStore.getCertificate(alias);
				String serialNumberCertificate =((X509Certificate) certificate).getSerialNumber().toString(); 
    			if(serialNumberCertificate.equals(recipientSerialNumber)) {
					return certificate;
    			}
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error ocurred while reading a keystore.");
		}
		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No valid certificate found.");
	}
    
    /**
     * Iščitava privatni ključ za zadati sertifikat (na osnovu alijasa) iz datog KeyStore-a.
     * @param microserviceName - ime mikroservisa
     * @param certificateAlias - alias sertifikata
     * @param certificatePassword - lozinka za sertifikat
     * @return Privatni ključ traženog sertifikata
     */
	public PrivateKey readPrivateKey(String microserviceName) {
		try {
			KeyStore keyStore = KeyStore.getInstance("JKS", "SUN");
			String keyStorePath = this.pathFromName(microserviceName) + KEYSTORE;
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(keyStorePath));
			keyStore.load(in, this.passwordOfMicroservice(microserviceName).toCharArray());
			String alias = this.aliasOfFirstValidCertificate(keyStorePath);
			if(keyStore.isKeyEntry(alias)) {
				PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, this.passwordOfMicroservice(microserviceName).toCharArray());
				return privateKey;
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error ocurred while reading a keystore.");
		}
		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No valid certificate found.");
	}
	
	/**
	 * Iščitava sve alijase iz Store-a na zadatoj putanji.
	 * @param storePath - relativna putanja do Store-a
	 * @return Lista svih alijasa koji se nalaze u zadatom Store-u
	 */
	private List<String> readAllAliases(String storePath){
		KeyStore keyStore = this.readContent(storePath);
		try {
			return Collections.list(keyStore.aliases());
		} catch (KeyStoreException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error ocurred while reading a keystore.");
		}
	}
	
    /**
     * Učitava sadrzaj iz KeyStore-a u radnu memoriju.
     * @param storePath - relativna putanja Store-a
     * @return KeyStore objekat koji predstavlja učitani sadržaj
     */
    private KeyStore readContent(String storePath) {
		try {
			KeyStore keyStore = KeyStore.getInstance("JKS", "SUN");
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(storePath));
			keyStore.load(in, this.passwordOfMicroservice(this.nameFromPath(storePath)).toCharArray());
			return keyStore;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error ocurred while reading a keystore.");
		}
	}
    
    /**
     * Konstruiše putanju do Store-a željenog mikroservisa.
     * @param microserviceName - ime mikroservisa
     * @return Putanja do Store-a
     */
    private String pathFromName(String microserviceName) {
    	return STORES_FOLDER + microserviceName + "/" + microserviceName;
    }
    
    /**
     * Rekonstruiše ime mikroservisa od prosleđene putanje do njegovog Store-a.
     * @param storePath - relativna putanja do Store-a
     * @return Ime mikroservisa
     */
    private String nameFromPath(String storePath) {
    	return storePath.substring(storePath.indexOf('/') + 1, storePath.lastIndexOf('/'));
    }
    
    /**
     * Pronalazi koja je lozinka korišćena za Store mikroservisa.
     * @param microserviceName - ime mikroservisa
     * @return Lozinka Store-a
     */
    private String passwordOfMicroservice(String microserviceName) {
    	return this.utilService.getPasswordOfMicroservice(microserviceName);
    }
	
    /**
     * Pronalazi alijas prvog validnog sertifikata koji se nalazi u Store-u na zadatoj putanji.
     * @param storePath - relativna putanja do Store-a
     * @return Alijas sertifikata
     */
    private String aliasOfFirstValidCertificate(String storePath) {
    	List<String> aliases = this.readAllAliases(storePath);
    	for(String alias : aliases) {
    		if(this.validateCertificate(alias)) {
    			return alias;
    		}
    	}
    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No valid certificate found.");
    }
    
    /**
     * Validira sertifikat kontaktirajući centralni PKI (radi se OCSP).
     * @param alias - alijas sertifikata koji se proverava
     * @return <code>True</code> ukoliko je validan, <code>False</code> ukoliko nije
     */
    private boolean validateCertificate(String alias) {
    	// TODO : Kontaktirati centralni PKI za proveru validnosti ovog sertifikata
    	return true;
    }
    
}
