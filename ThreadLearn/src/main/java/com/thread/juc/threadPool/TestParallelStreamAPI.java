package com.thread.juc.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 集合流式处理API
 */
public class TestParallelStreamAPI {

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 10000; i++) nums.add(100000+ r.nextInt(100000)) ;

        long start = System.currentTimeMillis();
        nums.forEach( v -> isPrime(v));
        long end = System.currentTimeMillis();
        System.out.println( end - start);

        /**
         * 并行流，内部使用ForkJoinPool处理，因此也是分而治之
         *
         * 不需要同步的情况下使用，效率更高
         */
        start = System.currentTimeMillis();
        nums.parallelStream().forEach(TestParallelStreamAPI::isPrime);
        end = System.currentTimeMillis();
        System.out.println( end - start);

    }

    private static boolean isPrime(Integer v){
        if(v > 1000) return true;
        return false;
    }
}
