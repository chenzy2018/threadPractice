package com.thread.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * 读锁是共享锁，所有线程都可以同时读
 * 写锁是排它锁。同一时间只能有一个线程在写
 */
public class TestReadWriteLock {


    static Lock lock = new ReentrantLock();
    static String str = "test1";
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock){
        try{
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over:"+str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, String value){
        try {
            lock.lock();
            Thread.sleep(1000);
            TestReadWriteLock.str = value + Thread.currentThread().getName();
            System.out.println("write over:"+str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

//        Runnable r1 = ()-> read(lock);
//        Runnable r2 = ()-> write(lock,"test2");

        Runnable r1 = ()-> read(readLock);
        Runnable r2 = ()-> write(writeLock,"test2");

        for(int i = 0; i<18; i++) new Thread(r1).start();
        for(int i = 0; i<2; i++) new Thread(r2).start();
    }
}
