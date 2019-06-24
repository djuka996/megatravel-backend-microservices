package com.megatravel.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import com.megatravel.configuration.MyLogger;
import com.megatravel.decodeJWT.DecodeJwtToken;

@Aspect
@Component
/**
 * Nessesery to have @LogExecution
 * @author Astral
 *
 */
public class AspectLogging {

	private static final String execution1 =  "execution(@com.megatravel.LogExecution * * (..))";
	
	@After(value = execution1)
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
    }
    
//    @AfterThrowing (pointcut = "execution(* com.howtodoinjava.app.service.impl.EmployeeManagerImpl.*(..))", throwing = "ex")
//    public void logAfterThrowingAllMethods(Exception ex) throws Throwable
//    {
//        System.out.println("****LoggingAspect.logAfterThrowingAllMethods() " + ex);
//    }
//	
    @AfterThrowing(pointcut = "execution(* com.megatravel..services..*(..))")
    public void aroundThrow(ProceedingJoinPoint joinPoint) throws Throwable {
		CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String[] parameterNames = codeSignature.getParameterNames();
        Object[] argumentValues = joinPoint.getArgs();
        
        System.out.println("First parameter's name: " + parameterNames[0]);
        System.out.println("First argument's value: " + argumentValues[0].toString());
        HttpServletRequest request = findHttpSevletRequest(parameterNames);
        if(request != null)
        {
        	MyLogger.warn(codeSignature.getName(), false, DecodeJwtToken.getUsername(request.getHeader("Authorization")), request.getRemoteAddr(), "");
        }
        else
        {
        	MyLogger.warn(codeSignature.getName(), false,"SOAP", "SOAP", "");
        }
    }
    
    private HttpServletRequest findHttpSevletRequest(Object[] objects) {
    	for (Object iter : objects) {
    		if(iter.getClass().equals(HttpServletRequest.class))
    			return (HttpServletRequest)iter;
		}
    	return null;
    }
}