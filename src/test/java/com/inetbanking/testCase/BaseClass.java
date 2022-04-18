package com.inetbanking.testCase;

import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class BaseClass {
	ReadConfig readConfig = new ReadConfig();
	public String baseURL = readConfig.getApplicationURL(); // "http://demo.guru99.com/v4/";
	public String username = readConfig.getUsername(); // "mngr390798";
	public String password = readConfig.getPassword(); // "yrUsYge";
	public static WebDriver driver;

	public static Logger logger;

	@SuppressWarnings("deprecation")
	@Parameters("browser")
	@BeforeClass
	void setup(String br) {

		logger = Logger.getLogger("inetBanking");

		PropertyConfigurator.configure("Log4j.properties");

		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");

		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
			driver = new ChromeDriver();
		} else if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readConfig.getFireFoxPath());
			driver = new FirefoxDriver();

		} else if (br.equals("ie")) {
			System.setProperty("webdriver.ie.driver", readConfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
		//driver.manage().window().maximize();

	}

	@AfterClass
	void tearDown() {
		// driver.quit();

	}

	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}

}