package com.vmware.talentboost.spring.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vmware.talentboost.spring.data.UnauthorizedException;
import com.vmware.talentboost.spring.data.UserAccount;
import com.vmware.talentboost.spring.impl.AccountManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-config.xml")
public class AuthTests {
	
	@Autowired
	private AccountManager accountManager;
	
	@Test
	public void testAuthenticatePositive() {
		try {
			String username = "tanya";
			UserAccount user = accountManager.authenticate(username);
			assertNotNull(user);
			assertNotNull(user.username);
			assertEquals("tanya", user.username);
			assertNotNull(user.role);
			assertEquals("player", user.role);
			
		} catch (UnauthorizedException e) {
			fail("Exception should have been thrown!");
		}
	}

	@Test
	public void testAuthenticateNegative(){
		String username = "valeri";
		boolean correctException  = false;
		try {
			UserAccount user = accountManager.authenticate(username);
		} catch (UnauthorizedException e) {
			// TODO Auto-generated catch block
			correctException = true;
		}finally{
			assertTrue(correctException);
		}
	}
	// Task 0: Add the new required jars to the project build path
	//
	// Task 1(optional): Add a negative test.
	//			- Add a test for a negative scenario (eg. someone else tries to play)
	//			- Re-run the tests.
	//			tip: Find a way to assure if the correct exception is thrown.
	// Task 2: Use spring instead of manual set up.
	//			- Add appropriate annotations to the test class.
	//			- Remove the manual set up.
	//			- Re-run the tests.
}
