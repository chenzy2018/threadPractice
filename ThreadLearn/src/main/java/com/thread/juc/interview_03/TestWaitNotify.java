package com.thread.juc.interview_03;

public class TestWaitNotify {

    static String[] strs = {"a","b","c","d"};
    static Object o = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(()->{
            for (int i = 0; i < 4; i++) {
                synchronized (o){
                    try {
                        System.out.print(i);
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"t1");

        Thread t2 = new Thread(()->{
            for (String str : strs) {
                synchronized (o){
                    try {
                        o.wait();
                        System.out.print(str);
                        o.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"t2");

        t2.start();
        t1.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
