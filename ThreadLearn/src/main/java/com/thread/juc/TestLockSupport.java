package com.thread.juc;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport使用更加自由，不需要获取锁，直接可以调用
 *
 * 底层是通过调用Unsafe.park()来实现，native方法
 */
public class TestLockSupport {

    public static void main(String[] args) {

        Thread t = new Thread(()->{
            for (int i = 0; i < 10; i++) {

                System.out.println(i);
                //阻塞线程执行
                if(i==5){
                    LockSupport.park();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //unpark可以在park之前调用，unpark之后的park都不生效，不会阻止线程执行
        System.out.println("8秒后继续运行");
        LockSupport.unpark(t);
    }
}
