package com.thread.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 原子类
 * 将一些非原子操作通过CAS包装成原子操作
 *
 * CAS是原语操作，cpu指令级别支持的，自旋的方式等待，默认自旋10次，如果拿到就执行，如果拿不到就进入就绪队列等待调度
 *
 * 存在ABA问题，即一个对象在CAS获取到的对象，可能已经被其他线程从A改成了B，再从B改回了A(例子：和前女友复合，她已经经历了多个男的)
 * 如果是基本数据类型，ABA问题可以忽略，因为只需要拿到正确的值就好了
 * 如果是对象的话，就需要处理ABA问题，加版本号，每次执行CAS的时候同时判断版本号
 * jdk提供了带版本号的原子类AtomicStampedReference
 */
public class TestAotmic {


    AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        TestAotmic testAotmic = new TestAotmic();

        List<Thread> list= new ArrayList<Thread>();

        for (int i= 0; i < 10; i++){
            list.add(new Thread(()->{
                for(int j=0; j< 100; j++){
                    testAotmic.atomicInteger.incrementAndGet();
                }
            }));
        }

        list.forEach((o)->o.start());

        list.forEach((o)-> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(testAotmic.atomicInteger);
    }
}
