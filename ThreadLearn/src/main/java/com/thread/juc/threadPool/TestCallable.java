package com.thread.juc.threadPool;

import java.util.concurrent.*;

/**
 * Callable接口
 *
 * 带返回值的Runnable
 */
public class TestCallable {

    public static void main(String[] args) {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello callable";
            }
        };

        ExecutorService e = Executors.newCachedThreadPool();

        Future<String> future = e.submit(callable);//异步

        try {
            System.out.println(future.get());//阻塞的
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }

        e.shutdown();//停止线程池
    }

}
