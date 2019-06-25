package com.megatravel.aspect;

import javax.servlet.http.HttpServletRequest;

import org.apache.coyote.http11.HeadersTooLargeException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import com.megatravel.configuration.MyLogger;
import com.megatravel.decodeJWT.DecodeJwtToken;
import com.megatravel.model.hotel.AccomodationType;

@SuppressWarnings("unused")
@Aspect
@Component
public class AspectLogging {

	private static final String annotation =  "@annotation(com.megatravel.aspect.annotation.LogService)";
	
	private static final String expresion = "execution(* com.megatravel.services..*..*(..))";
	
    @After(value = expresion)
    public void checkIfGoesIn(JoinPoint jpoinPoint) {
    	System.out.println(">>>>>Logging");
    	CodeSignature codeSignature = (CodeSignature) jpoinPoint.getSignature();
		  String[] parameterNames = codeSignature.getParameterNames();
	      Object[] argumentValues = jpoinPoint.getArgs();
	      System.out.println("First parameter's name: " + parameterNames[0]);
		  System.out.println("First argument's value: " + argumentValues[0].toString());
		  HttpServletRequest request = findHttpSevletRequest(argumentValues);
		  if(request != null)
		  {
		  	MyLogger.warn(codeSignature.getName(), false, DecodeJwtToken.getUsername(request.getHeader("Authorization")), request.getRemoteAddr(), "");
		  }
		  else
		  {
		  	MyLogger.warn(codeSignature.getName(), false,"SOAP", "SOAP", "");
		  }    	
    	//MyLogger.info("Create AccommodationType", false, DecodeJwtToken.getUsername(request.getHeader("Authorization")), request.getRemoteAddr(), "");
    }
	
	/*@After(value = execution1)
    public void afterService(ProceedingJoinPoint joinPoint) throws Throwable {
		CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String[] parameterNames = codeSignature.getParameterNames();
        Object[] argumentValues = joinPoint.getArgs();
        
        System.out.println("First parameter's name: " + parameterNames[0]);
        System.out.println("First argument's value: " + argumentValues[0].toString());
        HttpServletRequest request = findHttpSevletRequest(parameterNames);
        if(request != null)
        {
        	MyLogger.warn(codeSignature.getName(), true, DecodeJwtToken.getUsername(request.getHeader("Authorization")), request.getRemoteAddr(), "");
        }
        else
        {
        	MyLogger.warn(codeSignature.getName(), false,"Unknown", "Unknown", "");
        }
    }*/
    
//    @AfterThrowing (pointcut = "execution(* com.howtodoinjava.app.service.impl.EmployeeManagerImpl.*(..))", throwing = "ex")
//    public void logAfterThrowingAllMethods(Exception ex) throws Throwable
//    {
//        System.out.println("****LoggingAspect.logAfterThrowingAllMethods() " + ex);
//    }
//	
    //@Around(value= "execution(* com.megatravel..services..*.*(..))")
//	@Around(value= "execution(* com.megatravel.services.AccommodationTypeService.*(..))")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//		CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
//        String[] parameterNames = codeSignature.getParameterNames();
//        Object[] argumentValues = joinPoint.getArgs();
//        joinPoint.proceed();
//        System.out.println("First parameter's name: " + parameterNames[0]);
//        System.out.println("First argument's value: " + argumentValues[0].toString());
//        HttpServletRequest request = findHttpSevletRequest(parameterNames);
//        if(request != null)
//        {
//        	MyLogger.warn(codeSignature.getName(), false, DecodeJwtToken.getUsername(request.getHeader("Authorization")), request.getRemoteAddr(), "");
//        }
//        else
//        {
//        	MyLogger.warn(codeSignature.getName(), false,"SOAP", "SOAP", "");
//        }
//        return joinPoint;
//    }
//    
    private HttpServletRequest findHttpSevletRequest(Object[] objects) {
    	for (Object iter : objects) {
    		if(iter.getClass().equals(HttpServletRequest.class))
    			return (HttpServletRequest)iter;
		}
    	return null;
    }
}