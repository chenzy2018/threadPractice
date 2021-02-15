package com.thread.utils;

/**
 * 线程工具类
 */
public class ThreadUtil {

    public static void sleep(int secend){
        try {
            Thread.sleep(secend*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
