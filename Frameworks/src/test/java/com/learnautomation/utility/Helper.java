package com.learnautomation.utility;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Helper {

	// If you keep the methods "static" here you can call them directly by ther name
	// or else
	// you can create object of the class and access them(in normal case)

	// Screenshot,Alert,frame,windows,sync issues, javascript executor

	public static String captureScreenshot(WebDriver driver) {
		// TakesScreenshot is an interface and getScreenshotAs is a method in it.
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String screenshotPath = "./Screenshot/FreeCRM_" + getCurrentDateTime() + ".png";

		System.out.println(screenshotPath);
		try {
			FileHandler.copy(src, new File(screenshotPath));
		} catch (Exception e) {
			System.out.println("Unable to capture Screenshot" + e.getMessage());
		}

		return screenshotPath;
	}

	public static String getCurrentDateTime() {
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();

		// to format the date
		return customFormat.format(currentDate);

	}

}
