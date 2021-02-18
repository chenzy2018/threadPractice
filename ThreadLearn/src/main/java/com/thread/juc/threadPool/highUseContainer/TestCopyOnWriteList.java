package com.thread.juc.threadPool.highUseContainer;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * CopyOrWriteList使用
 *
 * 写时复制，适用于写少读多的情况
 *
 * 原理：写操作的时候，复制一份，再末尾加，添加完成之后再把原始list的引用指向新的list，这样基本不影响读
 * 写加了锁(排它锁)，读不加锁
 */
public class TestCopyOnWriteList {

    static final int length = 100;

    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();

        Random random = new Random();
        Thread[] threads = new Thread[length];
        //CountDownLatch countDownLatch = new CountDownLatch(length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    list.add("val"+random.nextInt(100000));
                }
                //countDownLatch.countDown();
            },"t"+i);
        }

        for(Thread thread : threads) thread.start();

        /*try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
