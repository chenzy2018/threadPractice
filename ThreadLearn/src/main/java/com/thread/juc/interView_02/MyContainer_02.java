package com.thread.juc.interView_02;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 容器实现
 *
 * 使用
 */
public class MyContainer_02<T> {

    private LinkedList<T> linkedList = new LinkedList<>();
    final private int max = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition set = lock.newCondition();
    private Condition get = lock.newCondition();

    /**
     * 为什么方法需要加synchronized?
     * 方法中存在++count,--count等操作，非原子操作，可以用原子类替代
     */
    public void set(T t){
        try {
            lock.lock();
            //使用while而不是用if是因为唤醒之后还需要再判断一次是否满足条件，因为有可能刚唤醒就有其他线程生产了，结果还是不满足，使用if就不会有唤醒之后的判断
            while(linkedList.size() == max){
                    set.await();
            }
            linkedList.add(t);
            ++count;
            get.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public T get(){
        T t = null;
        try {
            lock.lock();
            while(linkedList.size() == 0){
                    get.await();
            }
            t = linkedList.removeFirst();
            --count;
            set.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return t;
    }

    public int getCount(){
        return count;
    }

    public static void main(String[] args) {
        MyContainer_02<String> myContainer = new MyContainer_02<String>();
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 10;j++) {
                    myContainer.set("t");
                    System.out.println("生成："+myContainer.count);
                }

            },"set"+i).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 2; j++) {
                    myContainer.get();
                    System.out.println("消费："+myContainer.count);
                }
            },"get"+i).start();
        }
    }
}
