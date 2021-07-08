/* **********************************************************
 ##### Test Cases class for Sales Order Search test ########
 Author         : Vishal Mathur
 Created Date   :7-July-2021
 **********************************************************/
package com.TestCases;

import com.pages.BaseClass;
import com.pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class SearchSalesOrder extends BaseClass {
    static boolean continueExecution = true;
    static boolean executeMain=false;
    @Test
    public void searchSalesOrder () throws InterruptedException {// method for testing purchase order cases

        SearchPage searchPageObj = PageFactory.initElements(driver,SearchPage.class);
        logger = reports.createTest("Starting Test Execution");
        logGen.info("#### Starting Sales Order Test Execution ####");
        int rowTotal = excelData.getRowCount(configData.getSheetName("Sales"));
        logger.info( rowTotal + " rows returned from excel sheet");
        logGen.info( rowTotal + " rows returned from excel sheet");

        //getting the order IDs from Sales Order Excel sheet and executing one at a time
        for (int rowNum=1; rowNum<=rowTotal;rowNum++){
            String orderID = excelData.getData(configData.getSheetName("Sales"), rowNum, 0);
            logger = reports.createTest("Executing Case " + rowNum + " for Order Id : " + orderID);
            logGen.info("Executing Case " + rowNum + " for Order Id : " + orderID);
            if (rowNum==1){
                executeMain = true;
            }
            if (executeMain){
                logger.info("Searching from landing page search");
                logGen.info("Searching from landing page search");
                searchPageObj.searchOrderMain(orderID, "Sales");
                executeMain=false;
                Thread.sleep(4000);//For testing sync, will remove in later code updates
            } else{
                logger.info("Searching from Right side page search");
                logGen.debug("Searching from Right side page search");
                searchPageObj.searchOrder(orderID, "Sales");
                Thread.sleep(4000);//For testing sync, will remove in later code updates
            }
            if (!driver.getPageSource().contains("Order Not Found")) {
                logger.pass("Order details are available for Order Id : " + orderID);
                logGen.info("Order details are available for Order Id : " + orderID);
            } else {
                continueExecution = false;
                logger.fail("Order Not Found");
                logGen.info("Order Not Found");
                logger.info("Order details are not available for Order Id : " + orderID + " skipping Duplicate Check and Track Details fetch ....");
                logGen.info("Order details are not available for Order Id : " + orderID + " skipping Duplicate Check and Track Details fetch ....");
                continue;
            }

            if (!driver.getPageSource().contains("Duplicates Found")) {
                logger.pass("Duplicates values are not present for Order Id : " + orderID);
                logGen.info("Duplicates values are not present for Order Id : " + orderID);
            } else {
                driver.findElement(By.id("duplicateModalClose")).click();
                executeMain = true;
                logger.fail("Duplicates values are present for Order Id : " + orderID + " skipping Track Details fetch ....");
                logGen.info("Duplicates values are present for Order Id : " + orderID + " skipping Track Details fetch ....");
                continue;
            }
            boolean isPresent = searchPageObj.isMoreLinkPresent();
            if (isPresent) {
                searchPageObj.clickMoreLink();
                String trackingID = searchPageObj.getTrackDetails();
                logger.pass("Tracking details for order ID " + orderID + " is : \n" + trackingID);
                logGen.info("Tracking details for order ID " + orderID + " is : \n" + trackingID);
            } else {
                logger.fail("Tracking number is not available");
                logGen.info("Tracking number is not available");
            }
        }
    }

}