package com.drrepository.main.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final String TAG = "DateUtils";

    public static String getCustomDate(long data){
        String date = "";
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm");
            date = simpleDateFormat.format(new Date(data*1000));
        }catch (Exception e){
            e.printStackTrace();
        }
        return date;
    }
}
