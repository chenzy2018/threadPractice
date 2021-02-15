package com.thread.juc.interView_01;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过wait/notify来实现
 *
 * double调用，保证线程之间的同步需求
 * 标准实现方案，记住
 */
public class TestWaitNotifyDouble {

    List<Integer> list = new ArrayList<>();

    public void add(int i){
        list.add(i);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        TestWaitNotifyDouble testWaitNotifyDouble = new TestWaitNotifyDouble();
        final Object lock = new Object();

        //需要让t2先启动，否则可能t2还没wait，t1就已经结束了
        new Thread(()->{
            synchronized (lock) {
                //while (true) {
                if (testWaitNotifyDouble.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("长度为5，t2退出");
                //}
                lock.notify();
            }
        },"t2").start();

        new Thread(()->{
            synchronized (lock){
                for (int i = 0; i < 10; i++) {
                    testWaitNotifyDouble.add(i);
                    System.out.println("list添加了："+i);
                    if(i == 4){
                        try {
                            lock.notify();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        },"t1").start();
    }
}
