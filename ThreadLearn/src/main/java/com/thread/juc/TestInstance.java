package com.thread.juc;

/**
 * 单例模式，懒加载
 */
public class TestInstance {

    //此处volatile是避免指令重排
    private static volatile TestInstance testInstance;

    //放在方法上，锁的粒度过大，中间有很多业务代码其实不需要加锁，此时需要缩小锁的粒度
    public static /*synchronized*/ TestInstance getInstance(){
        //省略业务逻辑
        if(testInstance == null){//避免进入代码就加锁，提交代码效率
            //省略业务逻辑
            synchronized (TestInstance.class){
                //双重检查，避免多个线程通过第一个判断而成功创建出多个实例
                if(testInstance == null){
                    //省略业务逻辑
                    //此步操作在操作系统层面是多条指令，存在指令重排的情况，导致取到值不符合预期
                    testInstance = new TestInstance();
                }
            }
        }
        return testInstance;
    }

    public static void main(String[] args) {
        for(int i= 0; i < 50; i++){
            new Thread(()->{
                System.out.println(TestInstance.getInstance().hashCode());
            }).start();
        }
    }
}
