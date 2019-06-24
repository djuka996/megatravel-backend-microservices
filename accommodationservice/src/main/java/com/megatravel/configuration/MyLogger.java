package com.megatravel.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLogger {

	private static Logger logger = LoggerFactory.getLogger(MyLogger.class);
	
	private static final String errorMessage = "FATAL ERROR: ADMINISTRATOR TO SEE LOG FILE ERROR TWO REASONS FOR OCCURING:1)OUT OF MEMORY 2)INSUFFICENT PRIVILEGES";
	
	/**
	 * @param methodName - Operacija metode
	 * @param isSuccessful - Boolean, Da li je uspesno se pretvori u (SUCC,FAIL)
	 * @param user -  email od korisnika
	 * @param IP  - ip adressa (remote) od korisnika
	 * @param message - dodatak poruke kao informacije o parametrima poslati
	 * @return Formatira u format %method:%succ | %email-%ip, %message
	 */
	private static String prepareMessage(String methodName,boolean isSuccessful,String user,String IP,String message) {
		//return methodName.toUpperCase() + ":" + (isSuccessful?"SUCC":"FAIL") + " | " + user +"-" + IP + ", " + message;
		return String.format("%s:%s | %s-%s, %s",methodName.toUpperCase(),isSuccessful?"SUCC":"FAIL",user,IP,message);
	}
	
	/**
	 * Evidentira u info log fajl
	 * @param methodName - Operacija koja se vrsi
	 * @param isSuccessful - Da li je uspesno izvsena
	 * @param username - email od korisnika
	 * @param IP - ip adressa (remote) od korisnika
	 * @param message - dodatak poruke kao informacije o parametrima poslati
	 */
	public static void info(String methodName,boolean isSuccessful,String username,String IP,String message) {
		try {
			logger.info(prepareMessage(methodName, isSuccessful, username, IP, message));
		} catch (Exception e) {
			System.out.println(errorMessage);
		}
	}
	
	/**
	 * Evidentira u warn log fajl
	 * @param methodName - Operacija koja se vrsi
	 * @param isSuccessful - Da li je uspesno izvsena
	 * @param username - email od korisnika
	 * @param IP - ip adressa (remote) od korisnika
	 * @param message - dodatak poruke kao informacije o parametrima poslati
	 */
	public static void warn(String methodName,boolean isSuccessful,String username,String IP,String message) {
		
		try {
			logger.warn(prepareMessage(methodName, isSuccessful, username, IP, message));
		} catch (Exception e) {	
			System.out.println(errorMessage);
		}
	}
	
	/**
	 * Evidentira u debug log fajl
	 * @param methodName - Operacija koja se vrsi
	 * @param isSuccessful - Da li je uspesno izvsena
	 * @param username - email od korisnika
	 * @param IP - ip adressa (remote) od korisnika
	 * @param error - Ako se desio error proslediti, inace moze null
	 * @param message - dodatak poruke kao informacije o parametrima poslati
	 */
	public static void debug(String methodName,boolean isSuccessful,String username,String IP,String message,Throwable error) {
		try {
			if(error==null)
				logger.debug(prepareMessage(methodName, isSuccessful, username, IP, message));
			else 
				logger.debug(prepareMessage(methodName, isSuccessful, username, IP, message),error);
		} catch (Exception e) {
			System.out.println(errorMessage);
		}
	}
	
	/**
	 * Evidentira u error log fajl
	 * @param methodName - Operacija koja se vrsi
	 * @param isSuccessful - Da li je uspesno izvsena
	 * @param username - email od korisnika
	 * @param IP - ip adressa (remote) od korisnika
	 * @param error - Ako se desio error proslediti, inace moze null
	 * @param message - dodatak poruke kao informacije o parametrima poslati
	 */
	public static void error(String methodName,boolean isSuccessful,String username,String IP,String message,Throwable error) {
		try {
			if(error==null)
				logger.error(prepareMessage(methodName, isSuccessful, username, IP, message));
			else 
				logger.error(prepareMessage(methodName, isSuccessful, username, IP, message),error);
		} catch (Exception e) {
			System.out.println(errorMessage);
		}
	}
	
}
