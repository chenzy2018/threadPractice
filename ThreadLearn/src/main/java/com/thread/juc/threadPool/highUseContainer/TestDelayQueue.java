package com.thread.juc.threadPool.highUseContainer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue的使用
 *
 * 延迟阻塞队列，时间排序
 * 本质是使用PriorityQueue，只不过compareTo方法必须自己重写实现比较规则
 */
public class TestDelayQueue {

    static BlockingQueue<Mytask> blockingQueue = new DelayQueue<>();
    static Random random = new Random();
    private Mytask t1;

    //DelayQueue所需要传递的任务必须实现Delayed接口
    static class Mytask implements Delayed{
        String name;
        long runningTime;

        public Mytask(String name, long runTime){
            this.name = name;
            this.runningTime = runTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        //排序方法，维护队列内元素顺序
        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS))
                return -1;
            else if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS))
                return 1;
            else
                return 0;
        }

        @Override
        public String toString() {
            return name + " " + runningTime;
        }
    }

    public static void main(String[] args)  throws InterruptedException{
        long now = System.currentTimeMillis();

        Mytask t1 = new Mytask("t1",now + 1000);
        Mytask t2 = new Mytask("t2",now + 2000);
        Mytask t3 = new Mytask("t3",now + 3000);
        Mytask t4 = new Mytask("t4",now + 2500);
        Mytask t5 = new Mytask("t5",now + 1500);
        Mytask t6 = new Mytask("t6",now + 500);

        blockingQueue.put(t1);
        blockingQueue.put(t2);
        blockingQueue.put(t3);
        blockingQueue.put(t4);
        blockingQueue.put(t5);
        blockingQueue.put(t6);

        System.out.println(blockingQueue);
        for (int i = 0; i < 6; i++) {
            System.out.println(blockingQueue.take());
        }
    }
}
