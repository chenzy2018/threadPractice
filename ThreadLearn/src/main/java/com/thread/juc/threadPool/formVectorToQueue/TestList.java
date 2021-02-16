package com.thread.juc.threadPool.formVectorToQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用list实现
 *
 * 普通list没有加任何锁，线程不安全
 *
 * 会出现超卖(最后一张票的时候，所有线程都读取大于0，但是只有一个线程能真正卖出票，相当于多卖了9张票)
 */
public class TestList {

    static List<String> list = new ArrayList<String>();

    static {
        for (int i = 0; i < 1000; i++) {
            list.add("票"+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while(list.size()>0){
                    System.out.println("卖出："+list.get(0));
                    list.remove(0);
                }
            },"t"+i).start();
        }
    }
}
