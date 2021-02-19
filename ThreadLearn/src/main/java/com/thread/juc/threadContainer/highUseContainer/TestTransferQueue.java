package com.thread.juc.threadContainer.highUseContainer;

import java.util.concurrent.LinkedTransferQueue;

/**
 * TransferQueue的使用
 *
 * 比较强大的工具，其他几种BlockingQueue的组合，可以做成链表传递好几个任务
 *
 * 重点方法：transfer，线程往TransferQueue中装数据，然后阻塞在这里，等消费者消费，消费完才往后执行
 */
public class TestTransferQueue {

    public static void main(String[] args) throws InterruptedException{
        LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue<>();

        new Thread(()->{
            try {
                System.out.println(linkedTransferQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        linkedTransferQueue.transfer("ddd");//transfer方法：线程往TransferQueue中装数据，然后阻塞在这里，等消费者消费，消费完才往后执行
//        linkedTransferQueue.put("sss");//put方法：线程往TransferQueue中装数据，装完就继续执行了，不需要阻塞等待消费者消费(如果队列满了，才会阻塞)

//        new Thread(()->{
//            try {
//                System.out.println(linkedTransferQueue.take());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"t2").start();
    }
}
