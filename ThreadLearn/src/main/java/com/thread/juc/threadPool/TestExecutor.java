package com.thread.juc.threadPool;

import java.util.concurrent.Executor;

/**
 * Executor
 * 线程池的顶层接口
 */
public class TestExecutor implements Executor {

    @Override
    public void execute(Runnable command) {
        command.run();
    }

    public static void main(String[] args) {
        new TestExecutor().execute(()->System.out.println("asd"));
    }
}
