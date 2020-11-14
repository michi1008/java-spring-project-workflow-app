package com.michiko.pw.logging;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("within(com.michiko.pw.controllers..*)") 
			//+ "|| within(com.michiko.pma.dao..*")
	public void definePackagePointcuts() {
		//empty method just to name the location specified in the pointcut.
	}
	@Around("definePackagePointcuts()")  //giving advice
	public Object logAround(ProceedingJoinPoint jp ){
		
		log.debug("\n \n \n");
		log.debug("*********** Before method Execution ************* \n {}.{} () with arguments[s] = {}",
		jp.getSignature().getDeclaringTypeName(),
		jp.getSignature().getName(),Arrays.toString(jp.getArgs()));
				
		Object o = null;
		try {
			o = jp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("_________________________________ \n \n \n");
		log.debug("*********** After method Execution ************* \n {}.{} () with arguments[s] = {}",
		jp.getSignature().getDeclaringTypeName(),
		jp.getSignature().getName(),Arrays.toString(jp.getArgs()));
		log.debug("_________________________________ \n \n \n");
		
		return o;
	}
}
