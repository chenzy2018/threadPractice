package com.thread.juc.interview_03;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class TestTransferQueue {
    static LinkedTransferQueue<String> blockingQueue = new LinkedTransferQueue<>();
    static String[] strs = {"a","b","c","d"};

    public static void main(String[] args) {

        new Thread(()->{
            for (String str : strs) {
                try {
                    blockingQueue.take();
                    System.out.print(str);
                    blockingQueue.transfer("T2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"t2").start();

        new Thread(()->{
            for (int i = 0; i < 4; i++) {
                System.out.print(i);
                try {
                   blockingQueue.transfer("T1");
                   blockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

    }
}
