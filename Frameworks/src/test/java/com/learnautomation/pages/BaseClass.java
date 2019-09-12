package com.learnautomation.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.learnautomation.utility.BrowserFactory;
import com.learnautomation.utility.ConfigDataProvider;
import com.learnautomation.utility.ExcelDataProvider;
import com.learnautomation.utility.Helper;

public class BaseClass {

	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;

	@BeforeSuite
	public void setUpSuite() {

		Reporter.log("setting up reports and test is getting ready", true);

		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();
		// ExtentHtmlReporter extent = new ExtentHtmlReporter(
		// new File(System.getProperty("user.Dir") +"/Reports/FreeCRM.html"));
		System.out.println(System.getProperty("user.dir"));
		ExtentHtmlReporter extent = new ExtentHtmlReporter(
				new File("C://Eclipse//Frameworks//Reports//FreeCRM_" + Helper.getCurrentDateTime() + ".html"));
		report = new ExtentReports();
		report.attachReporter(extent);

		Reporter.log("set up of reports done and test started", true);
	}

	@BeforeTest
	public void Setup() {

		Reporter.log("Trying to start browser and getting application ready", true);

		driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getStagingURl());

		Reporter.log("browser is up and running", true);
	}

	@AfterTest
	public void tearDown() {
		BrowserFactory.quitApplication(driver);
	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException {

		Reporter.log("Test is about to end", true);

		// ITestResults to check go to testng or mukesh's how to take screenshot
		if (result.getStatus() == ITestResult.FAILURE) {
			// Helper.captureScreenshot(driver);

			// to attach the screenshot on failure in report
			logger.fail("Test Failed",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			// Helper.captureScreenshot(driver);
			// to attach the screenshot on failure in report
			logger.pass("Test Pass",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		// to create the report
		report.flush();

		Reporter.log("Test Completed---->>> Reposrt Generated", true);
	}

}
