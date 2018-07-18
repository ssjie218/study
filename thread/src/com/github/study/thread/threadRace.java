package com.github.study.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * date_race and race_condition
 * Created by pc on 2018/7/17.
 */
public class ThreadRace {
    private static int my_account = 0;      //我的账户余额
    private static int your_account = 100;  //你的账户余额
    private static AtomicInteger my_at_account =new AtomicInteger(0);      //我的账户余额
    private  static AtomicInteger your_at_account =new AtomicInteger(100);  //你的账户余额
    public static void main(String[] args) {
        Runnable r1=()->{
            racy_transfer(50);
            //racy_st_transfer(50);
        };
        Runnable r2=()->{
            racy_transfer(80);
            //racy_st_transfer(80);
        };
        Thread t1=new Thread(r1);
        Thread t2=new Thread(r2);
        t1.start();
        t2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(my_account+"||"+your_account);
        System.out.println(my_at_account.get()+"||"+your_at_account.get());
    }
    // 转账操作: 存在数据争用(data race)！
    synchronized static boolean racy_transfer( int m)
    {
        if (m <= your_account) {
            your_account -= m;
            System.out.println(your_account);
            my_account += m;
            System.out.println(my_account);
            System.out.println("true");
            return true;
        } else {
            System.out.println("false");
            return false;
        }
    }

    // 转账操作: 存在数据争用(data race)！
   synchronized static boolean racy_st_transfer( int m)
    {
        if (m <= your_at_account.get()) {
            your_at_account.getAndAdd(m*-1);
            System.out.println(your_at_account.get());
            my_at_account.getAndAdd(m);
            System.out.println(my_at_account.get());
            System.out.println("true");
            return true;
        } else {
            System.out.println("false");
            return false;
        }
    }
}
