package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static String stampToTimeyMdHms(Long stamp) {
        String sd = "";
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (stamp!=null){
            sd = sdf.format(new Date(stamp*1000)); // 时间戳转换日期
        }
        return sd;
    }

    public static String stampToTimeyMd(Long stamp) {
        String sd = "";
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (stamp!=null){
            sd = sdf.format(new Date(stamp*1000)); // 时间戳转换日期
        }
        return sd;
    }

    public static String dateToString(Date date){
        if (date==null){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    //获取月日格式的时间
    public static String getMdDateString(Integer hours){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hours);
        return sdf.format(calendar.getTime());
    }

    public static String getyMdDateString(Integer hours){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hours);
        return sdf.format(calendar.getTime());
    }

    public static Date getDateStart(Integer hours){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(TimeUtils.getyMdDateString(hours));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long calcToNowDays(Date date) {
        if (date==null){
            return 0L;
        }
        Long diff = new Date().getTime()-date.getTime();
        return diff/(1000 * 24 * 60 * 60);
    }

    //当前日开始时间戳（秒）
    public static Long dayTimeInMillis() {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis()/1000;
    }

    public static Date strToDate(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
