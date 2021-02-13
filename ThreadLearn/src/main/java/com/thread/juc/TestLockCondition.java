package com.thread.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可重入锁
 */
public class TestLockCondition {

    private final static String City = "shanghai";
    private int km ;
    private String site;
    private Lock kmLock = new ReentrantLock();//非公平锁
    private Lock siteLock = new ReentrantLock();
    private Condition kmCon = kmLock.newCondition();
    private Condition siteCon = siteLock.newCondition();


    public TestLockCondition(){

    }


    public TestLockCondition(int km, String site){
        this.km = km;
        this.site = site;
    }


    public void changeKm(int km){
        kmLock.lock();//上锁
        try{
            this.km = km;
            kmCon.signal();//想当于notify
            //kmCon.signalAll();//相当于notifyAll
        }finally {
            kmLock.unlock();
        }
    }


    public void changeSite(String site){
        siteLock.lock();//上锁
        try{
            this.site = site;
            siteCon.signal();//想当于notify
            //sikeCon.signalAll();//相当于notifyAll
        }finally {
            siteLock.unlock();
        }
    }


    public void waitKm(){
        kmLock.lock();//上锁
        try{
            while(this.km <= 100){
                kmCon.await();//想当于wait
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            kmLock.unlock();
        }
    }


    public void waitSite(){
        siteLock.lock();//上锁
        try{
            while(City.equals(this.site)){
                siteCon.await();//想当于wait
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            siteLock.unlock();
        }
    }


}