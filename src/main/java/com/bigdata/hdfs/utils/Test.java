package com.bigdata.hdfs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    public static void main(String[] args) {
        new Test().a();

//        System.out.println("****"+get_v_date_last_hour("202009101050"));
        System.out.println("****"+get_v_date_minute(0));
        System.out.println("****"+get_v_date_minute(-20));
    }


    public void a(){
        long start = System.currentTimeMillis();
        logger.info("======used [" + (System.currentTimeMillis() - start) / 1000 + "] seconds ..");
        logger.info("12334");
    }



    static String get_v_date_last_hour(String a) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        Calendar calendar = Calendar.getInstance();
        if (a == null || "".equals(a)) {
            calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 1);
            return df.format(calendar.getTime());
        } else {
            try {
                Date date = df.parse(a);
                calendar.setTime(date);
                calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 1);
                return df.format(calendar.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    static String get_v_date_minute(int n){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, n);
        calendar.getTime();
        return sdf.format(calendar.getTime());}
}
