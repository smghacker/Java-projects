package com.vmware.talentboost.spring.aspects;

import java.util.logging.Logger;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
@Aspect
public class AuthLoggingAspect {
	private static Logger logger = Logger.getLogger(AuthLoggingAspect.class.toString());
	
	@AfterReturning("execution(public * com.vmware.talentboost.spring.impl.AccountManager.authenticate(..))")
	public void logAuthentication(){
		logger.info("log");
	}
	// Task 3: Add an advice to log a message every time someone is authenticated.
	//			- Annotate this class to be used as an aspect.
	//			- Create a method to be advised to AccountManager's authenticate(..)
	//
	//		EASY MODE: Enable AOP in the current *config.xml file.
	//			- Define a bean for this aspect
	//			- Enable aspectj-autoproxy and import the bean in it.
	//
	//		HACKER MODE: Use a separate config file for aspects.
	//			- Create the new xml in the aspects package.
	//			- Import the new xml in the old xml.
	//			- Do the steps form the EASY MODE.
}
