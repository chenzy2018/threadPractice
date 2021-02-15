package com.thread.juc;

/**
 * VarHandle是1.9之后新增的类
 *
 * 他是一个引用，使用VarHandle可以做原子操作，避免用锁
 */
public class TestVarHandle {

    /*int x = 8;

    private static VarHandle handle;

    static {
        //需要三个参数，哪个类、哪个属性、该属性的类型
        //此handle和x都指向了内存中的 8 ，通过handle就可以直接访问 8
        handle = MathodHandles.lookup().findVarHandle(TestVarHandle.class, "x", int.class);
    }

    public static void main(String[] args) {
        TestVarHandle testVarHandle = new TestVarHandle();

        System.out.println((int) handle.get(testVarHandle));
        handle.set(testVarHandle,9);//将testVarHandle中的x的值从8变成9
        System.out.println(testVarHandle.x);

        handle.compareAndSet(testVarHandle,9,10);//原子操作，将x的值从9变成10，相当于x++/++x(线程不安全)
        System.out.println(testVarHandle.x);

        handle.getAndAdd(t,10);//原子操作
        System.out.println(testVarHandle.x);
    }*/
}
