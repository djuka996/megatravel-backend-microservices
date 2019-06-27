package com.megatravel.zuulsvr;


import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;

import com.megatravel.zuulsvr.filters.PostFilter;
import com.megatravel.zuulsvr.filters.PreFilter;
import com.megatravel.zuulsvr.filters.SigningPostFilter;
import com.megatravel.zuulsvr.utils.HostCustomVerifer;


@EnableAutoConfiguration
@SpringBootApplication
@Controller
@EnableDiscoveryClient
@EnableZuulProxy
@org.springframework.cloud.openfeign.EnableFeignClients
public class ZuulServerApplication {

	@Value("${server.ssl.key-store}")
	private Resource keyStoreLocation;
	
	@Value("${server.ssl.key-store-password}")
	private String keyStorePassword;
	
	@Value("${server.ssl.key-store-type}")
	private String keyStoreType;
	
	@Value("${server.ssl.trust-store-type}")
	private String trustStoreType;
	
	@Value("${server.ssl.trust-store}")
	private Resource trustStoreLocation;
	
	@Value("${server.ssl.trust-store-password}")
	private String trustStorePassword;
	
    public static void main(String[] args) {
    	SpringApplication.run(ZuulServerApplication.class, args);
    }
	
    private KeyStore getKeyStore(){
    	KeyStore keyStore = null;
		//HttpComponentsClientHttpRequestFactory requestFactory = null;
		try {
			keyStore = KeyStore.getInstance(keyStoreType);
			ClassPathResource classPathResource = new ClassPathResource(keyStoreLocation.getFilename());
			InputStream inputStream = classPathResource.getInputStream();
			keyStore.load(inputStream, keyStorePassword.toCharArray());
		}catch(Exception e) {
			System.err.println("Error Trust store");
		}
		return keyStore;
    }
    
//    @Bean
//    public BasicHttpClientConnectionManager basicHttpClientConnectionManager() {
//        SSLContextBuilder builder = new SSLContextBuilder();
//        try {
//        	builder.loadKeyMaterial(getKeyStore(), keyStorePassword.toCharArray());
//        	builder.loadTrustMaterial(keyStoreLocation.getFile(),keyStorePassword.toCharArray(), new TrustSelfSignedStrategy());
//        } catch (NoSuchAlgorithmException | CertificateException | IOException  | KeyStoreException | UnrecoverableKeyException e) {
//            System.out.println("Error 1");
//        }
// 
//        SSLConnectionSocketFactory sslsf = null;
//        try {
//            sslsf = new SSLConnectionSocketFactory(builder.build());
//        } catch (KeyManagementException | NoSuchAlgorithmException e) {
//        	 System.out.println("Error 2");
//        }
// 
//        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
//                .<ConnectionSocketFactory>create().register("https", sslsf)
//                .register("http", new PlainConnectionSocketFactory())
//                .build();
//
//        BasicHttpClientConnectionManager poolingConnectionManager = new BasicHttpClientConnectionManager(socketFactoryRegistry);
//        return poolingConnectionManager;
//    }
    
//    @Bean
//    public PoolingHttpClientConnectionManager poolingConnectionManager() {
//    	System.out.println("SomeoneCalling");
//        SSLContextBuilder builder = new SSLContextBuilder();
//        try {
//        	builder.loadKeyMaterial(getKeyStore(), keyStorePassword.toCharArray());
//        	builder.loadTrustMaterial(keyStoreLocation.getFile(),keyStorePassword.toCharArray(), new TrustStrategy() {				
//				@Override
//				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//					return true;
//				}
//			});
//        	builder.setProtocol("TLS");
//        } catch (NoSuchAlgorithmException | CertificateException | IOException  | KeyStoreException | UnrecoverableKeyException e) {
//            System.out.println("Error 1");
//        }
// 
//        SSLConnectionSocketFactory sslsf = null;
//        try {
//            sslsf = new SSLConnectionSocketFactory(builder.build());
//        } catch (KeyManagementException | NoSuchAlgorithmException e) {
//        	 System.out.println("Error 2");
//        }
// 
//        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
//                .<ConnectionSocketFactory>create().register("https", sslsf)
//                .register("http", new PlainConnectionSocketFactory())
//                .build();
//    
//        PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//        poolingConnectionManager.setMaxTotal(696969);
//        return poolingConnectionManager;
//    }

    
//    @Bean
//    public SSLConnectionSocketFactory sSLConnectionSocketFactory() {
//    	KeyStore keyStore = null;
//    	KeyStore trustStore = null;
//		try {
//			keyStore = KeyStore.getInstance(keyStoreType);
//			ClassPathResource classPathResource = new ClassPathResource(keyStoreLocation.getFilename());
//			InputStream inputStream = classPathResource.getInputStream();
//			keyStore.load(inputStream, keyStorePassword.toCharArray());
//			
//			trustStore = KeyStore.getInstance(trustStoreType);
//			ClassPathResource classPathResourceTrust = new ClassPathResource(trustStoreLocation.getFilename());
//			InputStream trustInput = classPathResourceTrust.getInputStream();
//			trustStore.load(trustInput, trustStorePassword.toCharArray());
//	
//			javax.net.ssl.SSLContext sslContext = SSLContextBuilder.create()
//					.loadKeyMaterial(keyStore, keyStorePassword.toCharArray())
//					.loadTrustMaterial(trustStore, new TrustStrategy() {
//						
//						@Override
//						public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//							return true;
//						}
//					}).setProtocol("TLS")
//					.build();
//		    SSLConnectionSocketFactory sslConnectionSocketFactory =
//		    		new SSLConnectionSocketFactory(sslContext,
//		    		new HostCustomVerifer(trustStoreLocation,trustStorePassword,trustStoreType));
//		    return sslConnectionSocketFactory;
//		}catch(Exception e) {
//			throw new InternalError(e);
//		}
//	}
    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }
    
    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
    
    @Bean
    public SigningPostFilter signingPostFilter() {
    	return new SigningPostFilter();
    }

}

