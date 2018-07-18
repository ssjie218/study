package com.github.study.thread;

/**
 * Created by shen on 2018/7/17.
 */
public class ThreadDaemon {
    public static void main(String[] args) {
        boolean isDaemon=true;
        Runnable r=()->{
            Thread thd=Thread.currentThread();
            while(true)
            System.out.printf("%s is %s alive and in %s state %n", thd.getName(), thd.isAlive() ? "" : "not", thd.getState());
        };
        Thread t1=new Thread(r,"thd1");
        t1.setDaemon(isDaemon);
        System.out.printf("%s is %s alive and in %s state %n", t1.getName(), t1.isAlive() ? "" : "not", t1.getState());

        Thread t2=new Thread(r,"thd2");
        t2.setDaemon(isDaemon);
        System.out.printf("%s is %s alive and in %s state %n", t2.getName(), t2.isAlive() ? "" : "not", t2.getState());
        t1.start();
        t2.start();
    }
}
