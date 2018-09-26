package thread.src.com.github.study.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 线程局部变量
 * Created by shen on 2018/7/18.
 */
public class ThreadLocalDemo {
    private static volatile ThreadLocal<String> userID=new ThreadLocal<String>();
    private static volatile String uid;
    private static AtomicInteger nid=new AtomicInteger(0);
    public static void main(String[] args) {
        Runnable r=()->{
            String name=Thread.currentThread().getName();
            if(name.equals("A")){
                userID.set("AAAAAA");
                uid="A";
                nid.set(1);
            }else{
                userID.set("BBBBB");
                uid="B";
                nid.set(2);
            }
            System.out.println(userID.get());
            System.out.println(uid);
            System.out.println(nid.get());
        };
        userID.set("CCCCC");
        new Thread(r,"A").start();
        new Thread(r,"B").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(userID.get());
        System.out.println(uid);
        System.out.println(nid.get());
    }
}
