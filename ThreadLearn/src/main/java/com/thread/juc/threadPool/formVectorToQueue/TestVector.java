package com.thread.juc.threadPool.formVectorToQueue;

import java.util.Vector;

/**
 * 使用Vector实现
 *
 * 依旧会出现超卖
 */
public class TestVector {

    static Vector<String> list = new Vector<String>();

    static {
        for (int i = 0; i < 1000; i++) {
            list.add("票"+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while(list.size()>0){//size()方法同步了
                    //但中间的业务逻辑没有同步，依旧会出现超卖的情况
                    System.out.println("卖出："+list.get(0));

                    list.remove(0);//remove()方法同步了
                }
            },"t"+i).start();
        }
    }
}
