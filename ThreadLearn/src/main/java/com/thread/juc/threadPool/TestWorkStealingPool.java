package com.thread.juc.threadPool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * WorkStealingPool工作密取线程池，本质就是ForkJoinPool
 * 存在意义就是封装一层而已，完全可以使用ForkJoinPool自己指定参数构建WorkStealingPool
 *
 * 一个线程对应一个任务队列，当线程完成了自己任务之后，会从其他线程的任务队列尾部拿任务放到自己任务队列的尾部，然后完成任务
 *
 * push和pop不需要加锁，因为都是自己push和pop，但是poll需要加锁，因为可能多个线程完成了任务都过来拿任务，所以需要用锁保证只有一个线程可以拿到一个任务
 */
public class TestWorkStealingPool {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());

        executorService.execute(new R(1000));
        executorService.execute(new R(2000));
        executorService.execute(new R(2000));
        executorService.execute(new R(2000));
        executorService.execute(new R(3000));

        //阻塞主线程，由于产生的是守护线程，不阻塞主线程，就看不到输出
        System.in.read();
    }

    static class R implements Runnable{

        int time;

        R(){

        }

        R(int time){
            this.time = time;
        }

        @Override
        public void run() {

        }
    }
}
