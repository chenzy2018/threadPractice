package com.thread.classLoader;

/**
 * 产生了内部类的内部类
 */
public class TestFunctionInterface {

    public static void main(String[] args) {
        I i1 = C::n;
        I i2 = C::n;
        I i3 = C::n;
        I i4 = ()-> C.n();//和上面等价

        System.out.println(i1.getClass());
        System.out.println(i2.getClass());
        System.out.println(i3.getClass());
        System.out.println(i4.getClass());

        /**
         * 会不会产生oom异常
         */
        //for(;;){ I j = C::n;}
        /**
         * 方法存放在method space
         *
         * <1.8 Perm Space  不会触发FGC  会抛出oom异常 (Perm Space的重大bug)
         * >1.8 Meta Space  会触发FGC  不会抛出oom异常
         */
    }

    /**
     * FunctionalInterface
     *  只有一个方法
     *
     * 任何一个具体的方法都可以看做是这个接口的实现
     */
    @FunctionalInterface
    public interface I {
        void m();
    }

    public static class C{
        public static void n(){

        }
    }
}
