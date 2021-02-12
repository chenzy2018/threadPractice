package main.java.com.org.czy.ThreadPractice;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier的使用
 */
public class TestCyclicBarrier {


    private static CyclicBarrier CyclicBarrier = new CyclicBarrier(4);


    private static CyclicBarrier CyclicBarrierRun = new CyclicBarrier(4, new CusThread());


    private static class CusThread implements Runnable{


        private int result;


        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                result += i;
            }
            System.out.println(Thread.currentThread().getName()+"执行了,结果是"+result);
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


            System.out.println(Thread.currentThread().getName()+"被拦了，等待中。。。");
            try {
                //CyclicBarrier.await();
                CyclicBarrierRun.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }


            System.out.println(Thread.currentThread().getName()+"通过，继续执行。。。。。");
        }
    }


    public static void main(String[] args) {
        //启动一个初始化线程
        //new Thread(new Runnable() {
        //    @Override
        //    public void run() {
        //        /**
        //         * 每步操作一次扣减，因此扣减的数不一定等于线程数
        //         */
        //        //第一步操作
        //        try {
        //            Thread.sleep(2);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //
        //        //第二步操作
        //        try {
        //            Thread.sleep(2);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        //
        //    }
        //}).start();


        //启动四个初始化线程
        for (int i = 0; i <= 3; i++) {
            new Thread(new CountThread()).start();
        }


        System.out.println(Thread.currentThread().getName()+"继续执行。。。。。");
    }
}
