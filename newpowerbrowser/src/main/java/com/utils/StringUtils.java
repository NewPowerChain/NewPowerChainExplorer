package com.utils;

public class StringUtils {

    //隐藏关键信息
    public static String hideCruxInfo(String s){
        String result = "";
        if (s != null) {
            result = s.replaceAll("([\\u4e00-\\u9fa5]{1})(.*)(.{2})", "$1" + "*******" + "$3");
        }
        return result;
    }

    public static String hideNameInfo(String s){
        String result = "";
        if (s != null) {
            result = s.replaceAll("([\\d\\D]{1})(.*)", "$1**");
        }
        return result;
    }



    //隐藏关键信息
    public static String hideTelInfo(String s){
        String result = "";
        if (s != null) {
            result = s.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        }
        return result;
    }
}