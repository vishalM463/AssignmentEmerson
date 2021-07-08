/* **********************************************************
 ##### Base Class for all Test Cases ########
 Author         : Vishal Mathur
 Created Date   :7-July-2021
 **********************************************************/
package com.pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.utility.BrowserSetup;
import com.utility.ConfigDataProvider;
import com.utility.ExcelDataProvider;
import com.utility.Helper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.File;

public class BaseClass {
    public static WebDriver driver;
    public static ExcelDataProvider excelData;
    public static ConfigDataProvider configData;
    public ExtentReports reports;
    public ExtentTest logger;

    @BeforeClass
    public void reportSetup() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/Assignment_"+ Helper.getDateTime() +".html"));
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);

        reports.setSystemInfo("OS", "Win 10");
        reports.setSystemInfo("User Name", "Vishal Mathur");

        htmlReporter.config().setDocumentTitle("QA Assignment");
        htmlReporter.config().setReportName("Search Test");

    }
    @BeforeClass
    public void dataProviderSetup(){
        excelData = new ExcelDataProvider();
        configData = new ConfigDataProvider();
    }
    @BeforeClass
    public void setup(){
        driver = BrowserSetup.startApp(driver, configData.getBrowser(), configData.getURL());
    }
    @AfterClass
    public void tearDown(){
        BrowserSetup.quitApp(driver);
    }

    @AfterMethod
    public void reportGen(){
        reports.flush();
    }
}
