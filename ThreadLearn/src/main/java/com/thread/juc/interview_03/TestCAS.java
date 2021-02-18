package com.thread.juc.interview_03;

/**
 * 此方法当做拓展知识面，优先采用wait/notify或者reentrantLock
 */
public class TestCAS {

    enum ReadToRun {T1,T2}

    //此处加volatile是为了保证线程之间的可见性，第一时间就可以感知数据变化，避免浪费cpu的性能
    static volatile ReadToRun readToRun = ReadToRun.T2;

    static String[] strs = {"a","b","c","d"};

    public static void main(String[] args) {

        new Thread(()->{
            for (String str : strs) {
                //自旋等待，会浪费cpu性能
                while (readToRun == ReadToRun.T2){

                }
                System.out.print(str);
                readToRun = ReadToRun.T2;
            }
        },"t2").start();

        new Thread(()->{
            for (int i = 0; i < 4; i++) {
                while (readToRun == ReadToRun.T1){

                }
                System.out.print(i);
                readToRun = ReadToRun.T1;
            }
        },"t1").start();
    }

}
