package com.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

    public static String getDateTime(){
        DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        Date currentDate = new Date();
        return customFormat.format(currentDate);
    }
}
