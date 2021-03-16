package com.thread.classLoader;

/**
 * 面试题
 *
 * 考察静态变量赋值过程
 */
public class TestLoad {

    public static void main(String[] args) {
        System.out.println(T.count);
    }
}

class T {
    /**
     * 这两行代码调换顺序，看看结果
     */
    public static T t = new T();
    public static int count = 2;

    private T(){
        count++;
        System.out.println("sdfs:"+count);
    }
}
