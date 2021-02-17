package com.thread.juc.interview_03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {

    static String[] strs = {"a","b","c","d"};
    static Lock lock = new ReentrantLock();
    static Condition c1 = lock.newCondition();
    static Condition c2 = lock.newCondition();

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 4; i++) {

                try {
                    lock.lock();
                    System.out.print(i);
                    c2.signal();
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        },"t1");

        Thread t2 = new Thread(()->{
            for (String str : strs) {
                try {
                    lock.lock();
                    c2.await();
                    System.out.print(str);
                    c1.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
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
