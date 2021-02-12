package com.thread.juc;

/**
 * 面试题：模拟银行账户
 * 给写方法加锁，读方法不加锁
 * 可不可以
 *
 * 会产生脏读(dirtyRead)
 * 如果业务上允许脏读，或者只是读取不会用结果做什么处理，可以不加锁
 * 加锁的效率很低
 */
public class Acount {

    private volatile String name;
    private int balance;

    public synchronized void set(String name, int balance){
        this.name = name;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }


    public /*synchronized*/ int read(String name){
        return this.balance;
    }

    public static void main(String[] args) {
        Acount acount= new Acount();
        new Thread(()->acount.set("ddd",100)).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(acount.read("dddd"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(acount.read("dddd"));
    }
}
