package com.github.study.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时门闩
 * Created by shen on 2018/7/18.
 */
public class ThreadCountDownLatchDemo {

    private final static int N = 3;

    public static void main(String[] args) {
        final CountDownLatch startSignal = new CountDownLatch(1);
        final CountDownLatch donenSignal = new CountDownLatch(N);
        Runnable r = new Runnable() {
            @Override
            public void run() {

                try {
                    report("entered run()");
                    startSignal.await();
                    report("doing work");
                    Thread.sleep((int) Math.random() * 1000);
                    donenSignal.countDown();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }

            }

            void report(String s) {
                System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread() + s);
            }

        };
        ExecutorService executor = Executors.newFixedThreadPool(N);
        for (int i = 0; i < N; i++)
            executor.execute(r);
            try {
                System.out.println("main thread doing something");
                Thread.sleep(1000);
                startSignal.countDown();
                System.out.println("main thread doing something else");
                donenSignal.await();
                executor.shutdownNow();


            } catch (InterruptedException e) {
                System.err.println(e);
            }



    }
}
