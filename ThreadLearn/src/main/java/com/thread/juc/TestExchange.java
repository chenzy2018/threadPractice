package com.thread.juc;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.Exchanger;

/**
 * 交换
 *
 * 用于两个线程之间交换数据
 */
public class TestExchange {

    static Exchanger<String> exchanger = new Exchanger<String>();

    public static void main(String[] args) {

        new Thread(()->{
                try {
                    String s = "t1";
                    s = exchanger.exchange(s);
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+":"+s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        },"t1").start();

        new Thread(()->{
            try {
                String s = "t2";
                s = exchanger.exchange(s);
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+":"+s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();
    }
}
