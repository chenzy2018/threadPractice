package com.thread.juc.threadPool;

import com.thread.utils.ThreadUtil;

import java.util.concurrent.*;

/**
 * Future接口：
 * 只能接收值，本身不是任务
 *
 *
 * FutureTask：实现Runnable, Future接口
 * 本身既可以作为任务又可以接收返回值
 */
public class TestFuture {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(5);

        Future<String> future = es.submit(()->{
            return "sdf";
        });

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(future.isDone());

        es.shutdown();

        //*************************************************
        FutureTask<String> futureTask = new FutureTask<String>(()->{
            ThreadUtil.sleep(1);
            return "dds";
        });

        new Thread(futureTask).start();

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
