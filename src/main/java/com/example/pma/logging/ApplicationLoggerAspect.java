package com.example.pma.logging;

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
public class ApplicationLoggerAspect {

	private final Logger log=LoggerFactory.getLogger(this.getClass());

	@Pointcut("within(com.example.pma.controllers..*)")
	//		+"|| within(com.example.pma.dao..*)")
	public void definePackagePointcuts() {

	}

	//@After("definePackagePointcuts()")
	@Around("definePackagePointcuts()")
	public Object logAfter(ProceedingJoinPoint jp) {
		log.debug("\n \n \n ------------Before Method Execution----\n");
		//	log.debug("************ Before Method Execution ************ \n {}.{} () with arguments[s] = {}",
		log.debug(" \n \n \n ");
		log.debug("************ Before Method Execution ************ \n {}.{} () with arguments[s] = {}",
				jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		log.debug("_________________________________________________ \n \n \n");

		Object o=null;

		try {
			o=jp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		log.debug(" \n \n \n ");
		log.debug("************ After Method Execution ************ \n {}.{} () with arguments[s] = {}",
				jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		log.debug("_________________________________________________ \n \n \n");
		return o;
	}

}