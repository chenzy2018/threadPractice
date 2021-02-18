package com.thread.juc;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.Exchanger;

/**
 * 交换
 *
 * 用于两个线程之间交换数据
 * 两个线程都需要阻塞到交换完才行
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
