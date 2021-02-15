package com.thread.juc.interView_01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 通过LockSupport实现线程之间的通信
 *
 * 单LockSupport的方案，能实现通信，但不能保证执行结果的一致性
 * 解决方案：双LockSupport
 */
public class TestLockSupport {

    List<Integer> list = new ArrayList<>();

    public void add(int i){
        list.add(i);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        TestLockSupport testLockSupport = new TestLockSupport();

        //需要让t2先启动，否则可能t2还没wait，t1就已经结束了
        Thread t2 = new Thread(()->{
            if (testLockSupport.size() != 5) {
                LockSupport.park();
            }
            System.out.println("长度为5，t2退出");
            //LockSupport.unpark(t1);
        });

        t2.start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                testLockSupport.add(i);
                System.out.println("list添加了：" + i);
                if (i == 4) {
                    LockSupport.unpark(t2);
                }
            }
        },"t1").start();
    }
}
