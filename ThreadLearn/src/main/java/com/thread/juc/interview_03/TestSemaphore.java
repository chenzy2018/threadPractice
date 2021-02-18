package com.thread.juc.interview_03;

import com.thread.utils.ThreadUtil;

import java.util.concurrent.Semaphore;

public class TestSemaphore {

    static String[] strs = {"a","b","c","d"};
    static Semaphore s1 = new Semaphore(1,true);//使用公平方式就可以保证顺序

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 4; i++) {
                try {
                    s1.acquire();
                    System.out.print(i);
                    s1.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");

        Thread t2 = new Thread(()->{
            for (String str : strs) {
                try {
                    s1.acquire();
                    System.out.print(str);
                    s1.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2");

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
