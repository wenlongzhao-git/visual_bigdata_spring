package com.bigdata.hdfs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    public static void main(String[] args) {
        new Test().a();
    }


    public void a(){
        long start = System.currentTimeMillis();
        logger.info("======used [" + (System.currentTimeMillis() - start) / 1000 + "] seconds ..");
        logger.info("12334");
    }

}
