package com.thread.juc.threadPool;

import java.util.concurrent.*;

/**
 * 这是一个简单的Join/Fork计算过程，将1—1001数字相加
 */
public class TestForkJoinPool {

    private static final Integer MAX = 200;

    /**
     * 可以继承RecursiveTask和RecursiveAction
     *
     * 其中RecursiveAction是不带返回值的，RecursiveTask是带有返回值的
     */
    static class MyForkJoinTask extends RecursiveTask<Integer> {
        // 子任务开始计算的值
        private Integer startValue;
        // 子任务结束计算的值
        private Integer endValue;
        public MyForkJoinTask(Integer startValue , Integer endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }
        @Override
        protected Integer compute() {
            // 如果条件成立，说明这个任务所需要计算的数值分为足够小了
            // 可以正式进行累加计算了
            if(endValue - startValue < MAX) {
                System.out.println("开始计算的部分：startValue = " + startValue + ";endValue = " + endValue);
                Integer totalValue = 0;
                for(int index = this.startValue ; index <= this.endValue  ; index++) {
                    totalValue += index;
                }
                return totalValue;
            }
            // 否则再进行任务拆分，拆分成两个任务
            else {
                MyForkJoinTask subTask1 = new MyForkJoinTask(startValue, (startValue + endValue) / 2);
                subTask1.fork();
                MyForkJoinTask subTask2 = new MyForkJoinTask((startValue + endValue) / 2 + 1 , endValue);
                subTask2.fork();
                return subTask1.join() + subTask2.join();
            }
        }
    }

    static class MyForkJoinTask1 extends RecursiveAction {
        // 子任务开始计算的值
        private Integer startValue;
        // 子任务结束计算的值
        private Integer endValue;
        public MyForkJoinTask1(Integer startValue , Integer endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }
        @Override
        protected void compute() {
            // 如果条件成立，说明这个任务所需要计算的数值分为足够小了
            // 可以正式进行累加计算了
            if(endValue - startValue < MAX) {
                System.out.println("开始计算的部分：startValue = " + startValue + ";endValue = " + endValue);
                Integer totalValue = 0;
                for(int index = this.startValue ; index <= this.endValue  ; index++) {
                    totalValue += index;
                }
                //return totalValue;
            }
            // 否则再进行任务拆分，拆分成两个任务
            else {
                MyForkJoinTask1 subTask1 = new MyForkJoinTask1(startValue, (startValue + endValue) / 2);
                subTask1.fork();
                MyForkJoinTask1 subTask2 = new MyForkJoinTask1((startValue + endValue) / 2 + 1 , endValue);
                subTask2.fork();
                //return subTask1.join() + subTask2.join();
            }
        }
    }

    public static void main(String[] args) {
        // 这是Fork/Join框架的线程池
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> taskFuture =  pool.submit(new MyForkJoinTask(1,1001));
        try {
            Integer result = taskFuture.get();
            System.out.println("result = " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(System.out);
        }
    }

}
