package com.inetbanking.testCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObject.LoginPage;

public class TC_LoginTest extends BaseClass {
	@Test
	public void loginTest() throws IOException {

	
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		lp.setPassord(password);
		lp.clickSubmit();

		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
		} else {
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			
		}

	}
} 
