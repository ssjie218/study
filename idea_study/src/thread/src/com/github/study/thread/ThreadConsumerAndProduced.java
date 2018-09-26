package thread.src.com.github.study.thread;

import java.util.function.Consumer;

/**
 * Created by pc on 2018/7/18.
 */
public class ThreadConsumerAndProduced {
    public static void main(String[] args) {
        Shared s = new Shared();
        new Producer(s).start();
        new Consumer(s).start();

    }

    static class Shared {
        private char c;
        private volatile boolean writeable = true; //可见不从缓存中取

        synchronized void setSharedChar(char c) {
            while (!writeable) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.c = c;
            writeable = false;
            notify();


        }


        synchronized char getSharedChar() {
            while (writeable) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            writeable = true;
            notify();
            return c;
        }
    }

    static class Producer extends Thread {
        private final Shared s;

        Producer(Shared s) {
            this.s = s;
        }

        @Override
        public void run() {
            for (char ch = 'A'; ch < 'Z'; ch++) {
                synchronized (s){
                    s.setSharedChar(ch);
                    System.out.println(ch + " produced by producer .");
                }
            }
        }
    }

    static class Consumer extends Thread {
        private final Shared s;

        Consumer(Shared s) {
            this.s = s;
        }

        @Override
        public void run() {
            char ch;
            do {
                synchronized (s) {
                    ch = s.getSharedChar();
                    System.out.println(ch + " consumed by consumer .");
                }
            }
            while (ch != 'Z');
        }
    }
}
