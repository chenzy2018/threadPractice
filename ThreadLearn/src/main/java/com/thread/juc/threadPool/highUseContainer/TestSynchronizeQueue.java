package com.thread.juc.threadPool.highUseContainer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * SynchronizeQueue的使用
 *
 * 容量为0，不存储内容，专门用来线程之间传递任务的，同一时间只能传递一个
 * 在任务调度的场景中比较常用
 */
public class TestSynchronizeQueue{

    public static void main(String[] args) throws InterruptedException{
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(blockingQueue.take());//阻塞，等待生产者生产
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        blockingQueue.put("a");//阻塞，等待消费者消费
//        blockingQueue.put("b");
//        blockingQueue.add("c");//调用add就会报错，因为SynchronousQueue的容量为0

        System.out.println(blockingQueue.size());
    }
}
