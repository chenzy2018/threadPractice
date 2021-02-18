package com.thread.juc.threadPool.highUseContainer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue的使用
 *
 * 有界阻塞队列
 */
public class TestArrayBlockingQueue{

    static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);
    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException{
        for (int i = 0; i < 10; i++) {
            blockingQueue.put("a"+i);
        }

        blockingQueue.put("a"+11);//已经满了，就阻塞了，等消费者消费
//        blockingQueue.add("a"+11);//添加，队列满了就报错异常
//        blockingQueue.offer("a"+11);//添加，不成功就返回false不报错，只是没添加成功
//        blockingQueue.offer("a"+11, 1, TimeUnit.SECONDS);//尝试添加1s，1s后不成功就返回false，不报错，只是没添加成功
        System.out.println(blockingQueue.size());
    }
}
