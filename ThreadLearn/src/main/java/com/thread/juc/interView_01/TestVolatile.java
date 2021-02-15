package com.thread.juc.interView_01;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile实现
 *
 * 此方案不可行
 */
public class TestVolatile {

    // 理想中，加上volatile，是希望t1改变list的长度之后，让t2线程能立马看见，从而t2能正常退出
    // 但此处加上volatile并不能解决问题,因为现在修饰的是一个引用变量list，修改的数据是list的指向的内容，所以并不是线程不可见的问题
    /*volatile*/ List<Integer> list = new ArrayList<>();

    public void add(int i){
        list.add(i);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args) {

        TestVolatile testVolatile = new TestVolatile();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                testVolatile.add(i);
                System.out.println("list添加了："+i);

            }
        },"t1").start();
        
        new Thread(()->{
            while(true){
                if(testVolatile.size() == 5){
                    break;
                }
            }
            System.out.println("长度为5，t2退出");
        },"t2").start();
    }

}
