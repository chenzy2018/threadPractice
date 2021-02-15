package com.thread.juc.interView_01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 通过countDownLatch实现线程之间的通信
 *
 * 单门闩的方案，能实现通信，但不能保证执行结果的一致性
 * 解决方案：双门闩
 */
public class TestCountDownLatch {

    List<Integer> list = new ArrayList<>();

    public void add(int i){
        list.add(i);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        TestCountDownLatch testCountDownLatch = new TestCountDownLatch();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        //第二个门闩，可以去掉测试单门闩的结果
        CountDownLatch countDownLatch1 = new CountDownLatch(1);

        //需要让t2先启动，否则可能t2还没await，t1就已经结束了
        new Thread(()->{
                if (testCountDownLatch.size() != 5) {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("长度为5，t2退出");
                countDownLatch1.countDown();
        },"t2").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                testCountDownLatch.add(i);
                System.out.println("list添加了：" + i);
                if (i == 4) {
                    countDownLatch.countDown();
                    try {
                        countDownLatch1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"t1").start();
    }

}
