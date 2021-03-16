package com.thread.juc;

/**
 * 测试使用volatile
 * 保证变量的可见性（基于cpu的MESI缓存一致性协议），以及避免指令重排序，但不能保证原子性，即不可完全替代synchronized
 */
public class TestVolatile {

    public volatile static boolean flag = true;

    public static void main(String[] args) {

        new Thread(()->{
            System.out.println("t1 run");
            while (flag){

            }
            System.out.println("t1 end");
        },"t1").start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TestVolatile.flag = false;
    }
}
