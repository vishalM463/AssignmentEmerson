/* **********************************************************
 ##### Utility class to get configs Data ########
 Author         : Vishal Mathur
 Created Date   :7-July-2021
 **********************************************************/
package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {
    static Properties propConf, propObjRepo;

    /* * Initializing properties in constructor **/
    public ConfigDataProvider (){
        File srcConf = new File("./Configs/config.properties");
        File srcObjRepo = new File("./Configs/ObjectRepo.properties");

        try {
            FileInputStream fisConf = new FileInputStream(srcConf);
            propConf = new Properties();
            propConf.load(fisConf);
            FileInputStream fisObjRepo = new FileInputStream(srcObjRepo);
            propObjRepo = new Properties();
            propObjRepo.load(fisObjRepo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getURL(){
        return propConf.getProperty("URL");
    }
    public String getBrowser(){
        return propConf.getProperty("Browser");
    }
    public String getSheetName(String orderType){
        if(orderType.equalsIgnoreCase("Purchase")) {
            return propConf.getProperty("PurchaseSheetName");
        }
        else if(orderType.equalsIgnoreCase("Sales")){
            return propConf.getProperty("SalesSheetName");
        }
        else {
            System.out.println("Invalid Sheet Name provided");
            return null;
        }

    }

    /* ** Optional methods if we want to get Object Locators from ObjectRepo.properties file ***/
    public String getMainText(){
        return propObjRepo.getProperty("MainSearchBoxID");
    }
    public String getMainSearchBtn(){
        return propObjRepo.getProperty("MainSearchBtbID");
    }
    public String getMainOrderType(){
        return propObjRepo.getProperty("MainOrderTypeID");
    }
    public String getText(){
        return propObjRepo.getProperty("SearchBoxID");
    }
    public String getSearchBtn(){
        return propObjRepo.getProperty("SearchBtbID");
    }
    public String getOrderType(){
        return propObjRepo.getProperty("OrderTypeID");
    }
    public String getMoreLinkXPATH(){
        return propObjRepo.getProperty("MoreLinkXPATH");
    }
    public String getTrackDetailsXPATH(){
        return propObjRepo.getProperty("TrackDetailsXPATH");
    }

}
