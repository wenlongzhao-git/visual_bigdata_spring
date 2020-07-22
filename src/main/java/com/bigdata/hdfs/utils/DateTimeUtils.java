package com.bigdata.hdfs.utils;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

        System.out.println(new Integer(5).getClass().getName());


        System.out.println(new DateTime());
        System.out.println(VeDate.getNowDateShort());
    }

}
