package com.thread.juc.interview_03;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestBlockingQueue {

    static BlockingQueue<String> b1 = new LinkedBlockingQueue<>();
    static BlockingQueue<String> b2 = new LinkedBlockingQueue<>();
    static String[] strs = {"a","b","c","d"};

    public static void main(String[] args) {
        new Thread(()->{
            for (String str : strs) {
                try {
                    b1.take();
                    System.out.print(str);
                    b2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"t2").start();

        new Thread(()->{
            for (int i = 0; i < 4; i++) {
                System.out.print(i);
                try {
                    b1.put("ok");
                    b2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();
    }
}
