package main.java.com.org.czy.ThreadPractice;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch使用
 */
public class TestCountDown {
    //6次扣减
    private static CountDownLatch countDown = new CountDownLatch(6);


    private static class CusThread implements Runnable{
        @Override
        public void run() {
            try {
                countDown.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"可以正常执行具体内容了。。。。");
        }
    }


    private static class CountThread implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDown.countDown();
            System.out.println(Thread.currentThread().getName()+"执行扣减了，继续执行。。。。。");
        }
    }


    public static void main(String[] args) {


        //启动一个初始化线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * 每步操作一次扣减，因此扣减的数不一定等于线程数
                 */
                //第一步操作
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDown.countDown();
                System.out.println(Thread.currentThread().getName()+"第一步执行扣减了，继续执行。。。。。");
                //第二步操作
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDown.countDown();
                System.out.println(Thread.currentThread().getName()+"第二步执行扣减了，继续执行。。。。。");
            }
        }).start();


        //启动客户线程
        new Thread(new CusThread()).start();


        //启动四个初始化线程
        for (int i = 0; i <= 3; i++) {
            new Thread(new CountThread()).start();
        }


        try {
            countDown.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(Thread.currentThread().getName()+"继续执行。。。。。");
    }
}
