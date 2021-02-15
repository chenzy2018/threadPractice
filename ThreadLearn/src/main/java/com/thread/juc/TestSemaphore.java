package com.thread.juc;

import java.util.concurrent.Semaphore;

/**
 * 信号量
 *
 * 初始化方法可以设定信号数量，表示同一时间最多可以有几个线程在运行
 * 默认是非公平的，可以构造方法的时候设置是否是公平
 * 可以用于限流
 */
public class TestSemaphore {

    static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {
        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+" is running");
                Thread.sleep(1000);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+" is running");
                Thread.sleep(1000);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();

        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+" is running");
                Thread.sleep(1000);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t3").start();
    }
}
