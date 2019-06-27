package com.megatravel.zuulsvr.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class HostCustomVerifer implements HostnameVerifier {

    private String trustStorePassword;
    private String trustResource;
	private String trustType;
	
    @Autowired
    public HostCustomVerifer(Resource trustResource,String trustStorePassword, String trustType) {
        this.trustStorePassword = trustStorePassword;
        this.trustResource = trustResource.getFilename();
        this.trustType = trustType;
    }
    
	@Override
	public boolean verify(String hostName, SSLSession sslSession) {
		try {
			X509Certificate list[] =  (X509Certificate[]) sslSession.getPeerCertificates();
			int size = list.length;
			KeyStore trustStore = KeyStore.getInstance(trustType);
			ClassPathResource classPathResourceTrust = new ClassPathResource(trustResource);
			InputStream trustInput = classPathResourceTrust.getInputStream();
			trustStore.load(trustInput, trustStorePassword.toCharArray());	
			for (X509Certificate x509Certificate : list) {
				ArrayList<String> content = Collections.list(trustStore.aliases());
				for (String alias : content) {
					X509Certificate cert = (X509Certificate) trustStore.getCertificate(alias);
					boolean isSelfSigned = cert.getIssuerDN().equals(cert.getSubjectDN());
			        if(cert.equals(x509Certificate) && (!isSelfSigned || size==1))
			        	return true;
				}
			}
		} catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException e) {
			e.printStackTrace();
		}
		return false;
	}

}
