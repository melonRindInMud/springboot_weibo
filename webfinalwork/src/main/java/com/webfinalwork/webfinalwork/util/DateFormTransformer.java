package com.webfinalwork.webfinalwork.util;

import java.util.Date;

// 日期格式转换器
public class DateFormTransformer {
    public static String TimeToDate(long tm) {
        Date date = new Date(tm);
        String temp = "";
        temp += String.valueOf(date.getYear() + 1900) + ".";
        temp += String.format("%02d", date.getMonth() + 1) + ".";
        temp += String.format("%02d", date.getDate()) + " ";
        temp += String.format("%02d", date.getHours()) + ":";
        temp += String.format("%02d", date.getMinutes());
        return temp;
    }
}
