package com.thread.juc.threadPool.highUseContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * ConcurrentHashMap使用
 *
 * Map最常使用的实现有两个：
 *  HashMap：数据没有排序，插入数据快，查找慢(LinkedhHashMap增加了链表结构，效率比较高)
 *  TreeMap：底层使用的是红黑树，因此数据是排好序的，查找快，插入过程可能会触发树的左旋右旋
 */
public class TestConcurretnHashMap {

    static final int length = 100;

    public static void main(String[] args) {
        //底层使用CAS操作
        Map<String,String> map = new  ConcurrentHashMap<>();
        //跳表，高并发且排好序，由于CAS放在TreeMap中很难实现，因此没有ConcurrentTreeMap，而使用ConcurrentSkipListMap来做高并发下的排序Map
        //本身是链表，不过会把关键元素提取再做一个链表(可以提取多个链表)，然后从顶层往下查找，这样就不用从头开始查，减少遍历次数
        //Map<String,String> map = new ConcurrentSkipListMap<>();

        Random random = new Random();
        Thread[] threads = new Thread[length];
        CountDownLatch countDownLatch = new CountDownLatch(length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    map.put("key"+random.nextInt(100000),"val"+random.nextInt(100000));
                }
                countDownLatch.countDown();
            },"t"+i);
        }

        for(Thread thread : threads) thread.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
