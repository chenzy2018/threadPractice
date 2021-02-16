package com.thread.juc.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试线程池
 */
public class TestThreadPool {

    private int corePoolSize ;

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 8, 500,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static class CzyRun implements Runnable{

        @Override
        public void run() {
            System.out.println("sdfs1");
        }
    }

    public static void main(String[] args) {
        CzyRun czyRun = new CzyRun();
        czyRun.run();

        Thread t = new Thread(() -> {

            System.out.println("dsfsd1");
        });

        threadPoolExecutor.execute(t);
        //t.start();
        threadPoolExecutor.shutdown();
    }

}
