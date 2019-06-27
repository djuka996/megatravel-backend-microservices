package com.megatravel;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.exceptions.SSLParamsException;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class LoginAndRegistrationApplication {
	
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
		SpringApplication.run(LoginAndRegistrationApplication.class, args);
	}

	
	// All the resources that cannot be found on server are redirected to
	// index.html. Angular will handle the routing for them.
	@Bean
	ErrorViewResolver supportPathBasedLocationStrategyWithoutHashes() {
		return new ErrorViewResolver() {

			@Override
			public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status,
					Map<String, Object> model) {
				return status == HttpStatus.NOT_FOUND
						? new ModelAndView("index.html", Collections.<String, Object>emptyMap(), HttpStatus.OK)
						: null;
			}
		};
	}
	
//	private SSLContext getSSlContext() {
//		KeyStore keyStore;
//		KeyStore trustStore;
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
//					.loadTrustMaterial(trustStore, null).setProtocol("TLS")
//					.build();
//			return sslContext;
//		}catch(Exception e) {
//			System.err.println("Unable to init SSLCONTEXT on init");
//			throw new SSLParamsException("Unable to init sslcontext");
//		}
//	}
	
//	@Bean
//	public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws NoSuchAlgorithmException {
//	    DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
//	    System.setProperty("javax.net.ssl.keyStore", "src/main/resources/auth.p12");
//	    System.setProperty("javax.net.ssl.keyStorePassword", "sifra");
//	    System.setProperty("javax.net.ssl.trustStore", "src/main/resources/authTrust.p12");
//	    System.setProperty("javax.net.ssl.trustStorePassword", "sifra");
//	    EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
//	    builder.withClientName("CONTAINERIZED-LOGIN");
//	    builder.withCustomSSL(getSSlContext());
//	    builder.withMaxTotalConnections(10000);
//	    builder.withMaxConnectionsPerHost(10);
//	    args.setEurekaJerseyClient(builder.build());
//	    return args;
//	}
	
	
	
	
//	@Bean
//	public CorsFilter corsFilter() {
//
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    CorsConfiguration config = new CorsConfiguration();
//	    config.setAllowCredentials(true); 
//	    config.addAllowedOrigin("https://localhost:4200");
//	    config.addAllowedHeader("*");
//	    config.addAllowedMethod("GET");
//	    config.addAllowedMethod("PUT");
//	    config.addAllowedMethod("POST");
//	    config.addAllowedMethod("DELETE");
//	    config.addAllowedMethod("OPTIONS");
//	    source.registerCorsConfiguration("/**", config);
//	    return new CorsFilter(source);
//	}

}
