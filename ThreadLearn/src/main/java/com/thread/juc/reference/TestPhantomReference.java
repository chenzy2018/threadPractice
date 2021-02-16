package com.thread.juc.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * 虚引用
 *
 * 遇到gc就会被垃圾回收
 * 用于管理堆外内存，给写JVM的人用的
 * 构造函数必须给一个队列，用于保存被垃圾回收的虚引用对象，即当队列有值就表示有虚引用对像被垃圾回收了
 *
 * NIO(Netty)中的DirectByteBuffer(直接内存)可以直接指向堆外内存(由操作系统直接管理)，此时gc是无法回收这部分，gc只能回收堆内存
 * 监测Queue,当有值的时候自己回收堆外内存(用Unsafe类的freeMemory方法回收)
 */
public class TestPhantomReference {

    private static final List<Object> list = new LinkedList<>();//用于制造内存溢出
    private static final ReferenceQueue<TestM> Queue = new ReferenceQueue<>();

    public static void main(String[] args) {
        PhantomReference<TestM> phantomReference = new PhantomReference<>(new TestM(), Queue);

        new Thread(()->{

            while (true){
                list.add(new byte[1024*1024*2]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(phantomReference.get());
            }
        }).start();

        new Thread(()->{
            while (true){
                Reference<? extends TestM> poll = Queue.poll();
                if(poll != null){
                    System.out.println("虚引用对象被垃圾回收了"+poll);
                }
            }
        }).start();
    }
}
