package com.megatravel.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import com.megatravel.configuration.MyLogger;
import com.megatravel.decodeJWT.DecodeJwtToken;

@Aspect
@Component
public class AspectLogging {

//	private static final String annotation =  "@annotation(com.megatravel.aspect.annotation.LogService)";
//	
//	
//	private static final String expresion = "execution(* com.megatravel.services..*..*(..))";
//	
//    @AfterReturning(value = expresion + " || " + annotation, returning = "result")
//    public void logAfterAllServiceMethods(JoinPoint jpoinPoint, Object result) {
//    	/*CodeSignature codeSignature = (CodeSignature) jpoinPoint.getSignature();
//    	  Object check = jpoinPoint.getThis();
//		  String[] parameterNames = codeSignature.getParameterNames();
//	      Object[] argumentValues = jpoinPoint.getArgs();
//		  int requestIter = findHttpSevletRequest(parameterNames);
//		  if( requestIter != -1)
//		  {
//			HttpServletRequest request = (HttpServletRequest) argumentValues[requestIter];
//		  	MyLogger.info(codeSignature.getName(), true, DecodeJwtToken.getUsername(request.getHeader("Authorization")), request.getRemoteAddr(), "");
//		  }
//		  else
//		  {
//		  	MyLogger.warn(codeSignature.getName(), true,"SOAP", "SOAP", "");
//		  }  */  	
//    }
//	
//    @AfterThrowing(value = expresion + " || " + annotation , throwing = "ex")
//    public void logAfterThrowingAllMethods(JoinPoint jpoinPoint,Exception ex) throws Throwable
//    {
//    	/*CodeSignature codeSignature = (CodeSignature) jpoinPoint.getSignature();
//    	Object check = jpoinPoint.getThis();
//		  String[] parameterNames = codeSignature.getParameterNames();
//	      Object[] argumentValues = jpoinPoint.getArgs();
//		  int requestIter = findHttpSevletRequest(parameterNames);
//		  if( requestIter != -1)
//		  {
//			HttpServletRequest request = (HttpServletRequest) argumentValues[requestIter];
//		  	MyLogger.warn(codeSignature.getName(), false, DecodeJwtToken.getUsername(request.getHeader("Authorization")), request.getRemoteAddr(), "");
//		  }
//		  else
//		  {
//		  	MyLogger.warn(codeSignature.getName(), false,"SOAP", "SOAP", ex.getMessage().substring(0, Math.min(100, ex.getMessage().length())));
//		  } */   	
//    }
//    
    
    /**
     * Trazi pod nazivom request parametar u Servison metodi ili annotiranoj metodi sa @LogService
     * @param strings
     * @return i-ta lokazija za HttpServletRequest ili -1 ako nema
     */
    private int findHttpSevletRequest(String[] strings) {
    	for (int i=0;i<strings.length; ++i) {
    		if(strings[i].equals("request"))
    			return i;
		}
    	return -1;
    }
}