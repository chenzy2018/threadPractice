package com.thread.juc.threadPool.formVectorToQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 使用Queue实现
 *
 * 效率比synchronize高
 */
public class TestQueue {

    static Queue<String> tickets = new ConcurrentLinkedDeque<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票"+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while(true){
                    String t = tickets.poll();//队头出队
                    if(t == null) break;
                    System.out.println("卖出："+t);
                }
            },"t"+i).start();
        }
    }
}
