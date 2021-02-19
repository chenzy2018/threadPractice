package com.thread.juc.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ExecutorService
 *
 * 继承Executor接口，完善了线程池的生命周期
 */
public class TestExecutorService {
    public static void main(String[] args) {
        ExecutorService e= Executors.newCachedThreadPool();

        //提交任务，具体什么时候执行由线程池决定，异步的
        e.submit(()->{
            System.out.println("ss");
        });

        e.shutdown();//停止线程池
    }
}
