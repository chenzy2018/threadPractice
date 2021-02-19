package com.thread.juc.threadContainer.highUseContainer;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentQueue的使用
 */
public class TestConcurrentQueue {

    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue<>();

//        for (int i = 0; i < 10; i++) {
//            //返回boolean，成功就是true，失败就false，失败了也不会报错，但add失败会报错，所以offer用的更多
//            queue.offer("a"+i);//add
//        }

        System.out.println(queue);
        System.out.println(queue.size());

        //弹出队列首并返回
        System.out.println(queue.poll());
        System.out.println(queue.size());

        //获取队列首但不弹出队列首
        System.out.println(queue.peek());
        System.out.println(queue.size());
    }
}
