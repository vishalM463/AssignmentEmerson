/* **********************************************************
 ##### Class for handling Search page operations ########
 Author         : Vishal Mathur
 Created Date   :7-July-2021
 **********************************************************/
package com.pages;

import com.utility.FetchElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage extends BaseClass{

    WebDriver driver;

    public SearchPage(WebDriver localDriver){
        this.driver=localDriver;
    }
    FetchElements fetchElements = new FetchElements();

    /* Page factories to get WebElements (Alternatively we can use FetchElements utility class instead) */
    @FindBy(id="mainSearchText") WebElement mainSearchBox;
    @FindBy(id = "mainSearchButton") WebElement mainSearchButton;
    @FindBy(id = "mainDdlSearchType") WebElement mainOrderTypeSelect;

    @FindBy(id="searchText") WebElement searchBox;
    @FindBy(id = "searchButton") WebElement searchButton;
    @FindBy(id = "ddlSearchType") WebElement orderTypeSelect;

//    @FindBy(xpath = "searchButton") WebElement moreLink;
//    @FindBy(xpath = "searchButton") List<WebElement> moreLinks;

//    @FindBy(xpath = "ddlSearchType") WebElement trackDetails;

    public void searchOrder(String orderID, String orderType) {
        Select objSelect =new Select(orderTypeSelect);
        if (orderType.equalsIgnoreCase("Purchase")){
            objSelect.selectByIndex(0);
        }
        else if (orderType.equalsIgnoreCase("Sales")){
            objSelect.selectByIndex(1);
        }
        else System.out.println("Invalid order type");
        searchBox.sendKeys(orderID);
        searchButton.click();
    }

    public void searchOrderMain(String orderID, String orderType) {
        Select objSelect =new Select(mainOrderTypeSelect);
        if (orderType.equalsIgnoreCase("Purchase")){
            objSelect.selectByIndex(0);
        }
        else if (orderType.equalsIgnoreCase("Sales")){
            objSelect.selectByIndex(1);
        }
        else System.out.println("Invalid order type");
        mainSearchBox.sendKeys(orderID);
        mainSearchButton.click();
    }

    public void clickMoreLink(){
        WebElement moreLink = fetchElements.getWebElement("XPATH",configData.getMoreLinkXPATH());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(moreLink));
        moreLink.click();
    }

    public boolean isMoreLinkPresent() throws InterruptedException {
        Thread.sleep(5000);
       return fetchElements.getWebElements("XPATH", configData.getMoreLinkXPATH()).size()>0;
    }

    public String getTrackDetails(){
        WebElement trackDetails = fetchElements.getWebElement("XPATH",configData.getTrackDetailsXPATH());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(trackDetails));
        return trackDetails.getText().trim();
    }
}
