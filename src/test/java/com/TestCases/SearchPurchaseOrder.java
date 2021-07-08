/* **********************************************************
 ##### Test Cases class for Sales Order Purchase test ######
 Author         : Vishal Mathur
 Created Date   :7-July-2021
 **********************************************************/
package com.TestCases;

import com.pages.BaseClass;
import com.pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class SearchPurchaseOrder extends BaseClass {
    static boolean continueExecution = true;
    static boolean executeMain=false;
    @Test
    public void searchPurchaseOrder () throws InterruptedException {

        SearchPage searchPageObj = PageFactory.initElements(driver,SearchPage.class);
        logger = reports.createTest("Starting Test Execution");
        int rowTotal = excelData.getRowCount(configData.getSheetName("Purchase"));
        logger.info( rowTotal + " rows returned from excel sheet");
        for (int rowNum=1; rowNum<=rowTotal;rowNum++){
            String orderID = excelData.getData(configData.getSheetName("Purchase"), rowNum, 0);
            logger = reports.createTest("Executing Case " + rowNum + " for Order Id : " + orderID);
            if (rowNum==1){
                executeMain = true;
            }
            if (executeMain){
                logger.info("Searching from landing page search");
                searchPageObj.searchOrderMain(orderID, "Purchase");
                executeMain=false;
                Thread.sleep(4000);
            } else{
                searchPageObj.searchOrder(orderID, "Purchase");
                Thread.sleep(4000);
            }
            if (!driver.getPageSource().contains("Order Not Found")) {
                logger.pass("Order details are available for Order Id : " + orderID);
//                System.out.println("Order details are available for Order Id : " + orderID);
            } else {
                continueExecution = false;
                logger.fail("Order Not Found");
                logger.info("Order details are not available for Order Id : " + orderID + " skipping Duplicate Check and Track Details fetch ....");
//                System.out.println("Order details are not available for Order Id : " + orderID + " skipping Duplicate Check and Track Details fetch ....");
                continue;
            }

            if (!driver.getPageSource().contains("Duplicates Found")) {
                logger.pass("Duplicates values are not present for Order Id : " + orderID);
//                System.out.println("Duplicates values are not present for Order Id : " + orderID);
            } else {
                driver.findElement(By.id("duplicateModalClose")).click();
                executeMain = true;
                logger.fail("Duplicates values are present for Order Id : " + orderID + " skipping Track Details fetch ....");
//                System.out.println("Duplicates values are present for Order Id : " + orderID + " skipping Track Details fetch ....");
                continue;
            }
//            boolean isPresent = driver.findElements(By.xpath("//*[@id=\"lineItemTable\"]/tbody/tr[2]/td[6]/div/div")).size()>0;
            boolean isPresent = searchPageObj.isMoreLinkPresent();
            if (isPresent) {
                searchPageObj.clickMoreLink();
                String trackingID = searchPageObj.getTrackDetails();
                logger.pass("Tracking details for order ID " + orderID + " is : \n" + trackingID);
//                System.out.println("Tracking details for order ID " + orderID + " is : \n" + trackingID);
            } else {
                logger.fail("Tracking number is not available");
//                System.out.println("Tracking number is not available");
            }
        }
    }
}
