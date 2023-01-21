package com.example.finstrumental.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatterUtils {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


    public static Date getDateFromString(String dt) throws ParseException {
        return formatter.parse(dt);
    }

    public static String getStringDateFromDate(Date dt) throws ParseException {
        return formatter.format(dt);
    }
}
