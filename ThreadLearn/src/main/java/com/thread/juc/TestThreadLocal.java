package com.thread.juc;

import com.thread.utils.ThreadUtil;

/**
 * ThreadLocal
 *
 * 每个线程都存自己的一份变量信息，线程之间的改动互不影响
 *
 * 用途：
 *  声明式事务，保证同一个事务中的方法拿到的是同一个数据库连接connection，从当前线程的ThreadLocalMap中拿
 */
public class TestThreadLocal {

    //private static String name = "t1";

    private static ThreadLocal<String> name = new ThreadLocal<String>();

    public static void main(String[] args) {

        /*new Thread(()->{
            ThreadUtil.sleep(2);
            System.out.println("线程1:"+name);
        },"t1").start();

        new Thread(()->{
            ThreadUtil.sleep(1);
            name = "t2";
            System.out.println("线程2:"+name);
        },"t2").start();*/

        new Thread(()->{
            ThreadUtil.sleep(2);
            System.out.println("线程1:"+name.get());
        },"t1").start();

        new Thread(()->{
            ThreadUtil.sleep(1);
            name.set("t2");
            System.out.println("线程2:"+name.get());
        },"t2").start();
    }
}
