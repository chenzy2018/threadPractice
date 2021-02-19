package com.thread.juc.threadContainer;

import java.util.Hashtable;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * HashTable
 *
 * 方法加锁，线程安全，效率较差，基本不用
 */
public class TestHashTable {

    static Hashtable<UUID, UUID> hashtable = new Hashtable<>();
    static int count = TestContants.count;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static final int threadCount = TestContants.threadCount;
    static CountDownLatch countDownLatch = new CountDownLatch(threadCount);

    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread{
        int start;
        int gap = count/threadCount;

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start+gap; i++) {
                hashtable.put(keys[i],values[i]);
            }
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new MyThread(i*(count/threadCount));
        }

        for(Thread thread : threads){
            thread.start();
        }

        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println(end - start);
        System.out.println(hashtable.size());
    }
}
