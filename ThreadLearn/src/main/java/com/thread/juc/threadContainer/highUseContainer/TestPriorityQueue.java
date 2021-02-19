package com.thread.juc.threadContainer.highUseContainer;

import java.util.PriorityQueue;

/**
 * PriorityQueue的使用
 * 优先级阻塞队列
 *
 * 底层使用的二叉树(小顶堆)，保证顺序
 */
public class TestPriorityQueue {

    public static void main(String[] args) {
        //可以使用构造函数执行排序方法，否则就使用默认实现
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();

        priorityQueue.add("c");
        priorityQueue.add("e");
        priorityQueue.add("a");
        priorityQueue.add("d");
        priorityQueue.add("z");

        //不能使用size()，因为每次循环size都会变，因此取不全，应该使用迭代器取或者先获取size()用变量保存后再小于变量循环取
        /*for (int i = 0; i < priorityQueue.size(); i++) {
            System.out.println(priorityQueue.poll());
        }*/

        int size = priorityQueue.size();
        for (int i = 0; i < size; i++) {
            System.out.println(priorityQueue.poll());
        }
    }
}
