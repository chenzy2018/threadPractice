package com.thread.juc.reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用
 *
 * 只要遭遇gc就会被垃圾回收
 *
 * 当一个强引用和一个弱引用同时指向同一个内存，当强引用消失之后，弱引用也就被回收
 * 一般用在容器里，WeakHashMap
 */
public class TestWeakReference {

    public static void main(String[] args) {
        WeakReference<TestM> weakReference = new WeakReference<TestM>(new TestM());

        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());

        /**
         * 弱引用的典雅使用案例--ThreadLocal
         *
         * ThreadLocal中使用到的Thread的ThreadLocal的Entry是继承了WeakReference
         * 当声明的threadLocal对象被置为null的时候，它对应的内存就应该被回收
         * 但如果Entry是强引用，那这块内存就永远无法释放，导致内存溢出，因此设计为了弱引用
         *
         * 但是此处还是存在内存泄漏，就是Entry该key对应的value没有被释放，
         * 因此当前声明的ThreadLocal对象不用了，要调用remove方法，释放这块空间
         */

        ThreadLocal<TestM> threadLocal = new ThreadLocal<TestM>();
        threadLocal.set(new TestM());
        threadLocal.remove();
    }
}
