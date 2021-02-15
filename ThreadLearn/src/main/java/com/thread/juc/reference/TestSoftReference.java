package com.thread.juc.reference;

import java.lang.ref.SoftReference;

/**
 * 弱引用
 *
 * 当内存够用的时候，不垃圾回收，当内存不够用的时候，就垃圾回收掉
 *
 * 非常适合用于缓存
 */
public class TestSoftReference {

    public static void main(String[] args) {
        //申请10M空间的内存
        SoftReference<byte[]> softReference = new SoftReference<byte[]>(new byte[1024*1024*10]);

        System.out.println(softReference.get());

        System.gc();

        System.out.println(softReference.get());

        //再申请15M的空间
        //运行设置栈大小-Xms20M -Xmx20M
        byte[] bytes = new byte[1024*1024*15];

        System.out.println(softReference.get());
    }
}
