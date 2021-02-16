package com.thread.juc.interView_02;

import java.util.LinkedList;

/**
 * 容器实现
 *
 * 存在问题：使用了notifyAll，唤醒的所有线程(包括消费者和生产者线程)，因为更多的锁争抢
 * 理想情况应该生产者唤醒消费者，消费者唤醒生产者，同类型不互相唤醒
 */
public class TestMyContainer_01<T> {

    private LinkedList<T> linkedList = new LinkedList<>();
    final private int max = 10;
    private int count = 0;

    /**
     * 为什么方法需要加synchronized?
     * 方法中存在++count,--count等操作，非原子操作，可以用原子类替代
     */
    public synchronized void set(T t){
        //使用while而不是用if是因为唤醒之后还需要再判断一次是否满足条件，因为有可能刚唤醒就有其他线程生产了，结果还是不满足，使用if就不会有唤醒之后的判断
        while(linkedList.size() == max){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        linkedList.add(t);
        ++count;
        this.notifyAll();
    }

    public synchronized T get(){
        while(linkedList.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t = linkedList.removeFirst();
        --count;
        this.notifyAll();
        return t;
    }

    public int getCount(){
        return count;
    }

    public static void main(String[] args) {
        TestMyContainer_01<String> myContainer = new TestMyContainer_01<String>();
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
