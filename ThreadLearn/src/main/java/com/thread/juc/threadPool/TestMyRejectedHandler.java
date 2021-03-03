package com.thread.juc.threadPool;

import java.util.concurrent.*;

/**
 * 自定义线程池拒绝策略
 */
public class TestMyRejectedHandler {

    public static void main(String[] args) {
        //使用自定义拒绝策略的线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                4, 10, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(50), Executors.defaultThreadFactory(),
                new Myhandler()
        );
    }

    /**
     * 实现RejectedExecutionHandler接口，即可自定义拒绝策略
     */
    static class Myhandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            if(executor.getQueue().size() < 10000){

            }
        }
    }
}
