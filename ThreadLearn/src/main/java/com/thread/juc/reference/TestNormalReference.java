package com.thread.juc.reference;

import java.io.IOException;
import java.sql.SQLOutput;

/**
 * 强引用
 *
 * 不会被垃圾回收
 * 经常会通过赋null来帮助gc
 */
public class TestNormalReference {

    public static void main(String[] args) {

        Object o = new Object();
        System.out.println(o);
        o=null; //help gc
        System.gc();
        System.out.println(o);

        try {
            //仅仅用于阻塞主线程，因为gc是守护线程执行，要等待结束才能看结果
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
