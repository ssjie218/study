package com.github.study.thread;

/**
 * Created by pc on 2018/7/17.
 */
public class ThreadHello {
    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println("hello thread");
        };
        Thread t = new Thread(r);
        t.start();
        class MyThread extends Thread {
            @Override
            public void run() {
                System.out.println("hello mythread");
            }
        }
        MyThread mt = new MyThread();
        mt.start();
        System.out.println(t.getName()+mt.getName());
    }

}
