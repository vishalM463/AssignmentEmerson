/* *************************************************************************
 #Utility class to fetch WebElements if we don't want to use page factories
 Author         : Vishal Mathur
 Created Date   :7-July-2021
 *************************************************************************/
package com.utility;

import com.pages.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FetchElements {

    public WebElement getWebElement (String locatorType, String locatorValue){
        switch (locatorType) {
            case "ID":
                return BaseClass.driver.findElement(By.id(locatorValue));
            case "CLASS":
                return BaseClass.driver.findElement(By.className(locatorValue));
            case "XPATH":
                return BaseClass.driver.findElement(By.xpath(locatorValue));
            case "TAG":
                return BaseClass.driver.findElement(By.tagName(locatorValue));
            default:
                return null;
        }
    }

    public List<WebElement> getWebElements (String locatorType, String locatorValue){
        switch (locatorType) {
            case "ID":
                return BaseClass.driver.findElements(By.id(locatorValue));
            case "CLASS":
                return BaseClass.driver.findElements(By.className(locatorValue));
            case "XPATH":
                return BaseClass.driver.findElements(By.xpath(locatorValue));
            case "TAG":
                return BaseClass.driver.findElements(By.tagName(locatorValue));
            default:
                return null;
        }
    }
}
