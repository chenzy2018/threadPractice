package com.thread.juc.threadContainer.highUseContainer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * LinkedBlockingQueue的使用
 *
 * 无界(长度不限，最大是Integer.MAX_VALUE)阻塞队列，链表实现
 *
 * BlockingQueue在Queue基础上，增加了两个阻塞方法，put、take
 */
public class TestLinkedBlockingQueue {

    static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(10);
    static Random random = new Random();

    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    blockingQueue.put("a"+i);//如果队列满了，就会阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                for (; ; ) {
                    try {
                        System.out.println(blockingQueue.take());//如果队列空了，就会阻塞
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"c"+i).start();
        }
    }
}
