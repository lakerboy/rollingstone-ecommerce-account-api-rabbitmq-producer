package com.rollingstone.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;


@Aspect
@Component
public class RestControllerAspect {

	private static final Logger logger = LoggerFactory.getLogger(RestControllerAspect.class);
	
	Counter accountCreatedCounter = Metrics.counter("com.rollingstone.account.created");
	
	@Before("excecution(public * com.rollingstone.spring.controller.*Controller.*(..))")
	public void generalAllMethodAspect() {
		logger.info("All Method Calls invoke this generatl aspect method");
	}
	
	@AfterReturning("excecution(public * com.rollingstone.spring.controller.*Controller.createAccount(..))")
	public void getCalledOnAccountSave() {
		logger.info("This Aspect method is called only on Account Save");
		accountCreatedCounter.increment();
	}
}
