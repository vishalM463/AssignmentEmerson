/* **********************************************************
 ##### Utility Class for setting up browsers ########
 Author         : Vishal Mathur
 Created Date   :7-July-2021
 **********************************************************/
package com.utility;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserSetup {

	public static WebDriver startApp(WebDriver driver, String browserName, String url) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("Browser not supported");
			return null;
		}
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		
		return driver;
		
	}
	
	public static void quitApp(WebDriver driver) {
		driver.quit();
	}
}
