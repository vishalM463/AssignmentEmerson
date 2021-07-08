package com.TestCases;

import com.pages.BaseClass;
import com.pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

/* ********************************************
##### This is a test class for doing rough work. Pls do not include it in execution ########
*********************************************/
public class TestClass extends BaseClass {
    static boolean continueExecution = true;
    static boolean executeMain=false;
    @Test
    public void searchSalesOrder () throws InterruptedException {
        SearchPage searchPageObj = PageFactory.initElements(driver,SearchPage.class);
        int rowTotal = excelData.getRowCount(configData.getSheetName("Purchase"));
        for (int rowNum=1; rowNum<=rowTotal;rowNum++){
            String orderID = excelData.getData(configData.getSheetName("Purchase"), rowNum, 0);
            if (rowNum==1){
                executeMain = true;
            }
            if (executeMain){
                    searchPageObj.searchOrderMain(orderID, "Purchase");
                    executeMain=false;
                    Thread.sleep(4000);
            }
            else{
                    searchPageObj.searchOrder(orderID, "Purchase");
                    Thread.sleep(4000);
            }
            if (!driver.getPageSource().contains("Order Not Found")) {
                System.out.println("Order details are available for Order Id : " + orderID);
            } else {
                continueExecution = false;
                System.out.println("Order details are not available for Order Id : " + orderID + " skipping Duplicate Check and Track Details fetch ....");
                continue;
            }

            if (!driver.getPageSource().contains("Duplicates Found")) {
                System.out.println("Duplicates values are not present for Order Id : " + orderID);
            } else {
                driver.findElement(By.id("duplicateModalClose")).click();
                executeMain = true;
                System.out.println("Duplicates values are present for Order Id : " + orderID + " skipping Track Details fetch ....");
                continue;
            }
            boolean isPresent = searchPageObj.isMoreLinkPresent();
                if (isPresent) {
                    searchPageObj.clickMoreLink();
                    String trackingID = searchPageObj.getTrackDetails();
                    System.out.println("Tracking details for order ID " + orderID + " is : \n" + trackingID);
                }
                else {
                    System.out.println("Tracking number is not available");
                }
        }
    }
}
