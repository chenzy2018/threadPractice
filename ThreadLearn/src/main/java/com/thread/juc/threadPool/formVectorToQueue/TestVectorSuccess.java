package com.thread.juc.threadPool.formVectorToQueue;

import java.util.Vector;

/**
 * 使用Vector实现
 *
 * 卖票整个用sync包起来可以解决，使用list是同理，但不是最好的办法
 */
public class TestVectorSuccess {

    static Vector<String> list = new Vector<String>();

    static {
        for (int i = 0; i < 1000; i++) {
            list.add("票"+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while(true){
                    synchronized(list){
                        if(list.size() == 0) break;
                        System.out.println("卖出："+list.get(0));
                        list.remove(0);
                    }
                }
            },"t"+i).start();
        }
    }
}
