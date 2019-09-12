package com.learnautomation.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.learnautomation.pages.BaseClass;
import com.learnautomation.pages.LoginPage;

public class LoginTestCRM extends BaseClass {

	@Test
	public void loginApp() {

		// Logger will be responsible for all the logging activities inside the test.
		logger = report.createTest("Log in to FreeCRM");

		String userID = excel.getStringData("Login", 0, 0);

		String password = excel.getStringData("Login", 0, 1);

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		logger.info("Starting Application");

		// For checking purpose

		logger.fail("Test Fail");

		loginPage.LoginToCRM(userID, password);

		logger.pass("Test pass");

		logger.fail("Test Fail");
	}

}
