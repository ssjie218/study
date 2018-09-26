package thread.src.com.github.study.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 缓存变量
 */
public class ThreadCache {
    private static int counter = 0;

    public static void main(String[] args) {
        Runnable r1 = () -> {
            for (int i = 0; i < 20000; i++) {
                getId();
            }

        };
        Runnable r2 = () -> {
            for (int i = 0; i < 20000; i++) {
                getId();
            }
        };
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter);
    }

    synchronized static void getId() {
        counter++;
    }

}
