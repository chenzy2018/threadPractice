package com.thread.classLoader;

import sun.awt.HKSCS;
import sun.net.spi.nameservice.dns.DNSNameService;

/**
 * 顶级父类 ClassLoader
 *
 * Bootstrap是C++实现的
 * ExtClassLoader和AppClassLoader是sun.misc.Launcher类的内部类
 */
public class ClassLoaderLevel {
    public static void main(String[] args) {

        /**
         * 获取加载某个类的类加载器
         *
         * 结果为null表示该类是由Bootstrap加载的，因为Bootstrap是C++实现的
         */
        System.out.println(String.class.getClassLoader());
        System.out.println(HKSCS.class.getClassLoader());
        System.out.println(DNSNameService.class.getClassLoader());
        System.out.println(ClassLoaderLevel.class.getClassLoader());

        /**
         * 获取加载某个类的类加载器的加载器
         *
         * 注意：几个加载器之前并不存在实际的extands关系，即不是父子类的关系，而是通过属性定义了parent，保证之间有个父子关系
         * 下面两个的结果 说明 ExtClassLoader和AppClassLoader都是由Bootstrap加载的，也就说明之间并没有父子类关系
         */
        System.out.println(DNSNameService.class.getClassLoader().getClass().getClassLoader());
        System.out.println(ClassLoaderLevel.class.getClassLoader().getClass().getClassLoader());

        /**
         * 三个加载器之间确实存在父子关系，getParent()是加载器提供的方法，获取加载器的parent属性
         * 即通过parent保证他们之间的父子关系，而不是使用继承，即确实没有父子类关系
         */
        System.out.println(ClassLoaderLevel.class.getClassLoader().getParent());
        System.out.println(ClassLoaderLevel.class.getClassLoader().getParent().getParent());
    }
}
