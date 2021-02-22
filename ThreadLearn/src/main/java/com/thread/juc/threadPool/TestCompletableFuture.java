package com.thread.juc.threadPool;

import com.thread.utils.ThreadUtil;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 *
 * 场景：提供一个服务，这个服务是去天猫、淘宝、京东爬取同一商品的价格，然后用于作比较
 */
public class TestCompletableFuture {

    //延时函数，代替访问天猫等网址的网络等的耗时
    private static void delay(){
        Random a = new Random();

        int i = a.nextInt(3);
        ThreadUtil.sleep(i);
        System.out.println("休眠了："+i);
    }

    private static int ToTM(){
        delay();
        return 2;
    }

    private static int ToTB(){
        delay();
        return 4;
    }

    private static int ToJD(){
        delay();
        return 3;
    }

    public static void main(String[] args) {

        Long start,end;

        //原始调用方案，耗时就是三个相加
//        start = System.currentTimeMillis();
//        System.out.println(ToTM());
//        System.out.println(ToTB());
//        System.out.println(ToJD());
//        end = System.currentTimeMillis();
//        System.out.println(end-start);

        start = System.currentTimeMillis();
        //创建三个异步任务，分别访问三个网址爬数据，耗时是最长的那个任务
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()->ToTM());
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(()->ToTB());
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(()->ToJD());
        //等待三个任务都结束，获取返回值, anyOf():等待某一个任务完成即可
        CompletableFuture.allOf(future1,future2,future3).join();
        try {
            System.out.println(future1.get()+" "+future2.get()+" "+future3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
