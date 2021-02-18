package com.thread.juc.interview_03;

import java.util.concurrent.locks.LockSupport;

public class TestLockSupport {

    static String[] strs = {"a","b","c","d"};
    static Thread t1 = null,t2=null;

    public static void main(String[] args) {

        t1 = new Thread(()->{
            for (int i = 0; i < 4; i++) {
                System.out.print(i);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        },"t1");

        t2 = new Thread(()->{
            for (String str : strs) {
                LockSupport.park();
                System.out.print(str);
                LockSupport.unpark(t1);
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
