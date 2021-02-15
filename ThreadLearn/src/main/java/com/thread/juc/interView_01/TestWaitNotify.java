package com.thread.juc.interView_01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过wait/notify来实现
 *
 * 此方案有问题
 * notify不会立刻释放锁，需要等当前线程的工作都完成之后，才会释放锁
 * 调用wait而进入等待队列的线程必须拿到锁之后才能真正进入就绪队列
 * 由于此上两点，t1调用了lock.notify()之后，t2被唤醒了，但不会直接运行，导致t2结束运行的语句打印在了最后
 */
public class TestWaitNotify {

    List<Integer> list = new ArrayList<>();

    public void add(int i){
        list.add(i);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        TestWaitNotify testWaitNodify = new TestWaitNotify();
        final Object lock = new Object();

        //需要让t2先启动，否则可能t2还没wait，t1就已经结束了
        new Thread(()->{
            synchronized (lock) {
                //while (true) {
                if (testWaitNodify.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("长度为5，t2退出");
                //}
            }
        },"t2").start();

        new Thread(()->{
            synchronized (lock){
                for (int i = 0; i < 10; i++) {
                    testWaitNodify.add(i);
                    System.out.println("list添加了："+i);
                    if(i == 4){
                        lock.notify();
                    }
                }
            }
        },"t1").start();
    }
}
